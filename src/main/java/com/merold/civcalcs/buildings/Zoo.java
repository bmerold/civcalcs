package com.merold.civcalcs.buildings;

import com.merold.civcalcs.city.City;
import com.merold.civcalcs.player.Player;

public class Zoo extends Building {

	public Zoo(City city, Player owner) {
		super(BuildingEnum.ZOO, city, owner, 200);
		maintenancePerTurn = 2;
		happinessPerTurn = 2;		
	}

}
