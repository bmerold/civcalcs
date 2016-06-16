package com.merold.civcalcs.buildings.enhancers;

import com.merold.civcalcs.tiles.Resource;

public class StoneWorksEnhancer extends BaseEnhancer {

	public StoneWorksEnhancer() {
		productionBonusResources.add(Resource.MARBLE);
		productionBonusResources.add(Resource.STONE);
	}

}
