package com.aei.MazkekaV2.OLED;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import com.aei.MazkekaV2.TempObj;
import com.pi4j.io.i2c.I2CFactory.UnsupportedBusNumberException;

public class OLEDctrl {
	
	private OLEDDisplay display;
	private BufferedImage img = new BufferedImage(128, 64, BufferedImage.TYPE_BYTE_BINARY);
	private Graphics2D graphics = img.createGraphics();
	
	public OLEDctrl() throws IOException, UnsupportedBusNumberException {
		display = new OLEDDisplay();
		display.clear();
		graphics.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 18));
		display.drawImage(img, 0, 0);
		display.update();
	}
	
	public void drawString(String str, int row) {
		graphics.drawString(str, 0, row);
		display.drawImage(img, 0, 0);
		try {
			display.update();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void drawTemp(String name, double temp, TempObj t) {
		clear();
		graphics.drawString("ID: " + name, 0, 18);
		graphics.drawString(t.getSit(temp).toString(), 0, 39);
		graphics.drawString(String.format("%.2f", temp), 0, 64);
		
		display.drawImage(img, 0, 0);
		try {
			display.update();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void clear() {
		graphics.clearRect(0, 0, 128, 64);
	}
}
