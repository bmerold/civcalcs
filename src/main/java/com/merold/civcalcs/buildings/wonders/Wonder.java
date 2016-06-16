package com.merold.civcalcs.buildings.wonders;

import com.merold.civcalcs.buildings.BuildingEnum;
import com.merold.civcalcs.buildings.Project;
import com.merold.civcalcs.city.City;
import com.merold.civcalcs.player.Player;

public class Wonder extends Project {

	protected Wonder(BuildingEnum name, City city, Player owner, double hammerCost) {
		super(name, city, owner, hammerCost);
	}

}
