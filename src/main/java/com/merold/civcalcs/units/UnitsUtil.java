package com.merold.civcalcs.units;

import java.util.ArrayList;
import java.util.List;

import com.merold.civcalcs.Game;
import com.merold.civcalcs.buildings.Building;
import com.merold.civcalcs.buildings.BuildingEnum;
import com.merold.civcalcs.buildings.Project;
import com.merold.civcalcs.city.City;
import com.merold.civcalcs.player.Player;

public class UnitsUtil {

	public static List<Project> availableBuildings(City city, Player player, Game game) {
		List<Project> available = new ArrayList<Project>();

		for (BuildingEnum building : BuildingEnum.values()) {
			if (!city.hasBuilding(building) && player.hasDiscoveredTech(building.requiredTech())
					&& !game.isCompleted(building) && !city.builtOn(building.getRestrictedCityTerrain())
					&& city.hasAtLeastOneImprovedTileWith(building.getRequiredImprovedResources())
					&& city.builtOn(building.getRequiredTerrainFeatures())
					&& player.hasAdopted(building.getRequiredPolicy())
					&& player.hasBuildingInAllCities(building.getRequiredBuilding())
					&& player.believes(building.getRequiredBelief())) {
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
		return available;
	}
}
