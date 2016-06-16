package com.merold.civcalcs.city;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.merold.civcalcs.Belief;
import com.merold.civcalcs.Religion;
import com.merold.civcalcs.buildings.Building;
import com.merold.civcalcs.buildings.BuildingEnum;
import com.merold.civcalcs.buildings.Project;
import com.merold.civcalcs.buildings.Shrine;
import com.merold.civcalcs.buildings.Temple;

import static com.merold.civcalcs.buildings.BuildingEnum.*;
import com.merold.civcalcs.buildings.enhancers.WorkedTileEnhancer;
import com.merold.civcalcs.buildings.wonders.AncientWonder;
import com.merold.civcalcs.buildings.wonders.ClassicalWonder;
import com.merold.civcalcs.buildings.wonders.Wonder;
import com.merold.civcalcs.greatworks.GreatWorkOfArt;
import com.merold.civcalcs.greatworks.GreatWorkOfMusic;
import com.merold.civcalcs.greatworks.GreatWorkOfWriting;
import com.merold.civcalcs.player.Player;
import com.merold.civcalcs.socialpolicies.SocialPolicy;
import com.merold.civcalcs.socialpolicies.SocialPolicyUtil;
import com.merold.civcalcs.specialists.Artist;
import com.merold.civcalcs.specialists.Engineer;
import com.merold.civcalcs.specialists.Merchant;
import com.merold.civcalcs.specialists.Musician;
import com.merold.civcalcs.specialists.Scientist;
import com.merold.civcalcs.specialists.Specialist;
import com.merold.civcalcs.specialists.SpecialistSlot;
import com.merold.civcalcs.specialists.Writer;
import com.merold.civcalcs.tiles.BaseTerrain;
import com.merold.civcalcs.tiles.Resource;
import com.merold.civcalcs.tiles.TerrainFeature;
import com.merold.civcalcs.tiles.Tile;
import com.merold.civcalcs.units.Unit;
import com.merold.civcalcs.units.UnitEnum;
import com.merold.civcalcs.util.MathUtil;

public class City {
	private double baseCulture;
	private int baseFood = 0;
	private double baseGAPoints;
	private double baseGEPoints;
	private double baseGMercPoints = 0;
	private double baseGMPoints;
	private double baseGold;
	private double baseGSPoints;
	private double baseGWPoints;
	private double baseProduction = 0;
	private double baseScience;
	private List<Project> buildings = new ArrayList<Project>();
	private double cultureModifier = 0;
	private Buildable currentlyConstructing;
	private List<WorkedTileEnhancer> enhancers = new ArrayList<WorkedTileEnhancer>();
	private double faith = 0;
	private double foodAddedByGrowthModifier;
	private double foodProduced;
	private int[] foodToGrow = { 0, 15, 24, 33, 44, 55, 66, 77, 89, 101, 114, 126, 139, 152, 165, 179, 193, 207, 221,
			235, 249, 264, 279, 294, 309, 324, 340, 355, 371, 387, 403, 419, 435, 452, 468, 485 };
	private double goldModifier = 0;
	private double greatArtistPoints;
	private double greatEngineerPoints = 0;
	private double greatMerchantPoints;
	private double greatMusicianPoints;
	private double greatPeopleModifier;
	private double greatScientistPoints;
	private double greatWriterPoints;
	private boolean holyCity = false;
	private double localHappiness = 0;
	private String name;
	private Player owner;
	private int population = 1;
	private double productionModifier = 0;
	private double scienceModifier = 0;
	private Tile startingTile;
	private double surplusFood = 0;
	private List<Tile> tiles = new ArrayList<Tile>();
	private double totalArtistPoints;
	private double totalCulture = 0;
	private double totalFoodProduced;
	private double totalGold;
	private double totalGreatEngineerPoints = 0;
	private double totalGreatMusicianPoints;
	private double totalMerchantPoints;
	private double totalProduction;
	private double totalScience = 0;
	private double totalScientistPoints;
	private double totalWriterPoints;
	private double tourism = 0;
	private int turnsToPopGrowth = 0;
	private double foodModifier;
	private List<TradeRoute> tradeRoutes = new ArrayList<TradeRoute>();
	private double cityGold;

	public City(String name, Player owner, Tile startingTile, List<Tile> tilesInBorder) {
		this.name = name;
		this.owner = owner;
		if (tilesInBorder != null) {
			this.tiles.addAll(tilesInBorder);
			tilesInBorder.stream().forEach(t -> t.setOwner(owner));
		}
		if (startingTile != null) {
			this.startingTile = startingTile;
			startingTile.setOwner(owner);
			work(startingTile);
		}
		;
	}

