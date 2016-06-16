package com.merold.civcalcs.buildings;

import com.merold.civcalcs.city.City;
import com.merold.civcalcs.player.Player;

public class Monument extends Building {

	public Monument(City city, Player owner) {
		super(BuildingEnum.MONUMENT, city, owner, 40);
		culturePerTurn = 2;
		maintenancePerTurn = 1;
	}
}
