package com.merold.civcalcs.buildings;

import com.merold.civcalcs.buildings.enhancers.UniversityEnhancer;
import com.merold.civcalcs.city.City;
import com.merold.civcalcs.player.Player;
import com.merold.civcalcs.socialpolicies.SocialPolicy;
import com.merold.civcalcs.specialists.SpecialistSlot;

public class University extends Building {

	public University(City city, Player owner) {
		super(BuildingEnum.UNIVERSITY, city, owner, 160);
		scientistSlots = 2;
		scientists.add(new SpecialistSlot());
		scientists.add(new SpecialistSlot());
		scienceModifier = 0.33;
		enhancer = new UniversityEnhancer();
	}

	@Override
	public double getScienceModifier() {
		double modifier = scienceModifier;
		if (owner.hasAdopted(SocialPolicy.FREE_THOUGHT)) {
			modifier += 0.17;
		}
		return modifier;
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
