package com.merold.civcalcs.buildings.nationalwonders;

import com.merold.civcalcs.buildings.BuildingEnum;
import com.merold.civcalcs.city.City;
import com.merold.civcalcs.player.Player;

public class NationalCollege extends NationalWonder {
	
	public NationalCollege(City city, Player player) {
		super(BuildingEnum.NATIONAL_COLLEGE, city, player, 155);
		culturePerTurn = 1;
		sciencePerTurn = 3;
		scienceModifier = 0.5;
	}

}
