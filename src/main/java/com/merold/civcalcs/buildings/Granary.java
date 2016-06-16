package com.merold.civcalcs.buildings;

import java.util.List;

import com.merold.civcalcs.buildings.enhancers.GranaryEnhancer;
import com.merold.civcalcs.city.City;
import com.merold.civcalcs.player.Player;
import com.merold.civcalcs.tiles.Tile;

public class Granary extends Building {

	public Granary(City city, Player owner) {
		super(BuildingEnum.GRANARY, city, owner, 60);
		maintenancePerTurn =1;
		foodPerTurn =2;
		enhancer = (new GranaryEnhancer());
	}

	@Override
	public double getFoodPerTurn() {
		double food = this.foodPerTurn;
		List<Tile> tiles = city.getWorkedTiles();
		for (Tile tile : tiles) {
			if (enhancer.hasFoodBonusResource(tile)) {
				food++;
			}
		}
		return food;
	}

}
