package com.merold.civcalcs.tiles;

import com.merold.civcalcs.science.Tech;
import com.merold.civcalcs.socialpolicies.SocialPolicy;

public class Manufactory extends Improvement {

	@Override
	public double getProduction(Tile tile) {
		int production = 4;
		if (tile.getOwner().hasDiscoveredTech(Tech.CHEMISTRY)) {
			production += 1;
		}
		if (tile.getOwner().hasAdopted(SocialPolicy.NEW_DEAL)) {
			production += 4;
		}
		return production;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Manufactory";
	}

}
