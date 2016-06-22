package com.merold.civcalcs.tiles;

import java.util.ArrayList;
import java.util.List;

import com.merold.civcalcs.buildings.enhancers.WorkedTileEnhancer;
import com.merold.civcalcs.city.City;
import com.merold.civcalcs.player.Player;
import com.merold.civcalcs.science.Tech;
import com.merold.civcalcs.units.UnitEnum;

public class Tile {
	public Player getOwner() {
		return owner;
	}

	private BaseTerrain baseTerrain;
	private List<TerrainFeature> terrainFeatures = new ArrayList<TerrainFeature>();
	private Improvement improvement = new NoImprovement();

	public List<TerrainFeature> getTerrainFeatures() {
		return terrainFeatures;
	}

	private final int xCoord;
	private final int yCoord;

	private boolean hasFreshWater = false;

	public boolean isHasFreshWater() {
		return hasFreshWater;
	}

	private boolean isCoastal = false;
	private int foodOutput = 0;
	private int productionOutput = 0;
	private int goldOutput = 0;
	private int scienceOutput = 0;
	private boolean improved = false;
	private Player owner;

	public double getGoldOutput() {
		double gold = goldOutput;
		gold += improvement.getGold(this);
		for (WorkedTileEnhancer enhancer : workedBy.getEnhancers()) {
			if (enhancer.hasGoldBonusResource(this)) {
				gold += 2;
			}
			if (enhancer.hasProductionAndGoldBonusResource(this)) {
				gold += 1;
			}
		}

		return gold;
	}

	public double getScienceOutput() {
		double science = 0;
		science += improvement.getScience(this);
		for (WorkedTileEnhancer enhancer : workedBy.getEnhancers()) {
			if (enhancer.hasTerrainFeatureScienceBonus(this)) {
				science += 2;
			}
		}

		return science;
	}

	public BaseTerrain getBaseTerrain() {
		return baseTerrain;
	}

	public void setGoldOutput(int goldOutput) {
		this.goldOutput = goldOutput;
	}

	private boolean isWorked = false;
	private Resource resource;

	public double getProductionOutput() {
		double production = productionOutput;
		production += improvement.getProduction(this);
		
		if (owner.hasDiscoveredTech(Tech.SCIENTIFIC_THEORY) && resource == Resource.COAL) {
			production += 1;
		}

		for (WorkedTileEnhancer enhancer : workedBy.getEnhancers()) {
			if (enhancer.hasBaseTerrainFoodAndProductionBonus(this)) {
				production += 1;
			}

			if (enhancer.hasProductionBonusResource(this)) {
				production += 1;
			}

			if (enhancer.hasTerrainFeatureProductionBonus(this)) {
				production += 1;
			}

			if (enhancer.hasProductionAndGoldBonusResource(this)) {
				production += 1;
			}
		}

		return production;
	}

	@Override
	public String toString() {
		return "Tile [coordinates = (" + yCoord + "," + xCoord + "), worked = " + isWorked + ", baseTerrain=" + baseTerrain + ", terrainFeatures=" + terrainFeatures
				+ ", improvement=" + improvement + ", resource=" + resource + "]";
	}

	public int getFoodOutput() {
		int food = foodOutput;
		food += improvement.getFood(this);
		for (WorkedTileEnhancer enhancer : workedBy.getEnhancers()) {
			if (enhancer.hasBaseTerrainFoodBonus(this)) {
				food += 1;
			}
			if (enhancer.hasFoodBonusResource(this)) {
				food += 1;
			}
		}
		return food;
	}

	private double combatModifier = 0;
	private City workedBy;

	public void work(City workedBy) {
		this.isWorked = true;
		this.workedBy = workedBy;
	}

	public void unWork() {
		if (!isWorked) {
			System.out.println("Tile is already un-worked: " + yCoord + "," + xCoord);
		}
		this.isWorked = false;
		this.workedBy = null;
	}

	public boolean isWorked() {
		return this.isWorked;
	}

