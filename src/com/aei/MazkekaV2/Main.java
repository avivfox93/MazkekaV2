package com.aei.MazkekaV2;

import java.io.IOException;
import java.util.ArrayList;

import com.aei.MazkekaV2.OLED.OLEDctrl;
import com.aei.MazkekaV2.Sensors.DS18b20;
import com.pi4j.io.i2c.I2CFactory.UnsupportedBusNumberException;

public class Main {
	private static OLEDctrl oled = null;
	public static String name;
	private static ArrayList<TempObj> temps = new ArrayList<TempObj>();
	private static TempObj activeTemp = new TempObj();
	private static SaveLoad saveLoad;
	public static MainGUI gui;
	public static boolean isDistilling = false;
	public static FireBase fb;
	private static Backgroud back;
	public static void main(String[] args) {
		saveLoad = new SaveLoad(temps);
		saveLoad.load();
		name = saveLoad.getName();
		fb =  new FireBase(SaveLoad.getFireBaseJSON(), name);
		DS18b20 tempSensor = new DS18b20();
		saveLoad.save();
		fb.setTempsList(temps);
		gui = new MainGUI(name, temps, activeTemp);
		gui.setVisible(true);
		fb.setActiveTemp(temps.indexOf(gui.getActiveTemp()));
		initOled();
		back = new Backgroud(tempSensor, gui.getTempLabel(), gui.getSitLabel(), activeTemp, oled);
		Runtime.getRuntime().addShutdownHook(new Thread()
		{
		    @Override
		    public void run()
		    {
		        Main.exit();
		    }
		});
	}
	
	public static void initOled() {
			try {
				oled = new OLEDctrl();
			} catch (IOException | UnsupportedBusNumberException e) {
				e.printStackTrace();
			}
	}
	
	public static void changeSit() {
		if(isDistilling) {
			startDistilling();
			System.out.println("Started!");
		}else {
			stopDistilling();
			System.out.println("Stopped!");
		}
	}
	public static void setActiveTemp(int i) {
		gui.setActiveTemp(i);
		back.setActiveTemp(temps.get(i));
		fb.setActiveTemp(i);
	}
	public static ArrayList<TempObj> getTemps() {
		return temps;
	}
	public static void save() {
		saveLoad.save();
	}
	public static void startDistilling() {
		isDistilling = true;
		fb.start();
		back.startDistill();
	}
	public static void stopDistilling() {
		isDistilling = false;
		fb.stop();
		back.stopDist();
	}
	public static void setActiveTemp(TempObj t) {
		activeTemp = t;
	}
	public static TempObj getActiveTemp() {
		return activeTemp;
	}
	public static void handleFireBase(boolean sit) {
		if(sit == isDistilling)
			return;
		gui.setTgl(sit);
	}
	public static void exit() {
		try {
			stopDistilling();
			fb.finish();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
