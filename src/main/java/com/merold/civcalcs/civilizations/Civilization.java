package com.merold.civcalcs.civilizations;

public class Civilization {
	
	private String name;
	private Influence influence;

	public Influence getInfluence() {
		return influence;
	}

	public void setInfluence(Influence influence) {
		this.influence = influence;
	}

	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return "Civilization [name=" + name + "]";
	}

	public Civilization(String name) {
		this.name = name;
		influence = Influence.EXOTIC;
	}
	
	public Civilization(String name, Influence influence) {
		this.name = name;
		this.influence = influence;
	}

	public static String INDIA = "India";

}
