package com.merold.civcalcs.buildings;

import static com.merold.civcalcs.science.Tech.*;
import com.merold.civcalcs.Belief;
import com.merold.civcalcs.buildings.nationalwonders.ArtistsGuild;
import com.merold.civcalcs.buildings.nationalwonders.CircusMaximus;
import com.merold.civcalcs.buildings.nationalwonders.EastIndiaCompany;
import com.merold.civcalcs.buildings.nationalwonders.GrandTemple;
import com.merold.civcalcs.buildings.nationalwonders.Hermitage;
import com.merold.civcalcs.buildings.nationalwonders.HeroicEpic;
import com.merold.civcalcs.buildings.nationalwonders.IronWorks;
import com.merold.civcalcs.buildings.nationalwonders.MusiciansGuild;
import com.merold.civcalcs.buildings.nationalwonders.NationalCollege;
import com.merold.civcalcs.buildings.nationalwonders.NationalEpic;
import com.merold.civcalcs.buildings.nationalwonders.OxfordUniversity;
import com.merold.civcalcs.buildings.nationalwonders.WritersGuild;
import com.merold.civcalcs.buildings.wonders.Alhambra;
import com.merold.civcalcs.buildings.wonders.AngkorWat;
import com.merold.civcalcs.buildings.wonders.BigBen;
import com.merold.civcalcs.buildings.wonders.Borobudur;
import com.merold.civcalcs.buildings.wonders.BrandenburgGate;
import com.merold.civcalcs.buildings.wonders.ChichenItza;
import com.merold.civcalcs.buildings.wonders.Colossus;
import com.merold.civcalcs.buildings.wonders.ForbiddenPalace;
import com.merold.civcalcs.buildings.wonders.GlobeTheater;
import com.merold.civcalcs.buildings.wonders.GreatLibrary;
import com.merold.civcalcs.buildings.wonders.GreatLighthouse;
import com.merold.civcalcs.buildings.wonders.GreatMosqueOfDjenne;
import com.merold.civcalcs.buildings.wonders.GreatWall;
import com.merold.civcalcs.buildings.wonders.HagiaSophia;
import com.merold.civcalcs.buildings.wonders.HangingGardens;
import com.merold.civcalcs.buildings.wonders.HimejiCastle;
import com.merold.civcalcs.buildings.wonders.LeaningTowerOfPisa;
import com.merold.civcalcs.buildings.wonders.Louvre;
import com.merold.civcalcs.buildings.wonders.MachPichu;
import com.merold.civcalcs.buildings.wonders.MausoleumOfHalicarnassus;
import com.merold.civcalcs.buildings.wonders.NotreDame;
import com.merold.civcalcs.buildings.wonders.Oracle;
import com.merold.civcalcs.buildings.wonders.Parthenon;
import com.merold.civcalcs.buildings.wonders.Petra;
import com.merold.civcalcs.buildings.wonders.PorcelainTower;
import com.merold.civcalcs.buildings.wonders.Pyramids;
import com.merold.civcalcs.buildings.wonders.RedFort;
import com.merold.civcalcs.buildings.wonders.SistineChapel;
import com.merold.civcalcs.buildings.wonders.StatueOfZeus;
import com.merold.civcalcs.buildings.wonders.Stonehenge;
import com.merold.civcalcs.buildings.wonders.TajMahal;
import com.merold.civcalcs.buildings.wonders.TempleOfArtemis;
import com.merold.civcalcs.buildings.wonders.TerraCottaArmy;
import com.merold.civcalcs.buildings.wonders.Uffizi;
import com.merold.civcalcs.city.City;
import com.merold.civcalcs.player.Player;
import com.merold.civcalcs.science.Tech;
import com.merold.civcalcs.socialpolicies.SocialPolicy;
import com.merold.civcalcs.tiles.BaseTerrain;
import com.merold.civcalcs.tiles.Resource;

import static com.merold.civcalcs.tiles.Resource.*;
import com.merold.civcalcs.tiles.TerrainFeature;

import javafx.scene.effect.Glow;

