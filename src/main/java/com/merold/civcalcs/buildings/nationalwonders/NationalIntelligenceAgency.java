package com.merold.civcalcs.buildings.nationalwonders;

import com.merold.civcalcs.buildings.BuildingEnum;
import com.merold.civcalcs.city.City;
import com.merold.civcalcs.player.Player;

public class NationalIntelligenceAgency extends NationalWonder {

	public NationalIntelligenceAgency(City city, Player owner) {
		super(BuildingEnum.NATIONAL_INTELLIGENCE_AGENCY, city, owner, 155);
		// TODO: Implement spy reduction.
		// TODO: Add spy.
	}

}
