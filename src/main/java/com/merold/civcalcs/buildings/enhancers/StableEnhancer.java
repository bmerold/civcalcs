package com.merold.civcalcs.buildings.enhancers;

import com.merold.civcalcs.tiles.Resource;

public class StableEnhancer extends BaseEnhancer {
	
	public StableEnhancer() {
		productionBonusResources.add(Resource.CATTLE);
		productionBonusResources.add(Resource.HORSES);
		productionBonusResources.add(Resource.SHEEP);
	}

}
