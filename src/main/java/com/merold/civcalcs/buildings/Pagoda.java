package com.merold.civcalcs.buildings;

import com.merold.civcalcs.city.City;
import com.merold.civcalcs.player.Player;

public class Pagoda extends Building {

	public Pagoda(City city, Player owner) {
		super(BuildingEnum.PAGODA, city, owner, 1);
		culturePerTurn = 2;
		faithPerTurn = 2;
		happinessPerTurn = 2;
	}

}
