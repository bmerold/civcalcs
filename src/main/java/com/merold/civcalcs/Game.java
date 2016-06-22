package com.merold.civcalcs;

import java.util.ArrayList;
import java.util.List;

import com.merold.civcalcs.buildings.BuildingEnum;
import com.merold.civcalcs.city.City;
import com.merold.civcalcs.player.Player;
import com.merold.civcalcs.units.CargoShip;

public class Game {

	private int turns = 500;
	private int currentTurn = 0;
	private List<BuildingEnum> builtWonders = new ArrayList<BuildingEnum>();
	private List<Player> players = new ArrayList<Player>();
	private boolean cityStateEmbargo = false;

	public void addPlayer(Player player) {
		players.add(player);
	}

	public void setCurrentTurn(int currentTurn) {
		this.currentTurn = currentTurn;
	}

	public int getTurnsLeft() {
		return turns - currentTurn;
	}

	public boolean isCompleted(BuildingEnum wonder) {
		// TODO Auto-generated method stub
		return builtWonders.contains(wonder);
	}

	public void completed(BuildingEnum wonder) {
		builtWonders.add(wonder);
		players.stream().forEach(
				p -> p.getCities().stream().filter(c -> c.isConstructing(wonder)).forEach(c -> c.cancelConstruction()));

	}

	public int currentTurn() {
		return currentTurn;
	}

	public void embargoCityStates() {
		cityStateEmbargo = true;
		players.stream().flatMap(p -> p.getUnits().stream()).filter(u -> (u instanceof CargoShip)).filter(u -> {
			CargoShip ship = (CargoShip) u;
			boolean cityStateRoute = false;
			if (ship.getRoute() != null && ship.getRoute().getTargetCity().getCiv() == null) {
				cityStateRoute = true;
			}
			return cityStateRoute;
		}).forEach(u -> {
			CargoShip ship = (CargoShip) u;
			ship.cancelTradeRoute();
		});
		;
	}

	public boolean isCityStateEmbargo() {
		return cityStateEmbargo;
	}
}