public enum BuildingEnum {
	MONUMENT,
	PALACE,
	GRANARY(POTTERY),
	SHRINE(POTTERY),
	TEMPLE_OF_ARTEMIS(ARCHERY),
	STONEHENGE(CALENDAR),
	STONE_WORKS(CALENDAR, BaseTerrain.PLAINS, STONE, MARBLE),
	LIBRARY(WRITING),
	GREAT_LIBRARY(WRITING),
	CIRCUS(TRAPPING, HORSES, IVORY),
	WATER_MILL(THE_WHEEL, TerrainFeature.RIVER),
	WALLS(MASONRY),
	PYRAMIDS(MASONRY, SocialPolicy.LIBERTY),
	MAUSOLEUM_OF_HALICARNASSUS(MASONRY),
	BARRACKS(BRONZE_WORKING),
	STATUE_OF_ZEUS(BRONZE_WORKING, SocialPolicy.HONOR),
	LIGHTHOUSE(OPTICS, TerrainFeature.BORDERS_COAST),
	GREAT_LIGHTHOUSE(OPTICS, TerrainFeature.BORDERS_COAST),
	COURTHOUSE(TODO),
	HANGING_GARDENS(MATHEMATICS, SocialPolicy.TRADITION),
	STABLE(HORSEBACK_RIDING, CATTLE, HORSES, SHEEP),
	COLLESEUM(CONSTRUCTION),
	CIRCUS_MAXIMUS(HORSEBACK_RIDING, COLLESEUM),
	CARAVANSARY(HORSEBACK_RIDING),
	TERRA_COTTA_ARMY(CONSTRUCTION),
	TEMPLE(PHILOSOPHY),
	NATIONAL_COLLEGE(PHILOSOPHY, LIBRARY),
	ORACLE(PHILOSOPHY),
	NATIONAL_EPIC(DRAMA_AND_POETRY, MONUMENT),
	AMPHITHEATER(DRAMA_AND_POETRY),
	WRITERS_GUILD(DRAMA_AND_POETRY),
	PARTHENON(DRAMA_AND_POETRY),
	MINT(CURRENCY, GOLD, SILVER),
	MARKET(CURRENCY),
	PETRA(CURRENCY, BaseTerrain.DESERT),
	GREAT_WALL(ENGINEERING),
	AQUEDUCT(ENGINEERING),
	HEROIC_EPIC(IRON_WORKING, BARRACKS),
	COLOSSUS(IRON_WORKING, TerrainFeature.BORDERS_COAST),
	GARDEN(THEOLOGY, TerrainFeature.RIVER, TerrainFeature.BORDERS_LAKE),
	HAGIA_SOPHIA(THEOLOGY),
	BOROBUDUR(THEOLOGY, true),
	GREAT_MOSQUE_OF_DJENNE(THEOLOGY, SocialPolicy.PIETY),
	GRAND_TEMPLE(THEOLOGY, true, BuildingEnum.TEMPLE),
	CHICHEN_ITZA(CIVIL_SERVICE),
	MOSQUE(Belief.MOSQUES),
	EAST_INDIA_COMPANY(GUILDS, MARKET),
	MACHU_PICH(GUILDS, TerrainFeature.MOUNTAIN),
	ARTISTS_GUILD(GUILDS),
	FORGE(METAL_CASTING, IRON),
	ARMORY(STEEL, BARRACKS),
	CASTLE(CHIVALRY, WALLS),
	HARBOR(COMPASS, TerrainFeature.BORDERS_COAST),
	UNIVERSITY(EDUCATION, LIBRARY),
	WORKSHOP(METAL_CASTING),
	ARSENAL(METALLURGY, CASTLE),
	BANK(BANKING, MARKET),
	CONSTABULARY(BANKING),
	OBSERVATORY(ASTRONOMY, TerrainFeature.MOUNTAIN),
	OPERA_HOUSE(ACOUSTICS, AMPHITHEATER),
	SEAPORT(NAVIGATION, HARBOR),
	WINDMILL(TerrainFeature.HILL, ECONOMICS),
	ZOO(PRINTING_PRESS, COLLESEUM),
	FACTORY(COAL, INDUSTRIALIZATION),
	HOSPITAL(BIOLOGY, AQUEDUCT),
	HYDRO_PLANT(ALUMINUM, ELECTRICITY, TerrainFeature.RIVER),
	MILITARY_ACADEMY(MILITARY_SCIENCE, ARMORY),
	MUSEUM(ARCHAEOLOGY, OPERA_HOUSE),
	POLICE_STATION(ELECTRICITY, CONSTABULARY),
	PUBLIC_SCHOOL(SCIENTIFIC_THEORY, UNIVERSITY),
	STOCK_EXCHANGE(ELECTRICITY, BANK),
	BROADCAST_TOWER(RADIO, MUSEUM),
	HOTEL(REFRIGERATION),
	MILITARY_BASE(REPLACEABLE_PARTS, ARSENAL),
	REASEARCH_LAB(PLASTICS, PUBLIC_SCHOOL),
	STADIUM(REFRIGERATION, ZOO),
	AIRPORT(RADAR),
	MEDICAL_LAB(PENICILLIN, HOSPITAL),
	SOLAR_PLANT(ECOLOGY, BaseTerrain.DESERT),
	NUCLEAR_PLANT(SOLAR_PLANT, NUCLEAR_FISSION, FACTORY, URANIUM),
	RECYCLING_CENTER(ECOLOGY),
	BOMB_SHELTER(TELECOMMUNICATIONS),
	SPACESHIP_FACTORY(ROBOTICS, ALUMINUM, FACTORY),
	OXFORD_UNIVERSITY(EDUCATION, UNIVERSITY),
	ANGKOR_WAT(EDUCATION),
	ALHAMBRA(CHIVALRY),
	CATHEDRAL(Belief.CATHEDRALS),
	PAGODA(Belief.PAGODAS),
	IRON_WORKS(MACHINERY, WORKSHOP),
	NOTRE_DAME(PHYSICS),
	SISTINE_CHAPEL(ACOUSTICS),
	MUSICIANS_GUILD(ACOUSTICS),
	FORBIDDEN_PALACE(BANKING, SocialPolicy.PATRONAGE),
	LEANING_TOWER_OF_PISA(PRINTING_PRESS),
	GLOBE_THEATER(PRINTING_PRESS),
	HIMEJI_CASTLE(GUNPOWDER),
	HERMITAGE(ARCHITECTURE, OPERA_HOUSE),
	PORCELAIN_TOWER(ARCHITECTURE, SocialPolicy.RATIONALISM),
	TAJ_MAHAL(ARCHITECTURE),
	UFFIZI(ARCHITECTURE, SocialPolicy.AESTHETICS),
	RED_FORT(METALLURGY),
	LOUVRE(ARCHAEOLOGY),
	BIG_BEN(INDUSTRIALIZATION, SocialPolicy.COMMERCE),
	BRANDENBURG_GATE(MILITARY_SCIENCE)
	;