	public void addTileInBorder(Tile tile) {
		if (tiles.contains(tile)) {
			throw new RuntimeException("Tile is already in this cities borders.");
		}
		tile.setOwner(owner);
		tiles.add(tile);
	}

	public void build(Buildable buildable) {
		if (buildable instanceof Project) {
			Project building = (Project) buildable;
			if (building instanceof Wonder) {
				owner.complete((Wonder) building);
				owner.recruit(building.createsUnits());
				Building bonusBuilding = building.alsoProvides();
				if (bonusBuilding != null && !hasBuilding(bonusBuilding.getType())) {
					addBuilding(bonusBuilding);
				}
			}
			addBuilding(building);
		} else if (buildable instanceof Unit) {
			owner.recruit((Unit) buildable);
		}
	}

	public boolean builtOn(BaseTerrain baseTerrain) {
		// TODO Auto-generated method stub
		return startingTile.getBaseTerrain() == baseTerrain;
	}

	public boolean builtOn(TerrainFeature... features) {

		if (features == null) {
			return true;
		} else {
			for (TerrainFeature requiredFeature : features) {
				for (TerrainFeature startingFeature : startingTile.getTerrainFeatures()) {
					if (startingFeature == requiredFeature) {
						return true;
					}
				}
			}
		}
		return false;
	}

	public void calculateOutput() {
		tourism = calculateTourism();
		totalProduction = calculateProduction(currentlyConstructing);

		faith = calculateFaith();
		totalScience = calculateScience();
		localHappiness = calculateHappiness();
		totalFoodProduced = calculateFood();
		turnsToPopGrowth = calculateTurnsUntilGrowth();
		totalGold = calculateGold();
		totalCulture = calculateCulture();
		greatPeopleModifier = calculateGreatPeopleModifier();
		totalGreatEngineerPoints = calculateGreatEngineerPoints();
		totalScientistPoints = calculateGreatScientistPoints();
		totalGreatMusicianPoints = calculateGreatMusicianPoints();
		totalArtistPoints = calculateGreatArtistPoints();
		totalWriterPoints = calculateGreatWriterPoints();
		totalMerchantPoints = calculateGreatMerchantPoints();
	}

	private double calculateFaith() {
		double faith = 0;
		faith += calculateFaithFromTerrain();
		faith += calculateFaithFromBuildings();
		return faith;
	}

	private double calculateFaithFromBuildings() {
		double faith = 0;
		for (Project building : buildings) {
			faith += building.getFaithPerTurn();
		}
		return faith;
	}

	private double calculateTourism() {
		double tourism = 0;
		tourism += calculateTourismFromBuildings();
		return tourism;
	}

	private double calculateTourismFromBuildings() {
		return buildings.stream().mapToDouble(c -> c.getTourismPerTurn()).sum();
	}

	public void cancelConstruction() {
		this.currentlyConstructing = null;
	}

	public void completeBuildable() {
		build(currentlyConstructing);
		currentlyConstructing = null;
	}

	public void employ(Specialist specialist) {
		boolean employed = false;
		if (specialist instanceof Merchant) {
			for (Project building : buildings) {
				if (building.hasOpenMerchantSlot()) {
					building.employMerchant((Merchant) specialist);
					employed = true;
					break;
				}
			}
		} else if (specialist instanceof Engineer) {
			for (Project building : buildings) {
				if (building.hasOpenEngineerSlot()) {
					building.employEngineer((Engineer) specialist);
					employed = true;
					break;
				}
			}
		} else if (specialist instanceof Scientist) {
			for (Project building : buildings) {
				if (building.hasOpenScientistSlot()) {
					building.employScientist((Scientist) specialist);
					employed = true;
					break;
				}
			}
		} else if (specialist instanceof Writer) {
			for (Project building : buildings) {
				if (building.hasOpenWriterSlot()) {
					building.employWriter((Writer) specialist);
					employed = true;
					break;
				}
			}
		} else if (specialist instanceof Artist) {
			for (Project building : buildings) {
				if (building.hasOpenArtistSlot()) {
					building.employArtist((Artist) specialist);
					employed = true;
					break;
				}
			}
		} else if (specialist instanceof Musician) {
			for (Project building : buildings) {
				if (building.hasOpenMusicianSlot()) {
					building.employMusician((Musician) specialist);
					employed = true;
					break;
				}
			}
		}
		if (!employed) {
			throw new RuntimeException("Unable to employ specialist.");
		}

	}

	public void foundReligion(Religion catholicism) {
		holyCity = true;

	}

	public double getBaseCulture() {

		return baseCulture;
	}

