package com.merold.civcalcs.buildings;

import com.merold.civcalcs.city.City;
import com.merold.civcalcs.player.Player;

public class Aqueduct extends Building {

	public Aqueduct(City city, Player player) {
		super(BuildingEnum.AQUEDUCT, city, player, 100);
		// TODO: Figure out how to quantify the growth carry over.
	}

	@Override
	public double getFoodAddedByGrowthModifier() {
		double food = 0.4 * city.getFoodToGrow()[city.getPopulation()];
		return food / city.getTurnsToPopGrowth();
	}
	
	
}
