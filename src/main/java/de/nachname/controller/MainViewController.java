package de.nachname.controller;

import de.nachname.commons.CoupledToggleGroup;
import de.nachname.model.Territory;
import de.nachname.view.MainViewBuilder;
import javafx.scene.Parent;
import javafx.stage.Stage;

public class MainViewController {
	private final Territory territory;
	
	private final ControlBarsController controlBarsController;
	private final TerritoryViewController territoryViewController;
	
	private final MainViewBuilder mainViewBuilder;
	
	public MainViewController(final Stage stage, final Territory territory) {
		this.territory = territory;
		
		final CoupledToggleGroup<PlaceSelection> placeSelectionToggleGroup = new CoupledToggleGroup<>();
		
		controlBarsController = new ControlBarsController(stage, territory, placeSelectionToggleGroup);
		territoryViewController = new TerritoryViewController(territory, placeSelectionToggleGroup);
		
		mainViewBuilder = new MainViewBuilder(this);
	}
	
	public Parent buildView() {
		return mainViewBuilder.build();
	}
	
	public ControlBarsController getControlBarsController() {
		return controlBarsController;
	}
	
	public TerritoryViewController getTerritoryViewController() {
		return territoryViewController;
	}
}
