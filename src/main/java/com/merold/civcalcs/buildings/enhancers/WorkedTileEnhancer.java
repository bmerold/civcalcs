package com.merold.civcalcs.buildings.enhancers;

import com.merold.civcalcs.tiles.Tile;

public interface WorkedTileEnhancer {

	public boolean hasBaseTerrainFoodBonus(Tile tile);
	public boolean hasBaseTerrainFoodAndProductionBonus(Tile tile);
	public boolean hasProductionBonusResource(Tile tile);
	public boolean hasFoodBonusResource(Tile tile);
	public boolean hasGoldBonusResource(Tile tile);
	public boolean hasTerrainFeatureProductionBonus(Tile tile);
	public boolean hasTerrainFeatureScienceBonus(Tile tile);
	public boolean hasProductionAndGoldBonusResource(Tile tile);
	

}
