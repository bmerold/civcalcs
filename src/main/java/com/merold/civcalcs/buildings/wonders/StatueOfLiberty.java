package com.merold.civcalcs.buildings.wonders;

import com.merold.civcalcs.buildings.BuildingEnum;
import com.merold.civcalcs.city.City;
import com.merold.civcalcs.player.Player;

public class StatueOfLiberty extends ModernWonder {

	public StatueOfLiberty(City city, Player owner) {
		super(BuildingEnum.STATUE_OF_LIBERTY, city, owner, 1060);
		culturePerTurn = 1;
	}
	
	@Override
	public double getPotentialProductionPerTurn() {
		double specialistProductionBonus = city.getBuildings()
				.stream()
				.filter(t -> t.getSpecialists().size() > 0)
				.flatMap(t -> t.getSpecialists().stream())
				.filter(t -> t.isFilled())
				.count();
		return (getBaseProduction() + specialistProductionBonus) * (1 + productionModifier + city.getProductionModifier())
				+ (city.getBaseProduction() * productionModifier);
	}
	

}
