package de.nachname.view;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;

public class MainViewBuilder {
	public Parent build() {
		final BorderPane root = new BorderPane();

		root.setTop(new ControlBarsBuilder().build());
		root.setCenter(buildMainContent());
		root.setBottom(buildStatusBar());

		return root;
	}

	private Node buildMainContent() {
		final SplitPane mainContent = new SplitPane();

		final TextArea editor = new TextArea();
		final ScrollPane simulationView = new ScrollPane();

		mainContent.getItems().addAll(editor, simulationView);

		return mainContent;
	}

	private Node buildStatusBar() {
		return new Label("Herzlich Willkommen!");
	}
}