	public boolean isRequiresHolyCity() {
		return requiresHolyCity;
	}

	private Tech requiredTech;
	private BaseTerrain restrictedCityTerrain;
	private TerrainFeature[] requiredTerrainFeature;
	private Resource[] requiredImprovedResources;
	private SocialPolicy requiredPolicy;
	private BuildingEnum requiredBuilding;
	private boolean requiresHolyCity = false;
	private Belief requiredBelief;
	private Resource consumes;
	private BuildingEnum restrictedBuilding;
	private TerrainFeature disallowedTerrainFeature;

	BuildingEnum(Tech requiredTech) {
		this.requiredTech = requiredTech;
	}

	BuildingEnum(Resource resource, Tech requiredTech) {
		this.requiredTech = requiredTech;
		this.consumes = resource;
	}

	BuildingEnum(Tech requiredTech, Resource resource, BuildingEnum requiredBuilding) {
		this.requiredTech = requiredTech;
		this.consumes = resource;
		this.requiredBuilding = requiredBuilding;
	}

	BuildingEnum(Resource resource, Tech requiredTech, TerrainFeature... feature) {
		this.requiredTech = requiredTech;
		this.consumes = resource;
		this.requiredTerrainFeature = feature;
	}

	BuildingEnum(Belief requiredBelief) {
		this.requiredBelief = requiredBelief;
	}

