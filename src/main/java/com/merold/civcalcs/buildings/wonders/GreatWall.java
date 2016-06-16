package com.merold.civcalcs.buildings.wonders;

import com.merold.civcalcs.buildings.Building;
import com.merold.civcalcs.buildings.BuildingEnum;
import com.merold.civcalcs.city.City;
import com.merold.civcalcs.player.Player;

public class GreatWall extends ClassicalWonder {

	public GreatWall(City city, Player player) {
		super(BuildingEnum.GREAT_WALL, city, player, 250);
		culturePerTurn = 1;
		greatEngineerPoints = 1;
		// TODO: Quantify movement reduction.
		// TODO: Obsolete after Dynamite discovery.
	}

	@Override
	public Building alsoProvides() {
		return (Building) BuildingEnum.WALLS.create(city, owner);
	}

}
