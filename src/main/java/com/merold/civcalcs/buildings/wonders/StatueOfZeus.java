package com.merold.civcalcs.buildings.wonders;

import com.merold.civcalcs.buildings.BuildingEnum;
import com.merold.civcalcs.city.City;
import com.merold.civcalcs.player.Player;

public class StatueOfZeus extends ClassicalWonder {
	
	public StatueOfZeus(City city, Player player) {
		super(BuildingEnum.STATUE_OF_ZEUS, city, player, 185);
		culturePerTurn = 1;
	}

}
