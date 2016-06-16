package com.merold.civcalcs.buildings;

import com.merold.civcalcs.city.City;
import com.merold.civcalcs.player.Player;
import com.merold.civcalcs.socialpolicies.SocialPolicy;

public class Observatory extends Building {

	public Observatory(City city, Player owner) {
		super(BuildingEnum.OBSERVATORY, city, owner, 200);
		scienceModifier = 0.5;
	}
	
	@Override
	public double getBaseGoldPerTurn() {
		double gold = 0;
		if (owner.hasAdopted(SocialPolicy.SOVEREIGNTY)) {
			gold += 1;
		}
		return gold;
	}

}
