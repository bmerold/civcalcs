package com.merold.civcalcs.buildings.wonders;

import java.util.ArrayList;
import java.util.List;

import com.merold.civcalcs.buildings.BuildingEnum;
import com.merold.civcalcs.city.City;
import com.merold.civcalcs.player.Player;
import com.merold.civcalcs.units.Unit;
import com.merold.civcalcs.units.UnitEnum;

public class Borobudur extends MedievalWonder {
	
	public Borobudur(City city, Player player) {
		super(BuildingEnum.BOROBUDUR, city, player, 300);
		faithPerTurn = 5;
	}

	@Override
	public List<Unit> createsUnits() {
		List<Unit> units = new ArrayList<Unit>();
		units.add(UnitEnum.MISSIONARY.create(owner));
		units.add(UnitEnum.MISSIONARY.create(owner));
		units.add(UnitEnum.MISSIONARY.create(owner));
		return units;
	}
	
	

}