	public int getBaseFood() {
		return baseFood;
	}

	public double getBaseGAPoints() {
		return baseGAPoints;
	}

	public double getBaseGEPoints() {
		return baseGEPoints;
	}

	public double getBaseGMercPoints() {
		return baseGMercPoints;
	}

	public double getBaseGMPoints() {
		return baseGMPoints;
	}

	public double getBaseGold() {
		return baseGold;
	}

	public double getBaseGSPoints() {
		return baseGSPoints;
	}

	public double getBaseGWPoints() {
		return baseGWPoints;
	}

	public double getBaseProduction() {
		return baseProduction;
	}

	public double getBaseScience() {
		return baseScience;
	}

	public List<Project> getBuildings() {
		return buildings;
	}

	public double getCultureModifier() {
		return cultureModifier;
	}

	public Buildable getCurrentlyConstructing() {
		return currentlyConstructing;
	}

	public List<WorkedTileEnhancer> getEnhancers() {
		return enhancers;
	}

	public double getFaith() {
		return faith;
	}

	public double getFoodAddedByGrowthModifier() {
		return foodAddedByGrowthModifier;
	}

	public double getFoodProduced() {
		return foodProduced;
	}

	public int[] getFoodToGrow() {
		return foodToGrow;
	}

	public double getGoldModifier() {
		return goldModifier;
	}

	public double getGreatArtistPoints() {
		return greatArtistPoints;
	}

	public double getGreatEngineerPoints() {
		return greatEngineerPoints;
	}

	public double getGreatMerchantPoints() {
		// TODO Auto-generated method stub
		return greatMerchantPoints;
	}

	public double getGreatMusicianPoints() {
		return greatMusicianPoints;
	}

	public double getGreatPeopleModifier() {
		return greatPeopleModifier;
	}

	public double getGreatScientistPoints() {
		return greatScientistPoints;
	}

	public double getGreatWriterPoints() {
		return greatWriterPoints;
	}

	public double getLocalHappiness() {
		return localHappiness;
	}

	public String getName() {
		return name;
	}

	public Player getOwner() {
		return owner;
	}

	public int getPopulation() {
		return population;
	}

	public double getProductionFor(Buildable buildable) {
		return calculateProduction(buildable);
	}

	public double getProductionModifier() {
		return productionModifier;
	}

	public double getScienceModifier() {
		return scienceModifier;
	}

	public Tile getStartingTile() {
		return startingTile;
	}

	public double getSurplusFood() {
		return surplusFood;
	}

	public List<Tile> getTiles() {
		return tiles;
	}

	public double getTotalArtistPoints() {
		return totalArtistPoints;
	}

	public double getTotalCulture() {
		return totalCulture;
	}

	public double getTotalFoodProduced() {
		return totalFoodProduced;
	}

	public double getTotalGold() {
		return totalGold;
	}

	public double getTotalGreatEngineerPoints() {
		return totalGreatEngineerPoints;
	}

	public double getTotalGreatMusicianPoints() {
		return totalGreatMusicianPoints;
	}

	public double getTotalMerchantPoints() {
		return totalMerchantPoints;
	}

	public double getTotalProduction() {
		return totalProduction;
	}

	public double getTotalScience() {
		return totalScience;
	}

	public double getTotalScientistPoints() {
		return totalScientistPoints;
	}

	public double getTotalWriterPoints() {
		return totalWriterPoints;
	}

	public double getTourism() {
		return tourism;
	}

	public int getTurnsToPopGrowth() {
		return turnsToPopGrowth;
	}

	public List<Tile> getWorkedTiles() {
		List<Tile> workedTiles = new ArrayList<Tile>();
		for (Tile tile : tiles) {
			if (tile.isWorked()) {
				workedTiles.add(tile);
			}
		}
		return workedTiles;
	}

	public void grow() {
		this.population++;
	}

	public boolean hasAtLeastOneImprovedTileWith(Resource... resources) {
		if (resources == null) {
			return true;
		}
		for (Resource resource : resources) {
			for (Tile tile : tiles) {
				if (tile.has(resource) && tile.isImproved()) {
					return true;
				}
			}
		}
		return false;
	}

	public boolean hasBuilding(BuildingEnum desiredBuilding) {
		for (Project building : buildings) {
			if (building.getType() == desiredBuilding) {
				return true;
			}
		}
		return false;
	}

	public boolean isConstructing(BuildingEnum wonder) {
		if (currentlyConstructing instanceof Wonder) {
			if (((Wonder) currentlyConstructing).getType() == wonder) {
				return true;
			}
		}
		return false;
	}

