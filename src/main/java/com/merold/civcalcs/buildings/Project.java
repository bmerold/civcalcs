package com.merold.civcalcs.buildings;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.merold.civcalcs.buildings.enhancers.WorkedTileEnhancer;
import com.merold.civcalcs.buildings.nationalwonders.NationalWonder;
import com.merold.civcalcs.buildings.wonders.Wonder;
import com.merold.civcalcs.city.Buildable;
import com.merold.civcalcs.city.City;
import com.merold.civcalcs.city.TradeRoute;
import com.merold.civcalcs.greatworks.GreatWorkOfArt;
import com.merold.civcalcs.greatworks.GreatWorkOfMusic;
import com.merold.civcalcs.greatworks.GreatWorkOfWriting;
import com.merold.civcalcs.greatworks.GreatWorkSlot;
import com.merold.civcalcs.player.Player;
import com.merold.civcalcs.specialists.Artist;
import com.merold.civcalcs.specialists.Engineer;
import com.merold.civcalcs.specialists.Merchant;
import com.merold.civcalcs.specialists.Musician;
import com.merold.civcalcs.specialists.Scientist;
import com.merold.civcalcs.specialists.SpecialistSlot;
import com.merold.civcalcs.specialists.Writer;
import com.merold.civcalcs.tiles.Tile;
import com.merold.civcalcs.units.Unit;

public class Project implements Buildable {

	private double hammerCost;
	private BuildingEnum name;
	protected City city;
	protected double culturePerTurn;
	protected double cultureModifier;
	protected double defense;
	protected WorkedTileEnhancer enhancer;
	protected double faithPerTurn;
	protected double foodPerTurn;
	protected double goldPerTurn;
	protected double goldModifier;
	protected double greatArtistPoints;
	protected double greatEngineerPoints;
	protected int greatMerchantPoints;
	protected double greatMusicianPoints;
	protected double greatPeopleModifier;
	protected int greatScientistPoints;
	protected int greatWorkOfArtSlots;
	protected int greatWorkOfWritingSlots;
	protected double greatWriterPoints;
	protected double growthModifier;
	protected double happinessPerTurn;
	protected int hitPoints;
	protected double maintenancePerTurn;
	protected Player owner;
	protected double productionPerTurn;
	protected double productionModifier;
	protected double sciencePerTurn;
	protected double scienceModifier;
	protected BuildingEnum requiredBuilding;
	protected int writerSlots;
	protected int scientistSlots;
	protected int merchantSlots;
	protected int engineerSlots;
	protected int artistSlots;
	protected int musicianSlots;
	protected List<GreatWorkSlot> gwofArtSlots = new ArrayList<GreatWorkSlot>();
	protected List<GreatWorkSlot> gwofWritingSlots = new ArrayList<GreatWorkSlot>();
	protected List<GreatWorkSlot> gwofMusicSlots = new ArrayList<GreatWorkSlot>();
	protected List<SpecialistSlot> writers = new ArrayList<SpecialistSlot>();
	protected List<SpecialistSlot> scientists = new ArrayList<SpecialistSlot>();
	protected List<SpecialistSlot> merchants = new ArrayList<SpecialistSlot>();
	protected List<SpecialistSlot> engineers = new ArrayList<SpecialistSlot>();
	protected List<SpecialistSlot> artists = new ArrayList<SpecialistSlot>();
	protected List<SpecialistSlot> musicians = new ArrayList<SpecialistSlot>();
	protected int greatWorkOfMusicSlots;
	protected double tourism;
	protected double greatWorkTourismModifier;
	protected double cultureConversionToTourismRate;
	private double tradeRouteBonus;
	protected double tourismModifier;

	public double getTourismModifier() {
		return tourismModifier;
	}

	public int getGreatWorkOfMusicSlots() {
		return greatWorkOfMusicSlots;
	}
	
