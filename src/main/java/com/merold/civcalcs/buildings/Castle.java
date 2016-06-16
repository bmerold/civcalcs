package com.merold.civcalcs.buildings;

import com.merold.civcalcs.city.City;
import com.merold.civcalcs.player.Player;

public class Castle extends Building {

	public Castle(City city, Player owner) {
		super(BuildingEnum.CASTLE, city, owner, 160);
		defense = 7;
		hitPoints = 25;
	}

}
