package com.merold.civcalcs.buildings;

import com.merold.civcalcs.buildings.enhancers.StoneWorksEnhancer;
import com.merold.civcalcs.city.City;
import com.merold.civcalcs.player.Player;

public class StoneWorks extends Building {

	public StoneWorks(City city, Player owner) {
		super(BuildingEnum.STONE_WORKS, city, owner, 75);
		productionPerTurn = 1;
		maintenancePerTurn = 1;
		happinessPerTurn = 1;
		enhancer = new StoneWorksEnhancer();

	}
}
