package com.merold.civcalcs.units;

import com.merold.civcalcs.player.Player;

public class LandUnit extends Unit {

	protected double strength;
	public LandUnit(UnitEnum name, Player owner, int cost) {
		super(name, owner, cost);
	}

}
