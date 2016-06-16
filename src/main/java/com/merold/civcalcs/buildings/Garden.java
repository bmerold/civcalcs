package com.merold.civcalcs.buildings;

import com.merold.civcalcs.city.City;
import com.merold.civcalcs.player.Player;

public class Garden extends Building {
	
	public Garden(City city, Player player) {
		super(BuildingEnum.GARDEN, city, player, 120);
		maintenancePerTurn = 1;
		greatPeopleModifier = 0.25;
	}
	

}
