package com.merold.civcalcs.buildings.enhancers;

import com.merold.civcalcs.tiles.BaseTerrain;
import com.merold.civcalcs.tiles.Resource;

public class LighthouseEnhancer extends BaseEnhancer {

	public LighthouseEnhancer() {
		baseTerrainFoodBonus.add(BaseTerrain.COAST);
		baseTerrainFoodBonus.add(BaseTerrain.OCEAN);
		foodBonusResources.add(Resource.FISH);
		productionBonusResources.add(Resource.WHALES);
		productionBonusResources.add(Resource.PEARLS);
		productionBonusResources.add(Resource.CRAB);
		productionBonusResources.add(Resource.FISH);
		// TODO: Find out what resources count as sea resources for production boost.
	}

}
