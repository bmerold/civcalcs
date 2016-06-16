package com.merold.civcalcs.buildings;

import com.merold.civcalcs.city.City;
import com.merold.civcalcs.player.Player;
import com.merold.civcalcs.specialists.SpecialistSlot;

public class Windmill extends Building {

	public Windmill(City city, Player owner) {
		super(BuildingEnum.WINDMILL, city, owner, 250);
		maintenancePerTurn = 2;
		engineerSlots = 1;
		engineers.add(new SpecialistSlot());
		productionPerTurn = 2;
		// TODO: Handle production modifier for buildings.
	}

}
