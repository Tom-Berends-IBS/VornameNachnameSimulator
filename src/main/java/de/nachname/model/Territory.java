package de.nachname.model;

import de.nachname.exceptions.InvalidDimensionsException;
import de.nachname.exceptions.PositionOutOfBoundsException;
import de.nachname.exceptions.WallInFrontException;

public class Territory {
	private static final int MAX_WIDTH = 100;
	private static final int MAX_HEIGHT = 100;

	private final Cell[][] grid;
	private final Hamster hamster;

	private int width;
	private int height;

	public Territory(final int width, final int height) {
		if(width < 0 || width > MAX_WIDTH || height < 0 || height > MAX_HEIGHT) {
			throw new InvalidDimensionsException();
		}
		
		this.width = width;
		this.height = height;

		this.grid = new Cell[MAX_WIDTH][MAX_HEIGHT];
		for(int x = 0; x < width; x++) {
			for(int y = 0; y < height; y++) {
				grid[x][y] = new Cell();
			}
		}

		hamster = new Hamster(Direction.NORTH, 0, 0);
	}

	public boolean isWall(final int x, final int y) {
		if(x < 0 || x >= width || y < 0 || y >= height) {
			throw new PositionOutOfBoundsException();
		}
		
		return grid[x][y].isWall();
	}

	public void setWall(final int x, final int y, final boolean wall) {
		if(x < 0 || x >= width || y < 0 || y >= height) {
			throw new PositionOutOfBoundsException();
		}
		
		grid[x][y].setWall(wall);
	}

	public int getNumCorns(final int x, final int y) {
		if(x < 0 || x >= width || y < 0 || y >= height) {
			throw new PositionOutOfBoundsException();
		}
		
		return grid[x][y].getNumCorns();
	}

	public void setNumCorns(final int x, final int y, final int numCorns) {
		if(x < 0 || x >= width || y < 0 || y >= height) {
			throw new PositionOutOfBoundsException();
		}
		
		grid[x][y].setNumCorns(numCorns);
	}
	
	public void clear(final int x, final int y) {
		if(x < 0 || x >= width || y < 0 || y >= height) {
			throw new PositionOutOfBoundsException();
		}
		
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
		if(x < 0 || x >= width || y < 0 || y >= height) {
			throw new PositionOutOfBoundsException();
		}

		if(isWall(x, y)) {
			throw new WallInFrontException();
		}
		
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

	public void setDimensions(final int newWidth, final int newHeight) {
		if(newWidth < 0 || newWidth > MAX_WIDTH || newHeight < 0 || newHeight > MAX_HEIGHT) {
			throw new InvalidDimensionsException();
		}

		this.width = newWidth;
		this.height = newHeight;

		for(int x = 0; x < width; x++) {
			for(int y = 0; y < height; y++) {
				if(grid[x][y] == null) {
					grid[x][y] = new Cell();
				}
			}
		}
	}
}
