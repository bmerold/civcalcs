package com.merold.civcalcs.player;

import java.util.ArrayList;
import java.util.List;

import com.merold.civcalcs.Belief;
import com.merold.civcalcs.Game;
import com.merold.civcalcs.buildings.Building;
import com.merold.civcalcs.buildings.BuildingEnum;
import com.merold.civcalcs.buildings.wonders.Wonder;
import com.merold.civcalcs.city.City;
import com.merold.civcalcs.science.Tech;
import com.merold.civcalcs.socialpolicies.SocialPolicy;
import com.merold.civcalcs.socialpolicies.SocialPolicyUtil;
import com.merold.civcalcs.tiles.Resource;
import com.merold.civcalcs.units.Unit;
import com.merold.civcalcs.units.UnitEnum;

public class Player {
	List<City> cities = new ArrayList<City>();

	List<Tech> techs = new ArrayList<Tech>();
	List<SocialPolicy> socialPolicies = new ArrayList<SocialPolicy>();
	List<Wonder> wonders = new ArrayList<Wonder>();
	List<Unit> units = new ArrayList<Unit>();
	List<Resource> resources = new ArrayList<Resource>();
	int tradeRoutes = 0;
	int tradeRoutesUsed = 0;

	public boolean hasOpenTradeRoutes() {
		return tradeRoutesUsed < tradeRoutes;
	}

	private City capital;

	private boolean goldenAge;

	private int goldenAgeEndTurn = 0;

	public List<City> getCities() {
		return cities;
	}

	public List<Tech> getTechs() {
		return techs;
	}

	public List<Wonder> getWonders() {
		return wonders;
	}

	public List<Unit> getUnits() {
		return units;
	}

	public boolean isGoldenAge() {
		return goldenAge;
	}

	public int getGoldenAgeEndTurn() {
		return goldenAgeEndTurn;
	}

	public Game getGame() {
		return game;
	}

	public List<Belief> getPantheon() {
		return pantheon;
	}

	private Game game;
	private List<Belief> pantheon = new ArrayList<Belief>();;

	public Player(Game game) {
		this.game = game;
	}

	public void discover(Tech tech) {
		techs.add(tech);
		if (tech == Tech.DRAMA_AND_POETRY && hasAdopted(SocialPolicy.LEGALISM)) {
			int builtCultureBuildings = 0;
			for (City city : cities) {
				city.build(BuildingEnum.AMPHITHEATER.create(city, this));
				builtCultureBuildings++;
				if (builtCultureBuildings >= 4) {
					break;
				}
			}
		}

		if (tech == Tech.ANIMAL_HUSBANDRY || tech == Tech.SAILING || tech == Tech.ENGINEERING || tech == Tech.COMPASS
				|| tech == Tech.BANKING || tech == Tech.BIOLOGY || tech == Tech.RAILROAD || tech == Tech.PENICILLIN) {
			tradeRoutes += 1;
		}
	}

	public boolean hasDiscoveredTech(Tech tech) {
		if (tech == null) {
			return true;
		} else {
			return techs.contains(tech);
		}
	}

	public void addCity(City city) {
		cities.add(city);
		if (city.hasBuilding(BuildingEnum.PALACE)) {
			this.capital = city;
		}

	}

	public void adopt(SocialPolicy tradition) {
		socialPolicies.add(tradition);
		if (SocialPolicyUtil.playerHasCompletedTradition(this)) {
			if (!capital.hasBuilding(BuildingEnum.AQUEDUCT)) {
				capital.build(BuildingEnum.AQUEDUCT.create(capital, this));
			}
		}

		if (tradition == SocialPolicy.CITIZENSHIP) {
			recruit(UnitEnum.WORKER.create(this));
			// TODO: Figure out how to quantify the worker improvement speed
			// bonus.
		}
	}

	public City getCapital() {
		return capital;

	}

	public boolean hasAdopted(SocialPolicy policy) {
		if (policy == null) {
			return true;
		} else {
			return socialPolicies.contains(policy);
		}
	}

	public void startGoldenAge() {
		this.goldenAge = true;
		int duration = 10;
		if (hasCompleted(BuildingEnum.CHICHEN_ITZA)) {
			duration = (int) Math.floor(duration * 1.5);
		}
		this.goldenAgeEndTurn = game.currentTurn() - 1 + duration;
	}

	public boolean isInGoldenAge() {
		if (goldenAgeEndTurn < game.currentTurn()) {
			goldenAge = false;
		}
		return goldenAge;
	}

	public void addBelief(Belief pantheon) {
		this.pantheon.add(pantheon);
	}

	public boolean believes(Belief belief) {
		if (belief == null) {
			return true;
		} else {
			return pantheon.contains(belief);
		}
	}

	public void complete(Wonder wonder) {
		game.completed(wonder.getType());
		wonders.add(wonder);
	}

	public boolean hasCompleted(BuildingEnum name) {
		for (Wonder wonder : wonders) {
			if (wonder.getType() == name) {
				return true;
			}
		}
		return false;
	}

	public void recruit(List<Unit> units) {
		units.addAll(units);
	}

	public void recruit(Unit unit) {
		units.add(unit);
	}

	public List<SocialPolicy> getSocialPolicies() {
		// TODO Auto-generated method stub
		return socialPolicies;
	}

	public boolean hasBuildingInAllCities(BuildingEnum requiredBuilding) {
		if (requiredBuilding == null) {
			return true;
		}
		for (City city : cities) {
			if (!city.hasBuilding(requiredBuilding)) {
				return false;
			}
		}
		return true;
	}

	public void connect(Resource resource) {
		if (!resources.contains(resource)) {
			resources.add(resource);
		}
	}

	public boolean hasConnected(Resource resource) {
		if (resource == null) {
			return true;
		} else {
			return resources.contains(resource);
		}
	}

	public void printUnitList() {
		units.stream().forEach(u -> System.out.println(u.getName()));

	}

	public void deleteUnit(UnitEnum settler) {
		Unit delete = null;
		for (Unit unit : units) {
			if (unit.getType() == settler) {
				delete = unit;
				break;
			}
		}
		units.remove(delete);

	}

	public void tradeRouteEstablished() {
		tradeRoutesUsed += 1;
	}
	public void removeTradeRoute() {
		tradeRoutesUsed -= 1;
	}
}