	public List<SpecialistSlot> getSpecialists() {
		List<SpecialistSlot> specialists = new ArrayList<SpecialistSlot>();
		specialists.addAll(merchants);
		specialists.addAll(scientists);
		specialists.addAll(engineers);
		specialists.addAll(artists);
		specialists.addAll(musicians);
		specialists.addAll(writers);
		return specialists;
	}
	
	public List<GreatWorkSlot> getGreatWorks() {
		List<GreatWorkSlot> greatWorks = new ArrayList<GreatWorkSlot>();
		greatWorks.addAll(gwofArtSlots);
		greatWorks.addAll(gwofMusicSlots);
		greatWorks.addAll(gwofWritingSlots);
		return greatWorks;
	}

	public BuildingEnum getRequiredBuilding() {
		return requiredBuilding;
	}

	protected Project(BuildingEnum name, City city, Player owner, double hammerCost) {
		this.hammerCost = hammerCost;
		this.name = name;
		this.city = city;
		this.owner = owner;
	}

	public Building alsoProvides() {
		return null;
	}

	public List<Unit> createsUnits() {
		return new ArrayList<Unit>();
	}

	public double getBaseCulturePerTurn() {
		return culturePerTurn;
	}

	public double getPotentialCulturePerTurn() {
		return culturePerTurn * (1 + cultureModifier + city.getCultureModifier())
				+ (city.getBaseCulture() * cultureModifier);
	}

	public double getDefense() {
		return defense;
	}

	public WorkedTileEnhancer getEnhancer() {
		return enhancer;
	}

	public double getFaithPerTurn() {
		return faithPerTurn;
	}

	public double getFoodAddedByGrowthModifier() {
		return 0;
	}

	public double getFoodPerTurn() {
		return foodPerTurn;
	}

	public double getGoldPerTurn() {
		double gold = getBaseGoldPerTurn();
		if (enhancer != null) {
			gold += city.getTiles().stream().filter(t -> t.isWorked()).filter(t -> enhancer.hasGoldBonusResource(t))
					.count() * 2;
			gold += city.getTiles().stream().filter(t -> t.isWorked()).filter(t -> enhancer.hasProductionAndGoldBonusResource(t))
					.count();
		}
		return gold * (1 + goldModifier + city.getGoldModifier()) + (city.getBaseGold() * goldModifier);
	}

	public double getBaseGoldPerTurn() {
		return goldPerTurn;
	}

	public double getGreatArtistPoints() {
		double points = greatArtistPoints;
		for (SpecialistSlot slot : artists) {
			if (slot.isFilled()) {
				points += 3;
			}
		}
		return points;
	}

	public double getGreatEngineerPoints() {
		double points = greatEngineerPoints;
		for (SpecialistSlot slot : engineers) {
			if (slot.isFilled()) {
				points += 3;
			}
		}
		return points;
	}
	
	public double getPotentialGreatEngineerPoints() {
		double basePoints = getGreatEngineerPoints();
		return (basePoints * (1 + city.getGreatPeopleModifier() + getPotentialGreatPeopleModifier())) + city.getBaseGEPoints() * getPotentialGreatPeopleModifier();
	}
	
	public double getPotentialGreatScientistPoints() {
		double basePoints = getGreatScientistPoints();
		return (basePoints * (1 + city.getGreatPeopleModifier() + getPotentialGreatPeopleModifier())) + city.getBaseGSPoints() * getPotentialGreatPeopleModifier();
	}
	
	public double getPotentialGreatMerchantPoints() {
		double basePoints = getGreatMerchantPoints();
		return (basePoints * (1 + city.getGreatPeopleModifier() + getPotentialGreatPeopleModifier())) + city.getBaseGMercPoints() * getPotentialGreatPeopleModifier();
	}
	
	public double getPotentialGreatArtistPoints() {
		double basePoints = getGreatArtistPoints();
		return (basePoints * (1 + city.getGreatPeopleModifier() + getPotentialGreatPeopleModifier())) + city.getBaseGAPoints() * getPotentialGreatPeopleModifier();
	}
	
