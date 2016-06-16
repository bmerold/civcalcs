package com.merold.civcalcs.buildings.enhancers;

import com.merold.civcalcs.tiles.TerrainFeature;

public class HydroPlantEnhancer extends BaseEnhancer {
	
	public HydroPlantEnhancer() {
		terrainFeatureProductionBonus.add(TerrainFeature.RIVER);
	}

}
