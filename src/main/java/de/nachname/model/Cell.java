package de.nachname.model;

import de.nachname.exceptions.CellBlockedException;
import de.nachname.exceptions.WallInFrontException;

public class Cell {
	private boolean wall;
	private int numCorns;

	public boolean isWall() {
		return wall;
	}

	public void setWall(final boolean wall) {
		if(numCorns > 0) {
			throw new CellBlockedException();
		}
		
		this.wall = wall;
	}

	public int getNumCorns() {
		return numCorns;
	}

	public void setNumCorns(final int numCorns) {
		if(numCorns < 0) {
			throw new IllegalArgumentException("numCorns must not be negative");
		}
		if(wall) {
			throw new WallInFrontException();
		}
		
		this.numCorns = numCorns;
	}

	public void clear() {
		wall = false;
		numCorns = 0;
	}
}
