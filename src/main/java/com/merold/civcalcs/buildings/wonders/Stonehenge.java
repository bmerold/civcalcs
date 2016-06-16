package com.merold.civcalcs.buildings.wonders;

import com.merold.civcalcs.buildings.BuildingEnum;
import com.merold.civcalcs.city.City;
import com.merold.civcalcs.player.Player;

public class Stonehenge extends AncientWonder {

	public Stonehenge(City city, Player owner) {
		super(BuildingEnum.STONEHENGE, city, owner, 185);
		faithPerTurn = 5;
		greatEngineerPoints = 1;
	}

}
