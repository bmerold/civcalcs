package com.merold.civcalcs.buildings.wonders;

import com.merold.civcalcs.buildings.BuildingEnum;
import com.merold.civcalcs.city.City;
import com.merold.civcalcs.player.Player;

public class BrandenburgGate extends IndustrialWonder {

	public BrandenburgGate(City city, Player owner) {
		super(BuildingEnum.BRANDENBURG_GATE, city, owner, 750);
		culturePerTurn = 1;
		greatScientistPoints = 2;
		// TODO: Add great general.
		// TODO: Add experience bonus
	}

}
