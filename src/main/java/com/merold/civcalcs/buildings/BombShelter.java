package com.merold.civcalcs.buildings;

import com.merold.civcalcs.city.City;
import com.merold.civcalcs.player.Player;

public class BombShelter extends Building {

	public BombShelter(City city, Player owner) {
		super(BuildingEnum.BOMB_SHELTER, city, owner, 300);
		maintenancePerTurn = 1;
		// TODO: Add reduced population loss from nukes.
	}

}
