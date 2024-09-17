package de.nachname.view;

import de.nachname.controller.MainViewController;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;

public class MainViewBuilder {
	private final MainViewController controller;

	private final ControlBarsBuilder controlBarsBuilder;

	public MainViewBuilder(final MainViewController controller) {
		this.controller = controller;

		controlBarsBuilder = new ControlBarsBuilder(controller.getControlBarsController());
	}

	public Parent build() {
		final BorderPane root = new BorderPane();

		root.setTop(controlBarsBuilder.build());
		root.setCenter(buildMainContent());
		root.setBottom(buildStatusBar());

		return root;
	}

	private Node buildMainContent() {
		final SplitPane mainContent = new SplitPane();

		final TextArea editor = new TextArea();
		final Node simulationView = buildSimulationView();

		mainContent.getItems().addAll(editor, simulationView);

		return mainContent;
	}
	
	private Node buildSimulationView() {
		final Node territoryView = controller.getTerritoryViewController().getView();

		final ScrollPane simulationView = new ScrollPane(territoryView);
		simulationView.setFitToWidth(true);
		simulationView.setFitToHeight(true);
		return simulationView;
	}

	private Node buildStatusBar() {
		return new Label("Herzlich Willkommen!");
	}
}
