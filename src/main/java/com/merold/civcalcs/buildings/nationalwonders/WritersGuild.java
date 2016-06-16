package com.merold.civcalcs.buildings.nationalwonders;

import com.merold.civcalcs.buildings.BuildingEnum;
import com.merold.civcalcs.city.City;
import com.merold.civcalcs.player.Player;
import com.merold.civcalcs.specialists.SpecialistSlot;

public class WritersGuild extends NationalWonder {
	
	public WritersGuild(City city, Player player) {
		super(BuildingEnum.WRITERS_GUILD, city, player, 100);
		maintenancePerTurn = 1;
		greatWriterPoints = 1;
		writerSlots = 2;
		writers.add(new SpecialistSlot());
		writers.add(new SpecialistSlot());
	}

}