	BuildingEnum(Tech requiredTech, boolean requiresHolyCity) {
		this.requiredTech = requiredTech;
		this.requiresHolyCity = requiresHolyCity;
	}
	
	BuildingEnum(Tech requiredTech, boolean requiresHolyCity, BuildingEnum required) {
		this.requiredTech = requiredTech;
		this.requiresHolyCity = requiresHolyCity;
		this.requiredBuilding = required;
	}

	BuildingEnum(Tech requiredTech, BuildingEnum requiredBuilding) {
		this.requiredTech = requiredTech;
		this.requiredBuilding = requiredBuilding;
	}

	BuildingEnum(BuildingEnum restrictedBuilding, Tech requiredTech, BuildingEnum requiredBuilding, Resource consumes) {
		this.requiredTech = requiredTech;
		this.requiredBuilding = requiredBuilding;
		this.restrictedBuilding = restrictedBuilding;
		this.consumes = consumes;
	}

	public BuildingEnum getRequiredBuilding() {
		return requiredBuilding;
	}

	BuildingEnum(Tech requiredTech, SocialPolicy policy) {
		this.requiredTech = requiredTech;
		this.requiredPolicy = policy;
	}

	BuildingEnum(Tech requiredTech, TerrainFeature... feature) {
		this.requiredTech = requiredTech;
		this.requiredTerrainFeature = feature;
	}
	
	BuildingEnum(TerrainFeature feature, Tech requiredTech) {
		this.requiredTech = requiredTech;
		this.disallowedTerrainFeature = feature;
	}

	BuildingEnum() {

	}

	BuildingEnum(Tech requiredTech, BaseTerrain restrictedCityTerrain, Resource... requiredImproved) {
		this.requiredTech = requiredTech;
		this.restrictedCityTerrain = restrictedCityTerrain;
		this.requiredImprovedResources = requiredImproved;
	}

	BuildingEnum(Tech requiredTech, Resource... requiredImproved) {
		this.requiredTech = requiredTech;
		this.requiredImprovedResources = requiredImproved;
	}

