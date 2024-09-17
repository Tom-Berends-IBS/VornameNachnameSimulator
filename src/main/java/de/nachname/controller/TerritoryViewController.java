package de.nachname.controller;

import de.nachname.commons.CoupledToggleGroup;
import de.nachname.model.Territory;
import de.nachname.view.TerritoryView;
import javafx.scene.Node;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

import java.util.Objects;

public class TerritoryViewController {
	private final Territory territory;
	private final CoupledToggleGroup<PlaceSelection> placeSelectionToggleGroup;
	
	private final TerritoryView territoryView;
	
	private boolean hamsterDragged;

	public TerritoryViewController(final Territory territory, final CoupledToggleGroup<PlaceSelection> placeSelectionToggleGroup) {
		this.territory = territory;
		this.placeSelectionToggleGroup = placeSelectionToggleGroup;
		
		territoryView = new TerritoryView(this);
	}

	private void placeObject(final int x, final int y) {
		switch(placeSelectionToggleGroup.getSelected()) {
			case HAMSTER -> territory.setHamsterPosition(x, y);
			case WALL -> territory.setWall(x, y, true);
			case CORN -> placeCorn(x, y);
			case DELETE -> territory.clear(x, y);
		}
	}

	public void canvasClicked(final MouseEvent mouseEvent) {
		if(hamsterDragged) {
			hamsterDragged = false;
			return;
		}

		final int x = (int) (mouseEvent.getX() / TerritoryView.CELL_SIZE);
		final int y = (int) (mouseEvent.getY() / TerritoryView.CELL_SIZE);

		if(Objects.requireNonNull(mouseEvent.getButton()) == MouseButton.PRIMARY) {
			placeObject(x, y);
		}
	}

	private void placeCorn(final int x, final int y) {
		territory.setNumCorns(x, y, territory.getNumCorns(x, y) + 1);
	}

	public void canvasPressed(final MouseEvent mouseEvent) {
		if(mouseEvent.getButton() != MouseButton.PRIMARY) {
			return;
		}

		final int x = (int) (mouseEvent.getX() / TerritoryView.CELL_SIZE);
		final int y = (int) (mouseEvent.getY() / TerritoryView.CELL_SIZE);

		hamsterDragged = territory.isHamsterAt(x, y);
	}

	public void canvasDragged(final MouseEvent mouseEvent) {
		if(!hamsterDragged) {
			return;
		}

		final int x = (int) (mouseEvent.getX() / TerritoryView.CELL_SIZE);
		final int y = (int) (mouseEvent.getY() / TerritoryView.CELL_SIZE);

		territory.setHamsterPosition(x, y);
	}

	public Territory getTerritory() {
		return territory;
	}

	public Node getView() {
		return territoryView;
	}
}