	protected double getPotentialGreatPeopleModifier() {
		return greatPeopleModifier;
	}
	
	public double getPotentialGreatMusicianPoints() {
		double basePoints = getGreatMusicianPoints();
		return (basePoints * (1 + city.getGreatPeopleModifier() + getPotentialGreatPeopleModifier())) + city.getBaseGMPoints() * getPotentialGreatPeopleModifier();
	}
	
	public double getPotentialGreatWriterPoints() {
		double basePoints = getGreatWriterPoints();
		return (basePoints * (1 + city.getGreatPeopleModifier() + getPotentialGreatPeopleModifier())) + city.getBaseGWPoints() * getPotentialGreatPeopleModifier();
	}

	public int getGreatMerchantPoints() {
		int points = greatMerchantPoints;
		for (SpecialistSlot slot : merchants) {
			if (slot.isFilled()) {
				points += 3;
			}
		}
		return points;
	}

	public double getGreatMusicianPoints() {
		double points = greatMusicianPoints;
		for (SpecialistSlot slot : musicians) {
			if (slot.isFilled()) {
				points += 3;
			}
		}
		return points;
	}

	public double getGreatPeopleModifier() {
		return greatPeopleModifier;
	}

	public double getGreatScientistPoints() {
		double points = greatScientistPoints;
		for (SpecialistSlot slot : scientists) {
			if (slot.isFilled()) {
				points += 3;
			}
		}
		return points;
	}

	public int getGreatWorkOfArtSlots() {
		return greatWorkOfArtSlots;
	}

	public int getGreatWorkOfWritingSlots() {
		return greatWorkOfWritingSlots;
	}

	public double getGreatWriterPoints() {
		double points = greatWriterPoints;
		for (SpecialistSlot slot : writers) {
			if (slot.isFilled()) {
				points += 3;
			}
		}
		return points;
	}

	public double getGrowthModifier() {
		return growthModifier;
	}

	public double getHammerCost() {
		return hammerCost;
	}

	public double getHappinessPerTurn() {
		return happinessPerTurn;
	}

	public int getHitPoints() {
		return hitPoints;
	}

	public double getMaintenancePerTurn() {
		return maintenancePerTurn;
	}

	public String getName() {
		return name.toString();
	}

	public BuildingEnum getType() {
		return name;
	}

	public City getOwner() {
		return city;
	}

	public double getBaseProduction() {
		return productionPerTurn;
	}

	public double getPotentialProductionPerTurn() {
		double productionAddedByEnhancer = 0;
		if (enhancer != null) {
			productionAddedByEnhancer += city.getTiles().stream().filter(t -> t.isWorked()).filter(t -> enhancer.hasProductionBonusResource(t))
					.count();
			productionAddedByEnhancer += city.getTiles().stream().filter(t -> t.isWorked()).filter(t -> enhancer.hasTerrainFeatureProductionBonus(t))
					.count();
			productionAddedByEnhancer += city.getTiles().stream().filter(t -> t.isWorked()).filter(t -> enhancer.hasProductionAndGoldBonusResource(t))
					.count();
		}
		return (getBaseProduction() + productionAddedByEnhancer) * (1 + productionModifier + city.getProductionModifier())
				+ (city.getBaseProduction() * productionModifier);
	}

	public double getSciencePerTurn() {
		return getBaseSciencePerTurn() * (1 + scienceModifier + city.getScienceModifier())
				+ (city.getBaseScience() * scienceModifier);
	}
	
	public double getPotentialSciencePerTurn() {
		return getBaseSciencePerTurn() * (1 + scienceModifier + city.getScienceModifier())
				+ (city.getBaseScience() * scienceModifier);
	}

	public double getBaseSciencePerTurn() {
		return sciencePerTurn;
	}

	public void setDefense(double defense) {
		this.defense = defense;
	}

	public double getCultureModifier() {
		return cultureModifier;
	}

	public City getCity() {
		return city;
	}

	public double getGoldModifier() {
		return goldModifier;
	}

