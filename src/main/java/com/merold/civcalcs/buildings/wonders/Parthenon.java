package com.merold.civcalcs.buildings.wonders;

import com.merold.civcalcs.buildings.BuildingEnum;
import com.merold.civcalcs.city.City;
import com.merold.civcalcs.greatworks.GreatWorkOfArt;
import com.merold.civcalcs.greatworks.GreatWorkSlot;
import com.merold.civcalcs.player.Player;

public class Parthenon extends ClassicalWonder {
	
	public Parthenon(City city, Player player) {
		super(BuildingEnum.PARTHENON, city, player, 250);
		culturePerTurn = 4;
		greatWorkOfArtSlots = 1;
		gwofArtSlots.add(new GreatWorkSlot());
		addGreatWorkOfArt(new GreatWorkOfArt());
	}

}
