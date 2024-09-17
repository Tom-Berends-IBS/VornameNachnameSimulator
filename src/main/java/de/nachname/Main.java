package de.nachname;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Main extends Application {
    public static void main(final String[] args) {
        launch(args);
    }

    @Override
    public void start(final Stage primaryStage) {
        final BorderPane root = new BorderPane();

        final SplitPane splitPane = new SplitPane();
        final TextArea textArea = new TextArea();
        final ScrollPane scrollPane = new ScrollPane();
        splitPane.getItems().addAll(textArea, scrollPane);

        final Label label = new Label("Herzlich Willkommen!");

        root.setCenter(splitPane);
        root.setBottom(label);
        
        final Scene scene = new Scene(root);

        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
