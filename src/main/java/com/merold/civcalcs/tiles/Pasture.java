package com.merold.civcalcs.tiles;

import static com.merold.civcalcs.tiles.Resource.*;

import com.merold.civcalcs.science.Tech;

public class Pasture extends Improvement {

	@Override
	public double getProduction(Tile tile) {
		if (tile.has(HORSES) || tile.has(CATTLE)) {
			return 1;
		} else {
			return 0;
		}
	}

	@Override
	public double getFood(Tile tile) {
		double food = 0;
		if (tile.has(SHEEP)) {
			food += 1;
		}
		if (tile.getOwner().hasDiscoveredTech(Tech.FERTILIZER)) {
			food += 1;
		}
		return food;
	}

	@Override
	public String toString() {
		return "Pasture";
	}

}
