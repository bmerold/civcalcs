package com.merold.civcalcs.buildings.enhancers;

import com.merold.civcalcs.tiles.Resource;

public class MintEnhancer extends BaseEnhancer {
	
	public MintEnhancer() {
		goldBonusResources.add(Resource.GOLD);
		goldBonusResources.add(Resource.SILVER);
	}

}
