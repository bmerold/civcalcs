package com.merold.civcalcs.buildings.wonders;

import com.merold.civcalcs.buildings.BuildingEnum;
import com.merold.civcalcs.city.City;
import com.merold.civcalcs.greatworks.GreatWorkSlot;
import com.merold.civcalcs.player.Player;

public class SistineChapel extends RenaisanceWonder {

	public SistineChapel(City city, Player owner) {
		super(BuildingEnum.SISTINE_CHAPEL, city, owner, 500);
		culturePerTurn = 1;
		greatWorkOfArtSlots = 2;
		gwofArtSlots.add(new GreatWorkSlot());
		gwofArtSlots.add(new GreatWorkSlot());
	}

	@Override
	public double getPotentialCulturePerTurn() {
		double baseCulture = culturePerTurn * (1 + city.getCultureModifier());
		double cultureAddedByMultiplier = owner.getCities().stream().mapToDouble(c -> c.getBaseCulture()).sum() * 0.25;
		return baseCulture + cultureAddedByMultiplier;
	}

}
