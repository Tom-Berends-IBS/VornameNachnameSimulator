package de.nachname;

import de.nachname.controller.MainViewController;
import de.nachname.model.Territory;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    public static void main(final String[] args) {
        launch(args);
    }

    @Override
    public void start(final Stage primaryStage) {
        final Territory territory = new Territory(15, 10);

        final MainViewController mainViewController = new MainViewController(primaryStage, territory);

        final Parent root = mainViewController.buildView();
        
        final Scene scene = new Scene(root);

        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
