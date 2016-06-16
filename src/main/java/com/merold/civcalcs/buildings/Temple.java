package com.merold.civcalcs.buildings;

import com.merold.civcalcs.Belief;
import com.merold.civcalcs.city.City;
import com.merold.civcalcs.player.Player;
import com.merold.civcalcs.socialpolicies.SocialPolicy;

public class Temple extends Building {
	public Temple(City city, Player player) {
		super(BuildingEnum.TEMPLE, city, player, 100);
		maintenancePerTurn = 2;
		faithPerTurn = 2;
	}

	@Override
	public double getFaithPerTurn() {
		double faith = faithPerTurn;
		if (owner.hasAdopted(SocialPolicy.ORGANIZED_RELIGION)) {
			faith += 1;
		}
		return faith;
	}

	@Override
	public double getGoldModifier() {
		if (owner.hasAdopted(SocialPolicy.THEOCRACY)) {
			return 0.25;
		} else {
			return 0;
		}
	}
}
