package com.merold.civcalcs.buildings;

import com.merold.civcalcs.buildings.enhancers.ForgeEnhancer;
import com.merold.civcalcs.city.City;
import com.merold.civcalcs.player.Player;

public class Forge extends Building {

	public Forge(City city, Player owner) {
		super(BuildingEnum.FORGE, city, owner, 120);
		enhancer = new ForgeEnhancer();
		maintenancePerTurn = 1;
		// TODO: 15% bonus to land units.
	}

}
