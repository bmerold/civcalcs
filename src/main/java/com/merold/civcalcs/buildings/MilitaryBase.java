package com.merold.civcalcs.buildings;

import com.merold.civcalcs.city.City;
import com.merold.civcalcs.player.Player;

public class MilitaryBase extends Building {

	public MilitaryBase(City city, Player owner) {
		super(BuildingEnum.MILITARY_BASE, city, owner, 500);
		defense = 12;
		hitPoints = 25;
	}

}
