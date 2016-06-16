package com.merold.civcalcs.buildings.nationalwonders;

import com.merold.civcalcs.buildings.BuildingEnum;
import com.merold.civcalcs.city.City;
import com.merold.civcalcs.player.Player;
import com.merold.civcalcs.specialists.SpecialistSlot;

public class MusiciansGuild extends NationalWonder {

	public MusiciansGuild(City city, Player owner) {
		super(BuildingEnum.MUSICIANS_GUILD, city, owner, 200);
		maintenancePerTurn = 1;
		greatMusicianPoints = 3;
		musicianSlots = 2;
		musicians.add(new SpecialistSlot());
		musicians.add(new SpecialistSlot());
	}

}
