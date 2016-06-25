package com.merold.civcalcs.buildings;

import com.merold.civcalcs.city.City;
import com.merold.civcalcs.player.Player;
import com.merold.civcalcs.socialpolicies.SocialPolicy;

public class MedicalLab extends Building {

	public MedicalLab(City city, Player owner) {
		super(BuildingEnum.MEDICAL_LAB, city, owner, 500);
	}
	
	@Override
	public double getFoodAddedByGrowthModifier() {
		double food = 0.25 * city.getFoodToGrow()[city.getPopulation()];
		return food / city.getTurnsToPopGrowth();
	}
	
	@Override
	public double getHappinessPerTurn() {
		double happiness = 0;
		if (owner.hasAdopted(SocialPolicy.URBANIZATION)) {
			happiness +=  1;
		}
		return happiness;
	}

}
