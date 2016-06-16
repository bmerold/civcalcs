package com.merold.civcalcs.buildings.wonders;

import com.merold.civcalcs.buildings.BuildingEnum;
import com.merold.civcalcs.city.City;
import com.merold.civcalcs.player.Player;

public class Colossus extends ClassicalWonder {
	
	public Colossus(City city, Player player) {
		super(BuildingEnum.COLOSSUS, city, player, 185);
		culturePerTurn = 1;
		goldPerTurn = 5;
		greatMerchantPoints = 1;
		// TODO: Quantify additional trade route slot.
		// TODO: Add Cargo Ship bonus unit.
		// TODO: Add bonus gold from trade route.
	}

}
