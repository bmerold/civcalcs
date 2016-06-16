package com.merold.civcalcs.buildings;

import com.merold.civcalcs.Belief;
import com.merold.civcalcs.city.City;
import com.merold.civcalcs.player.Player;

public class Mosque extends Building {

	public Mosque(City city, Player owner) {
		super(BuildingEnum.MOSQUE, city, owner, 1);
		faithPerTurn = 3;
		culturePerTurn = 2;
		happinessPerTurn = 1;
	}

	@Override
	public double getTourismPerTurn() {
		double tourism = 0;
		if (owner.believes(Belief.SACRED_SITES)) {
			tourism += 2;
		}
		return tourism;
	}

}
