package com.aei.MazkekaV2;

public class TempObj {
	
	private double methanol = 50;
	private double ethanol = 76;
	private double tails = 80;
	private double finish = 90;
	private String name;
	
	public TempObj() {
		this.name = "Eled";
	}
	public TempObj(String name, double methanol, double ethanol, double tails, double finish) {
		this.name = name;
		this.methanol = methanol;
		this.ethanol = ethanol;
		this.tails = tails;
		this.finish = finish;
	}
	public double getMethanol() {
		return methanol;
	}
	public void setMethanol(double methanol) {
		this.methanol = methanol;
	}
	public double getEthanol() {
		return ethanol;
	}
	public void setEthanol(double ethanol) {
		this.ethanol = ethanol;
	}
	public double getTails() {
		return tails;
	}
	public void setTails(double tails) {
		this.tails = tails;
	}
	public double getFinish() {
		return finish;
	}
	public void setFinish(double finish) {
		this.finish = finish;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public TEMP getSit(double temp) {
		if(temp < getMethanol())
			return TEMP.WARMING;
		if(temp < getEthanol())
			return TEMP.METHANOL;
		if(temp < getTails())
			return TEMP.ETHANOL;
		if(temp < getFinish())
			return TEMP.TAILS;
		return TEMP.FINISH;
	}
	public String save() {
		return name + ";" + methanol + ";" + ethanol + ";" + tails + ";" + finish;
	}
	public static TempObj getFromFile(String str) {
		try {
			String[] in = str.split(";");
			return new TempObj(in[0],Double.parseDouble(in[1]),Double.parseDouble(in[2]),Double.parseDouble(in[3]),
					Double.parseDouble(in[4]));
		}catch(Exception e) {
			e.printStackTrace();
			return new TempObj();
		}
	}
	@Override
	public String toString() {
		return this.name;
	}
}
