package com.aei.MazkekaV2;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class FireBase {
	
	private FirebaseDatabase defaultDatabase;
	private DatabaseReference database;
	
	private static final String REBOOT = "RB";
	private static final String SHUTDOWN = "SD";
	private static final String START = "S";
	private static final String STOP = "SP";
	private static final String RESET = "R";
	private static final String EXIT = "E";
	
	public FireBase(InputStream is, String name) {
		FirebaseOptions options;
		try {
			options = new FirebaseOptions.Builder()
			    .setCredentials(GoogleCredentials.fromStream(is))
			    .setDatabaseUrl("https://mazkekaiot2.firebaseio.com")
			    .build();
			FirebaseApp.initializeApp(options);
		} catch (IOException | NullPointerException e) {
			e.printStackTrace();
			return;
		}
		defaultDatabase = FirebaseDatabase.getInstance();
		database = defaultDatabase.getReference().child("Clients").child(name);
		database.child("CMD").setValueAsync("");
		database.child("ON").setValueAsync(true);
		database.child("CMD").addValueEventListener(new ValueEventListener() {
			
			@Override
			public void onDataChange(DataSnapshot arg0) {
				if(arg0.getValue(String.class).isEmpty())
					return;
				database.child("CMD").setValueAsync("");
				handleCommands(arg0.getValue(String.class));
			}
			
			@Override
			public void onCancelled(DatabaseError arg0) {
				
			}
		});
	}
	
	public void updateTemp(double temp, TempObj tObj) {
		database.child("Temp").setValueAsync(temp);
		database.child("SIT").setValueAsync(tObj.getSit(temp).toString());
	}
	
	@SuppressWarnings("unused")
	public void handleCommands(String cmd) {
		switch(cmd) {
		case REBOOT:
			try {
				Process p = Runtime.getRuntime().exec("shutdown -r 5");
			} catch (IOException e) {
				e.printStackTrace();
			}
			break;
		case SHUTDOWN:
			try {
				Process p = Runtime.getRuntime().exec("shutdown");
			} catch (IOException e) {
				e.printStackTrace();
			}
			break;
		case START:
			Main.startDistilling();
			break;
		case STOP:
			Main.stopDistilling();
			break;
		case RESET:
			break;
		case EXIT:
			System.exit(0);
		default:
			break;
		}
	}
	public void start() {
		database.child("W").setValueAsync(true);
	}
	public void stop() {
		database.child("W").setValueAsync(false);
	}
	public void finish() {
		database.child("W").setValueAsync(false);
		database.child("ON").setValueAsync(false);
	}
	public void setActiveTemp(int i) {
		database.child("ActiveTemp").setValueAsync(i);
		database.child("ActiveTemp").addValueEventListener(new ValueEventListener() {
			
			@Override
			public void onDataChange(DataSnapshot arg0) {
				Main.setActiveTemp(arg0.getValue(Integer.class));
			}
			
			@Override
			public void onCancelled(DatabaseError arg0) {

			}
		});
	}
	public void setTempsList(ArrayList<TempObj> temps) {
		String tempsStr = "";
		for(TempObj temp : temps)
			tempsStr += temp.getName() + "\n";
		database.child("Temps").setValueAsync(tempsStr);
	}
}