	public boolean isHolyCity() {
		return holyCity;
	}

	public void printOutput() {
		calculateOutput();
		System.out.println(name + " is producing: ");
		printOutputLine(MathUtil.round(totalFoodProduced, 2), "Food");
		printOutputLine(MathUtil.round(totalProduction, 2), "Production");
		printOutputLine(MathUtil.round(totalGold, 2), "Gold");
		printOutputLine(MathUtil.round(totalScience, 2), "Science");
		printOutputLine(faith, "Faith");
		printOutputLine(tourism, "Tourism");
		printOutputLine(Math.floor(totalCulture), "Culture");
		printOutputLine(MathUtil.round(localHappiness, 2), "Local Happiness");
		printOutputLine(totalGreatEngineerPoints, "Great Engineer Points");
		printOutputLine(totalMerchantPoints, "Great Merchant Points");
		printOutputLine(totalScientistPoints, "Great Scientist Points");
		printOutputLine(totalArtistPoints, "Great Artist Points");
		printOutputLine(totalGreatMusicianPoints, "Great Musician Points");
		printOutputLine(totalWriterPoints, "Great Writer Points");
		printOutputLine(turnsToPopGrowth, "turns until population growth");

	}

	public void printWorkedTiles() {
		tiles.stream().filter(t -> t.isWorked()).forEach(t -> System.out.println(t.toString()));
	}

	public void startBuilding(BuildingEnum buildable) {
		currentlyConstructing = buildable.create(this, owner);
	}

	public void startBuilding(UnitEnum buildable) {
		currentlyConstructing = buildable.create(owner);
	}

	public void unEmploy(Specialist specialist) {
		boolean employed = false;
		if (specialist instanceof Merchant) {
			for (Project building : buildings) {
				if (building.hasFilledMerchantSlot()) {
					building.unemployMerchant();
					employed = true;
					break;
				}
			}
		} else if (specialist instanceof Engineer) {
			for (Project building : buildings) {
				if (building.hasFilledEngineerSlot()) {
					building.unemployEngineer();
					employed = true;
					break;
				}
			}
		} else if (specialist instanceof Scientist) {
			for (Project building : buildings) {
				if (building.hasFilledScientistSlot()) {
					building.unemployScientist();
					employed = true;
					break;
				}
			}
		}
		if (!employed) {
			throw new RuntimeException("Unable to employ specialist.");
		}

	}

	public void unWork(Tile tile) {
		if (tiles.contains(tile) || startingTile == tile) {
			tile.unWork();
		} else {
			throw new RuntimeException(name + " can't un-work a tile unless it is within the city borders");
		}
	}

	public void work(Tile tile) {
		if (tiles.contains(tile) || startingTile == tile) {
			tile.work(this);
		} else {
			throw new RuntimeException(name + " can't work a tile unless it is within the city borders");
		}
	}

	private void addBuilding(Project building) {
		if (building.getEnhancer() != null) {
			enhancers.add(building.getEnhancer());
		}
		buildings.add(building);
	}

	private double calculateCulture() {
		baseCulture = 0;
		cultureModifier = calculateCultureModifier();

		double cultureFromBuildings = calculateCultureFromBuildings();
		double cultureFromPolicies = calculateCultureFromPolicies();
		double cultureFromSpecialists = calculateCultureFromSpecialists();
		double cultureFromReligion = calculateCultureFromReligion();
		double cultureFromTerrain = calculateCultureFromTerrain();
		baseCulture += cultureFromBuildings;
		baseCulture += cultureFromPolicies;
		baseCulture += cultureFromSpecialists;
		baseCulture += cultureFromReligion;
		baseCulture += cultureFromTerrain;

		return baseCulture * (1 + cultureModifier);
	}

	private double calculateCultureFromReligion() {
		double culture = 0;
		if (hasBuilding(BuildingEnum.PALACE) && owner.believes(Belief.GOD_KING)) {
			culture += 1;
		}
		return culture;
	}

	private double calculateCultureFromPolicies() {
		double culture = 0;
		if (owner.hasAdopted(SocialPolicy.TRADITION) && hasBuilding(BuildingEnum.PALACE)) {
			culture += 3;
		}

		if (owner.hasAdopted(SocialPolicy.LIBERTY)) {
			culture += 1;
		}
		return culture;
	}

