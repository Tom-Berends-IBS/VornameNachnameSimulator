package de.nachname.model;

import de.nachname.exceptions.InvalidDimensionsException;
import de.nachname.exceptions.PositionOutOfBoundsException;
import de.nachname.exceptions.WallInFrontException;

import java.util.HashSet;
import java.util.Set;

public class Territory {
	private static final int MAX_WIDTH = 100;
	private static final int MAX_HEIGHT = 100;

	private static void checkDimensions(final int width, final int height) {
		if(width <= 0 || width > MAX_WIDTH || height <= 0 || height > MAX_HEIGHT) {
			throw new InvalidDimensionsException();
		}
	}

	private static void extendGrid(final Cell[][] grid, final int width, final int height) {
		for(int x = 0; x < width; x++) {
			for(int y = 0; y < height; y++) {
				if(grid[x][y] == null) {
					grid[x][y] = new Cell();
				}
			}
		}
	}

	private final Set<DimensionsChangeListener> dimensionsChangeListeners;
	private final Set<HamsterChangeListener> hamsterChangeListeners;
	private final Set<CellChangeListener> cellChangeListeners;

	private final Cell[][] grid;
	private final Hamster hamster;

	private int width;
	private int height;

	public Territory(final int width, final int height) {
		dimensionsChangeListeners = new HashSet<>();
		hamsterChangeListeners = new HashSet<>();
		cellChangeListeners = new HashSet<>();
		
		this.grid = new Cell[MAX_WIDTH][MAX_HEIGHT];
		
		extendGrid(width, height);

		hamster = new Hamster(Direction.NORTH, 0, 0);
	}

	public void addDimensionsChangeListener(final DimensionsChangeListener listener) {
		dimensionsChangeListeners.add(listener);
	}

	public void addHamsterChangeListener(final HamsterChangeListener listener) {
		hamsterChangeListeners.add(listener);
	}

	public void addCellChangeListener(final CellChangeListener listener) {
		cellChangeListeners.add(listener);
	}

	private void extendGrid(final int newWidth, final int newHeight) {
		checkDimensions(newWidth, newHeight);

		this.width = newWidth;
		this.height = newHeight;

		extendGrid(grid, width, height);

		dimensionsChangeListeners.forEach(listener -> listener.dimensionsChanged(width, height));
	}

	private boolean isInBounds(final int x, final int y) {
		return x >= 0 && x < width && y >= 0 && y < height;
	}

	private void checkInBounds(final int x, final int y) {
		if(!isInBounds(x, y)) {
			throw new PositionOutOfBoundsException();
		}
	}

	private Cell getCell(final int x, final int y) {
		checkInBounds(x, y);

		return grid[x][y];
	}

	public boolean isWall(final int x, final int y) {
		return getCell(x, y).isWall();
	}

	public void setWall(final int x, final int y, final boolean wall) {
		getCell(x, y).setWall(wall);

		cellChangeListeners.forEach(listener -> listener.cellChanged(x, y));
	}

	public int getNumCorns(final int x, final int y) {
		return getCell(x, y).getNumCorns();
	}

	public void setNumCorns(final int x, final int y, final int numCorns) {
		getCell(x, y).setNumCorns(numCorns);

		cellChangeListeners.forEach(listener -> listener.cellChanged(x, y));
	}
	
	public void clear(final int x, final int y) {
		getCell(x, y).clear();

		cellChangeListeners.forEach(listener -> listener.cellChanged(x, y));
	}

	public Direction getHamsterDirection() {
		return hamster.getDirection();
	}

	public void setHamsterDirection(final Direction direction) {
		hamster.setDirection(direction);

		final int hamsterX = hamster.getX();
		final int hamsterY = hamster.getY();

		hamsterChangeListeners.forEach(listener -> listener.hamsterChanged(hamsterX, hamsterY, hamsterX, hamsterY));
	}
	
	public boolean isHamsterAt(final int x, final int y) {
		return hamster.getX() == x && hamster.getY() == y;
	}

	public int getHamsterX() {
		return hamster.getX();
	}

	public int getHamsterY() {
		return hamster.getY();
	}

	public void setHamsterPosition(final int x, final int y) {
		if(isHamsterAt(x, y)) {
			return;
		}
		
		checkInBounds(x, y);

		if(isWall(x, y)) {
			throw new WallInFrontException();
		}

		final int oldHamsterX = hamster.getX();
		final int oldHamsterY = hamster.getY();

		hamster.setPosition(x, y);

		hamsterChangeListeners.forEach(listener -> listener.hamsterChanged(oldHamsterX, oldHamsterY, x, y));
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
		checkDimensions(newWidth, newHeight);

		extendGrid(newWidth, newHeight);
	}
}
