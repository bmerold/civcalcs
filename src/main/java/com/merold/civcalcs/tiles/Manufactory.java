package com.merold.civcalcs.tiles;

import com.merold.civcalcs.science.Tech;

public class Manufactory extends Improvement {

	@Override
	public double getProduction(Tile tile) {
		int production = 4;
		if (tile.getOwner().hasDiscoveredTech(Tech.CHEMISTRY)) {
			production += 1;
		}
		return production;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Manufactory";
	}

}
