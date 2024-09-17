package de.nachname.commons;

import javafx.scene.image.Image;

import java.io.InputStream;

public final class Util {
	public static Image loadImage(final String file) {
		final InputStream resourceStream = Util.class.getResourceAsStream(file);
		if(resourceStream == null) {
			throw new IllegalArgumentException("Image not found: " + file);
		}
		return new Image(resourceStream);
	}

	private Util() {
	}
}
