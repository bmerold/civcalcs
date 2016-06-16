package com.merold.civcalcs.buildings.wonders;

import java.util.ArrayList;
import java.util.List;

import com.merold.civcalcs.buildings.BuildingEnum;
import com.merold.civcalcs.city.City;
import com.merold.civcalcs.player.Player;
import com.merold.civcalcs.units.Unit;
import com.merold.civcalcs.units.UnitEnum;

public class Pyramids extends AncientWonder {

	public Pyramids(City city, Player player) {
		super(BuildingEnum.PYRAMIDS, city, player, 185);
		culturePerTurn = 1;
		greatEngineerPoints = 1;
	}

	@Override
	public List<Unit> createsUnits() {
		ArrayList<Unit> units = new ArrayList<Unit>();
		units.add(UnitEnum.WORKER.create(owner));
		units.add(UnitEnum.WORKER.create(owner));
		return units;
	}

}
