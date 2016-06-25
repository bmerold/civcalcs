package com.merold.civcalcs.buildings;

import com.merold.civcalcs.city.City;
import com.merold.civcalcs.player.Player;
import com.merold.civcalcs.socialpolicies.SocialPolicy;
import com.merold.civcalcs.specialists.SpecialistSlot;

public class StockExchange extends Building {

	public StockExchange(City city, Player owner) {
		super(BuildingEnum.STOCK_EXCHANGE, city, owner, 500);
		merchantSlots = 2;
		merchants.add(new SpecialistSlot());
		merchants.add(new SpecialistSlot());
		goldPerTurn = 3;
		goldModifier = 0.25;
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
