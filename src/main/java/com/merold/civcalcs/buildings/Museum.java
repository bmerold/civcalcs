package com.merold.civcalcs.buildings;

import com.merold.civcalcs.city.City;
import com.merold.civcalcs.greatworks.GreatWorkSlot;
import com.merold.civcalcs.player.Player;

public class Museum extends Building {

	public Museum(City city, Player owner) {
		super(BuildingEnum.MUSEUM, city, owner, 300);
		maintenancePerTurn = 3;
		culturePerTurn = 1;
		greatWorkOfArtSlots = 2;
		gwofArtSlots.add(new GreatWorkSlot());
		gwofArtSlots.add(new GreatWorkSlot());
	}

}
