package com.aei.MazkekaV2.Sensors;

public interface Sensor {
	double getTemp();
	String getName();
	boolean isAvailable();
	void startThread();
}
