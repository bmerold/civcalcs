package com.merold.civcalcs.buildings;

import com.merold.civcalcs.Belief;
import com.merold.civcalcs.city.City;
import com.merold.civcalcs.player.Player;

public class Pagoda extends Building {

	public Pagoda(City city, Player owner) {
		super(BuildingEnum.PAGODA, city, owner, 1);
		culturePerTurn = 2;
		faithPerTurn = 2;
		happinessPerTurn = 2;
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
