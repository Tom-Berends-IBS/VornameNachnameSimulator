package de.nachname.view;

import javafx.scene.Node;
import javafx.scene.layout.VBox;

public class ControlBarsBuilder {
	public Node build() {
		final VBox controlBars = new VBox();

		controlBars.getChildren().addAll(
				new MenuBarBuilder().build(),
				new ToolBarBuilder().build()
		);

		return controlBars;
	}
}
