package com.merold.civcalcs.greatworks;

public class GreatWorkSlot {
	protected GreatWork work;

	public void add(GreatWork work) {
		if (this.work == null) {
			this.work = work;
		} else {
			throw new RuntimeException("Can't add a great work to a slot that already has a great work.");
		}
	}

	public GreatWork removeGreatWork() {
		GreatWork removed = this.work;
		this.work = null;
		return removed;
	}

	public boolean isEmpty() {
		return this.work == null;
	}

}
