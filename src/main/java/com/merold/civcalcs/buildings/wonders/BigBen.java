package com.merold.civcalcs.buildings.wonders;

import com.merold.civcalcs.buildings.BuildingEnum;
import com.merold.civcalcs.city.City;
import com.merold.civcalcs.player.Player;

public class BigBen extends IndustrialWonder {

	public BigBen(City city, Player owner) {
		super(BuildingEnum.BIG_BEN, city, owner, 750);
		culturePerTurn = 1;
		goldPerTurn = 4;
		greatMerchantPoints = 2;
		// TODO: Implement reduction to gold purchases.
	}

}
