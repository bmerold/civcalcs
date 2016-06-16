package com.merold.civcalcs.buildings;

import com.merold.civcalcs.city.City;
import com.merold.civcalcs.player.Player;

public class Stadium extends Building {

	public Stadium(City city, Player owner) {
		super(BuildingEnum.STADIUM, city, owner, 500);
		maintenancePerTurn = 2;
		happinessPerTurn = 2;
	}

}
