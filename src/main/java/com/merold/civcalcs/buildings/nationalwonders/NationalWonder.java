package com.merold.civcalcs.buildings.nationalwonders;

import com.merold.civcalcs.buildings.BuildingEnum;
import com.merold.civcalcs.buildings.wonders.Wonder;
import com.merold.civcalcs.city.City;
import com.merold.civcalcs.player.Player;

public class NationalWonder extends Wonder {

	protected NationalWonder(BuildingEnum name, City city, Player owner, double hammerCost) {
		super(name, city, owner, hammerCost);
	}

}
