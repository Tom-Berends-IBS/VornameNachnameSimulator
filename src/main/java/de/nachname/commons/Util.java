package de.nachname.commons;

import javafx.scene.image.Image;

import java.io.InputStream;

public final class Util {
	public static Image loadImage(final String file) {
		return loadImage(file, 0, 0);
	}

	public static Image loadImage(final String file, final double requestedWidth, final double requestedHeight) {
		return loadImage(file, requestedWidth, requestedHeight, true, true);
	}

	public static Image loadImage(final String file, final double requestedWidth, final double requestedHeight, final boolean preserveRatio, final boolean smooth) {
		final InputStream resourceStream = Util.class.getResourceAsStream(file);
		if(resourceStream == null) {
			throw new IllegalArgumentException("Image not found: " + file);
		}
		return new Image(resourceStream, requestedWidth, requestedHeight, preserveRatio, smooth);
	}

	private Util() {
	}
}
