package com.merold.civcalcs.buildings.wonders;

import com.merold.civcalcs.buildings.BuildingEnum;
import com.merold.civcalcs.city.City;
import com.merold.civcalcs.player.Player;

public class Pentagon extends AtomicWonder {

	public Pentagon(City city, Player owner) {
		super(BuildingEnum.PENTAGON, city, owner, 1250);
		culturePerTurn = 1;
		greatMerchantPoints = 2;
		// TODO: Implement unit upgrade bonus.
	}

}
