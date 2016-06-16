package com.merold.civcalcs.buildings;

import com.merold.civcalcs.city.City;
import com.merold.civcalcs.player.Player;
import com.merold.civcalcs.specialists.SpecialistSlot;

public class Market extends Building {
	
	public Market(City city, Player player) {
		super(BuildingEnum.MARKET, city, player, 100);
		goldModifier = 0.25;
		goldPerTurn = 1;
		merchantSlots = 1;
		merchants.add(new SpecialistSlot());
		// TODO: Figure out how to add the trade route bonus gold.
	}

}
