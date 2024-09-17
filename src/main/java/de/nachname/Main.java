package de.nachname;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Main extends Application {
    public static void main(final String[] args) {
        launch(args);
    }

    @Override
    public void start(final Stage primaryStage) {
        final BorderPane root = new BorderPane();
        final Scene scene = new Scene(root);

        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
