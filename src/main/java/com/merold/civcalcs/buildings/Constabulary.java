package com.merold.civcalcs.buildings;

import com.merold.civcalcs.city.City;
import com.merold.civcalcs.player.Player;

public class Constabulary extends Building {

	public Constabulary(City city, Player owner) {
		super(BuildingEnum.CONSTABULARY, city, owner, 160);
		maintenancePerTurn = 1;
		// TODO: Add stealing reduction.
	}

}
