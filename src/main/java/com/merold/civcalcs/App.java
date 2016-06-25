package com.merold.civcalcs;

import static com.merold.civcalcs.buildings.BuildingEnum.*;
import static com.merold.civcalcs.science.Tech.*;
import static com.merold.civcalcs.socialpolicies.SocialPolicy.*;
import static com.merold.civcalcs.tiles.BaseTerrain.*;
import static com.merold.civcalcs.tiles.TerrainFeature.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;

import com.merold.civcalcs.buildings.Building;
import com.merold.civcalcs.buildings.BuildingEnum;
import com.merold.civcalcs.buildings.Outputs;
import com.merold.civcalcs.buildings.util.BuildingsUtil;
import com.merold.civcalcs.city.Buildable;
import com.merold.civcalcs.city.City;
import com.merold.civcalcs.city.RouteType;
import com.merold.civcalcs.city.TargetTradeCity;
import com.merold.civcalcs.city.TradeRoute;
import com.merold.civcalcs.civilizations.Civilization;
import com.merold.civcalcs.civilizations.Influence;
import com.merold.civcalcs.player.Player;
import com.merold.civcalcs.science.Tech;
import com.merold.civcalcs.socialpolicies.SocialPolicy;
import com.merold.civcalcs.specialists.Artist;
import com.merold.civcalcs.specialists.Engineer;
import com.merold.civcalcs.specialists.Merchant;
import com.merold.civcalcs.specialists.Musician;
import com.merold.civcalcs.specialists.Scientist;
import com.merold.civcalcs.specialists.Writer;
import com.merold.civcalcs.tiles.Academy;
import com.merold.civcalcs.tiles.Camp;
import com.merold.civcalcs.tiles.Farm;
import com.merold.civcalcs.tiles.HolySite;
import com.merold.civcalcs.tiles.LumberMill;
import com.merold.civcalcs.tiles.Manufactory;
import com.merold.civcalcs.tiles.Mine;
import com.merold.civcalcs.tiles.Pasture;
import com.merold.civcalcs.tiles.Quarry;
import com.merold.civcalcs.tiles.Resource;
import com.merold.civcalcs.tiles.Tile;
import com.merold.civcalcs.tiles.TradingPost;
import com.merold.civcalcs.tiles.WorkBoats;
import com.merold.civcalcs.units.CargoShip;
import com.merold.civcalcs.units.Unit;
import com.merold.civcalcs.units.UnitEnum;
import com.merold.civcalcs.util.MathUtil;

public class App {
	private static Tile[][] populateGrid() {
		// Row 0
		Tile tileX0Y0 = new Tile(0, 0, Resource.NONE, GRASSLAND, HILL);
		Tile tileX1Y0 = new Tile(1, 0, Resource.COAL, GRASSLAND, HILL);
		Tile tileX2Y0 = new Tile(2, 0, Resource.NONE, GRASSLAND);
		Tile tileX3Y0 = new Tile(3, 0, Resource.NONE, TUNDRA, FOREST);

		// Row 1
		Tile tileX0Y1 = new Tile(0, 1, Resource.NONE, GRASSLAND, MOUNTAIN);
		Tile tileX1Y1 = new Tile(1, 1, Resource.NONE, GRASSLAND, FOREST);
		Tile tileX2Y1 = new Tile(2, 1, Resource.NONE, GRASSLAND, RIVER);
		Tile tileX3Y1 = new Tile(3, 1, Resource.STONE, GRASSLAND, RIVER, BORDERS_COAST);
		Tile tileX4Y1 = new Tile(4, 1, Resource.NONE, PLAINS, BORDERS_COAST);

		// Row 2
		Tile tileX0Y2 = new Tile(0, 2, Resource.CATTLE, GRASSLAND);
		Tile tileX1Y2 = new Tile(1, 2, Resource.NONE, GRASSLAND, RIVER);
		Tile tileX2Y2 = new Tile(2, 2, Resource.CATTLE, GRASSLAND, RIVER);
		Tile tileX3Y2 = new Tile(3, 2, Resource.NONE, GRASSLAND, FOREST, RIVER, BORDERS_COAST);
		Tile tileX4Y2 = new Tile(4, 2, Resource.NONE, COAST);
		Tile tileX5Y2 = new Tile(5, 2, Resource.NONE, COAST);

		// Row 3
		Tile tileX0Y3 = new Tile(0, 3, Resource.NONE, GRASSLAND);
		Tile tileX1Y3 = new Tile(1, 3, Resource.NONE, GRASSLAND, RIVER);
		Tile tileX2Y3 = new Tile(2, 3, Resource.NONE, GRASSLAND, RIVER);
		Tile startTileX3Y3 = new Tile(true, 3, 3, Resource.NONE, GRASSLAND, RIVER, BORDERS_COAST);
		Tile tileX4Y3 = new Tile(4, 3, Resource.FISH, COAST);
		Tile tileX5Y3 = new Tile(5, 3, Resource.PEARLS, COAST);
		Tile tileX6Y3 = new Tile(6, 3, Resource.NONE, PLAINS, HILL, BORDERS_COAST);

		// Row 4
		Tile tileX1Y4 = new Tile(1, 4, Resource.NONE, GRASSLAND, RIVER);
		Tile tileX2Y4 = new Tile(2, 4, Resource.NONE, GRASSLAND, RIVER, HILL);
		Tile tileX3Y4 = new Tile(3, 4, Resource.NONE, GRASSLAND);
		Tile tileX4Y4 = new Tile(4, 4, Resource.STONE, GRASSLAND, BORDERS_COAST);
		Tile tileX5Y4 = new Tile(5, 4, Resource.PEARLS, COAST);
		Tile tileX6Y4 = new Tile(6, 4, Resource.NONE, COAST);

		// Row 5
		Tile tileX2Y5 = new Tile(2, 5, Resource.IRON, GRASSLAND, HILL);
		Tile tileX3Y5 = new Tile(3, 5, Resource.HORSES, GRASSLAND);
		Tile tileX4Y5 = new Tile(4, 5, Resource.IVORY, GRASSLAND, BORDERS_COAST);
		Tile tileX5Y5 = new Tile(5, 5, Resource.PEARLS, COAST);
		Tile tileX6Y5 = new Tile(6, 5, Resource.NONE, COAST);

		// Row 6
		Tile tileX3Y6 = new Tile(3, 6, Resource.NONE, GRASSLAND, HILL);
		Tile tileX4Y6 = new Tile(4, 6, Resource.NONE, DESERT, BORDERS_COAST);
		Tile tileX5Y6 = new Tile(5, 6, Resource.NONE, GRASSLAND, HILL, BORDERS_COAST);
		Tile tileX6Y6 = new Tile(6, 6, Resource.NONE, COAST);

		Tile[][] grid = new Tile[][] { { tileX0Y0, tileX1Y0, tileX2Y0, tileX3Y0, null, null, null },
				{ tileX0Y1, tileX1Y1, tileX2Y1, tileX3Y1, tileX4Y1, null, null },
				{ tileX0Y2, tileX1Y2, tileX2Y2, tileX3Y2, tileX4Y2, tileX5Y2, null },
				{ tileX0Y3, tileX1Y3, tileX2Y3, startTileX3Y3, tileX4Y3, tileX5Y3, tileX6Y3 },
				{ null, tileX1Y4, tileX2Y4, tileX3Y4, tileX4Y4, tileX5Y4, tileX6Y4 },
				{ null, null, tileX2Y5, tileX3Y5, tileX4Y5, tileX5Y5, tileX6Y5 },
				{ null, null, null, tileX3Y6, tileX4Y6, tileX5Y6, tileX6Y6 } };
		return grid;
	}

