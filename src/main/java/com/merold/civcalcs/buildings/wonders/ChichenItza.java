package com.merold.civcalcs.buildings.wonders;

import com.merold.civcalcs.buildings.BuildingEnum;
import com.merold.civcalcs.city.City;
import com.merold.civcalcs.player.Player;

public class ChichenItza extends MedievalWonder {

	public ChichenItza(City city, Player owner) {
		super(BuildingEnum.CHICHEN_ITZA, city, owner, 300);
		culturePerTurn = 1;
		happinessPerTurn = 4;
		greatEngineerPoints = 1;
		// TODO: Implement the golden age bonus.
	}

}