	public double getProductionModifier() {
		return productionModifier;
	}

	public double getScienceModifier() {
		return scienceModifier;
	}

	public Map<Outputs, Double> getOutputs() {
		Map<Outputs, Double> outputs = new HashMap<>();
		for (Outputs key : Outputs.values()) {
			outputs.put(key, (double) 0);
		}
		Building also = alsoProvides();
		populateOutputMap(outputs, this);
		if (also != null) {
			populateOutputMap(outputs, also);
		}
		return outputs;
	}

	private void populateOutputMap(Map<Outputs, Double> outputs, Project building) {
		outputs.put(Outputs.CULTURE, outputs.get(Outputs.CULTURE) + building.getPotentialCulturePerTurn());
		outputs.put(Outputs.GOLD, outputs.get(Outputs.GOLD) + building.getGoldPerTurn());
		outputs.put(Outputs.SCIENCE, outputs.get(Outputs.SCIENCE) + building.getPotentialSciencePerTurn());
		outputs.put(Outputs.PRODUCTION, outputs.get(Outputs.PRODUCTION) + building.getPotentialProductionPerTurn());
		outputs.put(Outputs.FAITH, outputs.get(Outputs.FAITH) + building.getFaithPerTurn());
		outputs.put(Outputs.FOOD, outputs.get(Outputs.FOOD) + building.getPotentialFoodOutput());
		outputs.put(Outputs.TOURISM, outputs.get(Outputs.TOURISM) + building.getPotentialTourismPerTurn());
		outputs.put(Outputs.GROWTH_FOOD, outputs.get(Outputs.GROWTH_FOOD) + building.getFoodAddedByGrowthModifier());
		outputs.put(Outputs.HAPPINESS, outputs.get(Outputs.HAPPINESS) + building.getHappinessPerTurn());
		outputs.put(Outputs.GA_POINTS, outputs.get(Outputs.GA_POINTS) + building.getPotentialGreatArtistPoints());
		outputs.put(Outputs.GE_POINTS, outputs.get(Outputs.GE_POINTS) + building.getPotentialGreatEngineerPoints());
		outputs.put(Outputs.GMERCH_POINTS, outputs.get(Outputs.GMERCH_POINTS) + building.getPotentialGreatMerchantPoints());
		outputs.put(Outputs.GMUSC_POINTS, outputs.get(Outputs.GMUSC_POINTS) + building.getPotentialGreatMusicianPoints());
		outputs.put(Outputs.GS_POINTS, outputs.get(Outputs.GS_POINTS) + building.getPotentialGreatScientistPoints());
		outputs.put(Outputs.GW_POINTS, outputs.get(Outputs.GW_POINTS) + building.getPotentialGreatWriterPoints());
	}

	public int turnsToBuild(City city) {
		double cost = getHammerCost();
		double production = this.city.getProductionFor(this);
		int turnsToBuild = (int) Math.ceil(cost / production);
		return turnsToBuild;
	}

	public double outputTotalPerTurn() {
		double outputPerTurnTotal = 0;
		Map<Outputs, Double> outputs = getOutputs();
		for (Outputs output : Outputs.values()) {
			outputPerTurnTotal += outputs.get(output);
		}
		return outputPerTurnTotal;
	}

	public int getWriterSlots() {
		return writerSlots;
	}

	public int getScientistSlots() {
		return scientistSlots;
	}

	public int getMerchantSlots() {
		return merchantSlots;
	}

	public int getEngineerSlots() {
		return engineerSlots;
	}

	public int getArtistSlots() {
		return artistSlots;
	}

	public int getMusicianSlots() {
		return musicianSlots;
	}

	public void addGreatWorkOfArt(GreatWorkOfArt art) {
		if (greatWorkOfArtSlots == 0) {
			throw new RuntimeException("Can't add art to a building without art slots");
		} else {
			boolean added = false;
			for (GreatWorkSlot slot : gwofArtSlots) {
				if (slot.isEmpty()) {
					slot.add(art);
					added = true;
					break;
				}
			}
			if (!added) {
				throw new RuntimeException("Can't add art to a building with only full great work slots.");
			}
		}
	}

