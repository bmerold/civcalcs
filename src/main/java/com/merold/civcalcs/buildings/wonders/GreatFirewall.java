package com.merold.civcalcs.buildings.wonders;

import com.merold.civcalcs.buildings.BuildingEnum;
import com.merold.civcalcs.city.City;
import com.merold.civcalcs.player.Player;

public class GreatFirewall extends AtomicWonder {

	public GreatFirewall(City city, Player owner) {
		super(BuildingEnum.GREAT_FIREWALL, city, owner, 1250);
		// TODO: Implement 99.9% spy effectiveness reduction for city.
		// TODO: Implement 25% spy effectiveness reduction for civ.
		// TODO: Implement tourism reduction of Internet for other civs.
	}

}
