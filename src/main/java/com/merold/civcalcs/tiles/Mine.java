package com.merold.civcalcs.tiles;

import com.merold.civcalcs.science.Tech;

public class Mine extends Improvement {

	@Override
	public double getProduction(Tile tile) {
		int production = 1;
		if (tile.getOwner().hasDiscoveredTech(Tech.CHEMISTRY)) {
			production += 1;
		}
		return production;
	}
	
	@Override
	public String toString() {
		return "Mine";
	}

}
