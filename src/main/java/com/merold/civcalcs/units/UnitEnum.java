package com.merold.civcalcs.units;

import com.merold.civcalcs.player.Player;
import com.merold.civcalcs.science.Tech;

public enum UnitEnum {
	WORKER,
	MAORI_WARRIOR,
	GREAT_PROPHET,
	MISSIONARY,
	SCOUT,
	WARRIOR,
	SETTLER,
	WORK_BOAT(Tech.SAILING),
	GREAT_WRITER,
	CARGO_SHIP(Tech.SAILING),
	GREAT_SCIENTIST,
	GREAT_ARTIST,
	GREAT_MUSICIAN;

	private Tech requiredTech;

	UnitEnum(Tech requiredTech) {
		this.requiredTech = requiredTech;
	}

	UnitEnum() {

	}

	public Unit create(Player player) {
		Unit unit = null;
		switch (this) {
		case WORKER:
			unit = new Worker(player);
			break;
		case MAORI_WARRIOR:
			unit = new MaoriWarrior(player);
			break;
		case GREAT_PROPHET:
			unit = new GreatProphet(player);
			break;
		case MISSIONARY:
			unit = new Missionary(player);
			break;
		case SCOUT:
			unit = new Scout(player);
			break;
		case WARRIOR:
			unit = new Warrior(player);
			break;
		case SETTLER:
			unit = new Settler(player);
			break;
		case WORK_BOAT:
			unit = new WorkBoat(player);
			break;
		case GREAT_WRITER:
			unit = new GreatWriter(player);
			break;
		case CARGO_SHIP:
			unit = new CargoShip(player);
			break;
		case GREAT_SCIENTIST:
			unit = new GreatScientist(player);
			break;
		case GREAT_ARTIST:
			unit = new GreatArtist(player);
			break;
		case GREAT_MUSICIAN:
			unit = new GreatMusician(player);
			break;
		default:
			throw new RuntimeException("Unhandled unit creation for " + this);

		}
		return unit;
	}
}