	public void addGreatWorkOfMusic(GreatWorkOfMusic art) {
		if (greatWorkOfMusicSlots == 0) {
			throw new RuntimeException("Can't add music to a building without music slots");
		} else {
			boolean added = false;
			for (GreatWorkSlot slot : gwofMusicSlots) {
				if (slot.isEmpty()) {
					slot.add(art);
					added = true;
					break;
				}
			}
			if (!added) {
				throw new RuntimeException("Can't add music to a building with only full great work slots.");
			}
		}
	}

	public void addGreatWorkOfWriting(GreatWorkOfWriting writing) {
		if (greatWorkOfWritingSlots == 0) {
			throw new RuntimeException("Can't add writing to a building without writing slots");
		} else {
			boolean added = false;
			for (GreatWorkSlot slot : gwofWritingSlots) {
				if (slot.isEmpty()) {
					slot.add(writing);
					added = true;
					break;
				}
			}
			if (!added) {
				throw new RuntimeException("Can't add writing to a building with only full great work slots.");
			}
		}
	}

	public boolean hasOpenGreatWorkOfArtSlot() {
		for (GreatWorkSlot slot : gwofArtSlots) {
			if (slot.isEmpty()) {
				return true;
			}
		}
		return false;
	}

	public boolean hasOpenGreatWorkOfMusicSlot() {
		for (GreatWorkSlot slot : gwofMusicSlots) {
			if (slot.isEmpty()) {
				return true;
			}
		}
		return false;
	}

	public boolean hasOpenGreatWorkOfWritingSlot() {
		for (GreatWorkSlot slot : gwofWritingSlots) {
			if (slot.isEmpty()) {
				return true;
			}
		}
		return false;
	}

	public double getTourismPerTurn() {
		return tourism;
	}
	
	public double getPotentialTourismPerTurn() {
		double tourismFromBuildings = getTourismPerTurn();
		double tourism = 0;
		double greatWorksTourism = getTourismFromGreatWorks() * (1 + greatWorkTourismModifier + city.getGreatWorkTourismModifier()) + (city.getTourismFromGreatWorks() * greatWorkTourismModifier);
		
		double tourismFromWonderCulture = cultureConversionToTourismRate * city.getCultureFromWorldWonders();
		if (this instanceof Wonder && !(this instanceof NationalWonder)) {
			tourismFromWonderCulture += getBaseCulturePerTurn() * city.getCultureConversionToTourismRate();
		}
		
		tourism += tourismFromBuildings;
		tourism += greatWorksTourism;
		tourism += tourismFromWonderCulture;
		
		return (tourism * (1 + city.getTourismModifier())) + (city.getBaseTourism() * tourismModifier);
	}
	
	public double getTourismFromGreatWorks() {
		double tourismFromGreatWorks = 0;
		for (GreatWorkSlot slot : gwofArtSlots) {
			if (!slot.isEmpty()) {
				tourismFromGreatWorks += 2;
			}
		}
		for (GreatWorkSlot slot : gwofWritingSlots) {
			if (!slot.isEmpty()) {
				tourismFromGreatWorks += 2;
			}
		}
		for (GreatWorkSlot slot : gwofMusicSlots) {
			if (!slot.isEmpty()) {
				tourismFromGreatWorks += 2;
			}
		}
		return tourismFromGreatWorks;
	}

	public List<GreatWorkSlot> getGwofArtSlots() {
		return gwofArtSlots;
	}

	public List<GreatWorkSlot> getGwofWritingSlots() {
		return gwofWritingSlots;
	}

	public List<GreatWorkSlot> getGwofMusicSlots() {
		return gwofMusicSlots;
	}

	public List<SpecialistSlot> getWriters() {
		return writers;
	}

	public List<SpecialistSlot> getScientists() {
		return scientists;
	}

