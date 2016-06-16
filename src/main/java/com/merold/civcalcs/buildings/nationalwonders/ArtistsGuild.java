package com.merold.civcalcs.buildings.nationalwonders;

import com.merold.civcalcs.buildings.BuildingEnum;
import com.merold.civcalcs.city.City;
import com.merold.civcalcs.player.Player;
import com.merold.civcalcs.specialists.SpecialistSlot;

public class ArtistsGuild extends NationalWonder {

	public ArtistsGuild(City city, Player owner) {
		super(BuildingEnum.ARTISTS_GUILD, city, owner, 150);
		greatArtistPoints = 2;
		artistSlots = 2;
		artists.add(new SpecialistSlot());
		artists.add(new SpecialistSlot());
		maintenancePerTurn = 1;
	}

}
