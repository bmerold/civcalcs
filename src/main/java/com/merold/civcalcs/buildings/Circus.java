package com.merold.civcalcs.buildings;

import com.merold.civcalcs.city.City;
import com.merold.civcalcs.player.Player;

public class Circus extends Building {

	public Circus(City city, Player owner) {
		super(BuildingEnum.CIRCUS, city, owner, 70);
		happinessPerTurn = 2;
	}
}
