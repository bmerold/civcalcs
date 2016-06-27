package com.merold.civcalcs.buildings;

import com.merold.civcalcs.buildings.enhancers.HydroPlantEnhancer;
import com.merold.civcalcs.city.City;
import com.merold.civcalcs.player.Player;

public class HydroPlant extends Building {

	public HydroPlant(City city, Player owner) {
		super(BuildingEnum.HYDRO_PLANT, city, owner, 360);
		maintenancePerTurn = 3;
		enhancer = new HydroPlantEnhancer();
	}
	
	@Override
	public double getPotentialProductionPerTurn() {
		double productionAddedByEnhancer = 0;
		if (enhancer != null) {
			productionAddedByEnhancer += city.getTiles().stream()
					.filter(t -> t.isWorked())
					.filter(t -> t != t.getWorkedBy().getStartingTile())
					.filter(t -> enhancer.hasTerrainFeatureProductionBonus(t))
					.count();
		}
		return (productionPerTurn + productionAddedByEnhancer) * (1 + productionModifier + city.getProductionModifier())
				+ (city.getBaseProduction() * productionModifier);
	}

}
