package com.merold.civcalcs.buildings.wonders;

import com.merold.civcalcs.buildings.BuildingEnum;
import com.merold.civcalcs.city.City;
import com.merold.civcalcs.player.Player;

public class Neuschwanstein extends ModernWonder {

	public Neuschwanstein(City city, Player owner) {
		super(BuildingEnum.NEUSCHWANSTEIN, city, owner, 1060);
		happinessPerTurn = 2;
		culturePerTurn = 4;
		goldPerTurn = 6;
		greatMerchantPoints = 1;
		// TODO: Add bonuses for Castles.
	}

}
