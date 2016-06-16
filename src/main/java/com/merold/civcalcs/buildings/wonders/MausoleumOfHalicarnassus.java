package com.merold.civcalcs.buildings.wonders;

import java.util.List;

import com.merold.civcalcs.buildings.BuildingEnum;
import com.merold.civcalcs.buildings.enhancers.MausoleumEnhancer;
import com.merold.civcalcs.city.City;
import com.merold.civcalcs.player.Player;
import com.merold.civcalcs.tiles.Tile;

public class MausoleumOfHalicarnassus extends AncientWonder {

	public MausoleumOfHalicarnassus(City city, Player player) {
		super(BuildingEnum.MAUSOLEUM_OF_HALICARNASSUS, city, player, 185);
		culturePerTurn = 1;
		greatMerchantPoints = 1;
		enhancer = new MausoleumEnhancer();

		// TODO: Figure out how to quantify the Great Person expending bonus.
	}
}