	public Project create(City city, Player owner) {
		final Project building;
		switch (this) {
		case MONUMENT:
			building = new Monument(city, owner);
			break;
		case PALACE:
			building = new Palace(city, owner);
			break;
		case GRANARY:
			building = new Granary(city, owner);
			break;
		case SHRINE:
			building = new Shrine(city, owner);
			break;
		case TEMPLE_OF_ARTEMIS:
			building = new TempleOfArtemis(city, owner);
			break;
		case STONEHENGE:
			building = new Stonehenge(city, owner);
			break;
		case STONE_WORKS:
			building = new StoneWorks(city, owner);
			break;
		case LIBRARY:
			building = new Library(city, owner);
			break;
		case GREAT_LIBRARY:
			building = new GreatLibrary(city, owner);
			break;
		case CIRCUS:
			building = new Circus(city, owner);
			break;
		case WATER_MILL:
			building = new WaterMill(city, owner);
			break;
		case WALLS:
			building = new Walls(city, owner);
			break;
		case MAUSOLEUM_OF_HALICARNASSUS:
			building = new MausoleumOfHalicarnassus(city, owner);
			break;
		case PYRAMIDS:
			building = new Pyramids(city, owner);
			break;
		case BARRACKS:
			building = new Barracks(city, owner);
			break;
		case STATUE_OF_ZEUS:
			building = new StatueOfZeus(city, owner);
			break;
		case LIGHTHOUSE:
			building = new Lighthouse(city, owner);
			break;
		case GREAT_LIGHTHOUSE:
			building = new GreatLighthouse(city, owner);
			break;
		case COURTHOUSE:
			building = new Courthouse(city, owner);
			break;
		case HANGING_GARDENS:
			building = new HangingGardens(city, owner);
			break;
		case STABLE:
			building = new Stable(city, owner);
			break;
		case CARAVANSARY:
			building = new Caravansary(city, owner);
			break;
		case CIRCUS_MAXIMUS:
			building = new CircusMaximus(city, owner);
			break;
		case GARDEN:
			building = new Garden(city, owner);
			break;
		case AQUEDUCT:
			building = new Aqueduct(city, owner);
			break;
		case COLLESEUM:
			building = new Colleseum(city, owner);
			break;
		case TERRA_COTTA_ARMY:
			building = new TerraCottaArmy(city, owner);
			break;
		case TEMPLE:
			building = new Temple(city, owner);
			break;
		case ORACLE:
			building = new Oracle(city, owner);
			break;
		case NATIONAL_COLLEGE:
			building = new NationalCollege(city, owner);
			break;
		case NATIONAL_EPIC:
			building = new NationalEpic(city, owner);
			break;
		case AMPHITHEATER:
			building = new Amphitheater(city, owner);
			break;
		case WRITERS_GUILD:
			building = new WritersGuild(city, owner);
			break;
		case PARTHENON:
			building = new Parthenon(city, owner);
			break;
		case MINT:
			building = new Mint(city, owner);
			break;
		case MARKET:
			building = new Market(city, owner);
			break;
		case PETRA:
			building = new Petra(city, owner);
			break;
		case GREAT_WALL:
			building = new GreatWall(city, owner);
			break;
		case HEROIC_EPIC:
			building = new HeroicEpic(city, owner);
			break;
		case COLOSSUS:
			building = new Colossus(city, owner);
			break;
		case HAGIA_SOPHIA:
			building = new HagiaSophia(city, owner);
			break;
		case BOROBUDUR:
			building = new Borobudur(city, owner);
			break;
		case GRAND_TEMPLE:
			building = new GrandTemple(city, owner);
			break;
		case GREAT_MOSQUE_OF_DJENNE:
			building = new GreatMosqueOfDjenne(city, owner);
			break;
		case MOSQUE:
			building = new Mosque(city, owner);
			break;
		case CHICHEN_ITZA:
			building = new ChichenItza(city, owner);
			break;
		case EAST_INDIA_COMPANY:
			building = new EastIndiaCompany(city, owner);
			break;
		case MACHU_PICH:
			building = new MachPichu(city, owner);
			break;
		case ARTISTS_GUILD:
			building = new ArtistsGuild(city, owner);
			break;
		case AIRPORT:
			building = new Airport(city, owner);
			break;
		case ARMORY:
			building = new Armory(city, owner);
			break;
		case ARSENAL:
			building = new Arsenal(city, owner);
			break;
		case BANK:
			building = new Bank(city, owner);
			break;
		case BOMB_SHELTER:
			building = new BombShelter(city, owner);
			break;
		case BROADCAST_TOWER:
			building = new BroadcastTower(city, owner);
			break;
		case CASTLE:
			building = new Castle(city, owner);
			break;
		case CONSTABULARY:
			building = new Constabulary(city, owner);
			break;
		case FACTORY:
			building = new Factory(city, owner);
			break;
		case FORGE:
			building = new Forge(city, owner);
			break;
		case HARBOR:
			building = new Harbor(city, owner);
			break;
		case HOSPITAL:
			building = new Hospital(city, owner);
			break;
		case HOTEL:
			building = new Hotel(city, owner);
			break;
		case HYDRO_PLANT:
			building = new HydroPlant(city, owner);
			break;
		case MEDICAL_LAB:
			building = new MedicalLab(city, owner);
			break;
		case MILITARY_ACADEMY:
			building = new MilitaryAcademy(city, owner);
			break;
		case MILITARY_BASE:
			building = new MilitaryBase(city, owner);
			break;
		case MUSEUM:
			building = new Museum(city, owner);
			break;
		case NUCLEAR_PLANT:
			building = new NuclearPlant(city, owner);
			break;
		case OBSERVATORY:
			building = new Observatory(city, owner);
			break;
		case OPERA_HOUSE:
			building = new OperaHouse(city, owner);
			break;
		case POLICE_STATION:
			building = new PoliceStation(city, owner);
			break;
		case PUBLIC_SCHOOL:
			building = new PublicSchool(city, owner);
			break;
		case REASEARCH_LAB:
			building = new ResearchLab(city, owner);
			break;
		case RECYCLING_CENTER:
			building = new RecyclingCenter(city, owner);
			break;
		case SEAPORT:
			building = new Seaport(city, owner);
			break;
		case SOLAR_PLANT:
			building = new SolarPlant(city, owner);
			break;
		case SPACESHIP_FACTORY:
			building = new SpaceshipFactory(city, owner);
			break;
		case STADIUM:
			building = new Stadium(city, owner);
			break;
		case STOCK_EXCHANGE:
			building = new StockExchange(city, owner);
			break;
		case UNIVERSITY:
			building = new University(city, owner);
			break;
		case WINDMILL:
			building = new Windmill(city, owner);
			break;
		case WORKSHOP:
			building = new Workshop(city, owner);
			break;
		case ZOO:
			building = new Zoo(city, owner);
			break;
		case OXFORD_UNIVERSITY:
			building = new OxfordUniversity(city, owner);
			break;
		case ANGKOR_WAT:
			building = new AngkorWat(city, owner);
			break;
		case ALHAMBRA:
			building = new Alhambra(city, owner);
			break;
		case CATHEDRAL:
			building = new Cathedral(city, owner);
			break;
		case IRON_WORKS:
			building = new IronWorks(city, owner);
			break;
		case NOTRE_DAME:
			building = new NotreDame(city, owner);
			break;
		case PAGODA:
			building = new Pagoda(city, owner);
			break;
		case SISTINE_CHAPEL:
			building = new SistineChapel(city, owner);
			break;
		case MUSICIANS_GUILD:
			building = new MusiciansGuild(city, owner);
			break;
		case FORBIDDEN_PALACE:
			building = new ForbiddenPalace(city, owner);
			break;
		case GLOBE_THEATER:
			building = new GlobeTheater(city, owner);
			break;
		case LEANING_TOWER_OF_PISA:
			building = new LeaningTowerOfPisa(city, owner);
			break;
		case HIMEJI_CASTLE:
			building = new HimejiCastle(city, owner);
			break;
		case HERMITAGE:
			building = new Hermitage(city, owner);
			break;
		case PORCELAIN_TOWER:
			building = new PorcelainTower(city, owner);
			break;
		case TAJ_MAHAL:
			building = new TajMahal(city, owner);
			break;
		case UFFIZI:
			building = new Uffizi(city, owner);
			break;
		case RED_FORT:
			building = new RedFort(city, owner);
			break;
		case LOUVRE:
			building = new Louvre(city, owner);
			break;
		case BIG_BEN:
			building = new BigBen(city, owner);
			break;
		case BRANDENBURG_GATE:
			building = new BrandenburgGate(city, owner);
			break;
		default:
			throw new RuntimeException("Unhandled BuildingEnum " + this);
		}
		return building;
	}

	public Tech getRequiredTech() {
		return requiredTech;
	}

	public TerrainFeature[] getRequiredTerrainFeature() {
		return requiredTerrainFeature;
	}
	
	public TerrainFeature getDisallowedTerrainFeature() {
		return disallowedTerrainFeature;
	}

	public TerrainFeature[] getRequiredTerrainFeatures() {
		return requiredTerrainFeature;
	}

	public Tech requiredTech() {
		return this.requiredTech;
	}

	public SocialPolicy getRequiredPolicy() {
		return requiredPolicy;
	}

	public BaseTerrain getRestrictedCityTerrain() {
		return this.restrictedCityTerrain;
	}

	public Resource[] getRequiredImprovedResources() {
		return requiredImprovedResources;
	}

	public Belief getRequiredBelief() {
		return requiredBelief;
	}

	public Resource getRequiredResource() {
		return consumes;
	}
}