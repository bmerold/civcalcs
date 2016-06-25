package com.merold.civcalcs.buildings;

import com.merold.civcalcs.city.City;
import com.merold.civcalcs.player.Player;
import com.merold.civcalcs.socialpolicies.SocialPolicy;
import com.merold.civcalcs.specialists.SpecialistSlot;

public class Bank extends Building {

	public Bank(City city, Player owner) {
		super(BuildingEnum.BANK, city, owner, 200);
		goldPerTurn = 2;
		goldModifier = 0.25;
		merchantSlots = 1;
		merchants.add(new SpecialistSlot());
		// TODO: Add bonus trade route gold
				
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
