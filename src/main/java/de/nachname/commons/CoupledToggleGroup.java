package de.nachname.commons;

import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;

import java.util.HashMap;
import java.util.Map;

public class CoupledToggleGroup<K> {
	final ToggleGroup backingToggleGroup;
	final Map<K, CoupledToggle<K>> coupledToggles;
	
	public CoupledToggleGroup() {
		this.backingToggleGroup = new ToggleGroup();
		coupledToggles = new HashMap<>();
	}
	
	public void addToggle(final K coupleKey, final Toggle toggle) {
		final CoupledToggle<K> coupledToggle = coupledToggles.computeIfAbsent(coupleKey, k -> new CoupledToggle<>(k, backingToggleGroup));

		coupledToggle.addToggle(toggle);
	}
	
	@SuppressWarnings("unchecked")
	public K getSelected() {
		return switch(backingToggleGroup.getSelectedToggle()) {
			case final CoupledToggle<?> coupledToggle -> (K) coupledToggle.getKey();
			case null, default -> null;
		};
	}
}
