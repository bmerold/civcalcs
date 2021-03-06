package com.merold.civcalcs.buildings;

import com.merold.civcalcs.city.City;
import com.merold.civcalcs.city.RouteType;
import com.merold.civcalcs.city.TradeRoute;
import com.merold.civcalcs.player.Player;

public class Caravansary extends Building {

	public Caravansary(City city, Player player) {
		super(BuildingEnum.CARAVANSARY, city, player, 120);
		// TODO: Figure out how to quantify the 50% boost in land trade route distance.
		// TODO: Add bonus gold for connecting with other civs.
	}

	@Override
	public double getTradeRouteGold(TradeRoute route) {
		double gold = 0;
		if (route.routeType == RouteType.LAND) {
			gold += 2;
		}
		return gold;
	}
}
