package de.nachname;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {
    public static void main(final String[] args) {
        launch(args);
    }

    @Override
    public void start(final Stage primaryStage) {
        final BorderPane root = new BorderPane();

        final VBox controlBars = new VBox();

        final MenuBar menuBar = new MenuBar();
        final Menu editorMenu = new Menu("Editor");
        final Menu territoryMenu = new Menu("Territory");
        final Menu hamsterMenu = new Menu("Hamster");
        final Menu simulationMenu = new Menu("Simulation");
        menuBar.getMenus().addAll(editorMenu, territoryMenu, hamsterMenu, simulationMenu);

        final ToolBar toolBar = new ToolBar();
        final Button newButton = new Button();
        final Button openButton = new Button();
        final Button saveButton = new Button();
        final Button compileButton = new Button();
        final Button terrainButton = new Button();
        final Button hamsterButton = new Button();
        final Button cornButton = new Button();
        final Button wallButton = new Button();
        final Button deleteButton = new Button();
        final Button hamsterCornButton = new Button();
        final Button hamsterLeftButton = new Button();
        final Button hamsterMoveButton = new Button();
        final Button hamsterPickButton = new Button();
        final Button hamsterPutButton = new Button();
        final Button playButton = new Button();
        final Button pauseButton = new Button();
        final Button stopButton = new Button();

        newButton.setGraphic(new ImageView(new Image(Main.class.getResourceAsStream("/icons/New24.gif"))));
        openButton.setGraphic(new ImageView(new Image(Main.class.getResourceAsStream("/icons/Open24.gif"))));
        saveButton.setGraphic(new ImageView(new Image(Main.class.getResourceAsStream("/icons/Save24.gif"))));
        compileButton.setGraphic(new ImageView(new Image(Main.class.getResourceAsStream("/icons/Compile24.gif"))));
        terrainButton.setGraphic(new ImageView(new Image(Main.class.getResourceAsStream("/icons/Terrain24.gif"))));
        hamsterButton.setGraphic(new ImageView(new Image(Main.class.getResourceAsStream("/icons/Hamster24.png"))));
        cornButton.setGraphic(new ImageView(new Image(Main.class.getResourceAsStream("/icons/Corn24.gif"))));
        wallButton.setGraphic(new ImageView(new Image(Main.class.getResourceAsStream("/icons/Wall24.gif"))));
        deleteButton.setGraphic(new ImageView(new Image(Main.class.getResourceAsStream("/icons/Delete24.gif"))));
        hamsterCornButton.setGraphic(new ImageView(new Image(Main.class.getResourceAsStream("/icons/HamsterCorn24.png"))));
        hamsterLeftButton.setGraphic(new ImageView(new Image(Main.class.getResourceAsStream("/icons/HamsterLeft24.png"))));
        hamsterMoveButton.setGraphic(new ImageView(new Image(Main.class.getResourceAsStream("/icons/HamsterMove24.png"))));
        hamsterPickButton.setGraphic(new ImageView(new Image(Main.class.getResourceAsStream("/icons/HamsterPick24.png"))));
        hamsterPutButton.setGraphic(new ImageView(new Image(Main.class.getResourceAsStream("/icons/HamsterPut24.png"))));
        playButton.setGraphic(new ImageView(new Image(Main.class.getResourceAsStream("/icons/Play24.gif"))));
        pauseButton.setGraphic(new ImageView(new Image(Main.class.getResourceAsStream("/icons/Pause24.gif"))));
        stopButton.setGraphic(new ImageView(new Image(Main.class.getResourceAsStream("/icons/Stop24.gif"))));

        toolBar.getItems().addAll(
                newButton,
                openButton,
                new Separator(),
                saveButton,
                compileButton,
                new Separator(),
                terrainButton,
                hamsterButton,
                cornButton,
                wallButton,
                deleteButton,
                new Separator(),
                hamsterCornButton,
                hamsterLeftButton,
                hamsterMoveButton,
                hamsterPickButton,
                hamsterPutButton,
                new Separator(),
                playButton,
                pauseButton,
                stopButton,
                new Separator(),
                new Slider()
        );

        controlBars.getChildren().addAll(menuBar, toolBar);

        final SplitPane splitPane = new SplitPane();
        final TextArea textArea = new TextArea();
        final ScrollPane scrollPane = new ScrollPane();
        splitPane.getItems().addAll(textArea, scrollPane);

        final Label label = new Label("Herzlich Willkommen!");

        root.setTop(controlBars);
        root.setCenter(splitPane);
        root.setBottom(label);
        
        final Scene scene = new Scene(root);

        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
