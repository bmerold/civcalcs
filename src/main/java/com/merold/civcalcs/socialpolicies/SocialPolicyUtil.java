package com.merold.civcalcs.socialpolicies;

import java.util.ArrayList;
import java.util.List;

import com.merold.civcalcs.player.Player;

public class SocialPolicyUtil {

	private static List<SocialPolicy> traditionPolicies = new ArrayList<SocialPolicy>();
	private static List<SocialPolicy> pietyPolicies = new ArrayList<SocialPolicy>();
	static {
		traditionPolicies.add(SocialPolicy.ARISTOCRACY);
		traditionPolicies.add(SocialPolicy.OLIGARCHY);
		traditionPolicies.add(SocialPolicy.TRADITION);
		traditionPolicies.add(SocialPolicy.LANDED_ELITE);
		traditionPolicies.add(SocialPolicy.LEGALISM);
		traditionPolicies.add(SocialPolicy.MONARCHY);
		pietyPolicies.add(SocialPolicy.THEOCRACY);
		pietyPolicies.add(SocialPolicy.PIETY);
		pietyPolicies.add(SocialPolicy.ORGANIZED_RELIGION);
		pietyPolicies.add(SocialPolicy.MANDATE_OF_HEAVEN);
		pietyPolicies.add(SocialPolicy.RELGIOUS_TOLERANCE);
		pietyPolicies.add(SocialPolicy.REFORMATION);

	}

	public static boolean playerHasCompletedTradition(Player player) {
		List<SocialPolicy> playersPolicies = player.getSocialPolicies();

		return playersPolicies.containsAll(traditionPolicies);
	}
	
	public static boolean playerHasCompletedPiety(Player player) {
		List<SocialPolicy> playersPolicies = player.getSocialPolicies();

		return playersPolicies.containsAll(pietyPolicies);
	}

}
