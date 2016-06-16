package com.merold.civcalcs.buildings.wonders;

import com.merold.civcalcs.buildings.BuildingEnum;
import com.merold.civcalcs.city.City;
import com.merold.civcalcs.player.Player;

public class ForbiddenPalace extends RenaisanceWonder {

	public ForbiddenPalace(City city, Player owner) {
		super(BuildingEnum.FORBIDDEN_PALACE, city, owner, 500);
		culturePerTurn = 1;
		// TODO: Add additional delegates bonus.
		// TODO: Add unhappiness reduction for non-occupied cities.
	}

}
