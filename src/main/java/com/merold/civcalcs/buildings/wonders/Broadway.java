package com.merold.civcalcs.buildings.wonders;

import java.util.ArrayList;
import java.util.List;

import com.merold.civcalcs.buildings.BuildingEnum;
import com.merold.civcalcs.city.City;
import com.merold.civcalcs.greatworks.GreatWorkSlot;
import com.merold.civcalcs.player.Player;
import com.merold.civcalcs.units.Unit;
import com.merold.civcalcs.units.UnitEnum;

public class Broadway extends ModernWonder {

	public Broadway(City city, Player owner) {
		super(BuildingEnum.BROADWAY, city, owner, 1060);
		culturePerTurn = 2;
		greatWorkOfMusicSlots = 3;
		gwofMusicSlots.add(new GreatWorkSlot());
		gwofMusicSlots.add(new GreatWorkSlot());
		gwofMusicSlots.add(new GreatWorkSlot());
	}
	
	@Override
	public List<Unit> createsUnits() {
		List<Unit> units = new ArrayList<Unit>();
		units.add(UnitEnum.GREAT_MUSICIAN.create(owner));
		return units;
	}

}
