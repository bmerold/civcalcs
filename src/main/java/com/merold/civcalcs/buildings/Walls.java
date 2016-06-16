package com.merold.civcalcs.buildings;

import com.merold.civcalcs.city.City;
import com.merold.civcalcs.player.Player;

public class Walls extends Building {

	protected Walls(City city, Player owner) {
		super(BuildingEnum.WALLS, city, owner, 75);
		defense = 5;
		hitPoints = 50;
	}

}
