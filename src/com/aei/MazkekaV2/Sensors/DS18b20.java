package com.aei.MazkekaV2.Sensors;

import java.util.ArrayList;

import com.pi4j.component.temperature.TemperatureSensor;
import com.pi4j.io.w1.W1Master;
import com.pi4j.temperature.TemperatureScale;

public class DS18b20 implements Sensor{
	W1Master w1Master = new W1Master();
	ArrayList<TemperatureSensor> sensors = new ArrayList<TemperatureSensor>();
	private TemperatureScale scale = TemperatureScale.CELSIUS;
	private volatile double temp = -127;
	private boolean isRunning = false;
	private int numOfSensors = 0;
	public DS18b20() {
		for (TemperatureSensor device : w1Master.getDevices(TemperatureSensor.class)) {
            sensors.add(device);
        }
		numOfSensors = sensors.size();
	}
	
	public double getTemp() {
		if(sensors.get(0) == null)
			return -127;
		if(isRunning)
			return temp;
		return sensors.get(0).getTemperature(scale);
	}
	
	public double getTemp(int i) {
		if(i >= numOfSensors)
			i = 0;
		if(sensors.get(i) == null)
			return -127;
		if(isRunning && i == 0)
			return temp;
		return sensors.get(i).getTemperature(scale);
	}
	
	public double[] getTemps() {
		double[] temps = new double[numOfSensors];
		for(int i = 0 ; i < numOfSensors ; i++)
			temps[i] = sensors.get(i).getTemperature(scale);
		return temps;
	}
	
	public void startThread() {
		if(isRunning)
			return;
		new Thread() {
			@Override
			public void run() {
				temp = sensors.get(0).getTemperature(scale);
			}
		}.start();;
	}
	
	public void stopThread() {
		isRunning = false;
	}
	
	public void setScale(TemperatureScale scale) {
		this.scale = scale;
	}
	
	public String getName() {
		if(sensors.get(0) != null)
			return sensors.get(0).getName();
		return "";
	}

	public boolean isAvailable() {
		if(sensors.get(0) != null)
			return true;
		return false;
	}

}
