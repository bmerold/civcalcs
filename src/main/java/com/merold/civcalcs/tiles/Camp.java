package com.merold.civcalcs.tiles;

import com.merold.civcalcs.science.Tech;

public class Camp extends Improvement {

	@Override
	public double getProduction(Tile tile) {
		Resource resource = tile.getResource();
		if (resource == Resource.BISON || resource == Resource.DEER) {
			return 1;
		} else {
			return 0;
		}
	}

	@Override
	public double getGold(Tile tile) {
		Resource resource = tile.getResource();
		double gold = 0;
		if (resource == Resource.IVORY || resource == Resource.TRUFFLES || resource == Resource.FURS) {
			gold += 1;
			if (tile.getOwner().hasDiscoveredTech(Tech.ECONOMICS)) {
				gold += 1;
			}
		}

		return gold;
	}

	@Override
	public String toString() {
		return "Camp";
	}

}
