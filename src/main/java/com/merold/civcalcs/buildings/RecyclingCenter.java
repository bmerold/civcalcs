package com.merold.civcalcs.buildings;

import com.merold.civcalcs.city.City;
import com.merold.civcalcs.player.Player;

public class RecyclingCenter extends Building {

	public RecyclingCenter(City city, Player owner) {
		super(BuildingEnum.RECYCLING_CENTER, city, owner, 500);
		maintenancePerTurn = 3;
		// TODO: Add aluminum bonus.
	}

}
