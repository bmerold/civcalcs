package com.merold.civcalcs.city;

import com.merold.civcalcs.civilizations.Civilization;
import com.merold.civcalcs.civilizations.Influence;

public class TargetTradeCity {
	private double bonusGoldFromBuildings = 0;
	private Civilization civ;
	private double goldOutput = 0;
	private String name;
	private boolean nextToRiver = false;
	private int numDifferentResources = 0;

	public TargetTradeCity(String name, int numDifferentResources, double tradeYield, double bonusGoldFromBuildings) {
		this.name = name;
		this.numDifferentResources = numDifferentResources;
		setGoldOutputWithTradeYield(tradeYield);
		this.bonusGoldFromBuildings = bonusGoldFromBuildings;
	}

	public TargetTradeCity(String name, int numDifferentResources, double tradeYield, double bonusGoldFromBuildings,
			Civilization civ) {
		this.name = name;
		this.numDifferentResources = numDifferentResources;
		setGoldOutputWithTradeYield(tradeYield);
		this.bonusGoldFromBuildings = bonusGoldFromBuildings;
		this.civ = civ;
		this.nextToRiver = false;
	}

	public TargetTradeCity(String name, int numDifferentResources, double tradeYield, double bonusGoldFromBuildings,
			Civilization civ, boolean isNextToRiver) {
		this.name = name;
		this.numDifferentResources = numDifferentResources;
		setGoldOutputWithTradeYield(tradeYield);
		this.bonusGoldFromBuildings = bonusGoldFromBuildings;
		this.civ = civ;
		this.nextToRiver = isNextToRiver;
	}
	
	public double getBonusGoldFromBuildings() {
		return bonusGoldFromBuildings;
	}

	public Civilization getCiv() {
		// TODO Auto-generated method stub
		return civ;
	}

	public Influence getCivInfluence() {
		if (civ == null) {
			return Influence.NONE;
		} else {
			return civ.getInfluence();
		}
	}

	public double getGoldOutput() {
		return goldOutput;
	}

	public String getName() {
		return name;
	}

	public double getNumDifferentResources() {
		// TODO Auto-generated method stub
		return numDifferentResources;
	}

	public double getTotalGold() {
		return goldOutput;
	}

	public boolean isNextToRiver() {
		return nextToRiver;
	}

	public void setGoldOutputWithTradeYield(double tradeYield) {
		this.goldOutput = tradeYield;
	}

	public void setNumDifferentResources(int numDifferentResources) {
		this.numDifferentResources = numDifferentResources;
	}

}
