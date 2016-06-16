package com.merold.civcalcs.buildings;

import com.merold.civcalcs.city.City;
import com.merold.civcalcs.player.Player;

public class Colleseum extends Building {

	public Colleseum(City city, Player player) {
		super(BuildingEnum.COLLESEUM, city, player, 100);
		maintenancePerTurn = 1;
		happinessPerTurn = 2;
	}

	@Override
	public double getHappinessPerTurn() {
		if (city.getPopulation() >= 2) {
			return happinessPerTurn;
		} else {
			return 1;
		}
	}
}
