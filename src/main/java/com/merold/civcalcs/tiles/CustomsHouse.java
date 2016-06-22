package com.merold.civcalcs.tiles;

import com.merold.civcalcs.science.Tech;
import com.merold.civcalcs.socialpolicies.SocialPolicy;

public class CustomsHouse extends Improvement {

	@Override
	public double getGold(Tile tile) {
		double gold = 4;
		if (tile.getOwner().hasDiscoveredTech(Tech.ECONOMICS)) {
			gold += 1;
		}
		if (tile.getOwner().hasAdopted(SocialPolicy.NEW_DEAL)) {
			gold += 4;
		}
		return gold;
	}

	@Override
	public String toString() {
		return "Customs House";
	}
}
