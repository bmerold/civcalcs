package com.merold.civcalcs.units;

import java.util.HashMap;
import java.util.Map;

import com.merold.civcalcs.buildings.Outputs;
import com.merold.civcalcs.city.Buildable;
import com.merold.civcalcs.city.City;
import com.merold.civcalcs.player.Player;

public class Unit implements Buildable {
	public String getName() {
		return name.toString();
	}

	protected int productionCost;
	protected UnitEnum name;
	protected Player owner;
	protected int moves;

	public Unit(UnitEnum name, Player owner, int cost) {
		this.name = name;
		this.owner = owner;
		this.productionCost = cost;
	}
	
	public Unit(UnitEnum name, Player owner) {
		this.name = name;
		this.owner = owner;
		this.productionCost = 0;
	}

	@Override
	public int turnsToBuild(City city) {
		double production = city.getProductionFor(this);
		int turnsToBuild = (int) Math.ceil(productionCost / production);
		return turnsToBuild;
	}
	
	public double getFoodYield(City city) {
		return 0;
	}
	
	public double getGoldYield(City city) {
		return 0;
	}
	
	public double outputTotalPerTurn(City city) {
		double outputPerTurnTotal = 0;
		Map<Outputs, Double> outputs = getOutputs(city);
		for (Outputs output : Outputs.values()) {
			outputPerTurnTotal += outputs.get(output);
		}
		return outputPerTurnTotal;
	}
	
	public Map<Outputs, Double> getOutputs(City city) {
		Map<Outputs, Double> outputs = new HashMap<>();
		for (Outputs key : Outputs.values()) {
			outputs.put(key, (double) 0);
		}
		populateOutputMap(outputs, this, city);
		return outputs;
	}
	
	private void populateOutputMap(Map<Outputs, Double> outputs, Unit building, City city) {
		outputs.put(Outputs.GOLD, outputs.get(Outputs.GOLD) + building.getGoldYield(city));
		outputs.put(Outputs.FOOD, outputs.get(Outputs.FOOD) + building.getFoodYield(city));
	}

	public UnitEnum getType() {
		// TODO Auto-generated method stub
		return name;
	}

}
