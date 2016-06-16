package com.merold.civcalcs.buildings;

import com.merold.civcalcs.city.City;
import com.merold.civcalcs.greatworks.GreatWorkSlot;
import com.merold.civcalcs.player.Player;

public class Amphitheater extends Building {

	public Amphitheater(City city, Player player) {
		super(BuildingEnum.AMPHITHEATER, city, player, 100);
		maintenancePerTurn = 1;
		culturePerTurn = 1;
		greatWorkOfWritingSlots =1;
		gwofWritingSlots.add(new GreatWorkSlot());
	}
}
