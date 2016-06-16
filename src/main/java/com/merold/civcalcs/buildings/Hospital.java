package com.merold.civcalcs.buildings;

import com.merold.civcalcs.city.City;
import com.merold.civcalcs.player.Player;

public class Hospital extends Building {

	public Hospital(City city, Player owner) {
		super(BuildingEnum.HOSPITAL, city, owner, 360);
		maintenancePerTurn = 2;
		foodPerTurn = 5;
	}

}
