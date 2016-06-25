package com.merold.civcalcs.buildings.wonders;

import com.merold.civcalcs.buildings.BuildingEnum;
import com.merold.civcalcs.city.City;
import com.merold.civcalcs.greatworks.GreatWorkSlot;
import com.merold.civcalcs.player.Player;

public class SydneyOperaHouse extends AtomicWonder {

	public SydneyOperaHouse(City city, Player owner) {
		super(BuildingEnum.SYDNEY_OPERA_HOUSE, city, owner, 1250);
		greatWorkOfMusicSlots = 2;
		gwofMusicSlots.add(new GreatWorkSlot());
		gwofMusicSlots.add(new GreatWorkSlot());
		cultureModifier = 0.5;
		// TODO: Implement free social policy
	}

}
