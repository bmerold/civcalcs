package com.merold.civcalcs.buildings.wonders;

import com.merold.civcalcs.buildings.BuildingEnum;
import com.merold.civcalcs.city.City;
import com.merold.civcalcs.player.Player;

public class Oracle extends ClassicalWonder {
	
	public Oracle(City city, Player player) {
		super(BuildingEnum.ORACLE, city, player, 250);
		culturePerTurn = 3;
		greatScientistPoints = 1;
		//TODO: Quantify free social policy.
	}

}
