package com.merold.civcalcs.buildings.wonders;

import java.util.ArrayList;
import java.util.List;

import com.merold.civcalcs.buildings.Building;
import com.merold.civcalcs.buildings.BuildingEnum;
import com.merold.civcalcs.city.City;
import com.merold.civcalcs.player.Player;
import com.merold.civcalcs.units.Unit;
import com.merold.civcalcs.units.UnitEnum;

public class HagiaSophia extends MedievalWonder {

	public HagiaSophia(City city, Player player) {
		super(BuildingEnum.HAGIA_SOPHIA, city, player, 300);
		faithPerTurn = 3;
	}

	@Override
	public Building alsoProvides() {
		if (city.hasBuilding(BuildingEnum.TEMPLE)) {
			return null;
		} else {
			return (Building) BuildingEnum.TEMPLE.create(city, owner);
		}
	}

	@Override
	public List<Unit> createsUnits() {
		List<Unit> units = new ArrayList<Unit>();
		units.add(UnitEnum.GREAT_PROPHET.create(owner));
		return units;
	}

}
