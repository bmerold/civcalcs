package com.merold.civcalcs.buildings;

import com.merold.civcalcs.city.City;
import com.merold.civcalcs.player.Player;

public class Airport extends Building {

	public Airport(City city, Player owner) {
		super(BuildingEnum.HOTEL, city, owner, 250);
		maintenancePerTurn = 5;
		greatWorkTourismModifier = 0.5;
		cultureConversionToTourismRate = 0.5;
		// TODO: Add air unit capacity.
	}

}
