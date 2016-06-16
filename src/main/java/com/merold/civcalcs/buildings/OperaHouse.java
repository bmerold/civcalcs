package com.merold.civcalcs.buildings;

import com.merold.civcalcs.city.City;
import com.merold.civcalcs.greatworks.GreatWorkSlot;
import com.merold.civcalcs.player.Player;

public class OperaHouse extends Building {

	public OperaHouse(City city, Player owner) {
		super(BuildingEnum.OPERA_HOUSE, city, owner, 200);
		maintenancePerTurn = 1;
		culturePerTurn = 1;
		greatWorkOfMusicSlots = 1;
		gwofMusicSlots.add(new GreatWorkSlot());
	}

}
