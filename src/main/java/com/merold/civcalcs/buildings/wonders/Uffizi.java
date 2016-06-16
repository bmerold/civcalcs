package com.merold.civcalcs.buildings.wonders;

import java.util.ArrayList;
import java.util.List;

import com.merold.civcalcs.buildings.BuildingEnum;
import com.merold.civcalcs.city.City;
import com.merold.civcalcs.greatworks.GreatWorkSlot;
import com.merold.civcalcs.player.Player;
import com.merold.civcalcs.units.Unit;
import com.merold.civcalcs.units.UnitEnum;

public class Uffizi extends RenaisanceWonder {

	public Uffizi(City city, Player owner) {
		super(BuildingEnum.UFFIZI, city, owner, 625);
		culturePerTurn = 2;
		greatWorkOfArtSlots = 3;
		gwofArtSlots.add(new GreatWorkSlot());
		gwofArtSlots.add(new GreatWorkSlot());
		gwofArtSlots.add(new GreatWorkSlot());
	}

	@Override
	public List<Unit> createsUnits() {
		List<Unit> units = new ArrayList<Unit>();
		units.add(UnitEnum.GREAT_ARTIST.create(owner));
		return super.createsUnits();
	}
	
	

}
