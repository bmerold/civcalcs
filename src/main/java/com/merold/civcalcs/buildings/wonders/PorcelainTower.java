package com.merold.civcalcs.buildings.wonders;

import java.util.ArrayList;
import java.util.List;

import com.merold.civcalcs.buildings.BuildingEnum;
import com.merold.civcalcs.city.City;
import com.merold.civcalcs.player.Player;
import com.merold.civcalcs.units.Unit;
import com.merold.civcalcs.units.UnitEnum;

public class PorcelainTower extends RenaisanceWonder {

	public PorcelainTower(City city, Player owner) {
		super(BuildingEnum.PORCELAIN_TOWER, city, owner, 625);
		culturePerTurn = 1;
		greatScientistPoints = 2;
		// TODO: Quantify the 50% boost to RAs.
	}

	@Override
	public List<Unit> createsUnits() {
		List<Unit> units = new ArrayList<Unit>();
		units.add(UnitEnum.GREAT_SCIENTIST.create(owner));
		return units;
	}
}
