package com.merold.civcalcs.buildings.wonders;

import java.util.ArrayList;
import java.util.List;

import com.merold.civcalcs.buildings.BuildingEnum;
import com.merold.civcalcs.city.City;
import com.merold.civcalcs.greatworks.GreatWorkSlot;
import com.merold.civcalcs.player.Player;
import com.merold.civcalcs.units.Unit;
import com.merold.civcalcs.units.UnitEnum;

public class GlobeTheater extends RenaisanceWonder {

	public GlobeTheater(City city, Player owner) {
		super(BuildingEnum.GLOBE_THEATER, city, owner, 500);
		culturePerTurn = 2;
		greatWorkOfWritingSlots = 2;
		gwofWritingSlots.add(new GreatWorkSlot());
		gwofWritingSlots.add(new GreatWorkSlot());
	}
	
	@Override
	public List<Unit> createsUnits() {
		List<Unit> units = new ArrayList<Unit>();
		units.add(UnitEnum.GREAT_WRITER.create(owner));
		return super.createsUnits();
	}

}
