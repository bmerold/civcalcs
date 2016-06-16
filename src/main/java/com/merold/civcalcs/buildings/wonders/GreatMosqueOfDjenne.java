package com.merold.civcalcs.buildings.wonders;

import com.merold.civcalcs.buildings.Building;
import com.merold.civcalcs.buildings.BuildingEnum;
import com.merold.civcalcs.city.City;
import com.merold.civcalcs.player.Player;

public class GreatMosqueOfDjenne extends MedievalWonder {

	public GreatMosqueOfDjenne(City city, Player owner) {
		super(BuildingEnum.GREAT_MOSQUE_OF_DJENNE, city, owner, 300);
		faithPerTurn = 3;
		culturePerTurn = 1;
		greatEngineerPoints = 1;
		// TODO: Quantify missionary promotion.
		
	}

	@Override
	public Building alsoProvides() {
		return (Building) BuildingEnum.MOSQUE.create(city, owner);
	}

}
