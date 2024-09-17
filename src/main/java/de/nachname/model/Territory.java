package de.nachname.model;

public class Territory {
	private final Cell[][] grid;
	private final Hamster hamster;

	private int width;
	private int height;

	public Territory(final int width, final int height) {
		this.width = width;
		this.height = height;

		this.grid = new Cell[width][height];
		for(int x = 0; x < width; x++) {
			for(int y = 0; y < height; y++) {
				grid[x][y] = new Cell();
			}
		}

		hamster = new Hamster(Direction.NORTH, 0, 0);
	}

	public boolean isWall(final int x, final int y) {
		return grid[x][y].isWall();
	}

	public void setWall(final int x, final int y, final boolean wall) {
		grid[x][y].setWall(wall);
	}

	public int getNumCorns(final int x, final int y) {
		return grid[x][y].getNumCorns();
	}

	public void setNumCorns(final int x, final int y, final int numCorns) {
		grid[x][y].setNumCorns(numCorns);
	}
	
	public void clear(final int x, final int y) {
		grid[x][y].clear();
	}

	public Direction getHamsterDirection() {
		return hamster.getDirection();
	}

	public void setHamsterDirection(final Direction direction) {
		hamster.setDirection(direction);
	}

	public int getHamsterX() {
		return hamster.getX();
	}

	public int getHamsterY() {
		return hamster.getY();
	}

	public void setHamsterPosition(final int x, final int y) {
		hamster.setPosition(x, y);
	}

	public int getHamsterNumCorns() {
		return hamster.getNumCorns();
	}

	public void setHamsterNumCorns(final int numCorns) {
		hamster.setNumCorns(numCorns);
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public void setDimensions(final int width, final int height) {
		this.width = width;
		this.height = height;
	}
}
