package com.merold.civcalcs.buildings;

import com.merold.civcalcs.city.City;
import com.merold.civcalcs.player.Player;

public class Arsenal extends Building {

	public Arsenal(City city, Player owner) {
		super(BuildingEnum.ARSENAL, city, owner, 300);
		defense = 9;
		hitPoints = 25;
	}

}
