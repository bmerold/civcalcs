package com.merold.civcalcs.buildings;

import com.merold.civcalcs.buildings.enhancers.SeaportEnhancer;
import com.merold.civcalcs.city.City;
import com.merold.civcalcs.player.Player;
import com.merold.civcalcs.socialpolicies.SocialPolicy;

public class Seaport extends Building {

	public Seaport(City city, Player owner) {
		super(BuildingEnum.SEAPORT, city, owner, 250);
		maintenancePerTurn = 2;
		// TODO: Add naval production bonus.
		enhancer = new SeaportEnhancer();
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
