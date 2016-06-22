package com.merold.civcalcs.buildings.wonders;

import com.merold.civcalcs.buildings.BuildingEnum;
import com.merold.civcalcs.city.City;
import com.merold.civcalcs.player.Player;

public class CristoRedentor extends ModernWonder {

	public CristoRedentor(City city, Player owner) {
		super(BuildingEnum.CRISTO_REDENTOR, city, owner, 1250);
		culturePerTurn = 5;
		// TODO: Add culture purchase bonus.
	}

}