	public static void main(String[] args) {
		Game game = new Game();
		Player player = new Player(game);
		game.addPlayer(player);

		Tile[][] grid = populateGrid();

		List<Tile> tilesInBorder = new ArrayList<Tile>();
		tilesInBorder.add(grid[3][4]);
		tilesInBorder.add(grid[4][4]);
		tilesInBorder.add(grid[4][3]);
		tilesInBorder.add(grid[3][2]);
		tilesInBorder.add(grid[2][2]);
		tilesInBorder.add(grid[2][3]);
		City capital = new City("Washington", player, grid[3][3], tilesInBorder);
		capital.build(PALACE.create(capital, player));
		player.addCity(capital);
		player.recruit(UnitEnum.WARRIOR.create(player));
		capital.work(grid[2][2]);
		capital.startBuilding(UnitEnum.SCOUT);

		game.setCurrentTurn(3);
		capital.grow(); // Ruins discovered.
		capital.work(grid[4][4]);

		game.setCurrentTurn(6);
		capital.completeBuildable();
		player.recruit(UnitEnum.WORKER.create(player)); // Ruins discovered.
		capital.startBuilding(UnitEnum.SCOUT);

		game.setCurrentTurn(8);
		player.discover(POTTERY);
		capital.grow();
		capital.work(grid[4][3]);
		player.recruit(UnitEnum.SETTLER.create(player));

		game.setCurrentTurn(9);
		capital.grow();
		capital.work(grid[3][4]);

		game.setCurrentTurn(10);
		player.adopt(TRADITION);
		player.deleteUnit(UnitEnum.SETTLER);

		game.setCurrentTurn(11);
		capital.completeBuildable();
		capital.startBuilding(SHRINE);

		game.setCurrentTurn(12);
		capital.addTileInBorder(grid[5][4]);
		capital.unWork(grid[4][3]);
		capital.work(grid[5][4]);

		game.setCurrentTurn(14);
		player.adopt(ARISTOCRACY);

		game.setCurrentTurn(16);
		player.discover(WRITING);
		grid[3][2].addImprovement(new Farm());
		capital.unWork(grid[3][4]);
		capital.work(grid[3][2]);

		game.setCurrentTurn(19);
		capital.completeBuildable();
		capital.startBuilding(MONUMENT);

		game.setCurrentTurn(21);
		player.discover(ANIMAL_HUSBANDRY);
		capital.grow();
		capital.addTileInBorder(grid[4][5]);
		capital.work(grid[4][5]);

		game.setCurrentTurn(22);
		player.adopt(OLIGARCHY);

		game.setCurrentTurn(25);
		grid[4][3].addImprovement(new Farm());
		capital.addTileInBorder(grid[5][3]);
		capital.unWork(grid[4][5]);
		capital.work(grid[4][3]);

		game.setCurrentTurn(27);
		capital.completeBuildable();
		capital.startBuilding(GRANARY);

		game.setCurrentTurn(28);
		player.discover(CALENDAR);
		player.addBelief(Belief.GOD_KING);

		game.setCurrentTurn(30);
		capital.addTileInBorder(grid[3][5]);

		game.setCurrentTurn(31);
		player.adopt(LEGALISM);

		game.setCurrentTurn(32);
		player.discover(ARCHERY);
		grid[5][3].addImprovement(new Pasture());
		capital.unWork(grid[4][4]);
		capital.work(grid[5][3]);

		game.setCurrentTurn(33);
		capital.grow();
		capital.work(grid[4][4]);

		game.setCurrentTurn(36);
		capital.addTileInBorder(grid[5][5]);
		capital.completeBuildable();
		capital.startBuilding(GREAT_LIBRARY);

		game.setCurrentTurn(38);
		player.startGoldenAge();
		capital.unWork(grid[4][4]);
		capital.work(grid[3][5]);

		game.setCurrentTurn(41);
		grid[2][2].addImprovement(new Pasture());

		game.setCurrentTurn(42);
		capital.addTileInBorder(grid[1][3]);

		game.setCurrentTurn(43);
		player.adopt(LANDED_ELITE);

		game.setCurrentTurn(44);
		capital.grow();
		capital.work(grid[4][5]);

		game.setCurrentTurn(47);
		player.discover(SAILING);

		game.setCurrentTurn(48);
		capital.unWork(grid[3][5]);
		capital.unWork(grid[4][5]);
		capital.work(grid[4][4]);
		capital.work(grid[1][3]);

		game.setCurrentTurn(49);
		capital.addTileInBorder(grid[2][1]);
		grid[4][4].addImprovement(new Quarry());

		game.setCurrentTurn(52);
		capital.addTileInBorder(grid[2][0]);
		capital.unWork(grid[1][3]);
		capital.work(grid[2][0]);

		game.setCurrentTurn(53);
		capital.completeBuildable();
		capital.startBuilding(NATIONAL_COLLEGE);
		capital.grow();
		player.discover(PHILOSOPHY);
		player.discover(MASONRY);
		capital.work(grid[1][3]);

		game.setCurrentTurn(54);
		player.discover(OPTICS);

		game.setCurrentTurn(58);
		grid[1][3].addImprovement(new Quarry());
		capital.addTileInBorder(grid[1][2]);

		game.setCurrentTurn(58);
		player.adopt(MONARCHY);

		game.setCurrentTurn(61);
		capital.grow();
		capital.work(grid[3][5]);

		game.setCurrentTurn(63);
		player.discover(DRAMA_AND_POETRY);

		game.setCurrentTurn(64);
		capital.completeBuildable();
		capital.startBuilding(GREAT_LIGHTHOUSE);

		game.setCurrentTurn(65);
		player.discover(TRAPPING);

		game.setCurrentTurn(66);
		player.discover(THE_WHEEL);
		capital.addTileInBorder(grid[4][2]);

		game.setCurrentTurn(67);
		grid[2][0].addImprovement(new Pasture());
		capital.unWork(grid[3][5]);
		capital.work(grid[4][2]);

		game.setCurrentTurn(68);
		capital.grow();
		capital.work(grid[3][5]);

		game.setCurrentTurn(69);
		player.discover(MATHEMATICS);

		game.setCurrentTurn(70);
		capital.addTileInBorder(grid[1][1]);

		game.setCurrentTurn(72);
		player.discover(HORSEBACK_RIDING);

		game.setCurrentTurn(75);
		capital.completeBuildable();
		grid[5][4].addImprovement(new Camp());
		capital.work(grid[3][4]);
		capital.work(grid[4][5]);
		capital.work(grid[5][5]);
		capital.unWork(grid[4][2]);
		capital.unWork(grid[5][3]);
		capital.unWork(grid[5][4]);
		capital.startBuilding(STONE_WORKS);

		game.setCurrentTurn(76);
		capital.grow();
		capital.work(grid[5][4]);

		game.setCurrentTurn(77);
		player.discover(CURRENCY);
		player.adopt(PIETY);

		game.setCurrentTurn(78);
		player.discover(BRONZE_WORKING);

		game.setCurrentTurn(80);
		capital.completeBuildable();
		capital.startBuilding(MARKET);

		game.setCurrentTurn(81);
		capital.grow();
		capital.work(grid[5][3]);

		game.setCurrentTurn(82);
		grid[4][2].addImprovement(new Farm());
		capital.addTileInBorder(grid[5][2]);

		game.setCurrentTurn(85);
		capital.completeBuildable();
		player.discover(CIVIL_SERVICE);
		capital.startBuilding(HANGING_GARDENS);

		game.setCurrentTurn(87);
		capital.grow();
		capital.work(grid[4][2]);

		game.setCurrentTurn(89);
		grid[5][2].addImprovement(new Mine());
		capital.unWork(grid[4][2]);
		capital.work(grid[5][2]);

		game.setCurrentTurn(92);
		player.discover(THEOLOGY);

		game.setCurrentTurn(94);
		capital.grow();
		capital.work(grid[4][2]);

		game.setCurrentTurn(95);
		player.startGoldenAge();
		capital.completeBuildable();
		capital.startBuilding(TEMPLE_OF_ARTEMIS);

		game.setCurrentTurn(96);
		capital.addTileInBorder(grid[3][1]);
		grid[3][1].addImprovement(new Farm());
		capital.unWork(grid[4][3]);
		capital.work(grid[3][1]);

		game.setCurrentTurn(97);
		player.adopt(SocialPolicy.ORGANIZED_RELIGION);

		game.setCurrentTurn(99);
		capital.grow();
		capital.employ(new Merchant());

		game.setCurrentTurn(100);
		capital.completeBuildable();
		capital.startBuilding(STONEHENGE);

		game.setCurrentTurn(101);
		capital.addTileInBorder(grid[3][6]);

		game.setCurrentTurn(102);
		player.discover(EDUCATION);
		grid[2][1].addImprovement(new Farm());

		game.setCurrentTurn(103);
		game.completed(STATUE_OF_ZEUS);

		game.setCurrentTurn(105);
		capital.completeBuildable();
		capital.startBuilding(GREAT_MOSQUE_OF_DJENNE);
		capital.grow();
		capital.work(grid[2][1]);
		player.discover(CONSTRUCTION);
		capital.build(UNIVERSITY.create(capital, player));
		capital.unEmploy(new Merchant());
		capital.employ(new Scientist());

		game.setCurrentTurn(107);
		player.discover(ENGINEERING);
		capital.grow();
		capital.employ(new Scientist());

		game.setCurrentTurn(108);
		capital.build(WATER_MILL.create(capital, player));

		game.setCurrentTurn(109);
		game.completed(COLOSSUS);
		grid[1][2].addImprovement(new Farm());
		capital.unEmploy(new Scientist());
		capital.work(grid[1][2]);

		game.setCurrentTurn(110);
		player.discover(IRON_WORKING);

		game.setCurrentTurn(111);
		capital.grow();
		capital.employ(new Scientist());

		game.setCurrentTurn(112);
		capital.foundReligion(Religion.PROTESTANTISM);
		player.addBelief(Belief.INTERFAITH_DIALOGUE);
		player.addBelief(Belief.CATHEDRALS);

		game.setCurrentTurn(113);
		player.discover(METAL_CASTING);
		grid[4][1].addImprovement(new Academy());
		capital.addTileInBorder(grid[4][1]);
		capital.unEmploy(new Scientist());
		capital.work(grid[4][1]);

		game.setCurrentTurn(114);
		capital.completeBuildable();
		capital.startBuilding(MAUSOLEUM_OF_HALICARNASSUS);

		game.setCurrentTurn(115);
		capital.grow();
		grid[2][3].addImprovement(new LumberMill());
		capital.work(grid[2][3]);

		game.setCurrentTurn(117);
		player.discover(GUILDS);

		game.setCurrentTurn(119);
		player.adopt(SocialPolicy.MANDATE_OF_HEAVEN);

		game.setCurrentTurn(120);
		capital.grow();
		capital.employ(new Scientist());

		game.setCurrentTurn(121);
		capital.completeBuildable();
		player.discover(COMPASS);
		capital.startBuilding(TEMPLE);

		game.setCurrentTurn(123);
		capital.completeBuildable();
		capital.startBuilding(GRAND_TEMPLE);

		game.setCurrentTurn(124);
		grid[1][1].addImprovement(new LumberMill());
		capital.unEmploy(new Scientist());
		capital.work(grid[1][1]);

		game.setCurrentTurn(125);
		capital.grow();
		capital.employ(new Scientist());

		game.setCurrentTurn(126);
		game.completed(PYRAMIDS);
		player.discover(CHIVALRY);
		capital.build(CATHEDRAL.create(capital, player));

		game.setCurrentTurn(127);
		capital.completeBuildable();
		capital.startBuilding(WORKSHOP);

		game.setCurrentTurn(130);
		capital.completeBuildable();
		capital.grow();
		capital.unEmploy(new Scientist());
		capital.employ(new Engineer());
		capital.work(grid[4][3]);
		capital.startBuilding(OXFORD_UNIVERSITY);

		game.setCurrentTurn(131);
		player.discover(MACHINERY);
		capital.addTileInBorder(grid[0][1]);
		grid[0][1].addImprovement(new Mine());
		capital.unEmploy(new Scientist());
		capital.work(grid[0][1]);

		game.setCurrentTurn(134);
		capital.completeBuildable();
		player.discover(ASTRONOMY);
		capital.startBuilding(IRON_WORKS);

		game.setCurrentTurn(136);
		player.discover(PHYSICS);
		capital.grow();
		capital.employ(new Scientist());

		game.setCurrentTurn(137);
		capital.completeBuildable();
		grid[0][2].addImprovement(new Farm());
		capital.addTileInBorder(grid[0][2]);
		capital.unEmploy(new Scientist());
		capital.work(grid[0][2]);
		capital.startBuilding(NOTRE_DAME);

		game.setCurrentTurn(138);
		player.addBelief(Belief.PAGODAS);
		player.addBelief(Belief.RELIGIOUS_TEXTS);

		game.setCurrentTurn(141);
		player.discover(STEEL);
		capital.grow();
		capital.employ(new Scientist());
		player.adopt(SocialPolicy.THEOCRACY);

		game.setCurrentTurn(144);
		capital.completeBuildable();
		grid[1][4].addImprovement(new Farm());
		capital.startBuilding(STABLE);

		game.setCurrentTurn(146);
		capital.completeBuildable();
		game.completed(TERRA_COTTA_ARMY);
		capital.startBuilding(UnitEnum.WORK_BOAT);

		game.setCurrentTurn(147);
		capital.completeBuildable();
		grid[6][5].addImprovement(new Manufactory());
		grid[3][4].addImprovement(new WorkBoats());
		capital.unEmploy(new Scientist());
		capital.addTileInBorder(grid[6][5]);
		capital.work(grid[6][5]);
		capital.build(PAGODA.create(capital, player));
		player.discover(ACOUSTICS);
		capital.startBuilding(UnitEnum.WORK_BOAT);

		game.setCurrentTurn(148);
		capital.completeBuildable();
		capital.grow();
		capital.employ(new Scientist());
		grid[4][5].addImprovement(new WorkBoats());
		capital.startBuilding(UnitEnum.WORK_BOAT);

		game.setCurrentTurn(149);
		capital.completeBuildable();
		grid[3][5].addImprovement(new WorkBoats());
		capital.startBuilding(UnitEnum.WORK_BOAT);
		capital.unWork(grid[0][2]);
		capital.employ(new Scientist());
		capital.startBuilding(UnitEnum.WORK_BOAT);

		game.setCurrentTurn(150);
		capital.completeBuildable();
		game.completed(PARTHENON);
		player.startGoldenAge();
		grid[5][5].addImprovement(new WorkBoats());
		capital.unWork(grid[4][3]);
		capital.addTileInBorder(grid[1][4]);
		capital.work(grid[1][4]);
		capital.startBuilding(EAST_INDIA_COMPANY);

		game.setCurrentTurn(152);
		capital.completeBuildable();
		capital.startBuilding(NATIONAL_EPIC);

		game.setCurrentTurn(153);
		capital.completeBuildable();
		capital.startBuilding(CHICHEN_ITZA);

		game.setCurrentTurn(154);
		player.discover(BANKING);

		game.setCurrentTurn(155);
		capital.grow();
		capital.employ(new Merchant());

		game.setCurrentTurn(157);
		capital.completeBuildable();
		capital.startBuilding(BANK);

		game.setCurrentTurn(159);
		grid[6][3].addImprovement(new TradingPost());
		player.adopt(SocialPolicy.RELGIOUS_TOLERANCE);
		capital.unEmploy(new Merchant());
		capital.addTileInBorder(grid[6][3]);
		capital.work(grid[6][3]);

		game.setCurrentTurn(160);
		capital.completeBuildable();
		capital.startBuilding(ORACLE);

		game.setCurrentTurn(161);
		player.discover(PRINTING_PRESS);

		game.setCurrentTurn(163);
		capital.grow();
		capital.work(grid[4][3]);
		capital.completeBuildable();
		player.adopt(SocialPolicy.REFORMATION);
		player.addBelief(Belief.SACRED_SITES);
		capital.startBuilding(SISTINE_CHAPEL);

		game.setCurrentTurn(164);
		grid[6][4].addImprovement(new HolySite());
		capital.addTileInBorder(grid[6][4]);
		capital.unWork(grid[1][4]);
		capital.work(grid[6][4]);

		game.setCurrentTurn(166);
		grid[0][0].addImprovement(new Academy());
		capital.addTileInBorder(grid[0][0]);
		capital.unEmploy(new Scientist());
		capital.work(grid[0][0]);

		game.setCurrentTurn(167);
		player.discover(GUNPOWDER);
		grid[3][6].addImprovement(new HolySite());
		capital.unEmploy(new Scientist());
		capital.work(grid[3][6]);

		game.setCurrentTurn(170);
		capital.completeBuildable();
		capital.startBuilding(WRITERS_GUILD);

		game.setCurrentTurn(171);
		capital.completeBuildable();
		capital.startBuilding(ALHAMBRA);
		capital.grow();
		capital.unWork(grid[1][1]);
		capital.employ(new Writer());
		capital.employ(new Writer());

		game.setCurrentTurn(175);
		player.adopt(SocialPolicy.RATIONALISM);

		game.setCurrentTurn(177);
		capital.completeBuildable();
		capital.startBuilding(GREAT_WALL);

		game.setCurrentTurn(180);
		game.completed(GREAT_WALL);
		Civilization india = new Civilization("India", Influence.POPULAR);
		Civilization byzantium = new Civilization("Byzantium", Influence.EXOTIC);
		Civilization denmark = new Civilization("Denmark", Influence.INFLUENTIAL);
		Civilization mongolia = new Civilization("Mongolia", Influence.FAMILIAR);
		Civilization persia = new Civilization("Persia", Influence.POPULAR);
		Civilization theAztecs = new Civilization("The Aztecs", Influence.POPULAR);
		Civilization theIncas = new Civilization("The Incas", Influence.POPULAR);
		TargetTradeCity tenoch = new TargetTradeCity("Tenochtitlan", 6, 0.87, 3, theAztecs);
		TargetTradeCity buenesAires = new TargetTradeCity("Buenes Aires", 6, 0.4, 0);
		TargetTradeCity belGrade = new TargetTradeCity("Belgrade", 6, 0.75, 0);
		TargetTradeCity geneva = new TargetTradeCity("Geneva", 6, 0.68, 0);
		TargetTradeCity turfan = new TargetTradeCity("Turfan", 6, 0.31, 1, mongolia);
		TargetTradeCity oldSarai = new TargetTradeCity("Old Sarai", 4, 0.5, 1, mongolia);
		TargetTradeCity valetta = new TargetTradeCity("Valetta", 6, 0.75, 0);
		capital.addAvailableTradeRoute(tenoch, RouteType.SEA);
		capital.addAvailableTradeRoute(buenesAires, RouteType.SEA);
		capital.addAvailableTradeRoute(belGrade, RouteType.SEA);
		capital.addAvailableTradeRoute(geneva, RouteType.SEA);
		capital.addAvailableTradeRoute(turfan, RouteType.SEA);
		capital.addAvailableTradeRoute(oldSarai, RouteType.SEA);
		capital.addAvailableTradeRoute(valetta, RouteType.SEA);
		CargoShip cargoShip1 = (CargoShip) UnitEnum.CARGO_SHIP.create(player);
		CargoShip cargoShip2 = (CargoShip) UnitEnum.CARGO_SHIP.create(player);
		CargoShip cargoShip3 = (CargoShip) UnitEnum.CARGO_SHIP.create(player);
		CargoShip cargoShip4 = (CargoShip) UnitEnum.CARGO_SHIP.create(player);
		CargoShip cargoShip5 = (CargoShip) UnitEnum.CARGO_SHIP.create(player);
		cargoShip1.setHomeCity(capital);
		cargoShip2.setHomeCity(capital);
		cargoShip3.setHomeCity(capital);
		cargoShip4.setHomeCity(capital);
		cargoShip5.setHomeCity(capital);
		cargoShip1.establishTradeRoute();
		cargoShip2.establishTradeRoute();
		cargoShip3.establishTradeRoute();
		cargoShip4.establishTradeRoute();
		cargoShip5.establishTradeRoute();
		capital.addGreatWorkOfWriting();
		capital.startBuilding(MUSICIANS_GUILD);

		game.setCurrentTurn(182);
		capital.grow();
		grid[3][0].addImprovement(new Farm());
		capital.addTileInBorder(grid[3][0]);
		capital.work(grid[3][0]);

		game.setCurrentTurn(184);
		player.startGoldenAge();
		player.discover(NAVIGATION);
		player.discover(ARCHITECTURE);
		capital.completeBuildable();
		capital.startBuilding(BuildingEnum.BOROBUDUR);
		capital.unEmploy(new Engineer());
		capital.unWork(grid[2][3]);
		capital.employ(new Musician());
		capital.employ(new Musician());

		game.setCurrentTurn(186);
		player.adopt(SocialPolicy.HUMANISM);

		game.setCurrentTurn(187);
		capital.completeBuildable();
		capital.startBuilding(ARTISTS_GUILD);
		grid[6][3].addImprovement(new HolySite());

		game.setCurrentTurn(189);
		capital.completeBuildable();
		capital.unWork(grid[4][2]);
		capital.unWork(grid[0][1]);
		capital.employ(new Artist());
		capital.employ(new Artist());
		capital.startBuilding(BuildingEnum.LEANING_TOWER_OF_PISA);

		game.setCurrentTurn(190);
		grid[5][2].addImprovement(new Manufactory());

		game.setCurrentTurn(191);
		player.discover(Tech.ECONOMICS);

		game.setCurrentTurn(194);
		player.adopt(SocialPolicy.FREE_THOUGHT);
		capital.grow();
		capital.work(grid[0][2]);

		game.setCurrentTurn(196);
		capital.completeBuildable();
		capital.addGreatWorkOfArt();
		capital.startBuilding(BuildingEnum.CIRCUS);

		game.setCurrentTurn(197);
		capital.completeBuildable();
		capital.startBuilding(BuildingEnum.COLLESEUM);

		game.setCurrentTurn(198);
		capital.completeBuildable();
		capital.startBuilding(BuildingEnum.HAGIA_SOPHIA);

		game.setCurrentTurn(199);
		player.discover(Tech.METALLURGY);

		game.setCurrentTurn(200);
		capital.addGreatWorkOfWriting();

		game.setCurrentTurn(202);
		player.adopt(SocialPolicy.RATIONALISM);

		game.setCurrentTurn(203);
		capital.completeBuildable();
		capital.startBuilding(BuildingEnum.FORGE);

		game.setCurrentTurn(205);
		capital.completeBuildable();
		capital.addGreatWorkOfArt();
		capital.startBuilding(BuildingEnum.HIMEJI_CASTLE);

		game.setCurrentTurn(206);
		game.completed(BuildingEnum.PETRA);
		player.discover(Tech.CHEMISTRY);
		capital.unWork(grid[5][4]);
		capital.work(grid[0][1]);

		game.setCurrentTurn(207);
		capital.grow();
		capital.work(grid[5][4]);

		game.setCurrentTurn(212);
		capital.completeBuildable();
		capital.startBuilding(BuildingEnum.CIRCUS_MAXIMUS);
		player.adopt(SocialPolicy.SOVEREIGNTY);

		game.setCurrentTurn(214);
		capital.completeBuildable();
		player.discover(Tech.ARCHAEOLOGY);
		capital.startBuilding(BuildingEnum.ZOO);

		game.setCurrentTurn(216);
		capital.completeBuildable();
		capital.startBuilding(BuildingEnum.WINDMILL);

		game.setCurrentTurn(220);
		capital.completeBuildable();
		capital.startBuilding(BuildingEnum.TAJ_MAHAL);

		game.setCurrentTurn(222);
		player.adopt(SocialPolicy.SCIENTIFIC_REVOLUTION);
		capital.grow();
		capital.employ(new Scientist());
		player.discover(Tech.INDUSTRIALIZATION);
		capital.addTileInBorder(grid[0][3]);
		capital.addTileInBorder(grid[2][4]);
		capital.addTileInBorder(grid[2][5]);
		capital.addTileInBorder(grid[4][6]);
		capital.addTileInBorder(grid[5][6]);
		capital.addTileInBorder(grid[6][6]);
		grid[0][3].addImprovement(new TradingPost());
		capital.work(grid[0][0]);
		capital.work(grid[0][1]);
		capital.work(grid[0][2]);
		capital.work(grid[1][2]);
		capital.work(grid[1][3]);
		capital.work(grid[2][0]);
		capital.work(grid[2][1]);
		capital.work(grid[2][2]);
		capital.work(grid[3][0]);
		capital.work(grid[3][1]);
		capital.work(grid[3][2]);
		capital.work(grid[3][3]);
		capital.work(grid[3][4]);
		capital.work(grid[3][5]);
		capital.work(grid[3][6]);
		capital.work(grid[4][1]);
		capital.work(grid[4][3]);
		capital.work(grid[4][4]);
		capital.work(grid[4][5]);
		capital.work(grid[5][2]);
		capital.work(grid[5][3]);
		capital.work(grid[5][4]);
		capital.work(grid[5][5]);
		capital.work(grid[6][3]);
		capital.work(grid[6][4]);
		capital.work(grid[6][5]);

		game.setCurrentTurn(222);
		player.discover(Tech.SCIENTIFIC_THEORY);

		game.setCurrentTurn(223);
		player.startGoldenAge();
		capital.addGreatWorkOfWriting();

		game.setCurrentTurn(226);
		capital.completeBuildable();
		capital.startBuilding(BuildingEnum.PUBLIC_SCHOOL);
		capital.addGreatWorkOfArt();
		capital.unEmploy(new Scientist());
		capital.work(grid[0][3]);

		game.setCurrentTurn(230);
		capital.completeBuildable();
		capital.startBuilding(BuildingEnum.FACTORY);

		game.setCurrentTurn(232);
		player.adopt(SocialPolicy.EXPLORATION);

		game.setCurrentTurn(234);
		capital.completeBuildable();
		capital.startBuilding(BuildingEnum.PORCELAIN_TOWER);

		game.setCurrentTurn(236);
		player.discover(Tech.MILITARY_SCIENCE);

		game.setCurrentTurn(238);
		game.completed(ANGKOR_WAT);
		capital.grow();
		capital.employ(new Engineer());

		game.setCurrentTurn(240);
		capital.completeBuildable();
		capital.startBuilding(BRANDENBURG_GATE);
		player.discover(FERTILIZER);

		game.setCurrentTurn(242);
		player.adopt(SocialPolicy.MARITIME_INFRASTRUCTURE);

		game.setCurrentTurn(245);
		player.discover(BIOLOGY);
		CargoShip cargoShip6 = (CargoShip) UnitEnum.CARGO_SHIP.create(player);
		cargoShip6.setHomeCity(capital);
		cargoShip6.establishTradeRoute();

		game.setCurrentTurn(246);
		capital.completeBuildable();
		capital.startBuilding(HOSPITAL);

		game.setCurrentTurn(250);
		capital.completeBuildable();
		capital.startBuilding(GLOBE_THEATER);

		game.setCurrentTurn(253);
		player.discover(ELECTRICITY);
		player.adopt(NAVAL_TRADITION);

		game.setCurrentTurn(254);
		capital.completeBuildable();
		capital.startBuilding(STOCK_EXCHANGE);
		capital.addGreatWorkOfWriting();

		game.setCurrentTurn(254);
		capital.addGreatWorkOfArt();
		grid[0][1].addImprovement(new Manufactory());
		capital.grow();
		capital.employ(new Engineer());

		game.setCurrentTurn(257);
		capital.completeBuildable();
		capital.startBuilding(OPERA_HOUSE);

		game.setCurrentTurn(259);
		capital.completeBuildable();
		capital.startBuilding(HERMITAGE);
		capital.addGreatWorkOfMusic();

		game.setCurrentTurn(261);
		capital.completeBuildable();
		capital.startBuilding(RED_FORT);

		game.setCurrentTurn(262);
		capital.grow();
		capital.employ(new Scientist());
		player.discover(STEAM_POWER);

		game.setCurrentTurn(264);
		player.adopt(SocialPolicy.MERCHANT_NAVY);

		game.setCurrentTurn(266);
		capital.completeBuildable();
		capital.startBuilding(HARBOR);
		capital.addGreatWorkOfWriting();

		game.setCurrentTurn(267);
		capital.completeBuildable();
		capital.startBuilding(SEAPORT);
		player.adopt(FREEDOM);
		player.adopt(SocialPolicy.CIVIL_SOCIETY);
		player.adopt(SocialPolicy.AVANT_GARDE);

		game.setCurrentTurn(270);
		capital.completeBuildable();
		capital.startBuilding(LOUVRE);

		game.setCurrentTurn(274);
		player.discover(REFRIGERATION);
		player.adopt(SocialPolicy.TREASURE_FLEETS);
		TargetTradeCity constantinople = new TargetTradeCity("Constantinople", 6, 1.27, 1, byzantium);
		TargetTradeCity milan = new TargetTradeCity("Milan", 5, 0.29, 0);
		TargetTradeCity monaco = new TargetTradeCity("Monaco", 6, 0.68, 0);
		TargetTradeCity ormus = new TargetTradeCity("Ormus", 6, 0.45, 0);
		TargetTradeCity delhi = new TargetTradeCity("Delhi", 5, 1.87, 2, india);
		capital.addAvailableTradeRoute(constantinople, RouteType.SEA);
		capital.addAvailableTradeRoute(milan, RouteType.SEA);
		capital.addAvailableTradeRoute(monaco, RouteType.SEA);
		capital.addAvailableTradeRoute(ormus, RouteType.SEA);
		capital.addAvailableTradeRoute(delhi, RouteType.SEA);
		cargoShip1.reestablishTradeRoute();

		game.setCurrentTurn(275);
		cargoShip2.reestablishTradeRoute();

		game.setCurrentTurn(276);
		capital.completeBuildable();
		capital.addGreatWorkOfArt();
		capital.startBuilding(HOTEL);

		game.setCurrentTurn(279);
		player.discover(RADIO);
		capital.completeBuildable();
		capital.startBuilding(EIFEL_TOWER);
		capital.grow();
		capital.employ(new Scientist());

		game.setCurrentTurn(281);
		player.startGoldenAge();
		cargoShip4.reestablishTradeRoute();
		cargoShip4.reestablishTradeRoute();

		game.setCurrentTurn(282);
		capital.grow();
		capital.employ(new Engineer());

		game.setCurrentTurn(283);
		theAztecs.setInfluence(Influence.INFLUENTIAL);
		player.adopt(SocialPolicy.NEW_DEAL);

		game.setCurrentTurn(285);
		player.discover(REPLACEABLE_PARTS);
		game.embargoCityStates();
		cargoShip1.reestablishTradeRoute();
		cargoShip2.reestablishTradeRoute();
		cargoShip3.reestablishTradeRoute();
		cargoShip4.reestablishTradeRoute();
		cargoShip6.reestablishTradeRoute();
		cargoShip5.cancelTradeRoute();

		game.setCurrentTurn(286);
		capital.completeBuildable();
		capital.startBuilding(BuildingEnum.STATUE_OF_LIBERTY);
		mongolia.setInfluence(Influence.POPULAR);
		byzantium.setInfluence(Influence.FAMILIAR);

		game.setCurrentTurn(291);
		capital.grow();
		capital.employ(new Engineer());

		game.setCurrentTurn(293);
		capital.completeBuildable();
		capital.startBuilding(BuildingEnum.BROADWAY);

		game.setCurrentTurn(293);
		capital.completeBuildable();
		capital.startBuilding(BuildingEnum.MUSEUM);
		capital.addGreatWorkOfMusic();
		capital.addGreatWorkOfMusic();
		capital.addGreatWorkOfMusic();

		game.setCurrentTurn(295);
		capital.addGreatWorkOfArt();
		capital.completeBuildable();
		capital.startBuilding(BuildingEnum.BROADCAST_TOWER);
		player.discover(FLIGHT);

		game.setCurrentTurn(298);
		capital.completeBuildable();
		theIncas.setInfluence(Influence.INFLUENTIAL);
		capital.startBuilding(STADIUM);

		game.setCurrentTurn(300);
		india.setInfluence(Influence.INFLUENTIAL);

		game.setCurrentTurn(301);
		capital.completeBuildable();
		capital.startBuilding(WALLS);

		game.setCurrentTurn(302);
		capital.completeBuildable();
		capital.employ(new Merchant());
		capital.grow();
		capital.startBuilding(BARRACKS);

		game.setCurrentTurn(303);
		capital.completeBuildable();
		capital.startBuilding(BuildingEnum.CARAVANSARY);
		theAztecs.setInfluence(Influence.INFLUENTIAL);
		player.adopt(SocialPolicy.VOLUNTEER_ARMY);

		game.setCurrentTurn(304);
		capital.completeBuildable();
		capital.startBuilding(HEROIC_EPIC);
		player.discover(RAILROAD);
		persia.setInfluence(Influence.INFLUENTIAL);

		game.setCurrentTurn(305);
		capital.completeBuildable();
		capital.startBuilding(ARMORY);

		game.setCurrentTurn(306);
		capital.completeBuildable();
		capital.startBuilding(BuildingEnum.CONSTABULARY);

		game.setCurrentTurn(307);
		capital.completeBuildable();
		capital.startBuilding(BuildingEnum.ARSENAL);

		game.setCurrentTurn(308);
		capital.startWeLoveTheKingDay();

		game.setCurrentTurn(309);
		capital.completeBuildable();
		capital.startBuilding(BuildingEnum.MILITARY_ACADEMY);
		capital.addGreatWorkOfArt();
		capital.addGreatWorkOfMusic();
		player.discover(PLASTICS);

		game.setCurrentTurn(311);
		capital.completeBuildable();
		capital.startBuilding(BuildingEnum.REASEARCH_LAB);
		byzantium.setInfluence(Influence.FAMILIAR);
		denmark.setInfluence(Influence.DOMINANT);
		mongolia.setInfluence(Influence.INFLUENTIAL);

		game.setCurrentTurn(312);
		capital.grow();
		capital.employ(new Merchant());
		
		game.setCurrentTurn(313);
		player.adopt(SocialPolicy.MEDIA_CULTURE);

		game.setCurrentTurn(314);
		capital.completeBuildable();
		capital.startBuilding(BuildingEnum.CRISTO_REDENTOR);

		game.setCurrentTurn(315);
		player.discover(ELECTRONICS);


		game.setCurrentTurn(322);
		capital.grow();
		capital.employ(new Scientist());
		capital.completeBuildable();
		capital.startBuilding(BuildingEnum.POLICE_STATION);
		player.adopt(SocialPolicy.CREATIVE_EXPRESSION);
		
		game.setCurrentTurn(323);
		player.startGoldenAge();
		capital.completeBuildable();
		capital.startBuilding(NATIONAL_INTELLIGENCE_AGENCY);
		
		game.setCurrentTurn(324);
		player.startGoldenAge();
		capital.completeBuildable();
		capital.startBuilding(BuildingEnum.MILITARY_BASE);
		player.discover(BALLISTICS);
		
		game.setCurrentTurn(327);
		capital.completeBuildable();
		
		game.setCurrentTurn(330);
		player.adopt(SocialPolicy.THEIR_FINEST_HOUR);
		
		game.setCurrentTurn(333);
		player.discover(Tech.COMBUSTION);
		
		game.setCurrentTurn(338);
		player.adopt(SocialPolicy.SPACE_PROCUREMENTS);
		
		game.setCurrentTurn(339);
		capital.addGreatWorkOfArt();
		
		game.setCurrentTurn(340);
		player.discover(PENICILLIN);
		capital.grow();
		capital.employ(new Scientist());
		capital.grow();
		capital.employ(new Merchant());
		capital.startBuilding(MEDICAL_LAB);
		TargetTradeCity nicaea = new TargetTradeCity("Nicaea", 5, .06, 0, byzantium);
		TargetTradeCity adrianople = new TargetTradeCity("Adrianople", 6, .45, 1, byzantium);
		capital.addAvailableTradeRoute(adrianople, RouteType.SEA);
		capital.addAvailableTradeRoute(nicaea, RouteType.SEA);
		cargoShip5.establishTradeRoute();
		theAztecs.setInfluence(Influence.DOMINANT);
		mongolia.setInfluence(Influence.DOMINANT);
		byzantium.setInfluence(Influence.INFLUENTIAL);
		india.setInfluence(Influence.INFLUENTIAL);
		
		game.setCurrentTurn(344);
		capital.completeBuildable();
		capital.startBuilding(UnitEnum.CARGO_SHIP);
		
		game.setCurrentTurn(344);
		capital.completeBuildable();
		player.discover(ATOMIC_THEORY);
		CargoShip cargoShip7 = new CargoShip(player);
		cargoShip7.setHomeCity(capital);
		cargoShip7.establishTradeRoute();
		
		System.out.println(game.getTurnsLeft() + " turns left.");
		// player.printUnitList();
		capital.printOutput();
		 capital.getTradeRoutes().stream().sorted((t1, t2) ->
		 t2.getGoldYield().compareTo(t1.getGoldYield()))
		 .forEach(t -> System.out.println(t.getSourceCity().getName() + " -> "
		 + t.getTargetCity().getName()
		 + ": " + MathUtil.round(t.getGoldYield(), 1) + (t.isAvailable() ? ""
		 : "(*)")));

		printAvailableBuildings(game, capital, player);

	}

