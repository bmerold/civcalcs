package com.merold.civcalcs.buildings.enhancers;

import java.util.ArrayList;
import java.util.List;

import com.merold.civcalcs.tiles.BaseTerrain;
import com.merold.civcalcs.tiles.Resource;
import com.merold.civcalcs.tiles.TerrainFeature;
import com.merold.civcalcs.tiles.Tile;

public class BaseEnhancer implements WorkedTileEnhancer {

	protected List<Resource> productionBonusResources = new ArrayList<Resource>();
	protected List<Resource> foodBonusResources = new ArrayList<Resource>();
	protected List<BaseTerrain> baseTerrainFoodBonus = new ArrayList<BaseTerrain>();
	protected List<TerrainFeature> terrainFeatureProductionBonus = new ArrayList<TerrainFeature>();
	protected List<Resource> goldBonusResources = new ArrayList<Resource>();
	protected List<BaseTerrain> baseTerrainFoodAndProductionBonus = new ArrayList<BaseTerrain>();
	protected List<Resource> productionAndGoldBonusResources = new ArrayList<Resource>();
	protected List<TerrainFeature> terrainFeatureScienceBonus = new ArrayList<TerrainFeature>();

	public BaseEnhancer() {
		super();
	}

	@Override
	public boolean hasBaseTerrainFoodBonus(Tile tile) {
		return baseTerrainFoodBonus.contains(tile.getBaseTerrain());
	}

	@Override
	public boolean hasProductionBonusResource(Tile tile) {
		return productionBonusResources.contains(tile.getResource());
	}

	@Override
	public boolean hasFoodBonusResource(Tile tile) {
		return foodBonusResources.contains(tile.getResource());
	}

	@Override
	public boolean hasGoldBonusResource(Tile tile) {
		return goldBonusResources.contains(tile.getResource());
	}

	@Override
	public boolean hasBaseTerrainFoodAndProductionBonus(Tile tile) {
		return baseTerrainFoodAndProductionBonus.contains(tile.getBaseTerrain());
	}

	@Override
	public boolean hasTerrainFeatureProductionBonus(Tile tile) {
		for (TerrainFeature feature : tile.getTerrainFeatures()) {
			if (terrainFeatureProductionBonus.contains(feature)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean hasProductionAndGoldBonusResource(Tile tile) {

		return productionAndGoldBonusResources.contains(tile.getResource());
	}

	@Override
	public boolean hasTerrainFeatureScienceBonus(Tile tile) {
		// TODO Auto-generated method stub
		return terrainFeatureScienceBonus.contains(tile.getTerrainFeatures());
	}

}