package com.merold.civcalcs.buildings.wonders;

import com.merold.civcalcs.buildings.BuildingEnum;
import com.merold.civcalcs.city.City;
import com.merold.civcalcs.player.Player;

public class MachPichu extends MedievalWonder {

	public MachPichu(City city, Player owner) {
		super(BuildingEnum.MACHU_PICH, city, owner, 300);
		faithPerTurn = 2;
		goldPerTurn = 5;
		greatMerchantPoints = 1;
		// TODO: Add city connection bonus gold.
	}

}