	private static void printBuildingOutputPerGame(Game game, double output, int turnsToBuild, String name) {

		double outputForGame = outputForGame(game, output, turnsToBuild);
		if (outputForGame > 0) {
			System.out.println("\t" + MathUtil.round(outputForGame, 1) + "(" + output + ") " + name);
		}
	}

	private static void printAvailableBuildings(Game game, City capital, Player player) {
		List<Buildable> available = BuildingsUtil.availableBuildings(capital, player, game);
		if (capital.getCurrentlyConstructing() == null) {
			for (Buildable building : available) {
				System.out
						.println(building.getName() + " can be built in " + building.turnsToBuild(capital) + " turns.");
				if (building instanceof Building) {
					printBuildingSlots(building);
					printBonusUnits((Building) building);
					printBuildingMaintenancePerGame(game, (Building) building);
				}
				System.out.println("  Produces:");
				printBuildingOutputsPerGame(game, building, capital);
				printBuildingAnalytics(available, building, capital);
			}
		}
	}

	private static void printBuildingAnalytics(List<Buildable> available, Buildable building, City city) {
		System.out.println("  Analytics:");
		System.out.println("\t" + building.outputTotalPerTurn(city) + " per turn output.");
		Map<String, Double> queue = new HashMap<String, Double>();
		for (Buildable buildingcomp : available) {
			double otherOpCost = building.turnsToBuild(city) * buildingcomp.outputTotalPerTurn(city);
			double selfOpCost = buildingcomp.turnsToBuild(city) * building.outputTotalPerTurn(city);
			double value = (otherOpCost - selfOpCost);
			queue.put(buildingcomp.getName(), value);
		}
		Map<String, Double> sortedMap = queue.entrySet().stream().sorted(Entry.comparingByValue())
				.collect(Collectors.toMap(Entry::getKey, Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));

		for (Entry<String, Double> entry : sortedMap.entrySet()) {
			System.out.println("\t" + entry.getKey() + " " + entry.getValue());
		}
	}

