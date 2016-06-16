package com.merold.civcalcs.buildings.wonders;

import com.merold.civcalcs.buildings.BuildingEnum;
import com.merold.civcalcs.city.City;
import com.merold.civcalcs.player.Player;

public class TempleOfArtemis extends AncientWonder {

	public TempleOfArtemis(City city, Player owner) {
		super(BuildingEnum.TEMPLE_OF_ARTEMIS, city, owner, 185);
		culturePerTurn=1;
		greatEngineerPoints = 1;
		this.city = city;
	}

	@Override
	public double getFoodAddedByGrowthModifier() {
		return owner.getCities().stream().mapToDouble(c -> c.getBaseFood()).sum() * 0.1;
	}
}
