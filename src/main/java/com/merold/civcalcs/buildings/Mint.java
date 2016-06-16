package com.merold.civcalcs.buildings;

import com.merold.civcalcs.buildings.enhancers.MintEnhancer;
import com.merold.civcalcs.city.City;
import com.merold.civcalcs.player.Player;

public class Mint extends Building {

	public Mint(City city, Player player) {
		super(BuildingEnum.MINT, city, player, 100);
		enhancer = new MintEnhancer();
	}
}