	private double calculateCultureFromSpecialists() {
		double culture = 0;
		for (Project building : buildings) {
			List<SpecialistSlot> writers = building.getWriters();
			List<SpecialistSlot> artists = building.getArtists();
			List<SpecialistSlot> musicians = building.getMusicians();

			for (SpecialistSlot specialistSlot : writers) {
				if (specialistSlot.isFilled()) {
					culture += 3;
				}
			}

			for (SpecialistSlot specialistSlot : artists) {
				if (specialistSlot.isFilled()) {
					culture += 3;
				}
			}

			for (SpecialistSlot specialistSlot : musicians) {
				if (specialistSlot.isFilled()) {
					culture += 3;
				}
			}
		}
		return culture;

	}

	private double calculateCultureFromTerrain() {
		double culture = 0;
		for (Tile tile : tiles) {
			if (tile.isWorked()) {
				culture += tile.getCultureOutput();
			}
		}
		return culture;
	}

	private double calculateFaithFromTerrain() {
		double faith = 0;
		for (Tile tile : tiles) {
			if (tile.isWorked()) {
				faith += tile.getFaithOutput();
			}
		}
		return faith;
	}

	private double calculateCultureFromBuildings() {
		double culture = 0;
		for (Project building : buildings) {
			culture += building.getBaseCulturePerTurn();
		}
		return culture;
	}

	private double calculateCultureModifier() {
		double modifier = 0;
		for (Project building : buildings) {
			modifier += building.getCultureModifier();
		}
		if (owner.hasCompleted(SISTINE_CHAPEL)) {
			modifier += 0.25;
		}
		return modifier;
	}

	private double calculateFood() {
		baseFood = 0;
		foodProduced = 0;
		turnsToPopGrowth = 0;
		surplusFood = 0;


		double foodFromTerrain = calculateFoodFromTerrain();
		double foodFromBuildings = calculateFoodFromBuildings();
		double foodConsumed = foodConsumed();
		double growthModifier = calculateGrowthModifier();
		foodModifier = calculateFoodModifier();

		baseFood += foodFromTerrain;
		baseFood += foodFromBuildings;

		foodProduced = (baseFood * (1 + foodModifier)) - foodConsumed;
		foodAddedByGrowthModifier = (foodProduced > 0) ? foodProduced * growthModifier : 0;

		double totalFoodProduced = foodProduced + foodAddedByGrowthModifier;
		return totalFoodProduced;
	}

	public double getFoodModifier() {
		return foodModifier;
	}

	private double calculateFoodFromBuildings() {
		double food = 0;

		for (Project building : buildings) {
			food += building.getFoodPerTurn();
		}

		return food;
	}

	private double calculateFoodFromTerrain() {
		double food = 0;

		food += startingTile.getFoodOutput();
		for (Tile tile : tiles) {
			if (tile.isWorked()) {
				food += tile.getFoodOutput();
			}
		}

		if (owner.hasAdopted(SocialPolicy.LANDED_ELITE) && this.hasBuilding(BuildingEnum.PALACE)) {
			food += 2;
		}
		return food;
	}

	private double calculateFoodModifier() {
		double modifier = 0;
		/* Calculate food from global Wonders. */
		if (owner.hasCompleted(BuildingEnum.TEMPLE_OF_ARTEMIS)) {
			modifier += 0.1;
		}
		return modifier;
	}

	private double calculateGold() {
		baseGold = 0;
		double goldFromTiles = calculateGoldFromTiles();
		double goldFromBuildings = calculateGoldFromBuildings();
		double goldFromReligions = calculateGoldFromReligion();
		double goldFromSpecialists = calculateGoldFromSpecialists();
		goldModifier = calculateGoldModifier();

		baseGold += goldFromTiles;
		baseGold += goldFromBuildings;
		baseGold += goldFromSpecialists;
		baseGold += goldFromReligions;
		cityGold = baseGold * (1 + goldModifier);
		double goldFromTradeRoutes = calculateGoldFromTradeRoutes();
		return cityGold + goldFromTradeRoutes;
	}

	public double getCityGold() {
		return cityGold;
	}

	private double calculateGoldFromTradeRoutes() {
		double gold = 0;
		gold = tradeRoutes.stream().filter(t -> !t.isAvailable()).mapToDouble(t -> t.getGoldYield()).sum();
		return gold;
	}

	private double calculateGoldFromBuildings() {
		double goldFromBuildings = 0;
		for (Project building : buildings) {
			goldFromBuildings += building.getBaseGoldPerTurn();
		}
		return goldFromBuildings;
	}

	private double calculateGoldFromReligion() {
		double goldFromReligions = 0;
		if (hasBuilding(BuildingEnum.PALACE) && owner.believes(Belief.GOD_KING)) {
			goldFromReligions += 1;
		}
		return goldFromReligions;
	}

