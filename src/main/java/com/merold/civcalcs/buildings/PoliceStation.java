package com.merold.civcalcs.buildings;

import com.merold.civcalcs.city.City;
import com.merold.civcalcs.player.Player;

public class PoliceStation extends Building {

	public PoliceStation(City city, Player owner) {
		super(BuildingEnum.POLICE_STATION, city, owner, 300);
		maintenancePerTurn = 1;
		// TODO: Add reduction to theft.
	}

}
