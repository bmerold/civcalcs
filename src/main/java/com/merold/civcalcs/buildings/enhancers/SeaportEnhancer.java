package com.merold.civcalcs.buildings.enhancers;

import com.merold.civcalcs.tiles.Resource;

public class SeaportEnhancer extends BaseEnhancer {

	public SeaportEnhancer() {
		productionAndGoldBonusResources.add(Resource.FISH);
		productionAndGoldBonusResources.add(Resource.OCEAN_OIL);
		productionAndGoldBonusResources.add(Resource.PEARLS);
		productionAndGoldBonusResources.add(Resource.CRAB);
		productionAndGoldBonusResources.add(Resource.WHALES);
	}

}