	public List<SpecialistSlot> getMerchants() {
		return merchants;
	}

	public List<SpecialistSlot> getEngineers() {
		return engineers;
	}

	public List<SpecialistSlot> getArtists() {
		return artists;
	}

	public List<SpecialistSlot> getMusicians() {
		return musicians;
	}
	
	public double getTradeRouteGold(TradeRoute route) {
		return tradeRouteBonus;
	}

	public boolean hasOpenMerchantSlot() {
		for (SpecialistSlot slot : merchants) {
			if (slot.isOpen()) {
				return true;
			}
		}
		return false;
	}
	
	public boolean hasFilledMerchantSlot() {
		for (SpecialistSlot slot : merchants) {
			if (slot.isFilled()) {
				return true;
			}
		}
		return false;
	}

	public void employMerchant(Merchant merchant) {
		if (merchantSlots == 0) {
			throw new RuntimeException("Can't employ a merchant in a building without merchant slots");
		} else {
			boolean added = false;
			for (SpecialistSlot slot : merchants) {
				if (slot.isOpen()) {
					slot.add(merchant);
					added = true;
					break;
				}
			}
			if (!added) {
				throw new RuntimeException("Can't employ a merchant in a building with only full merchant slots.");
			}
		}
	}

	public double getGreatWorkTourismModifier() {
		return greatWorkTourismModifier;
	}

	public double getCultureConversionToTourismRate() {
		return cultureConversionToTourismRate;
	}

	public boolean hasOpenEngineerSlot() {
		for (SpecialistSlot slot : engineers) {
			if (slot.isOpen()) {
				return true;
			}
		}
		return false;

	}
	
	public boolean hasFilledEngineerSlot() {
		for (SpecialistSlot slot : engineers) {
			if (slot.isFilled()) {
				return true;
			}
		}
		return false;

	}

	public void employEngineer(Engineer engineer) {
		if (engineerSlots == 0) {
			throw new RuntimeException("Can't employ an engineer in a building without engineer slots");
		} else {
			boolean added = false;
			for (SpecialistSlot slot : engineers) {
				if (slot.isOpen()) {
					slot.add(engineer);
					added = true;
					break;
				}
			}
			if (!added) {
				throw new RuntimeException("Can't employ an engineer in a building with only full engineer slots.");
			}
		}
	}

	public boolean hasOpenScientistSlot() {
		for (SpecialistSlot slot : scientists) {
			if (slot.isOpen()) {
				return true;
			}
		}
		return false;
	}
	
	public boolean hasFilledScientistSlot() {
		for (SpecialistSlot slot : scientists) {
			if (slot.isFilled()) {
				return true;
			}
		}
		return false;
	}
	
	public boolean hasOpenWriterSlot() {
		for (SpecialistSlot slot : writers) {
			if (slot.isOpen()) {
				return true;
			}
		}
		return false;
	}
	
	public boolean hasFilledWriterSlot() {
		for (SpecialistSlot slot : writers) {
			if (slot.isFilled()) {
				return true;
			}
		}
		return false;
	}
	
	public boolean hasOpenArtistSlot() {
		for (SpecialistSlot slot : artists) {
			if (slot.isOpen()) {
				return true;
			}
		}
		return false;
	}
	
	public boolean hasFilledArtistSlot() {
		for (SpecialistSlot slot : artists) {
			if (slot.isFilled()) {
				return true;
			}
		}
		return false;
	}public boolean hasOpenMusicianSlot() {
		for (SpecialistSlot slot : musicians) {
			if (slot.isOpen()) {
				return true;
			}
		}
		return false;
	}
	
	public boolean hasFilledMusicianSlot() {
		for (SpecialistSlot slot : musicians) {
			if (slot.isFilled()) {
				return true;
			}
		}
		return false;
	}

