package com.merold.civcalcs.city;

import java.util.Map;

import com.merold.civcalcs.buildings.Outputs;

public interface Buildable {
	
	public int turnsToBuild(City city);
	public String getName();
	public double outputTotalPerTurn(City city);
	public Map<Outputs, Double> getOutputs(City city);

}