	private double calculateGoldFromSpecialists() {
		double goldFromSpecialists = 0;
		for (Project building : buildings) {
			List<SpecialistSlot> merchants = building.getMerchants();

			for (SpecialistSlot merchantSlot : merchants) {
				if (merchantSlot.isFilled()) {
					goldFromSpecialists += 2;
				}
			}
		}
		return goldFromSpecialists;
	}

	private double calculateGoldFromTiles() {
		double goldFromTiles = 0;
		for (Tile tile : tiles) {
			if (tile.isWorked()) {
				goldFromTiles += tile.getGoldOutput();
				if (tile.getGoldOutput() >= 1 && owner.isInGoldenAge()) {
					goldFromTiles += 1;
				}
			}
		}

		goldFromTiles += calculateStartingTileGold();
		return goldFromTiles;
	}

	private double calculateGoldModifier() {
		double modifier = 0;
		for (Project building : buildings) {
			modifier += building.getGoldModifier();
		}

		if (owner.hasAdopted(SocialPolicy.COMMERCE) && hasBuilding(BuildingEnum.PALACE)) {
			modifier += 0.25;
		}
		return modifier;
	}

	private double calculateGreatArtistPoints() {
		baseGAPoints = 0;
		for (Project building : buildings) {
			baseGAPoints += building.getGreatArtistPoints();
		}
		return baseGAPoints * (1 + greatPeopleModifier);
	}

	private double calculateGreatEngineerPoints() {
		baseGEPoints = 0;
		for (Project building : buildings) {
			baseGEPoints += building.getGreatEngineerPoints();
		}
		return baseGEPoints * (1 + greatPeopleModifier);
	}

	private double calculateGreatMerchantPoints() {
		baseGMercPoints = 0;
		for (Project building : buildings) {
			baseGMercPoints += building.getGreatMerchantPoints();
		}
		return baseGMercPoints * (1 + greatPeopleModifier);
	}

	private double calculateGreatMusicianPoints() {
		baseGMPoints = 0;
		for (Project building : buildings) {
			baseGMPoints += building.getGreatMusicianPoints();
		}
		return baseGMPoints * (1 + greatPeopleModifier);
	}

	private double calculateGreatPeopleModifier() {
		double modifier = 0;
		for (Project building : buildings) {
			modifier += building.getGreatPeopleModifier();
		}

		if (owner.hasCompleted(LEANING_TOWER_OF_PISA)) {
			modifier += 0.25;
		}

		if (owner.hasAdopted(SocialPolicy.AVANT_GARDE)) {
			modifier += 0.25;
		}
		return modifier;
	}

	private double calculateGreatScientistPoints() {
		baseGSPoints = 0;
		for (Project building : buildings) {
			baseGSPoints += building.getGreatScientistPoints();
		}
		double scientistModifier = 0;
		if (owner.hasAdopted(SocialPolicy.HUMANISM)) {
			scientistModifier += 0.25;
		}

		return baseGSPoints * (1 + greatPeopleModifier + scientistModifier);
	}

	private double calculateGreatWriterPoints() {
		baseGWPoints = 0;
		for (Project building : buildings) {
			baseGWPoints += building.getGreatWriterPoints();
		}
		return baseGWPoints * (1 + greatPeopleModifier);
	}

	private double calculateGrowthModifier() {
		double modifier = 0;

		/* Calculate food from Social policies. */
		if (owner.hasAdopted(SocialPolicy.LANDED_ELITE) && this.hasBuilding(BuildingEnum.PALACE)) {
			modifier += 0.1;
		}
		if (SocialPolicyUtil.playerHasCompletedTradition(owner)) {
			modifier += 0.15;
		}
		return modifier;
	}

	private double calculateHappiness() {
		localHappiness = 0;
		double happinessFromReligion = calculateHappinessFromReligion();
		double happinessFromBuildings = calculateHappinessFromBuildings();
		localHappiness += happinessFromBuildings;
		localHappiness += happinessFromReligion;
		return localHappiness;
	}

	private double calculateHappinessFromBuildings() {
		double happiness = 0;
		for (Project building : buildings) {
			happiness += building.getHappinessPerTurn();
		}
		return happiness;
	}

	private double calculateHappinessFromReligion() {
		double happinessFromReligions = 0;

		return happinessFromReligions;
	}

	private double calculateProduction(Buildable constructing) {
		/* Reset internal production state. */
		baseProduction = 0;
		double productionFromTerrain = calculateProductionFromTerrain();
		double productionFromBuildings = calculateProductionFromBuildings();
		double productionFromReligion = calculateProductionFromReligion();
		double productionFromSpecialists = calculateProductionFromSpecialists();

		productionModifier = calculateProductionModifier(constructing);

		baseProduction += productionFromTerrain;
		baseProduction += productionFromBuildings;
		baseProduction += productionFromReligion;
		baseProduction += productionFromSpecialists;

		return baseProduction * (productionModifier + 1);
	}

