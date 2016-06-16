package com.merold.civcalcs.buildings.nationalwonders;

import com.merold.civcalcs.buildings.BuildingEnum;
import com.merold.civcalcs.city.City;
import com.merold.civcalcs.greatworks.GreatWorkSlot;
import com.merold.civcalcs.player.Player;

public class NationalEpic extends NationalWonder {

	public NationalEpic(City city, Player player) {
		super(BuildingEnum.NATIONAL_EPIC, city, player, 155);
		culturePerTurn = 1;
		greatWorkOfWritingSlots = 1;
		gwofWritingSlots.add(new GreatWorkSlot());
		greatPeopleModifier = 0.25;
	}
}
