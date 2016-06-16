package com.merold.civcalcs.buildings;

import com.merold.civcalcs.city.City;
import com.merold.civcalcs.player.Player;
import com.merold.civcalcs.socialpolicies.SocialPolicy;
import com.merold.civcalcs.specialists.SpecialistSlot;

public class PublicSchool extends Building {

	public PublicSchool(City city, Player owner) {
		super(BuildingEnum.PUBLIC_SCHOOL, city, owner, 300);
		sciencePerTurn = 3;
		scientistSlots = 1;
		scientists.add(new SpecialistSlot());
	}
	
	@Override
	public double getPotentialSciencePerTurn() {
		return (city.getPopulation() / 2.0) * (1 + scienceModifier + city.getScienceModifier())
				+ (city.getBaseScience() * scienceModifier);
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