	private double calculateProductionFromBuildings() {
		double production = 0;

		for (Project building : buildings) {
			production += building.getBaseProduction();
		}
		return production;
	}

	private double calculateProductionFromReligion() {
		double production = 0;
		if (hasBuilding(BuildingEnum.PALACE) && owner.believes(Belief.GOD_KING)) {
			production += 1;
		}
		return production;
	}

	private double calculateScienceFromReligion() {
		double science = 0;
		if (hasBuilding(BuildingEnum.PALACE) && owner.believes(Belief.GOD_KING)) {
			science += 1;
		}
		return science;
	}

	private double calculateProductionFromSpecialists() {
		double productionFromSpecialists = 0;
		for (Project building : buildings) {
			List<SpecialistSlot> engineers = building.getEngineers();

			for (SpecialistSlot engineerSlot : engineers) {
				if (engineerSlot.isFilled()) {
					productionFromSpecialists += 2;
				}
			}
		}
		return productionFromSpecialists;
	}

	private double calculateProductionFromTerrain() {
		double production = startingTile.getProductionOutput();
		if (owner.hasAdopted(SocialPolicy.MARITIME_INFRASTRUCTURE)) {
			production += 3;
		}
		for (Tile tile : tiles) {
			if (tile.isWorked()) {
				production += tile.getProductionOutput();
			}
		}
		return production;
	}

	private double calculateProductionModifier(Buildable constructing) {
		double modifier = 0;
		for (Project building : buildings) {
			modifier += building.getProductionModifier();
		}

		/* Add production from Social Policies. */
		if (owner.hasAdopted(SocialPolicy.ARISTOCRACY) && isCapital() && constructing instanceof Wonder) {
			modifier += 0.15;
		}

		if (owner.hasAdopted(SocialPolicy.PIETY)
				&& (constructing instanceof Shrine || constructing instanceof Temple)) {
			modifier += 1;
		}

		if (owner.hasConnected(Resource.MARBLE)
				&& (constructing instanceof AncientWonder || constructing instanceof ClassicalWonder)) {

			modifier += 0.15;
		}

		/* Add bonus production from golden age. */
		if (owner.isInGoldenAge()) {
			modifier += 0.2;
		}

		if (this.hasBuilding(WINDMILL) && constructing instanceof Building) {
			modifier += 0.1;
		}
		return modifier;
	}

	private double calculateScience() {
		baseScience = 0;
		scienceModifier = calculateScienceModifier();

		double scienceFromTerrain = calculateScienceFromTerrain();
		double scienceFromBuildings = calculateScienceFromBuildings();
		double scienceFromSpecialists = calculateScienceFromSpecialists();
		double scienceFromPopulation = population;
		double extraFromPopulation = calculateExtraScienceFromPopulation();
		double scienceFromReligion = calculateScienceFromReligion();

		baseScience += scienceFromTerrain;
		baseScience += scienceFromBuildings;
		baseScience += scienceFromSpecialists;
		baseScience += scienceFromPopulation;
		baseScience += extraFromPopulation;
		baseScience += scienceFromReligion;

		return baseScience * (1 + scienceModifier);
	}

	private double calculateExtraScienceFromPopulation() {
		double extraScienceFromPopulation = 0;
		if (this.hasBuilding(BuildingEnum.LIBRARY)) {
			extraScienceFromPopulation += population / 2.0;
		}

		if (this.hasBuilding(BuildingEnum.PUBLIC_SCHOOL)) {
			extraScienceFromPopulation += population / 2.0;
		}
		return extraScienceFromPopulation;
	}

	private double calculateScienceFromBuildings() {
		double science = 0;
		for (Project building : buildings) {
			science += building.getBaseSciencePerTurn();
		}
		return science;
	}

	private double calculateScienceFromSpecialists() {
		double scienceFromSpecialists = 0;
		for (Project building : buildings) {
			List<SpecialistSlot> scientists = building.getScientists();

			for (SpecialistSlot scientistSlot : scientists) {
				if (scientistSlot.isFilled()) {
					scienceFromSpecialists += 3;
				}
			}
			if (owner.hasAdopted(SocialPolicy.RATIONALISM)) {
				scienceFromSpecialists += building.getArtists().stream().filter(t -> t.isFilled()).count() * 2;
				scienceFromSpecialists += building.getWriters().stream().filter(t -> t.isFilled()).count() * 2;
				scienceFromSpecialists += building.getMusicians().stream().filter(t -> t.isFilled()).count() * 2;
				scienceFromSpecialists += building.getEngineers().stream().filter(t -> t.isFilled()).count() * 2;
				scienceFromSpecialists += building.getScientists().stream().filter(t -> t.isFilled()).count() * 2;
				scienceFromSpecialists += building.getMerchants().stream().filter(t -> t.isFilled()).count() * 2;
			}

		}
		return scienceFromSpecialists;
	}

