package com.merold.civcalcs.buildings;

import com.merold.civcalcs.city.City;
import com.merold.civcalcs.player.Player;

public class Hotel extends Building {

	public Hotel(City city, Player owner) {
		super(BuildingEnum.HOTEL, city, owner, 300);
		greatWorkTourismModifier = 0.5;
		cultureConversionToTourismRate = 0.5;
	}

}
