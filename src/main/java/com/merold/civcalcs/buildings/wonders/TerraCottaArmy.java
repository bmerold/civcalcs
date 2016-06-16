package com.merold.civcalcs.buildings.wonders;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.merold.civcalcs.buildings.BuildingEnum;
import com.merold.civcalcs.city.City;
import com.merold.civcalcs.player.Player;
import com.merold.civcalcs.units.LandUnit;
import com.merold.civcalcs.units.Unit;
import com.merold.civcalcs.units.UnitEnum;

public class TerraCottaArmy extends ClassicalWonder {
	
	public TerraCottaArmy(City city, Player player) {
		super(BuildingEnum.TERRA_COTTA_ARMY, city, player, 250);
		culturePerTurn = 1;
	}

	@Override
	public List<Unit> createsUnits() {
		Set<UnitEnum> unitTypes = new HashSet<UnitEnum>();
		for (Unit unit : owner.getUnits()) {
			if (unit instanceof LandUnit) {
				unitTypes.add(unit.getType());
			}
		}
		
		List<Unit> units = new ArrayList<Unit>();
		for (UnitEnum unitEnum : unitTypes) {
			units.add(unitEnum.create(owner));
		}
		return units;
	}
	
	

}
