package com.merold.civcalcs.city;

import com.merold.civcalcs.socialpolicies.SocialPolicy;
import com.merold.civcalcs.units.Unit;

public class TradeRoute {

	private City sourceCity;
	private TargetTradeCity targetCity;
	public RouteType routeType;
	public Unit assignedUnit = null;

	public boolean isAvailable() {
		return assignedUnit == null;
	}

	public TradeRoute(City sourceCity, TargetTradeCity targetCity, RouteType type) {
		this.sourceCity = sourceCity;
		this.targetCity = targetCity;
		this.routeType = type;
	}

	public Double getGoldYield() {
		double baseGold = 1;
		double goldFromSourceCity = 0.05 * sourceCity.getCityGold();
		double goldFromTargetCity = targetCity.getTotalGold();
		double goldFromResources = 0.5 * targetCity.getNumDifferentResources();
		double goldFromBuildings = targetCity.getBonusGoldFromBuildings()
				+ sourceCity.getTradeRouteGoldFromBuildings(this);
		double totalGold = baseGold;
		totalGold += goldFromSourceCity;
		totalGold += goldFromTargetCity;
		totalGold += goldFromResources;
		totalGold += goldFromBuildings;
		
		double modifier = 1;

		if (routeType == RouteType.SEA) {
			totalGold = totalGold * 2;
			if (this.sourceCity.getOwner().hasAdopted(SocialPolicy.TREASURE_FLEETS)) {
				totalGold += 4;
			}
		}
		
		if (routeType == RouteType.LAND) {
			if (targetCity.isNextToRiver()) {
				modifier += 0.25;
			}
		}
		return totalGold * modifier;

	}

	public City getSourceCity() {
		return sourceCity;
	}

	public TargetTradeCity getTargetCity() {
		return targetCity;
	}

	public double getScienceYield() {
		double science = 0;
		switch (targetCity.getCivInfluence()) {
		case INFLUENTIAL:
			science = 3;
			break;
		case FAMILIAR:
			science = 1;
			break;
		case POPULAR:
			science = 2;
			break;
		case DOMINANT:
			science = 4;
			break;
		default:
			break;
		}
		return science;
	}

	public void assignUnit(Unit unit) {
		if (assignedUnit == null) {
			assignedUnit = unit;
		} else {
			throw new RuntimeException("Can't assign a new Caravan/Cargo Ship to a route that already has one.");
		}
	}

	public void unassignUnit(Unit unit) {
		if (assignedUnit == unit) {
			assignedUnit = null;
			
		} else {
			throw new RuntimeException("Can't unassign a Caravan/Cargo Ship from a route it is not already assigned.");
		}
	}
}
