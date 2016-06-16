package com.merold.civcalcs.units;

import com.merold.civcalcs.player.Player;

public class Settler extends LandUnit {

	public Settler(Player player) {
		super(UnitEnum.SETTLER, player, 106);
		moves = 2;
	}

}
