package com.merold.civcalcs.buildings.wonders;

import com.merold.civcalcs.buildings.BuildingEnum;
import com.merold.civcalcs.city.City;
import com.merold.civcalcs.player.Player;

public class LeaningTowerOfPisa extends RenaisanceWonder {

	public LeaningTowerOfPisa(City city, Player owner) {
		super(BuildingEnum.LEANING_TOWER_OF_PISA, city, owner, 500);
		culturePerTurn = 1;
		// TODO: Add free great person thingy.
	}
	
	@Override
	protected double getPotentialGreatPeopleModifier() {
		// TODO Auto-generated method stub
		return 0.25;
	}
	
	

}
