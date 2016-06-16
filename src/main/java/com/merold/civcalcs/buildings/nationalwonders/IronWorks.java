package com.merold.civcalcs.buildings.nationalwonders;

import com.merold.civcalcs.buildings.BuildingEnum;
import com.merold.civcalcs.city.City;
import com.merold.civcalcs.player.Player;

public class IronWorks extends NationalWonder {

	public IronWorks(City city, Player owner) {
		super(BuildingEnum.IRON_WORKS, city, owner, 155);
		productionPerTurn = 8;
	}

}
