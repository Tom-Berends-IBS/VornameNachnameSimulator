package de.nachname;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Main extends Application {
    public static void main(final String[] args) {
        launch(args);
    }

    @Override
    public void start(final Stage primaryStage) {
        final BorderPane root = new BorderPane();

        final MenuBar menuBar = new MenuBar();
        final Menu editorMenu = new Menu("Editor");
        final Menu territoryMenu = new Menu("Territory");
        final Menu hamsterMenu = new Menu("Hamster");
        final Menu simulationMenu = new Menu("Simulation");
        menuBar.getMenus().addAll(editorMenu, territoryMenu, hamsterMenu, simulationMenu);

        final SplitPane splitPane = new SplitPane();
        final TextArea textArea = new TextArea();
        final ScrollPane scrollPane = new ScrollPane();
        splitPane.getItems().addAll(textArea, scrollPane);

        final Label label = new Label("Herzlich Willkommen!");

        root.setTop(menuBar);
        root.setCenter(splitPane);
        root.setBottom(label);
        
        final Scene scene = new Scene(root);

        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
