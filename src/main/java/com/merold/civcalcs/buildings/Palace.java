package com.merold.civcalcs.buildings;

import com.merold.civcalcs.Belief;
import com.merold.civcalcs.city.City;
import com.merold.civcalcs.greatworks.GreatWorkSlot;
import com.merold.civcalcs.player.Player;

public class Palace extends Building {

	public Palace(City city, Player owner) {
		super(BuildingEnum.PALACE, city, owner, 0);
		culturePerTurn = 1;
		defense = 2.5;
		goldPerTurn = 3;
		sciencePerTurn = 3;
		productionPerTurn = 3;
		greatWorkOfArtSlots = 1;
		gwofArtSlots.add(new GreatWorkSlot());
	}

	@Override
	public double getFaithPerTurn() {
		double faith = faithPerTurn;
		if (owner.believes(Belief.GOD_KING)) {
			faith += 1;
		}
		return faith;
	}
}
