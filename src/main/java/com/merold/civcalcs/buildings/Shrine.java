package com.merold.civcalcs.buildings;

import com.merold.civcalcs.city.City;
import com.merold.civcalcs.player.Player;
import com.merold.civcalcs.socialpolicies.SocialPolicy;

public class Shrine extends Building {

	public Shrine(City city, Player owner) {
		super(BuildingEnum.SHRINE, city, owner, 40);
		faithPerTurn =1;
		maintenancePerTurn =1;
	}

	@Override
	public double getFaithPerTurn() {
		double faith = faithPerTurn;
		if (owner.hasAdopted(SocialPolicy.ORGANIZED_RELIGION)) {
			faith += 1;
		}
		return faith;
	}
	
	

}
