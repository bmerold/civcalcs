package com.merold.civcalcs.buildings.nationalwonders;

import com.merold.civcalcs.buildings.BuildingEnum;
import com.merold.civcalcs.city.City;
import com.merold.civcalcs.player.Player;

public class GrandTemple extends NationalWonder {
	
	public GrandTemple(City city, Player player) {
		super(BuildingEnum.GRAND_TEMPLE, city, player, 155);
		faithPerTurn = 8;
		// TODO: Quantify doubling the religious pressure.
	}

}
