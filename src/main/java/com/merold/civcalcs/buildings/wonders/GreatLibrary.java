package com.merold.civcalcs.buildings.wonders;

import com.merold.civcalcs.buildings.Building;
import com.merold.civcalcs.buildings.BuildingEnum;
import com.merold.civcalcs.buildings.Library;
import com.merold.civcalcs.city.City;
import com.merold.civcalcs.greatworks.GreatWorkSlot;
import com.merold.civcalcs.player.Player;

public class GreatLibrary extends AncientWonder {

	public GreatLibrary(City city, Player owner) {
		super(BuildingEnum.GREAT_LIBRARY, city, owner, 185);
		culturePerTurn = 1;
		sciencePerTurn = 3;
		greatScientistPoints = 1;
		greatWorkOfWritingSlots = 2;
		gwofWritingSlots.add(new GreatWorkSlot());
		gwofWritingSlots.add(new GreatWorkSlot());
		// TODO: Figure out how to handle the free Tech.
	}

	@Override
	public Building alsoProvides() {
		if (city.hasBuilding(BuildingEnum.LIBRARY)) {
			return null;
		} else {
			return new Library(city, owner);
		}
	}

}
