package com.merold.civcalcs.buildings.wonders;

import com.merold.civcalcs.buildings.BuildingEnum;
import com.merold.civcalcs.city.City;
import com.merold.civcalcs.player.Player;

public class AngkorWat extends MedievalWonder {

	public AngkorWat(City city, Player owner) {
		super(BuildingEnum.ANGKOR_WAT, city, owner, 400);
		culturePerTurn = 1;
		greatEngineerPoints = 1;
		// TODO: Add bonus for reducing culture and gold purchase costs.
	}

}
