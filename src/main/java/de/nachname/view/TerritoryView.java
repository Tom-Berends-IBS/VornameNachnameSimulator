package de.nachname.view;

import de.nachname.controller.TerritoryViewController;
import de.nachname.model.Direction;
import de.nachname.model.Territory;
import javafx.geometry.Insets;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

import java.util.EnumMap;
import java.util.Map;

import static de.nachname.commons.Util.loadImage;
import static java.lang.Math.min;

public class TerritoryView extends StackPane {
	public static final double CELL_SIZE = 32.0;

	private static final Insets MARGIN = new Insets(20);

	private static final Paint BACKGROUND_COLOR = Color.valueOf("90ee90");

	private static final Image WALL_SPRITE;
	private static final Image[] CORN_SPRITES;
	private static final Map<Direction, Image> HAMSTER_SPRITES;

	static {
		WALL_SPRITE = loadImage("/sprites/Wall32.png", CELL_SIZE, CELL_SIZE);

		CORN_SPRITES = new Image[12];
		for(int n = 1; n <= CORN_SPRITES.length; n++) {
			CORN_SPRITES[n - 1] = loadImage("/sprites/" + n + "Corn32.png", CELL_SIZE, CELL_SIZE);
		}

		HAMSTER_SPRITES = new EnumMap<>(Direction.class);
		for(final Direction direction : Direction.values()) {
			HAMSTER_SPRITES.put(direction, loadImage("/sprites/" + direction + "Hamster32.png", CELL_SIZE, CELL_SIZE));
		}
	}

	private final Territory territory;

	private final Canvas backgroundCanvas;
	private final Canvas mainCanvas;

	public TerritoryView(final TerritoryViewController controller) {
		this.territory = controller.getTerritory();

		backgroundCanvas = new Canvas();
		mainCanvas = new Canvas();

		StackPane.setMargin(backgroundCanvas, MARGIN);
		StackPane.setMargin(mainCanvas, MARGIN);

		getChildren().addAll(
				backgroundCanvas,
				mainCanvas
		);

		territory.addDimensionsChangeListener(this::resetCanvases);

		territory.addHamsterChangeListener((oldI, oldJ, newI, newJ) -> {
			drawCell(oldI, oldJ);
			drawCell(newI, newJ);
		});

		territory.addCellChangeListener(this::drawCell);

		mainCanvas.setOnMouseClicked(controller::canvasClicked);
		mainCanvas.setOnMousePressed(controller::canvasPressed);
		mainCanvas.setOnMouseDragged(controller::canvasDragged);

		resetCanvases(territory.getWidth(), territory.getHeight());
	}

	private void resetCanvases(final int width, final int height) {
		backgroundCanvas.setWidth(width * CELL_SIZE);
		backgroundCanvas.setHeight(height * CELL_SIZE);
		mainCanvas.setWidth(width * CELL_SIZE);
		mainCanvas.setHeight(height * CELL_SIZE);

		drawBackground();
		draw();
	}

	public void drawBackground() {
		final GraphicsContext gc = backgroundCanvas.getGraphicsContext2D();

		gc.clearRect(0, 0, mainCanvas.getWidth(), mainCanvas.getHeight());

		gc.setFill(BACKGROUND_COLOR);
		gc.setStroke(Color.BLACK);

		for(int i = 0; i < territory.getWidth(); i++) {
			for(int j = 0; j < territory.getHeight(); j++) {
				final double x = i * CELL_SIZE;
				final double y = j * CELL_SIZE;

				gc.fillRect(x, y, CELL_SIZE, CELL_SIZE);
				gc.strokeRect(x, y, CELL_SIZE, CELL_SIZE);
			}
		}
	}

	public void draw() {
		final GraphicsContext gc = mainCanvas.getGraphicsContext2D();

		gc.clearRect(0, 0, mainCanvas.getWidth(), mainCanvas.getHeight());

		for(int i = 0; i < territory.getWidth(); i++) {
			for(int j = 0; j < territory.getHeight(); j++) {
				drawCell(gc, i, j);
			}
		}
	}

	public void drawCell(final GraphicsContext gc, final int i, final int j) {
		final double x = i * CELL_SIZE;
		final double y = j * CELL_SIZE;

		gc.clearRect(x, y, CELL_SIZE, CELL_SIZE);

		final int numCorns = territory.getNumCorns(i, j);
		if(numCorns > 0) {
			gc.drawImage(CORN_SPRITES[min(numCorns, 12) - 1], x, y);
		}
		if(territory.isHamsterAt(i, j)) {
			gc.drawImage(HAMSTER_SPRITES.get(territory.getHamsterDirection()), x, y);
		}
		if(territory.isWall(i, j)) {
			gc.drawImage(WALL_SPRITE, x, y);
		}
	}

	public void drawCell(final int i, final int j) {
		drawCell(mainCanvas.getGraphicsContext2D(), i, j);
	}
}
