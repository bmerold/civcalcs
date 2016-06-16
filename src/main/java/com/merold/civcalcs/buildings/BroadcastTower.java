package com.merold.civcalcs.buildings;

import com.merold.civcalcs.city.City;
import com.merold.civcalcs.greatworks.GreatWorkSlot;
import com.merold.civcalcs.player.Player;

public class BroadcastTower extends Building {

	public BroadcastTower(City city, Player owner) {
		super(BuildingEnum.BROADCAST_TOWER, city, owner, 500);
		maintenancePerTurn = 3;
		culturePerTurn = 1;
		cultureModifier = 0.33;
		greatWorkOfMusicSlots = 1;
		gwofMusicSlots.add(new GreatWorkSlot());
	}

}
