package com.merold.civcalcs.buildings.enhancers;

import com.merold.civcalcs.tiles.BaseTerrain;

public class PetraEnhancer extends BaseEnhancer {
	
	public PetraEnhancer() {
		baseTerrainFoodAndProductionBonus.add(BaseTerrain.DESERT);
	}

}
