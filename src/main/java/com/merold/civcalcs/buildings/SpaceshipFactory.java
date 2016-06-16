package com.merold.civcalcs.buildings;

import com.merold.civcalcs.city.City;
import com.merold.civcalcs.player.Player;

public class SpaceshipFactory extends Building {

	public SpaceshipFactory(City city, Player owner) {
		super(BuildingEnum.SPACESHIP_FACTORY, city, owner, 360);
		maintenancePerTurn = 3;
		// TODO: Add modifier for spaceship parts.
		productionPerTurn = 3;
	}

}
