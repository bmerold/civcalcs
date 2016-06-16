package com.merold.civcalcs.buildings;

import com.merold.civcalcs.city.City;
import com.merold.civcalcs.player.Player;
import com.merold.civcalcs.socialpolicies.SocialPolicy;
import com.merold.civcalcs.specialists.SpecialistSlot;

public class ResearchLab extends Building {

	public ResearchLab(City city, Player owner) {
		super(BuildingEnum.REASEARCH_LAB, city, owner, 300);
		maintenancePerTurn = 3;
		scientistSlots = 1;
		scientists.add(new SpecialistSlot());
		scienceModifier = 0.5;
		sciencePerTurn = 4;
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
