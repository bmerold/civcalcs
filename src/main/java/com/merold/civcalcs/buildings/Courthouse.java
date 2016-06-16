package com.merold.civcalcs.buildings;

import com.merold.civcalcs.city.City;
import com.merold.civcalcs.player.Player;

public class Courthouse extends Building {

	public Courthouse(City city, Player player) {
		super(BuildingEnum.COURTHOUSE, city, player, 100);
		maintenancePerTurn = 4;
		// TODO: Figure out how to quantify the unhappiness reduction.
	}
}
