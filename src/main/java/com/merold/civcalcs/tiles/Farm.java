package com.merold.civcalcs.tiles;

import com.merold.civcalcs.science.Tech;

public class Farm extends Improvement {

	@Override
	public double getFood(Tile tile) {
		double food = 1;
		if (tile.getOwner().hasDiscoveredTech(Tech.CIVIL_SERVICE) && tile.isHasFreshWater()) {
			food +=1;
		}
		
		if (tile.getOwner().hasDiscoveredTech(Tech.FERTILIZER) && !tile.isHasFreshWater()) {
			food +=1;
		}
		return food;
	}

	@Override
	public String toString() {
		return "Farm";
	}

}
