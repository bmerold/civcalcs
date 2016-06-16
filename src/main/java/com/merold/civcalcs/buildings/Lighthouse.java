package com.merold.civcalcs.buildings;

import com.merold.civcalcs.buildings.enhancers.LighthouseEnhancer;
import com.merold.civcalcs.city.City;
import com.merold.civcalcs.player.Player;
import com.merold.civcalcs.socialpolicies.SocialPolicy;

public class Lighthouse extends Building {

	public Lighthouse(City city, Player player) {
		super(BuildingEnum.LIGHTHOUSE, city, player, 75);
		maintenancePerTurn = 1;
		enhancer = new LighthouseEnhancer();
	}
	
	@Override
	public double getHappinessPerTurn() {
		double happiness = 0;
		if (owner.hasAdopted(SocialPolicy.NAVAL_TRADITION)) {
			happiness += 1;
		}
		return happiness;
	}
	
	@Override
	public double getBaseGoldPerTurn() {
		double gold = 0;
		if (owner.hasAdopted(SocialPolicy.MERCHANT_NAVY)) {
			gold += 1;
		}
		return gold;
	}

}
