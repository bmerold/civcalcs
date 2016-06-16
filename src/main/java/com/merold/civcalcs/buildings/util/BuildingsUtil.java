package com.merold.civcalcs.buildings.util;

import java.util.ArrayList;
import java.util.List;

import com.merold.civcalcs.Game;
import com.merold.civcalcs.buildings.Building;
import com.merold.civcalcs.buildings.BuildingEnum;
import com.merold.civcalcs.city.Buildable;
import com.merold.civcalcs.city.City;
import com.merold.civcalcs.player.Player;
import com.merold.civcalcs.units.Unit;
import com.merold.civcalcs.units.UnitEnum;

public class BuildingsUtil {

	public static List<Buildable> availableBuildings(City city, Player player, Game game) {
		List<Buildable> available = new ArrayList<Buildable>();

		for (BuildingEnum building : BuildingEnum.values()) {
			if (!city.hasBuilding(building) && player.hasDiscoveredTech(building.requiredTech())
					&& !game.isCompleted(building) && !city.builtOn(building.getRestrictedCityTerrain())
					&& city.hasAtLeastOneImprovedTileWith(building.getRequiredImprovedResources())
					&& city.builtOn(building.getRequiredTerrainFeatures())
					&& !city.builtOn(building.getDisallowedTerrainFeature())
					&& player.hasAdopted(building.getRequiredPolicy())
					&& player.hasBuildingInAllCities(building.getRequiredBuilding())
					&& player.believes(building.getRequiredBelief())
					&& player.hasConnected(building.getRequiredResource())) {
				if (building.isRequiresHolyCity()) {
					if (city.isHolyCity()) {
						available.add(building.create(city, player));
					} else {
						// NOT available to be built due to requiring a holy
						// city.
					}
				} else {
					available.add(building.create(city, player));
				}
			}
		}
		
		for (UnitEnum unitName : UnitEnum.values()) {
			Unit unit = unitName.create(player);
			if (unit.getGoldYield(city) > 0 || unit.getFoodYield(city) > 0) {
				available.add(unit);
			}
		}
		return available;
	}
}