	public void employScientist(Scientist scientist) {
		if (scientistSlots == 0) {
			throw new RuntimeException("Can't employ a scientist in a building without scientist slots");
		} else {
			boolean added = false;
			for (SpecialistSlot slot : scientists) {
				if (slot.isOpen()) {
					slot.add(scientist);
					added = true;
					break;
				}
			}
			if (!added) {
				throw new RuntimeException("Can't employ a scientist in a building with only full scientist slots.");
			}
		}
	}
	
	public void employWriter(Writer writer) {
		if (writerSlots == 0) {
			throw new RuntimeException("Can't employ a writer in a building without writer slots");
		} else {
			boolean added = false;
			for (SpecialistSlot slot : writers) {
				if (slot.isOpen()) {
					slot.add(writer);
					added = true;
					break;
				}
			}
			if (!added) {
				throw new RuntimeException("Can't employ a writer in a building with only full writer slots.");
			}
		}
	}
	
	public void employArtist(Artist artist) {
		if (artistSlots == 0) {
			throw new RuntimeException("Can't employ a artist in a building without artist slots");
		} else {
			boolean added = false;
			for (SpecialistSlot slot : artists) {
				if (slot.isOpen()) {
					slot.add(artist);
					added = true;
					break;
				}
			}
			if (!added) {
				throw new RuntimeException("Can't employ a artist in a building with only full artist slots.");
			}
		}
	}
	
	public void employMusician(Musician musician) {
		if (musicianSlots == 0) {
			throw new RuntimeException("Can't employ a musician in a building without musician slots");
		} else {
			boolean added = false;
			for (SpecialistSlot slot : musicians) {
				if (slot.isOpen()) {
					slot.add(musician);
					added = true;
					break;
				}
			}
			if (!added) {
				throw new RuntimeException("Can't employ a musician in a building with only full musician slots.");
			}
		}
	}

	public int turnsToBuild() {
		return turnsToBuild(city);
	}

	public double getPotentialFoodOutput() {
		double food = this.foodPerTurn;
		List<Tile> tiles = city.getTiles();
		if (enhancer != null) {
			for (Tile tile : tiles) {
				if (enhancer.hasBaseTerrainFoodBonus(tile)) {
					food += 1;
				}
				if (enhancer.hasFoodBonusResource(tile)) {
					food += 1;
				}
			}
		}
		return food;
	}

	@Override
	public double outputTotalPerTurn(City city) {
		// TODO Auto-generated method stub
		return outputTotalPerTurn();
	}

	@Override
	public Map<Outputs, Double> getOutputs(City city) {
		// TODO Auto-generated method stub
		return getOutputs();
	}

	public void unemployEngineer() {
		if (engineerSlots == 0) {
			throw new RuntimeException("Can't unemploy an engineer in a building without engineer slots");
		} else {
			boolean removed = false;
			for (SpecialistSlot slot : engineers) {
				if (slot.isFilled()) {
					slot.unEmploySpecialist();
					;
					removed = true;
					break;
				}
			}
			if (!removed) {
				throw new RuntimeException("Can't unemploy an engineer in a building with only empty engineer slots.");
			}
		}
	}

	public void unemployMerchant() {
		if (merchantSlots == 0) {
			throw new RuntimeException("Can't unemploy an merchant in a building without merchant slots");
		} else {
			boolean removed = false;
			for (SpecialistSlot slot : merchants) {
				if (slot.isFilled()) {
					slot.unEmploySpecialist();
					removed = true;
					break;
				}
			}
			if (!removed) {
				throw new RuntimeException("Can't unemploy an merchant in a building with only empty merchant slots.");
			}
		}
	}

	public void unemployScientist() {
		if (scientistSlots == 0) {
			throw new RuntimeException("Can't unemploy an scientist in a building without scientist slots");
		} else {
			boolean removed = false;
			for (SpecialistSlot slot : scientists) {
				if (slot.isFilled()) {
					slot.unEmploySpecialist();
					removed = true;
					break;
				}
			}
			if (!removed) {
				throw new RuntimeException(
						"Can't unemploy an scientist in a building with only empty scientist slots.");
			}
		}
	}
}
