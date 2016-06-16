package com.merold.civcalcs.buildings.enhancers;

import com.merold.civcalcs.tiles.Resource;

public class MausoleumEnhancer extends BaseEnhancer {

	public MausoleumEnhancer() {
		goldBonusResources.add(Resource.MARBLE);
		goldBonusResources.add(Resource.STONE);
	}

}
