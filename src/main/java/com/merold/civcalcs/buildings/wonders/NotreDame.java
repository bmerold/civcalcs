package com.merold.civcalcs.buildings.wonders;

import com.merold.civcalcs.buildings.BuildingEnum;
import com.merold.civcalcs.city.City;
import com.merold.civcalcs.player.Player;

public class NotreDame extends MedievalWonder {

	public NotreDame(City city, Player owner) {
		super(BuildingEnum.NOTRE_DAME, city, owner, 400);
		faithPerTurn = 4;
		happinessPerTurn = 10;
		greatMerchantPoints = 1;
	}

}
