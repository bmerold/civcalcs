package com.merold.civcalcs.buildings;

import com.merold.civcalcs.city.City;
import com.merold.civcalcs.player.Player;
import com.merold.civcalcs.specialists.SpecialistSlot;

public class Factory extends Building {

	public Factory(City city, Player owner) {
		super(BuildingEnum.FACTORY, city, owner, 360);
		productionModifier = 0.1;
		productionPerTurn = 4;
		engineerSlots = 2;
		engineers.add(new SpecialistSlot());
		engineers.add(new SpecialistSlot());
		maintenancePerTurn = 3;
	}

}
