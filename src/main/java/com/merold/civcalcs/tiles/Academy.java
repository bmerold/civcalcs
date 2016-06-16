package com.merold.civcalcs.tiles;

import com.merold.civcalcs.science.Tech;

public class Academy extends Improvement {

	@Override
	public double getScience(Tile tile) {
		int science = 8;
		if (tile.getOwner().hasDiscoveredTech(Tech.SCIENTIFIC_THEORY)) {
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
