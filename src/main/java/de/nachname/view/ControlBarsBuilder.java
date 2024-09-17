package de.nachname.view;

import de.nachname.controller.ControlBarsController;
import javafx.scene.Node;
import javafx.scene.layout.VBox;

public class ControlBarsBuilder {
	private final ControlBarsController controller;

	public ControlBarsBuilder(final ControlBarsController controller) {
		this.controller = controller;
	}
	
	public Node build() {
		final VBox controlBars = new VBox();

		controlBars.getChildren().addAll(
				new MenuBarBuilder(controller).build(),
				new ToolBarBuilder(controller).build()
		);

		return controlBars;
	}
}
