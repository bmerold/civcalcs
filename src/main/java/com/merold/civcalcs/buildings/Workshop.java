package com.merold.civcalcs.buildings;

import com.merold.civcalcs.city.City;
import com.merold.civcalcs.player.Player;
import com.merold.civcalcs.specialists.SpecialistSlot;

public class Workshop extends Building {

	public Workshop(City city, Player owner) {
		super(BuildingEnum.WORKSHOP, city, owner, 120);
		maintenancePerTurn = 2;
		engineers.add(new SpecialistSlot());
		engineerSlots = 1;
		productionPerTurn = 2;
		productionModifier = 0.1;
	}

}
