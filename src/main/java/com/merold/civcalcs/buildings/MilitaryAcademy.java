package com.merold.civcalcs.buildings;

import com.merold.civcalcs.city.City;
import com.merold.civcalcs.player.Player;

public class MilitaryAcademy extends Building {

	public MilitaryAcademy(City city, Player owner) {
		super(BuildingEnum.MILITARY_ACADEMY, city, owner, 300);
		maintenancePerTurn = 1;
		// TODO: Add experience bonus.
	}

}
