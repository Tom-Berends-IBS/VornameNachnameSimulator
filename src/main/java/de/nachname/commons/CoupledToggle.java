package de.nachname.commons;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public final class CoupledToggle<K> implements Toggle {
	private static final Object USER_DATA_KEY = new Object();

	private final K key;
	private final Set<Toggle> toggles;

	private final ObjectProperty<ToggleGroup> toggleGroup;
	private final BooleanProperty selected;

	private ObservableMap<Object, Object> properties;

	public CoupledToggle(final K key, final ToggleGroup toggleGroup) {
		this.key = key;
		toggles = new HashSet<>();

		this.toggleGroup = new SimpleObjectProperty<>(toggleGroup);
		selected = new SimpleBooleanProperty();

		selected.addListener((_, _, isSelected) -> toggles.forEach(toggle -> toggle.setSelected(isSelected)));
	}

	public CoupledToggle(final K key) {
		this(key, null);
	}

	public void addToggle(final Toggle toggle) {
		if(!toggles.add(toggle)) {
			return;
		}

		toggle.selectedProperty().addListener((_, _, isSelected) -> {
			if(isSelected == null || !isSelected) {
				if(getToggleGroup().getSelectedToggle() == this) {
					getToggleGroup().selectToggle(null);
				}
			} else {
				getToggleGroup().selectToggle(this);
			}
		});
	}

	@Override
	public ToggleGroup getToggleGroup() {
		return toggleGroupProperty().get();
	}

	@Override
	public void setToggleGroup(final ToggleGroup toggleGroup) {
		toggleGroupProperty().set(toggleGroup);
	}

	@Override
	public ObjectProperty<ToggleGroup> toggleGroupProperty() {
		return toggleGroup;
	}

	@Override
	public boolean isSelected() {
		return selectedProperty().get();
	}

	@Override
	public void setSelected(final boolean selected) {
		selectedProperty().set(selected);
	}

	@Override
	public BooleanProperty selectedProperty() {
		return selected;
	}

	@Override
	public Object getUserData() {
		return getProperties().get(USER_DATA_KEY);
	}

	@Override
	public void setUserData(final Object value) {
		getProperties().put(USER_DATA_KEY, value);
	}

	@Override
	public ObservableMap<Object, Object> getProperties() {
		if(properties == null) {
			properties = FXCollections.observableMap(new HashMap<>());
		}
		return properties;
	}

	public K getKey() {
		return key;
	}
}