	public Tile(boolean isCity, int xCoord, int yCoord, Resource resource, BaseTerrain baseTerrain,
			TerrainFeature... terrainFeatures) {
		this.xCoord = xCoord;
		this.yCoord = yCoord;
		processBaseTerrain(baseTerrain);
		processTerrainFeatures(terrainFeatures);
		processTerrainResource(resource);

		if (isCity) {
			if (foodOutput < 2) {
				foodOutput = 2;
			}

			if (productionOutput < 1) {
				productionOutput = 1;
			}
		}
	}

	public void setOwner(Player owner) {
		this.owner = owner;
	}

	public Tile(int xCoord, int yCoord, Resource resource, BaseTerrain baseTerrain, TerrainFeature... terrainFeatures) {
		this(false, xCoord, yCoord, resource, baseTerrain, terrainFeatures);
	}

	private void processTerrainResource(Resource resource) {
		switch (resource) {
		case CATTLE:
			foodOutput += 1;
			break;
		case STONE:
			productionOutput += 1;
			break;
		case MARBLE:
			goldOutput += 2;
			break;
		case COPPER:
			goldOutput += 2;
			break;
		case HORSES:
			productionOutput += 1;
			break;
		case FISH:
			foodOutput += 1;
			break;
		case PEARLS:
			goldOutput += 2;
			break;
		case IVORY:
			goldOutput += 2;
			break;
		case IRON:
			productionOutput += 1;
			break;
		case NONE:
			break;
		case COAL:
			break;
		default:
			throw new RuntimeException("Unhandled tile resource type " + resource);
		}

		this.resource = resource;
	}

	private void processTerrainFeatures(TerrainFeature... terrainFeatures) {
		for (TerrainFeature feature : terrainFeatures) {
			switch (feature) {
			case RIVER:
				hasFreshWater = true;
				break;
			case FOREST:
				foodOutput = 1;
				productionOutput = 1;
				combatModifier = 0.25;
				break;
			case HILL:
				productionOutput = 2;
				foodOutput = 0;
				combatModifier = 0.25;
				break;
			case BORDERS_COAST:
				isCoastal = true;
				break;
			case MOUNTAIN:
				productionOutput = 0;
				foodOutput = 0;
				goldOutput = 0;
				break;
			default:
				throw new RuntimeException("Unhandled terrain feature " + feature);
			}

			this.terrainFeatures.add(feature);
		}
	}

	public Resource getResource() {
		return resource;
	}

	public void setResource(Resource resource) {
		this.resource = resource;
	}

	private void processBaseTerrain(BaseTerrain baseTerrain) {
		switch (baseTerrain) {
		case GRASSLAND:
			foodOutput = 2;
			this.baseTerrain = baseTerrain;
			break;
		case PLAINS:
			foodOutput = 1;
			productionOutput = 1;
			this.baseTerrain = baseTerrain;
			break;
		case COAST:
			foodOutput = 1;
			this.baseTerrain = baseTerrain;
			break;
		case OCEAN:
			foodOutput = 1;
			this.baseTerrain = baseTerrain;
			break;
		case TUNDRA:
			foodOutput = 1;
			break;
		case DESERT:
			break;
		default:
			throw new RuntimeException("Unhandled base terrain type " + baseTerrain);
		}
	}

	public boolean has(Resource resource) {
		return this.resource == resource;
	}

	public boolean isImproved() {
		// TODO Auto-generated method stub
		return improved;
	}

	public void addImprovement(Improvement improvement) {
		if (improvement instanceof WorkBoats) {
			owner.deleteUnit(UnitEnum.WORK_BOAT);
		}
		this.improvement = improvement;
		if (this.resource != Resource.NONE) {
			owner.connect(this.resource);
		}
		improved = true;
	}

	public boolean has(TerrainFeature desiredFeature) {
		for (TerrainFeature feature : terrainFeatures) {
			if (feature == desiredFeature) {
				return true;
			}
		}
		return false;
	}

	public double getCultureOutput() {
		double culture = 0;
		culture += improvement.getCulture(this);
		return culture;
	}
	
	public double getFaithOutput() {
		double faith = 0;
		faith += improvement.getFaith(this);
		return faith;
	}

}
