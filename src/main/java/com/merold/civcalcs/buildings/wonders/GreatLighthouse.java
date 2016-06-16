package com.merold.civcalcs.buildings.wonders;

import com.merold.civcalcs.buildings.Building;
import com.merold.civcalcs.buildings.BuildingEnum;
import com.merold.civcalcs.buildings.Lighthouse;
import com.merold.civcalcs.city.City;
import com.merold.civcalcs.player.Player;

public class GreatLighthouse extends ClassicalWonder {

	public GreatLighthouse(City city, Player player) {
		super(BuildingEnum.GREAT_LIGHTHOUSE, city, player, 185);
		culturePerTurn = 1;
		greatMerchantPoints = 1;
		// TODO: Figure out how to value the Sight and Movement promotions.

	}

	@Override
	public Building alsoProvides() {
		if (city.hasBuilding(BuildingEnum.LIGHTHOUSE)) {
			return null;
		} else {
			return new Lighthouse(city, owner);
		}
	}
}
