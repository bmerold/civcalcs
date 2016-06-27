package com.merold.civcalcs.units;

import java.util.Optional;

import com.merold.civcalcs.city.City;
import com.merold.civcalcs.city.RouteType;
import com.merold.civcalcs.city.TradeRoute;
import com.merold.civcalcs.player.Player;

public class Caravan extends Unit {
	
	public TradeRoute getRoute() {
		return route;
	}

	private City homeCity;
	private TradeRoute route;

	public Caravan(Player player) {
		super(UnitEnum.CARAVAN, player, 75);
	}

	@Override
	public double getGoldYield(City city) {
		if (city.getOwner().hasOpenTradeRoutes()) {
			return city.getTradeRoutes()
					.stream()
					.filter(t -> t.isAvailable())
					.filter(t -> t.routeType == RouteType.LAND)
					.filter(t -> {
						if (t.getSourceCity().getOwner().getGame().isCityStateEmbargo() == false) {
							return true;
						} else {
							if (t.getTargetCity().getCiv() == null) {
								return false;
							} else {
								return true;
							}
						}
						})
					.sorted((t1, t2) -> t2.getGoldYield().compareTo(t1.getGoldYield()))
					.findFirst()
					.map(t -> t.getGoldYield()).orElse((double) 0);
		} else {
			return 0;
		}

	}

	public City getHomeCity() {
		return homeCity;
	}

	public void setHomeCity(City homeCity) {
		this.homeCity = homeCity;
	}

	public void establishTradeRoute() {
		if (!owner.hasOpenTradeRoutes()) {
			throw new RuntimeException("No open trade route slots.");
		}

		Optional<TradeRoute> tradeRoute = homeCity.getTradeRoutes().stream()
				.filter(t -> t.isAvailable())
				.filter(t -> t.routeType == RouteType.LAND)
				.filter(t -> {
					boolean cityStateEmbargo = t.getSourceCity().getOwner().getGame().isCityStateEmbargo();
					boolean cityState =  t.getTargetCity().getCiv() == null;
					if (cityStateEmbargo && cityState) {
						return false;
					} else {
						return true;
					}
				})
				.sorted((t1, t2) -> t2.getGoldYield().compareTo(t1.getGoldYield()))
				.findFirst();
		if (!tradeRoute.isPresent()) {
			throw new RuntimeException("Couldn't find an available trade route.");
		}
		
		TradeRoute route = tradeRoute.get();
		route.assignUnit(this);
		this.route = route;
		owner.tradeRouteEstablished();
	}
	
	public void reestablishTradeRoute() {
		this.route.unassignUnit(this);
		this.route = null;
		owner.removeTradeRoute();
		establishTradeRoute();
	}

	public void cancelTradeRoute() {
		this.route.unassignUnit(this);
		this.route = null;
		owner.removeTradeRoute();
		
	}

}
