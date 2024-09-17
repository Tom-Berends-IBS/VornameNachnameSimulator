package de.nachname.view;

import javafx.scene.Node;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCombination;

import static de.nachname.commons.Util.loadImage;

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

	private static MenuItem createMenuItem(final String text) {
		return createMenuItem(text, null, null);
	}

	private static MenuItem createMenuItem(final String text, final Image icon) {
		return createMenuItem(text, icon, null);
	}

	private static MenuItem createMenuItem(final String text, final String accelerator) {
		return createMenuItem(text, null, accelerator);
	}

	private static MenuItem createMenuItem(final String text, final Image icon, final String accelerator) {
		final MenuItem item = new MenuItem(text);

		if(icon != null) {
			item.setGraphic(new ImageView(icon));
		}

		if(accelerator != null) {
			item.setAccelerator(KeyCombination.valueOf(accelerator));
		}

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
				createMenuItem("_Neu", NEW_ICON, "Ctrl+N"),
				createMenuItem("_Öffnen", OPEN_ICON, "Ctrl+O"),
				createMenuItem("S_peichern", SAVE_ICON, "Ctrl+S"),
				new SeparatorMenuItem(),
				createMenuItem("_Kompilieren", "Ctrl+K"),
				createMenuItem("_Drucken", PRINT_ICON, "Ctrl+P"),
				new SeparatorMenuItem(),
				createMenuItem("_Beenden", "Ctrl+Q")
		);
		return editorMenu;
	}

	private Menu buildTerritoryMenu() {
		final Menu territoryMenu = new Menu("_Territorium");
		territoryMenu.getItems().addAll(
				createMenuItem("_Größe ändern..."),
				new SeparatorMenuItem(),
				createMenuItem("H_amster platzieren"),
				createMenuItem("_Korn platzieren"),
				createMenuItem("_Mauer platzieren"),
				createMenuItem("Kachel _löschen")
		);
		return territoryMenu;
	}

	private Menu buildHamsterMenu() {
		final Menu hamsterMenu = new Menu("_Hamster");
		hamsterMenu.getItems().addAll(
				createMenuItem("_Körner im Maul..."),
				createMenuItem("_linksUm", "Ctrl+Shift+L"),
				createMenuItem("_vor", "Ctrl+Shift+V"),
				createMenuItem("_nimm", "Ctrl+Shift+N"),
				createMenuItem("_gib", "Ctrl+Shift+G")
		);
		return hamsterMenu;
	}

	private Menu buildSimulationMenu() {
		final Menu simulationMenu = new Menu("_Simulation");
		simulationMenu.getItems().addAll(
				createMenuItem("Start/_Fortsetzen", PLAY_ICON, "Ctrl+F11"),
				createMenuItem("_Pause", PAUSE_ICON),
				createMenuItem("St_opp", STOP_ICON, "Ctrl+F12")
		);
		return simulationMenu;
	}
}
