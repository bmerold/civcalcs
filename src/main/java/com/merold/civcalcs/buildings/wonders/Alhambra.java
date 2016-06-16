package com.merold.civcalcs.buildings.wonders;

import com.merold.civcalcs.buildings.Building;
import com.merold.civcalcs.buildings.BuildingEnum;
import com.merold.civcalcs.city.City;
import com.merold.civcalcs.player.Player;

public class Alhambra extends MedievalWonder {

	public Alhambra(City city, Player owner) {
		super(BuildingEnum.ALHAMBRA, city, owner, 400);
		culturePerTurn = 1;
		// TODO: Add rough terrain promotion.
		cultureModifier = 0.2;
		
	}

	@Override
	public Building alsoProvides() {
		return (Building) BuildingEnum.CASTLE.create(city, owner);
	}

}
