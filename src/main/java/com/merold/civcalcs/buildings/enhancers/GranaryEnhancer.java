package com.merold.civcalcs.buildings.enhancers;

import com.merold.civcalcs.tiles.Resource;

public class GranaryEnhancer extends BaseEnhancer {

	public GranaryEnhancer() {
		foodBonusResources.add(Resource.BANANAS);
		foodBonusResources.add(Resource.DEER);
		foodBonusResources.add(Resource.WHEAT);
	}

}
