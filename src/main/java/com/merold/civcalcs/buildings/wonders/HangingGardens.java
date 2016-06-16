package com.merold.civcalcs.buildings.wonders;

import com.merold.civcalcs.buildings.Building;
import com.merold.civcalcs.buildings.BuildingEnum;
import com.merold.civcalcs.city.City;
import com.merold.civcalcs.player.Player;

public class HangingGardens extends ClassicalWonder {

	public HangingGardens(City city, Player player) {
		super(BuildingEnum.HANGING_GARDENS, city, player, 250);
		foodPerTurn = 6;
		culturePerTurn = 1;
	}

	@Override
	public Building alsoProvides() {
		if (city.hasBuilding(BuildingEnum.GARDEN)) {
			return null;
		} else {
			return (Building) BuildingEnum.GARDEN.create(city, owner);
		}
	}

}
