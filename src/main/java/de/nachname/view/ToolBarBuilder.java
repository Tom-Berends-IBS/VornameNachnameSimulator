package de.nachname.view;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Separator;
import javafx.scene.control.Slider;
import javafx.scene.control.ToolBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import static de.nachname.commons.Util.loadImage;

public class ToolBarBuilder {
	private static final Image NEW_ICON;
	private static final Image OPEN_ICON;
	private static final Image SAVE_ICON;
	private static final Image COMPILE_ICON;
	private static final Image TERRAIN_ICON;
	private static final Image HAMSTER_ICON;
	private static final Image CORN_ICON;
	private static final Image WALL_ICON;
	private static final Image DELETE_ICON;
	private static final Image HAMSTER_CORN_ICON;
	private static final Image HAMSTER_LEFT_ICON;
	private static final Image HAMSTER_MOVE_ICON;
	private static final Image HAMSTER_PICK_ICON;
	private static final Image HAMSTER_PUT_ICON;
	private static final Image PLAY_ICON;
	private static final Image PAUSE_ICON;
	private static final Image STOP_ICON;

	static {
		NEW_ICON = loadImage("/icons/New24.gif");
		OPEN_ICON = loadImage("/icons/Open24.gif");
		SAVE_ICON = loadImage("/icons/Save24.gif");
		COMPILE_ICON = loadImage("/icons/Compile24.gif");
		TERRAIN_ICON = loadImage("/icons/Terrain24.gif");
		HAMSTER_ICON = loadImage("/icons/Hamster24.png");
		CORN_ICON = loadImage("/icons/Corn24.gif");
		WALL_ICON = loadImage("/icons/Wall24.gif");
		DELETE_ICON = loadImage("/icons/Delete24.gif");
		HAMSTER_CORN_ICON = loadImage("/icons/HamsterCorn24.png");
		HAMSTER_LEFT_ICON = loadImage("/icons/HamsterLeft24.png");
		HAMSTER_MOVE_ICON = loadImage("/icons/HamsterMove24.png");
		HAMSTER_PICK_ICON = loadImage("/icons/HamsterPick24.png");
		HAMSTER_PUT_ICON = loadImage("/icons/HamsterPut24.png");
		PLAY_ICON = loadImage("/icons/Play24.gif");
		PAUSE_ICON = loadImage("/icons/Pause24.gif");
		STOP_ICON = loadImage("/icons/Stop24.gif");
	}

	private static Node createButton(final Image icon) {
		final Button button = new Button();
		button.setGraphic(new ImageView(icon));
		return button;
	}

	public Node build() {
		final ToolBar toolBar = new ToolBar();

		toolBar.getItems().addAll(
				createButton(NEW_ICON),
				createButton(OPEN_ICON),
				new Separator(),
				createButton(SAVE_ICON),
				createButton(COMPILE_ICON),
				new Separator(),
				createButton(TERRAIN_ICON),
				createButton(HAMSTER_ICON),
				createButton(CORN_ICON),
				createButton(WALL_ICON),
				createButton(DELETE_ICON),
				new Separator(),
				createButton(HAMSTER_CORN_ICON),
				createButton(HAMSTER_LEFT_ICON),
				createButton(HAMSTER_MOVE_ICON),
				createButton(HAMSTER_PICK_ICON),
				createButton(HAMSTER_PUT_ICON),
				new Separator(),
				createButton(PLAY_ICON),
				createButton(PAUSE_ICON),
				createButton(STOP_ICON),
				new Separator(),
				new Slider()
		);

		return toolBar;
	}
}
