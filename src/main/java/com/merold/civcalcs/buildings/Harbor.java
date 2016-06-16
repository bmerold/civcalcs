package com.merold.civcalcs.buildings;

import com.merold.civcalcs.city.City;
import com.merold.civcalcs.city.RouteType;
import com.merold.civcalcs.city.TradeRoute;
import com.merold.civcalcs.player.Player;
import com.merold.civcalcs.socialpolicies.SocialPolicy;

public class Harbor extends Building {

	public Harbor(City city, Player owner) {
		super(BuildingEnum.HARBOR, city, owner, 120);
		maintenancePerTurn = 2;
		// TODO: Add naval city connection.
		// TODO: Add 50% range to sea trade routes.
		// TODO: Add 2 gold for sea trade routes.
	}

	@Override
	public double getTradeRouteGold(TradeRoute route) {
		double gold = 0;
		if (route.routeType == RouteType.SEA) {
			gold += 1;
		}
		return gold;
	}

	@Override
	public double getHappinessPerTurn() {
		double happiness = 0;
		if (owner.hasAdopted(SocialPolicy.NAVAL_TRADITION)) {
			happiness += 1;
		}
		return happiness;
	}

	@Override
	public double getBaseGoldPerTurn() {
		double gold = 0;
		if (owner.hasAdopted(SocialPolicy.MERCHANT_NAVY)) {
			gold += 1;
		}
		return gold;
	}

}
