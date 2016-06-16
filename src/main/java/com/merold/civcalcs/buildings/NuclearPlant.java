package com.merold.civcalcs.buildings;

import com.merold.civcalcs.city.City;
import com.merold.civcalcs.player.Player;

public class NuclearPlant extends Building {

	public NuclearPlant(City city, Player owner) {
		super(BuildingEnum.NUCLEAR_PLANT, city, owner, 500);
		productionModifier = 0.15;
		maintenancePerTurn = 3;
		productionPerTurn = 5;
	}

}
