package com.merold.civcalcs.buildings;

import com.merold.civcalcs.city.City;
import com.merold.civcalcs.player.Player;

public class Armory extends Building {

	public Armory(City city, Player owner) {
		super(BuildingEnum.ARMORY, city, owner, 160);
		maintenancePerTurn = 1;
		// TODO: Quantify the +15 experience
	}

}
