package com.merold.civcalcs.units;

import com.merold.civcalcs.city.City;
import com.merold.civcalcs.player.Player;
import com.merold.civcalcs.science.Tech;
import com.merold.civcalcs.tiles.Resource;
import com.merold.civcalcs.tiles.Tile;

public class WorkBoat extends Unit {

	private Tile toBeImproved;

	public WorkBoat(Player player) {
		super(UnitEnum.WORK_BOAT, player, 50);
		moves = 4;
	}

	@Override
	public double getFoodYield(City city) {
		initialize(city);
		double food = 0;
		double modifier = city.getFoodModifier();
		if (toBeImproved != null) {
		if (hasFoodResource(toBeImproved)) {
			food += 1;
		}}
		return food * (1 + modifier);
	}

	@Override
	public double getGoldYield(City city) {
		initialize(city);
		double gold = 0;
		double modifier = city.getGoldModifier();
		if (toBeImproved != null) {
			if (owner.hasDiscoveredTech(Tech.COMPASS)) {
				gold += 1;
			}
		}

		return gold * (1 + modifier);
	}

	private boolean hasFoodResource(Tile tile) {
		Resource resource = tile.getResource();
		if (Resource.FISH == resource || resource == Resource.WHALES || resource == Resource.CRAB || resource == Resource.PEARLS) {
			return true;
		} else {
			return false;
		}
	}

	private boolean hasGoldResource(Tile tile) {
		return Resource.PEARLS == tile.getResource();
	}

	private void initialize(City city) {
		if (toBeImproved == null) {
			for (Tile tile : city.getTiles()) {
				if (!tile.isImproved() && (hasGoldResource(tile) || hasFoodResource(tile))) {
					toBeImproved = tile;
					break;
				}
			}
		}
	}

}
