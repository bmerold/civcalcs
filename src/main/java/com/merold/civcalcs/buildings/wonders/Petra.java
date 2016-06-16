package com.merold.civcalcs.buildings.wonders;

import java.util.List;

import com.merold.civcalcs.buildings.BuildingEnum;
import com.merold.civcalcs.buildings.enhancers.PetraEnhancer;
import com.merold.civcalcs.city.City;
import com.merold.civcalcs.player.Player;
import com.merold.civcalcs.tiles.TerrainFeature;
import com.merold.civcalcs.tiles.Tile;

public class Petra extends ClassicalWonder {

	public Petra(City city, Player player) {
		super(BuildingEnum.PETRA, city, player, 250);
		culturePerTurn = 1;
		greatEngineerPoints = 1;
		// TODO: Add additional trade route slot.
		// TODO: Add caravan.
		enhancer = new PetraEnhancer();
	}

	@Override
	public double getFoodPerTurn() {
		double food = this.foodPerTurn;
		List<Tile> tiles = city.getWorkedTiles();
		for (Tile tile : tiles) {
			if (enhancer.hasBaseTerrainFoodAndProductionBonus(tile) && !tile.has(TerrainFeature.FLOOD_PLAINS)) {
				food++;
			}
		}
		return food;
	}

	@Override
	public double getBaseProduction() {
		double production = this.productionPerTurn;
		List<Tile> tiles = city.getWorkedTiles();
		for (Tile tile : tiles) {
			if (enhancer.hasBaseTerrainFoodAndProductionBonus(tile) && !tile.has(TerrainFeature.FLOOD_PLAINS)) {
				production++;
			}
		}
		return production;
	}
}
