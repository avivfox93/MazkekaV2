package com.aei.MazkekaV2;

import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JLabel;

import com.aei.MazkekaV2.OLED.OLEDctrl;
import com.aei.MazkekaV2.Sensors.Sensor;


public class Backgroud {
	private Sensor sensor;
	private JLabel tempLabel;
	private JLabel sitLabel;
	private TempObj activeTempObj;
	private boolean graphicIsRunning = false;
	private boolean distIsRunning = false;
	private IOManager ioManager;
	private Timer timer = new Timer();
	private Timer oledTimer = new Timer();
	private OLEDctrl oled;
	
	public Backgroud(Sensor sensor, JLabel tempLabel, JLabel sitLabel, TempObj activeTempObj, OLEDctrl oled) {
		this.sensor = sensor;
		this.tempLabel = tempLabel;
		this.sitLabel = sitLabel;
		this.activeTempObj = activeTempObj;
		this.oled = oled;
		this.ioManager = new IOManager();
		startGraphicThread();
		startOLEDThread();
		sensor.startThread();
		Main.fb.updateTemp(sensor.getTemp(), activeTempObj);
	}
	private void startGraphicThread() {
		if(graphicIsRunning)
			return;
		graphicIsRunning = true;
		new Thread() {
			@Override
			public void run() {
				while(graphicIsRunning)
					setTemp(sensor.getTemp());
			}
		}.start();
	}
	
	private void startFireBaseThread() {
		timer.purge();
		timer = new Timer();
		TimerTask fTask = new TimerTask() {
			@Override
			public void run() {
				Main.fb.updateTemp(sensor.getTemp(), Main.getActiveTemp());
			}
		};
		timer.scheduleAtFixedRate(fTask, 0l, 3000);
	}
	
	private void startOLEDThread() {
		TimerTask oTask = new TimerTask() {
			@Override
			public void run() {
				if(oled != null)
					oled.drawTemp(Main.name, sensor.getTemp(), activeTempObj);
			}
		};
		oledTimer.scheduleAtFixedRate(oTask, 0l, 250);
	}
	
	public void startDistill() {
		if(distIsRunning)
			return;
		distIsRunning = true;
		startFireBaseThread();
		ioManager.setHeater(true);
		new Thread() {
			@Override
			public void run() {
				while(distIsRunning) {
					if(activeTempObj.getSit(sensor.getTemp()) == TEMP.FINISH)
						stopDist();
				}
			}
		}.start();
	}
	public void setActiveTemp(TempObj t) {
		this.activeTempObj = t;
	}
	public void stopDist() {
		distIsRunning = false;
		ioManager.setHeater(false);
		timer.cancel();
	}
	private void setTemp(double temp) {
		tempLabel.setText(Double.toString(temp));
		sitLabel.setText(activeTempObj.getSit(temp).toString());
	}
}
