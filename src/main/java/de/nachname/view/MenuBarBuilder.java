package de.nachname.view;

import de.nachname.controller.ControlBarsController;
import de.nachname.controller.PlaceSelection;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCombination;

import static de.nachname.commons.Util.loadImage;
import static de.nachname.controller.PlaceSelection.*;

public class MenuBarBuilder {
	private static final Image NEW_ICON;
	private static final Image OPEN_ICON;
	private static final Image SAVE_ICON;
	private static final Image PRINT_ICON;
	private static final Image PLAY_ICON;
	private static final Image PAUSE_ICON;
	private static final Image STOP_ICON;

	static {
		NEW_ICON = loadImage("/icons/New16.gif");
		OPEN_ICON = loadImage("/icons/Open16.gif");
		SAVE_ICON = loadImage("/icons/Save16.gif");
		PRINT_ICON = loadImage("/icons/Print16.gif");
		PLAY_ICON = loadImage("/icons/Play16.gif");
		PAUSE_ICON = loadImage("/icons/Pause16.gif");
		STOP_ICON = loadImage("/icons/Open16.gif");
	}

	private static MenuItem createMenuItem(
			final String text,
			final Image icon,
			final String accelerator,
			final EventHandler<ActionEvent> onAction
	) {
		final MenuItem item = new MenuItem(text);

		if(icon != null) {
			item.setGraphic(new ImageView(icon));
		}

		if(accelerator != null) {
			item.setAccelerator(KeyCombination.valueOf(accelerator));
		}

		if(onAction != null) {
			item.setOnAction(onAction);
		}

		return item;
	}

	private final ControlBarsController controller;

	public MenuBarBuilder(final ControlBarsController controller) {
		this.controller = controller;
	}

	private MenuItem createPlaceMenuItem(final String text, final PlaceSelection placeKey) {
		final RadioMenuItem item = new RadioMenuItem(text);

		controller.getPlaceSelectionToggleGroup().addToggle(placeKey, item);

		item.setOnAction(_ -> item.setSelected(true));

		return item;
	}

	public Node build() {
		final MenuBar menuBar = new MenuBar();

		menuBar.getMenus().addAll(
				buildEditorMenu(),
				buildTerritoryMenu(),
				buildHamsterMenu(),
				buildSimulationMenu()
		);

		return menuBar;
	}

	private Menu buildEditorMenu() {
		final Menu editorMenu = new Menu("_Editor");
		editorMenu.getItems().addAll(
				createMenuItem("_Neu", NEW_ICON, "Ctrl+N", null),
				createMenuItem("_Öffnen", OPEN_ICON, "Ctrl+O", null),
				createMenuItem("S_peichern", SAVE_ICON, "Ctrl+S", null),
				new SeparatorMenuItem(),
				createMenuItem("_Kompilieren", null, "Ctrl+K", null),
				createMenuItem("_Drucken", PRINT_ICON, "Ctrl+P", null),
				new SeparatorMenuItem(),
				createMenuItem("_Beenden", null, "Ctrl+Q", null)
		);
		return editorMenu;
	}

	private Menu buildTerritoryMenu() {
		final Menu territoryMenu = new Menu("_Territorium");
		territoryMenu.getItems().addAll(
				createMenuItem("_Größe ändern...", null, null, _ -> controller.changeDimensions()),
				new SeparatorMenuItem(),
				createPlaceMenuItem("H_amster platzieren", HAMSTER),
				createPlaceMenuItem("_Korn platzieren", CORN),
				createPlaceMenuItem("_Mauer platzieren", WALL),
				createPlaceMenuItem("Kachel _löschen", DELETE)
		);
		return territoryMenu;
	}

	private Menu buildHamsterMenu() {
		final Menu hamsterMenu = new Menu("_Hamster");
		hamsterMenu.getItems().addAll(
				createMenuItem("_Körner im Maul...", null, null, null),
				createMenuItem("_linksUm", null, "Ctrl+Shift+L", null),
				createMenuItem("_vor", null, "Ctrl+Shift+V", null),
				createMenuItem("_nimm", null, "Ctrl+Shift+N", null),
				createMenuItem("_gib", null, "Ctrl+Shift+G", null)
		);
		return hamsterMenu;
	}

	private Menu buildSimulationMenu() {
		final Menu simulationMenu = new Menu("_Simulation");
		simulationMenu.getItems().addAll(
				createMenuItem("Start/_Fortsetzen", PLAY_ICON, "Ctrl+F11", null),
				createMenuItem("_Pause", PAUSE_ICON, null, null),
				createMenuItem("St_opp", STOP_ICON, "Ctrl+F12", null)
		);
		return simulationMenu;
	}
}
