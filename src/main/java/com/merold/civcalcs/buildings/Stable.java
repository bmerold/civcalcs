package com.merold.civcalcs.buildings;

import com.merold.civcalcs.buildings.enhancers.StableEnhancer;
import com.merold.civcalcs.city.City;
import com.merold.civcalcs.player.Player;

public class Stable extends Building {

	public Stable(City city, Player player) {
		super(BuildingEnum.STABLE, city, player, 100);
		maintenancePerTurn = 1;
		enhancer = new StableEnhancer();
	}
	
}
