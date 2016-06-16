package com.merold.civcalcs.units;

import com.merold.civcalcs.player.Player;

public class Scout extends LandUnit {

	public Scout(Player player) {
		super(UnitEnum.SCOUT, player, 25);
		moves = 2;
		strength = 5;
	}
	
	

}
