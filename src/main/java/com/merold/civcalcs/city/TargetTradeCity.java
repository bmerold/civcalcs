package com.merold.civcalcs.city;

public class TargetTradeCity {
	private int numDifferentResources = 0;
	private double goldOutput = 0;
	private double bonusGoldFromBuildings = 0;
	private String name;

	public TargetTradeCity(String name, int numDifferentResources, double tradeYield, double bonusGoldFromBuildings) {
		this.name = name;
		this.numDifferentResources = numDifferentResources;
		setGoldOutputWithTradeYield(tradeYield);
		this.bonusGoldFromBuildings = bonusGoldFromBuildings;
	}

	public double getGoldOutput() {
		return goldOutput;
	}

	public double getBonusGoldFromBuildings() {
		return bonusGoldFromBuildings;
	}

	public String getName() {
		return name;
	}

	public void setGoldOutputWithTradeYield(double tradeYield) {
		this.goldOutput = tradeYield;
	}

	public double getNumDifferentResources() {
		// TODO Auto-generated method stub
		return numDifferentResources;
	}

	public void setNumDifferentResources(int numDifferentResources) {
		this.numDifferentResources = numDifferentResources;
	}

	public double getTotalGold() {
		return goldOutput ;
	}

}
