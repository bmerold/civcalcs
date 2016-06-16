package com.merold.civcalcs.city;

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
		double goldFromBuildings = targetCity.getBonusGoldFromBuildings() + sourceCity.getTradeRouteGoldFromBuildings(this);
		double totalGold = baseGold;
		totalGold += goldFromSourceCity;
		totalGold += goldFromTargetCity;
		totalGold += goldFromResources;
		totalGold += goldFromBuildings;
		
		if (routeType == RouteType.SEA) {
			totalGold = totalGold *2;
		}
		return totalGold;
		
	}
	
	public City getSourceCity() {
		return sourceCity;
	}

	public TargetTradeCity getTargetCity() {
		return targetCity;
	}

	public double getScienceYield() {
		return 0;
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
