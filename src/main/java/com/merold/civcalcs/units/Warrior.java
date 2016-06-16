package com.merold.civcalcs.units;

import com.merold.civcalcs.player.Player;

public class Warrior extends LandUnit {

	public Warrior(Player player) {
		super(UnitEnum.WARRIOR, player, 40);
		strength = 8;
		moves = 2;
	}

}
