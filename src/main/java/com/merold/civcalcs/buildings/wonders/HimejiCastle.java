package com.merold.civcalcs.buildings.wonders;

import com.merold.civcalcs.buildings.Building;
import com.merold.civcalcs.buildings.BuildingEnum;
import com.merold.civcalcs.city.City;
import com.merold.civcalcs.player.Player;

public class HimejiCastle extends RenaisanceWonder {

	public HimejiCastle(City city, Player owner) {
		super(BuildingEnum.HIMEJI_CASTLE, city, owner, 500);
		culturePerTurn = 1;
		greatEngineerPoints = 2;
		// TODO: Add 15% combat modifier for units in friendly territory.
	}

	@Override
	public Building alsoProvides() {
		if (!city.hasBuilding(BuildingEnum.CASTLE)) {
			return (Building) BuildingEnum.CASTLE.create(city, owner);
		} else {
			return null;
		}
	}
	
	

}
