package de.nachname.model;

public enum Direction {
	NORTH(0, -1),
	EAST(1, 0),
	SOUTH(0, 1),
	WEST(-1, 0);

	private final int xOffset;
	private final int yOffset;

	Direction(final int xOffset, final int yOffset) {
		this.xOffset = xOffset;
		this.yOffset = yOffset;
	}

	public Direction getLeftwardDirection() {
		return switch(this) {
			case NORTH -> WEST;
			case EAST -> NORTH;
			case SOUTH -> EAST;
			case WEST -> SOUTH;
		};
	}

	public int getXOffset() {
		return xOffset;
	}

	public int getYOffset() {
		return yOffset;
	}
}
