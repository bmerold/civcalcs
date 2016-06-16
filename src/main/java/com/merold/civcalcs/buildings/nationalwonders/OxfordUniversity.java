package com.merold.civcalcs.buildings.nationalwonders;

import com.merold.civcalcs.buildings.BuildingEnum;
import com.merold.civcalcs.city.City;
import com.merold.civcalcs.greatworks.GreatWorkSlot;
import com.merold.civcalcs.player.Player;

public class OxfordUniversity extends NationalWonder {

	public OxfordUniversity(City city, Player owner) {
		super(BuildingEnum.OXFORD_UNIVERSITY, city, owner, 155);
		culturePerTurn = 1;
		sciencePerTurn = 3;
		greatWorkOfWritingSlots = 2;
		gwofWritingSlots.add(new GreatWorkSlot());
		gwofWritingSlots.add(new GreatWorkSlot());
		// TODO: Add free tech bonus.
	}

}
