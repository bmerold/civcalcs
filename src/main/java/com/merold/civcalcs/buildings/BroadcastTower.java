package com.merold.civcalcs.buildings;

import com.merold.civcalcs.city.City;
import com.merold.civcalcs.greatworks.GreatWorkSlot;
import com.merold.civcalcs.player.Player;
import com.merold.civcalcs.socialpolicies.SocialPolicy;

public class BroadcastTower extends Building {

	public BroadcastTower(City city, Player owner) {
		super(BuildingEnum.BROADCAST_TOWER, city, owner, 500);
		maintenancePerTurn = 3;
		culturePerTurn = 1;
		cultureModifier = 0.33;
		greatWorkOfMusicSlots = 1;
		gwofMusicSlots.add(new GreatWorkSlot());
	}
	
	@Override
	public double getTourismModifier() {
		double modifier = 0;
		if (owner.hasAdopted(SocialPolicy.MEDIA_CULTURE)) {
			modifier += 0.34;
		}
		return modifier;
	}

}
