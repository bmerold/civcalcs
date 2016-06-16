package com.merold.civcalcs.buildings.wonders;

import com.merold.civcalcs.buildings.BuildingEnum;
import com.merold.civcalcs.city.City;
import com.merold.civcalcs.player.Player;

public class TajMahal extends RenaisanceWonder {

	public TajMahal(City city, Player owner) {
		super(BuildingEnum.TAJ_MAHAL, city, owner, 625);
		happinessPerTurn = 4;
		culturePerTurn = 1;
		// TODO: Add the golden age trigger.
	}

}
