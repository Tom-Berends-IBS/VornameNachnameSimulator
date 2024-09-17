package de.nachname.model;

public class Cell {
	private boolean wall;
	private int numCorns;

	public boolean isWall() {
		return wall;
	}

	public void setWall(final boolean wall) {
		this.wall = wall;
	}

	public int getNumCorns() {
		return numCorns;
	}

	public void setNumCorns(final int numCorns) {
		this.numCorns = numCorns;
	}

	public void clear() {
		wall = false;
		numCorns = 0;
	}
}