	private double calculateScienceFromTerrain() {
		double science = 0;
		for (Tile tile : getWorkedTiles()) {
			science += tile.getScienceOutput();
		}
		return science;
	}

	private double calculateScienceModifier() {
		double modifier = 0;
		for (Project building : buildings) {
			modifier += building.getScienceModifier();
		}
		return modifier;
	}

	private double calculateStartingTileGold() {
		double startingTileGold = startingTile.getGoldOutput();
		if (owner.hasAdopted(SocialPolicy.MONARCHY) && hasBuilding(BuildingEnum.PALACE)) {
			startingTileGold += Math.floor(population / 2.0);
		}
		if (startingTileGold >= 1 && owner.isInGoldenAge()) {
			startingTileGold += 1;
		}
		return startingTileGold;
	}

	private int calculateTurnsUntilGrowth() {
		if (hasBuilding(AQUEDUCT)) {
			surplusFood += foodToGrow[population - 1] * 0.4;
		}
		return (int) Math.ceil((foodToGrow[population] - surplusFood) / totalFoodProduced);
	}

	private double foodConsumed() {
		double specialists = getNumberOfSpecialists();
		double citizens = population - specialists;
		double foodConsumed = citizens * 2;
		if (owner.hasAdopted(SocialPolicy.CIVIL_SOCIETY)) {
			foodConsumed += specialists;
		} else {
			foodConsumed += specialists * 2;
		}

		return foodConsumed;
	}

	private double getNumberOfSpecialists() {
		double num = 0;
		num += buildings.stream().filter(t -> !t.getEngineers().isEmpty()).flatMap(t -> t.getEngineers().stream())
				.filter(t -> t.isFilled()).count();
		num += buildings.stream().filter(t -> !t.getArtists().isEmpty()).flatMap(t -> t.getArtists().stream())
				.filter(t -> t.isFilled()).count();
		num += buildings.stream().filter(t -> !t.getWriters().isEmpty()).flatMap(t -> t.getWriters().stream())
				.filter(t -> t.isFilled()).count();
		num += buildings.stream().filter(t -> !t.getMusicians().isEmpty()).flatMap(t -> t.getMusicians().stream())
				.filter(t -> t.isFilled()).count();
		num += buildings.stream().filter(t -> !t.getScientists().isEmpty()).flatMap(t -> t.getScientists().stream())
				.filter(t -> t.isFilled()).count();
		num += buildings.stream().filter(t -> !t.getMerchants().isEmpty()).flatMap(t -> t.getMerchants().stream())
				.filter(t -> t.isFilled()).count();
		return num;
	}

	private boolean isCapital() {
		for (Project building : buildings) {
			if (building.getType() == BuildingEnum.PALACE) {
				return true;
			}
		}
		return false;
	}

	private void printOutputLine(double output, String label) {
		if (output > 0) {
			System.out.println("\t" + output + " " + label);
		}
	}

	public double getNumDifferentResources() {
		return 0;
	}

	public List<TradeRoute> getTradeRoutes() {
		return tradeRoutes;
	}

	public void addAvailableTradeRoute(TargetTradeCity targetCity, RouteType type) {
		tradeRoutes.add(new TradeRoute(this, targetCity, type));
	}

	public void addGreatWorkOfWriting() {
		buildings.stream().filter(b -> b.hasOpenGreatWorkOfWritingSlot()).findFirst().get()
				.addGreatWorkOfWriting(new GreatWorkOfWriting());
	}

	public void addGreatWorkOfArt() {
		buildings.stream().filter(b -> b.hasOpenGreatWorkOfArtSlot()).findFirst().get()
				.addGreatWorkOfArt(new GreatWorkOfArt());
	}

	public void addGreatWorkOfMusic() {
		buildings.stream().filter(b -> b.hasOpenGreatWorkOfMusicSlot()).findFirst().get()
				.addGreatWorkOfMusic(new GreatWorkOfMusic());
	}

	public double getTradeRouteGoldFromBuildings(TradeRoute route) {
		return buildings.stream().mapToDouble(t -> t.getTradeRouteGold(route)).sum();
	}
}
