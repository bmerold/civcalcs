package com.merold.civcalcs.buildings.nationalwonders;

import com.merold.civcalcs.buildings.BuildingEnum;
import com.merold.civcalcs.city.City;
import com.merold.civcalcs.player.Player;

public class CircusMaximus extends NationalWonder {
	
	public CircusMaximus(City city, Player player) {
		super(BuildingEnum.CIRCUS_MAXIMUS, city, player, 155);
		happinessPerTurn = 5;
		culturePerTurn = 1;
		requiredBuilding = BuildingEnum.COLLESEUM;
	}

}
