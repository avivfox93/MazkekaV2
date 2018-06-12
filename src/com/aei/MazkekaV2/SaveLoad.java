package com.aei.MazkekaV2;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;

public class SaveLoad {
	private Preferences prefs;
	private ArrayList<TempObj> temps;
	private String name;
	private static final String NUMOFTEMPS = "NUM";
	private static final String TEMPSARRAY = "TEMPS";
	private static final String NAMEKEY = "NAME";
	final static char[] hexaMap = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
	
	public SaveLoad(ArrayList<TempObj> temps) {
		this.temps = temps;
		prefs = Preferences.userNodeForPackage(this.getClass());
		//clear();
	}
	
	public void load() {
		name = prefs.get(NAMEKEY, randName());
		temps.clear();
		int i = prefs.getInt(NUMOFTEMPS, 0);
		if(i <= 0) {
			temps.add(new TempObj());
			return;
		}
		try {
			String[] arr = prefs.get(TEMPSARRAY, "NULL").split("#");
			for(int j = 0 ; j < i ; j++) {
				temps.add(TempObj.getFromFile(arr[j]));
			}
		}catch(Exception e) {
			e.printStackTrace();
			temps.add(new TempObj());
		}
	}
	public void save() {
		prefs.put(NAMEKEY, name);
		prefs.putInt(NUMOFTEMPS, temps.size());
		prefs.put(TEMPSARRAY, tempsArrayToString(temps));
	}
	public void clear() {
		try {
			prefs.clear();
		} catch (BackingStoreException e) {
			e.printStackTrace();
		}
	}
	public String getName() {
		return this.name;
	}
	public static String randName() {
		String deviceName = "";
        for (int i = 0; i < 6; i++) {
            deviceName += hexaMap[1 + (int) (Math.random() * 14)];
        }
        return deviceName;
	}
	public static String tempsArrayToString(ArrayList<TempObj> arr) {
		String res = "";
		for(TempObj t : arr) {
			res += t.save() + "#";
		}
		return res;
	}
	public static InputStream getFireBaseJSON() {	
		return SaveLoad.class.getResourceAsStream("Resources/firebase.json");
	}
}