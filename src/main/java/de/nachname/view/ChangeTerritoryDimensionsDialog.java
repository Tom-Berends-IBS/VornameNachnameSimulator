package de.nachname.view;

import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.stage.Modality;
import javafx.stage.Window;

public class ChangeTerritoryDimensionsDialog extends Dialog<ChangeTerritoryDimensionsDialog.Dimensions> {
	public record Dimensions(int width, int height) {}
	
	private final TextField widthField;
	private final TextField heightField;
	
	public ChangeTerritoryDimensionsDialog(final Window owner) {
		widthField = new TextField("10");
		heightField = new TextField("10");
		
		setTitle("Territorium Dimensionen ändern");
		initOwner(owner);
		initModality(Modality.WINDOW_MODAL);
		
		final ButtonType confirmButton = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
		final ButtonType cancelButton = new ButtonType("Abbrechen", ButtonBar.ButtonData.CANCEL_CLOSE);
		
		getDialogPane().getButtonTypes().addAll(
				confirmButton, 
				cancelButton
		);

		final GridPane grid = new GridPane();
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(20, 150, 10, 10));

		grid.add(new Label("Width:"), 0, 0);
		grid.add(widthField, 1, 0);
		grid.add(new Label("Height:"), 0, 1);
		grid.add(heightField, 1, 1);

		GridPane.setHgrow(widthField, Priority.ALWAYS);
		GridPane.setHgrow(heightField, Priority.ALWAYS);

		getDialogPane().setContent(grid);

		setResultConverter(dialogButton -> {
			if (dialogButton == confirmButton) {
				try {
					final int width = Integer.parseInt(widthField.getText());
					final int height = Integer.parseInt(heightField.getText());
					if (width <= 0 || height <= 0) {
						throw new NumberFormatException("Dimensionen müssen positiv sein");
					}
					return new Dimensions(width, height);
				} catch (final NumberFormatException e) {
					showAlert("Ungültige Eingabe!", e.getMessage());
					return null;
				}
			}
			return null;
		});
	}

	private void showAlert(final String title, final String content) {
		final Alert alert = new Alert(Alert.AlertType.ERROR);
		alert.setTitle(title);
		alert.setContentText(content);
		alert.initOwner(getOwner());
		alert.showAndWait();
	}

	public Dimensions showAndWaitForDimensions() {
		Dimensions result;
		do {
			result = showAndWait().orElse(null);
		} while (result == null);
		return result;
	}
}
