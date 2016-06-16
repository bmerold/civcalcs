package com.merold.civcalcs.buildings.nationalwonders;

import com.merold.civcalcs.buildings.BuildingEnum;
import com.merold.civcalcs.city.City;
import com.merold.civcalcs.greatworks.GreatWorkSlot;
import com.merold.civcalcs.player.Player;

public class Hermitage extends NationalWonder {

	public Hermitage(City city, Player owner) {
		super(BuildingEnum.HERMITAGE, city, owner, 155);
		culturePerTurn = 2;
		cultureModifier = 0.5;
		greatWorkOfArtSlots = 3;
		gwofArtSlots.add(new GreatWorkSlot());
		gwofArtSlots.add(new GreatWorkSlot());
		gwofArtSlots.add(new GreatWorkSlot());
	}

}
