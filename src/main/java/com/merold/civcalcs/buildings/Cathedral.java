package com.merold.civcalcs.buildings;

import com.merold.civcalcs.Belief;
import com.merold.civcalcs.city.City;
import com.merold.civcalcs.greatworks.GreatWorkSlot;
import com.merold.civcalcs.player.Player;

public class Cathedral extends Building {

	public Cathedral(City city, Player owner) {
		super(BuildingEnum.CATHEDRAL, city, owner, 1);
		happinessPerTurn = 1;
		faithPerTurn = 1;
		culturePerTurn = 1;
		greatWorkOfArtSlots = 1;
		gwofArtSlots.add(new GreatWorkSlot());
	}

}
