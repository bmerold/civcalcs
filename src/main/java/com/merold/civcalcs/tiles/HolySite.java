package com.merold.civcalcs.tiles;

import com.merold.civcalcs.socialpolicies.SocialPolicy;
import com.merold.civcalcs.socialpolicies.SocialPolicyUtil;

public class HolySite extends Improvement {

	@Override
	public double getGold(Tile tile) {
		double gold = 0;
		if (tile.getOwner().hasAdopted(SocialPolicy.THEOCRACY)) {
			gold += 3;
		}
		return gold;
	}

	@Override
	public double getFaith(Tile tile) {
		double faith = 6;
		if (tile.getOwner().hasAdopted(SocialPolicy.NEW_DEAL)) {
			faith += 4;
		}
		return faith;
	}

	@Override
	public double getCulture(Tile tile) {
		double culture = 0;
		if (SocialPolicyUtil.playerHasCompletedPiety(tile.getOwner())) {
			culture += 3;
		}
		return culture;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Holy Site";
	}
}
