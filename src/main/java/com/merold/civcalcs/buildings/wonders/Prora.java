package com.merold.civcalcs.buildings.wonders;

import com.merold.civcalcs.buildings.BuildingEnum;
import com.merold.civcalcs.city.City;
import com.merold.civcalcs.player.Player;

public class Prora extends ModernWonder {

	public Prora(City city, Player owner) {
		super(BuildingEnum.PRORA, city, owner, 1050);
		happinessPerTurn = 2;
	}
	
	@Override
	public double getHappinessPerTurn() {
		return happinessPerTurn + owner.getSocialPolicies().size();
	}

}
