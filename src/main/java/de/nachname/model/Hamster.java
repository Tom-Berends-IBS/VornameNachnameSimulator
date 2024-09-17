package de.nachname.model;

public class Hamster {
	private Direction direction;
	private int x;
	private int y;
	private int numCorns;

	public Hamster(final Direction direction, final int x, final int y) {
		this.direction = direction;
		this.x = x;
		this.y = y;
	}

	public Direction getDirection() {
		return direction;
	}

	public void setDirection(final Direction direction) {
		this.direction = direction;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public void setPosition(final int x, final int y) {
		this.x = x;
		this.y = y;
	}

	public int getNumCorns() {
		return numCorns;
	}

	public void setNumCorns(final int numCorns) {
		if(numCorns < 0) {
			throw new IllegalArgumentException("numCorns must not be negative");
		}
		
		this.numCorns = numCorns;
	}
}
