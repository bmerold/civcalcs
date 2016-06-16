package com.merold.civcalcs.buildings;

import com.merold.civcalcs.city.City;
import com.merold.civcalcs.player.Player;

public class Barracks extends Building {
	
	public Barracks(City city, Player player) {
		super(BuildingEnum.BARRACKS, city, player, 75);
		maintenancePerTurn = 1;
		// TODO: Add unit experience logic.
	}

}
