package de.nachname.controller;

import de.nachname.commons.CoupledToggleGroup;
import de.nachname.model.Territory;
import de.nachname.view.ChangeTerritoryDimensionsDialog;
import de.nachname.view.ControlBarsBuilder;
import javafx.scene.Node;
import javafx.stage.Stage;

public class ControlBarsController {
	private final Stage stage;
	private final Territory territory;
	private final CoupledToggleGroup<PlaceSelection> placeSelectionToggleGroup; 
	
	private final ControlBarsBuilder viewBuilder;
	
	public ControlBarsController(final Stage stage, final Territory territory, final CoupledToggleGroup<PlaceSelection> placeSelectionToggleGroup) {
		this.stage = stage;
		this.territory = territory;
		this.placeSelectionToggleGroup = placeSelectionToggleGroup;
		
		this.viewBuilder = new ControlBarsBuilder(this);
	}
	
	public CoupledToggleGroup<PlaceSelection> getPlaceSelectionToggleGroup() {
		return placeSelectionToggleGroup;
	}
	
	public void changeDimensions() {
		final ChangeTerritoryDimensionsDialog changeDimensionsDialog = new ChangeTerritoryDimensionsDialog(stage);
		final ChangeTerritoryDimensionsDialog.Dimensions newDimensions = changeDimensionsDialog.showAndWaitForDimensions();
		territory.setDimensions(newDimensions.width(), newDimensions.height());
	}
	
	public Node buildView() {
		return viewBuilder.build();
	}
}
