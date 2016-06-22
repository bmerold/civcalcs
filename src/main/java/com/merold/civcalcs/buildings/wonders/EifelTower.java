package com.merold.civcalcs.buildings.wonders;

import com.merold.civcalcs.buildings.BuildingEnum;
import com.merold.civcalcs.city.City;
import com.merold.civcalcs.player.Player;

public class EifelTower extends ModernWonder {

	public EifelTower(City city, Player owner) {
		super(BuildingEnum.EIFEL_TOWER, city, owner, 1060);
		happinessPerTurn = 5;
		culturePerTurn = 1;
		greatMerchantPoints = 2;
		tourism = 12;
	}

}
