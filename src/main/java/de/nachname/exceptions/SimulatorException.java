package de.nachname.exceptions;

public class SimulatorException extends RuntimeException {
	public SimulatorException() {
	}

	public SimulatorException(final String message) {
		super(message);
	}
}
