package com.merold.civcalcs.buildings;

import com.merold.civcalcs.buildings.enhancers.MintEnhancer;
import com.merold.civcalcs.city.City;
import com.merold.civcalcs.player.Player;
import com.merold.civcalcs.socialpolicies.SocialPolicy;

public class Mint extends Building {

	public Mint(City city, Player player) {
		super(BuildingEnum.MINT, city, player, 100);
		enhancer = new MintEnhancer();
	}
	
	@Override
	public double getHappinessPerTurn() {
		double happiness = 0;
		if (owner.hasAdopted(SocialPolicy.CAPITALISM)) {
			happiness +=  1;
		}
		return happiness;
	}
}
