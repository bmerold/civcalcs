package com.merold.civcalcs.specialists;

public class SpecialistSlot {
	protected Specialist employed;

	public void add(Specialist employee) {
		if (this.employed == null) {
			this.employed = employee;
		} else {
			throw new RuntimeException("Can't employ a specialist to a slot that already has a specialist.");
		}
	}

	public void unEmploySpecialist() {
		employed = null;
	}

	public boolean isFilled() {
		return this.employed != null;
	}
	
	public boolean isOpen() {
		return this.employed == null;
	}

}
