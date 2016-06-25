package com.merold.civcalcs.tiles;

import com.merold.civcalcs.science.Tech;
import com.merold.civcalcs.socialpolicies.SocialPolicy;

public class Academy extends Improvement {

	@Override
	public double getScience(Tile tile) {
		int science = 8;
		if (tile.getOwner().hasDiscoveredTech(Tech.SCIENTIFIC_THEORY)) {
			science += 2;
		}

		if (tile.getOwner().hasAdopted(SocialPolicy.NEW_DEAL)) {
			science += 4;
		}
		
		if (tile.getOwner().hasDiscoveredTech(Tech.ATOMIC_THEORY)) {
			science += 2;
		}
		return science;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Academy";
	}

}
