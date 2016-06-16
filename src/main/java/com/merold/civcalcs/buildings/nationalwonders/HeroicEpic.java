package com.merold.civcalcs.buildings.nationalwonders;

import com.merold.civcalcs.buildings.BuildingEnum;
import com.merold.civcalcs.city.City;
import com.merold.civcalcs.greatworks.GreatWorkSlot;
import com.merold.civcalcs.player.Player;

public class HeroicEpic extends NationalWonder {

	public HeroicEpic(City city, Player player) {
		super(BuildingEnum.HEROIC_EPIC, city, player, 155);
		culturePerTurn = 1;
		greatWorkOfWritingSlots =1;
		gwofWritingSlots.add(new GreatWorkSlot());
		// TODO: Quantify Morale promotion.
	}

}
