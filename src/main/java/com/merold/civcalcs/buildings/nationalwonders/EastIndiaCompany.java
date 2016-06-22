package com.merold.civcalcs.buildings.nationalwonders;

import com.merold.civcalcs.buildings.BuildingEnum;
import com.merold.civcalcs.city.City;
import com.merold.civcalcs.player.Player;
import com.merold.civcalcs.socialpolicies.SocialPolicy;

public class EastIndiaCompany extends NationalWonder {

	public EastIndiaCompany(City city, Player owner) {
		super(BuildingEnum.EAST_INDIA_COMPANY, city, owner, 155);
		goldPerTurn = 4;
		// TODO: Add bonus trade gold.
	}
	
	@Override
	public double getBaseProduction() {
		double production = super.getBaseProduction();
		if (owner.hasAdopted(SocialPolicy.MERCHANT_NAVY)) {
			production += 4;
		}
		return production;
	}
	
	@Override
	public double getBaseCulturePerTurn() {
		double culture = super.getBaseCulturePerTurn();
		return culture;
	}
	

}
