package com.aei.MazkekaV2;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.Pin;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;

public class IOManager {
	private Pin heaterPin = RaspiPin.GPIO_21;
	private GpioPinDigitalOutput hotPlate;
	private final GpioController gpio = GpioFactory.getInstance();
	
	public IOManager() {
		this.hotPlate = gpio.provisionDigitalOutputPin(this.heaterPin, "HEATER",PinState.LOW);
	}
	public IOManager(Pin heaterPin) {
		this.heaterPin = heaterPin;
		this.hotPlate = gpio.provisionDigitalOutputPin(this.heaterPin, "HEATER",PinState.LOW);
	}
	
	public void setHeater(boolean state) {
		if(state)
			hotPlate.high();
		else
			hotPlate.low();
	}
}
