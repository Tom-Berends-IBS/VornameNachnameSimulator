package de.nachname.model;

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

		territory.setHamsterPosition(newX, newY);
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

		territory.setNumCorns(hamsterX, hamsterY, cellNumCorns - 1);
		territory.setHamsterNumCorns(hamsterNumCorns + 1);
	}

	public void gib() {
		final int hamsterX = territory.getHamsterX();
		final int hamsterY = territory.getHamsterY();

		final int hamsterNumCorns = territory.getHamsterNumCorns();
		final int cellNumCorns = territory.getNumCorns(hamsterX, hamsterY);

		territory.setHamsterNumCorns(hamsterNumCorns - 1);
		territory.setNumCorns(hamsterX, hamsterY, cellNumCorns + 1);
	}

	public boolean vornFrei() {
		final Direction direction = territory.getHamsterDirection();
		final int hamsterX = territory.getHamsterX();
		final int hamsterY = territory.getHamsterY();

		final int forwardX = hamsterX + direction.getXOffset();
		final int forwardY = hamsterY + direction.getYOffset();

		return territory.isWall(forwardX, forwardY);
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
