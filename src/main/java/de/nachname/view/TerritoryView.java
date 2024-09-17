package de.nachname.view;

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
	private final Canvas canvas;

	public TerritoryView(final Territory territory) {
		this.territory = territory;

		canvas = new Canvas(territory.getWidth() * CELL_SIZE, territory.getHeight() * CELL_SIZE);

		StackPane.setMargin(canvas, MARGIN);

		getChildren().add(canvas);

		territory.addDimensionsChangeListener((newWidth, newHeight) -> {
			canvas.setWidth(newWidth * CELL_SIZE);
			canvas.setHeight(newHeight * CELL_SIZE);

			draw();
		});

		territory.addHamsterChangeListener((oldI, oldJ, newI, newJ) -> {
			final GraphicsContext gc = canvas.getGraphicsContext2D();

			gc.setFill(BACKGROUND_COLOR);
			gc.setStroke(Color.BLACK);

			final double oldX = oldI * CELL_SIZE;
			final double oldY = oldJ * CELL_SIZE;

			final double newX = newI * CELL_SIZE;
			final double newY = newJ * CELL_SIZE;

			gc.fillRect(oldX, oldY, CELL_SIZE, CELL_SIZE);
			gc.strokeRect(oldX, oldY, CELL_SIZE, CELL_SIZE);

			gc.fillRect(newX, newY, CELL_SIZE, CELL_SIZE);
			gc.strokeRect(newX, newY, CELL_SIZE, CELL_SIZE);

			int numCorns = territory.getNumCorns(oldI, oldJ);
			if(numCorns > 0) {
				gc.drawImage(CORN_SPRITES[min(numCorns, 12) - 1], oldX, oldY);
			}
			if(territory.isWall(oldI, oldJ)) {
				gc.drawImage(WALL_SPRITE, oldX, oldY);
			}
			if(territory.isHamsterAt(oldI, oldJ)) {
				gc.drawImage(HAMSTER_SPRITES.get(territory.getHamsterDirection()), oldX, oldY);
			}

			numCorns = territory.getNumCorns(newI, newJ);
			if(numCorns > 0) {
				gc.drawImage(CORN_SPRITES[min(numCorns, 12) - 1], newX, newY);
			}
			if(territory.isWall(newI, newJ)) {
				gc.drawImage(WALL_SPRITE, newX, newY);
			}
			if(territory.isHamsterAt(newI, newJ)) {
				gc.drawImage(HAMSTER_SPRITES.get(territory.getHamsterDirection()), newX, newY);
			}
		});

		territory.addCellChangeListener((i, j) -> {
			final GraphicsContext gc = canvas.getGraphicsContext2D();

			gc.setFill(BACKGROUND_COLOR);
			gc.setStroke(Color.BLACK);

			final double x = i * CELL_SIZE;
			final double y = j * CELL_SIZE;

			gc.fillRect(x, y, CELL_SIZE, CELL_SIZE);
			gc.strokeRect(x, y, CELL_SIZE, CELL_SIZE);

			final int numCorns = territory.getNumCorns(i, j);
			if(numCorns > 0) {
				gc.drawImage(CORN_SPRITES[min(numCorns, 12) - 1], x, y);
			}
			if(territory.isWall(i, j)) {
				gc.drawImage(WALL_SPRITE, x, y);
			}
			if(territory.isHamsterAt(i, j)) {
				gc.drawImage(HAMSTER_SPRITES.get(territory.getHamsterDirection()), x, y);
			}
		});

		draw();
	}

	public void draw() {
		final GraphicsContext gc = canvas.getGraphicsContext2D();

		gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

		gc.setFill(BACKGROUND_COLOR);
		gc.setStroke(Color.BLACK);

		for(int i = 0; i < territory.getWidth(); i++) {
			for(int j = 0; j < territory.getHeight(); j++) {
				final double x = i * CELL_SIZE;
				final double y = j * CELL_SIZE;

				gc.fillRect(x, y, CELL_SIZE, CELL_SIZE);
				gc.strokeRect(x, y, CELL_SIZE, CELL_SIZE);

				final int numCorns = territory.getNumCorns(i, j);
				if(numCorns > 0) {
					gc.drawImage(CORN_SPRITES[min(numCorns, 12) - 1], x, y);
				}
				if(territory.isWall(i, j)) {
					gc.drawImage(WALL_SPRITE, x, y);
				}
				if(territory.isHamsterAt(i, j)) {
					gc.drawImage(HAMSTER_SPRITES.get(territory.getHamsterDirection()), x, y);
				}
			}
		}
	}
}
