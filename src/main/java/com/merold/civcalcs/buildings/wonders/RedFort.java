package com.merold.civcalcs.buildings.wonders;

import com.merold.civcalcs.buildings.BuildingEnum;
import com.merold.civcalcs.city.City;
import com.merold.civcalcs.player.Player;

public class RedFort extends Wonder {

	public RedFort(City city, Player owner) {
		super(BuildingEnum.RED_FORT, city, owner, 625);
		culturePerTurn = 1;
		defense = 12;
		greatScientistPoints = 1;
		// TODO: Implement defensive bonus.
	}
	

}
