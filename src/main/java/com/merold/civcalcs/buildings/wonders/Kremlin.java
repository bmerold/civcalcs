package com.merold.civcalcs.buildings.wonders;

import com.merold.civcalcs.buildings.BuildingEnum;
import com.merold.civcalcs.city.City;
import com.merold.civcalcs.player.Player;

public class Kremlin extends ModernWonder {

	public Kremlin(City city, Player owner) {
		super(BuildingEnum.KREMLIN, city, owner, 1060);
	}

}
