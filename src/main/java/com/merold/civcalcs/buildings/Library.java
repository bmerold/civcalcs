package com.merold.civcalcs.buildings;

import com.merold.civcalcs.city.City;
import com.merold.civcalcs.player.Player;
import com.merold.civcalcs.socialpolicies.SocialPolicy;

public class Library extends Building {

	public Library(City city, Player owner) {
		super(BuildingEnum.LIBRARY, city, owner, 75);
		maintenancePerTurn = 1;
		this.city = city;
	}

	@Override
	public double getPotentialSciencePerTurn() {
		return (city.getPopulation() / 2.0) * (1 + scienceModifier + city.getScienceModifier())
				+ (city.getBaseScience() * scienceModifier);
	}
	
	@Override
	public double getBaseGoldPerTurn() {
		double gold = 0;
		if (owner.hasAdopted(SocialPolicy.SOVEREIGNTY)) {
			gold += 1;
		}
		return gold;
	}

}