	private static void printBonusUnits(Building building) {
		List<Unit> bonusUnits = building.createsUnits();
		if (bonusUnits.size() > 0) {
			System.out.println("  Bonus Units:");
			System.out.print("\t");
			for (Unit unit : bonusUnits) {
				System.out.print(unit.getName() + ",");
			}
			System.out.print("\r\n");
		}

	}

	private static void printBuildingOutputsPerGame(Game game, Buildable building, City city) {
		Map<Outputs, Double> outputs = building.getOutputs(city);
		for (Outputs output : Outputs.values()) {
			printBuildingOutputPerGame(game, outputs.get(output), building.turnsToBuild(city), output.toString());
		}
	}

	private static void printBuildingSlots(Buildable buildable) {
		StringBuilder sb = new StringBuilder();
		if (buildable instanceof Building) {
			Building building = (Building) buildable;
			appendNullSafe(printBuildingSlot(building.getGreatWorkOfArtSlots(), "Great Work of Art or Artifact"), sb);
			appendNullSafe(printBuildingSlot(building.getGreatWorkOfWritingSlots(), "Great Work of Writing"), sb);
			appendNullSafe(printBuildingSlot(building.getGreatWorkOfMusicSlots(), "Great Work of Music"), sb);
			appendNullSafe(printBuildingSlot(building.getWriterSlots(), "Writer"), sb);
			appendNullSafe(printBuildingSlot(building.getArtistSlots(), "Artist"), sb);
			appendNullSafe(printBuildingSlot(building.getMusicianSlots(), "Musician"), sb);
			appendNullSafe(printBuildingSlot(building.getScientistSlots(), "Scientist"), sb);
			appendNullSafe(printBuildingSlot(building.getEngineerSlots(), "Engineer"), sb);
			appendNullSafe(printBuildingSlot(building.getMerchantSlots(), "Merchant"), sb);
		}

		if (sb.length() > 0) {
			System.out.println("  Contains:");
			System.out.print(sb.toString());
		}

	}

	private static void appendNullSafe(String message, StringBuilder sb) {
		if (StringUtils.isNotBlank(message)) {
			sb.append(message);
		}
	}

	private static String printBuildingSlot(int numSlots, String slotName) {
		String slotsMessage = null;
		if (numSlots > 0) {
			slotsMessage = "\t" + numSlots + " " + slotName + " slots\r\n";
		}
		return slotsMessage;
	}

	private static void printBuildingMaintenancePerGame(Game game, Building building) {

		double maintenance = outputForGame(game, building.getMaintenancePerTurn(), building.turnsToBuild());
		if (maintenance > 0) {
			System.out.println("  Maintenance Costs:");
			System.out.println("\t" + maintenance + " Gold");
		}
	}

	private static double outputForGame(Game game, double outputPerTurn, int turns) {
		return (game.getTurnsLeft() - turns) * outputPerTurn;
	}
}
