package com.merold.civcalcs.buildings;

import com.merold.civcalcs.city.City;
import com.merold.civcalcs.player.Player;

public class WaterMill extends Building {

	public WaterMill(City city, Player owner) {
		super(BuildingEnum.WATER_MILL, city, owner, 75);
		foodPerTurn = 2;
		productionPerTurn = 1;
		maintenancePerTurn = 2;
	}
}
