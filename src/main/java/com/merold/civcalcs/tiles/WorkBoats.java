package com.merold.civcalcs.tiles;

import com.merold.civcalcs.science.Tech;

public class WorkBoats extends Improvement {
	
	@Override
	public double getFood(Tile tile) {
		double food = 0;
		Resource resource = tile.getResource();
		if (resource == Resource.CRAB || resource == Resource.WHALES || resource == Resource.FISH || tile.getResource() == Resource.PEARLS) {
			food += 1;
		}
		return food;
	}

	@Override
	public double getGold(Tile tile) {
		double gold = 0;
		if (tile.getOwner().hasDiscoveredTech(Tech.COMPASS)) {
			gold += 1;
		}

		return gold;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Work Boats";
	}

}
