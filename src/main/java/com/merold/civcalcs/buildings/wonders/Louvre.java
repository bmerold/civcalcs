package com.merold.civcalcs.buildings.wonders;

import com.merold.civcalcs.buildings.BuildingEnum;
import com.merold.civcalcs.city.City;
import com.merold.civcalcs.player.Player;

public class Louvre extends IndustrialWonder {

	public Louvre(City city, Player owner) {
		super(BuildingEnum.LOUVRE, city, owner, 750);
		culturePerTurn = 2;
		greatWorkOfArtSlots = 4;
	}

}
