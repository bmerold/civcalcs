package com.merold.civcalcs.buildings;

import com.merold.civcalcs.city.City;
import com.merold.civcalcs.player.Player;

public class SolarPlant extends Building {

	public SolarPlant(City city, Player owner) {
		super(BuildingEnum.SOLAR_PLANT, city, owner, 500);
		maintenancePerTurn = 3;
		productionModifier = 0.15;
		productionPerTurn = 5;
	}

}
