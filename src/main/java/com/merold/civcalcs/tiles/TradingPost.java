package com.merold.civcalcs.tiles;

import com.merold.civcalcs.science.Tech;
import com.merold.civcalcs.socialpolicies.SocialPolicy;

public class TradingPost extends Improvement {

	@Override
	public double getGold(Tile tile) {
		double gold = 1;
		if (tile.getOwner().hasDiscoveredTech(Tech.ECONOMICS)) {
			gold += 1;
		}
		return gold;
	}

	@Override
	public double getScience(Tile tile) {
		double science = 0;
		if (tile.getOwner().hasAdopted(SocialPolicy.FREE_THOUGHT)) {
			science +=1;
		}
		return science;
	}

}
