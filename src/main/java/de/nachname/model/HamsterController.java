package de.nachname.model;

import de.nachname.exceptions.*;

public class HamsterController {
	private final Territory territory;

	public HamsterController(final Territory territory) {
		this.territory = territory;
	}

	public void vor() {
		final Direction direction = territory.getHamsterDirection();
		final int oldX = territory.getHamsterX();
		final int oldY = territory.getHamsterY();

		final int newX = oldX + direction.getXOffset();
		final int newY = oldY + direction.getYOffset();

		try {
			territory.setHamsterPosition(newX, newY);
		} catch(final PositionOutOfBoundsException | WallInFrontException _) {
			throw new MauerDaException();
		}
	}

	public void linksUm() {
		final Direction direction = territory.getHamsterDirection();
		territory.setHamsterDirection(direction.getLeftwardDirection());
	}

	public void nimm() {
		final int hamsterX = territory.getHamsterX();
		final int hamsterY = territory.getHamsterY();

		final int hamsterNumCorns = territory.getHamsterNumCorns();
		final int cellNumCorns = territory.getNumCorns(hamsterX, hamsterY);

		try {
			territory.setNumCorns(hamsterX, hamsterY, cellNumCorns - 1);
			territory.setHamsterNumCorns(hamsterNumCorns + 1);
		} catch(final IllegalArgumentException _) {
			throw new KachelLeerException();
		}
	}

	public void gib() {
		final int hamsterX = territory.getHamsterX();
		final int hamsterY = territory.getHamsterY();

		final int hamsterNumCorns = territory.getHamsterNumCorns();
		final int cellNumCorns = territory.getNumCorns(hamsterX, hamsterY);

		try {
			territory.setHamsterNumCorns(hamsterNumCorns - 1);
			territory.setNumCorns(hamsterX, hamsterY, cellNumCorns + 1);
		} catch(final IllegalArgumentException _) {
			throw new MaulLeerException();
		} catch(final WallInFrontException _) {
			throw new MauerDaException();
		}
	}

	public boolean vornFrei() {
		final Direction direction = territory.getHamsterDirection();
		final int hamsterX = territory.getHamsterX();
		final int hamsterY = territory.getHamsterY();

		final int forwardX = hamsterX + direction.getXOffset();
		final int forwardY = hamsterY + direction.getYOffset();

		try {
			return !territory.isWall(forwardX, forwardY);
		} catch(final PositionOutOfBoundsException _) {
			return false;
		}
	}

	public boolean kornDa() {
		final int hamsterX = territory.getHamsterX();
		final int hamsterY = territory.getHamsterY();

		return territory.getNumCorns(hamsterX, hamsterY) > 0;
	}

	public boolean maulLeer() {
		return territory.getHamsterNumCorns() == 0;
	}
}
