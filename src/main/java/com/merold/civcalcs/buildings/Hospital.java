package com.merold.civcalcs.buildings;

import com.merold.civcalcs.city.City;
import com.merold.civcalcs.player.Player;
import com.merold.civcalcs.socialpolicies.SocialPolicy;

public class Hospital extends Building {

	public Hospital(City city, Player owner) {
		super(BuildingEnum.HOSPITAL, city, owner, 360);
		maintenancePerTurn = 2;
		foodPerTurn = 5;
	}
	
	@Override
	public double getHappinessPerTurn() {
		double happiness = 0;
		if (owner.hasAdopted(SocialPolicy.URBANIZATION)) {
			happiness +=  1;
		}
		return happiness;
	}

}
