package de.nachname;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCombination;
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
        final MenuItem newMenuItem = new MenuItem("Neu");
        final MenuItem openMenuItem = new MenuItem("Öffnen");
        final MenuItem saveMenuItem = new MenuItem("Speichern");
        final MenuItem compileMenuItem = new MenuItem("Kompilieren");
        final MenuItem printMenuItem = new MenuItem("Drucken");
        final MenuItem quitMenuItem = new MenuItem("Beenden");

        newMenuItem.setAccelerator(KeyCombination.keyCombination("Ctrl+N"));
        openMenuItem.setAccelerator(KeyCombination.keyCombination("Ctrl+O"));
        saveMenuItem.setAccelerator(KeyCombination.keyCombination("Ctrl+S"));
        compileMenuItem.setAccelerator(KeyCombination.keyCombination("Ctrl+K"));
        printMenuItem.setAccelerator(KeyCombination.keyCombination("Ctrl+P"));
        quitMenuItem.setAccelerator(KeyCombination.keyCombination("Ctrl+Q"));

        newMenuItem.setGraphic(new ImageView(new Image(Main.class.getResourceAsStream("/icons/New16.gif"))));
        openMenuItem.setGraphic(new ImageView(new Image(Main.class.getResourceAsStream("/icons/Open16.gif"))));
        saveMenuItem.setGraphic(new ImageView(new Image(Main.class.getResourceAsStream("/icons/Save16.gif"))));
        printMenuItem.setGraphic(new ImageView(new Image(Main.class.getResourceAsStream("/icons/Print16.gif"))));

        editorMenu.getItems().addAll(
                newMenuItem,
                openMenuItem,
                saveMenuItem,
                new SeparatorMenuItem(),
                compileMenuItem,
                printMenuItem,
                new SeparatorMenuItem(),
                quitMenuItem
        );
        
        final Menu territoryMenu = new Menu("Territory");
        final MenuItem changeSizeMenuItem = new MenuItem("Größe ändern...");
        final MenuItem hamsterMenuItem = new RadioMenuItem("Hamster platzieren");
        final MenuItem cornMenuItem = new RadioMenuItem("Korn platzieren");
        final MenuItem wallMenuItem = new RadioMenuItem("Mauer platzieren");
        final MenuItem deleteMenuitem = new RadioMenuItem("kachel löschen");

        territoryMenu.getItems().addAll(
                changeSizeMenuItem,
                new SeparatorMenuItem(),
                hamsterMenuItem,
                cornMenuItem,
                wallMenuItem,
                deleteMenuitem
        );
        
        final Menu hamsterMenu = new Menu("Hamster");
        final MenuItem numCornsMenuItem = new MenuItem("Körner im Maul...");
        final MenuItem turnLeftMenuItem = new MenuItem("linksUm");
        final MenuItem moveMenuItem = new MenuItem("vor");
        final MenuItem pickCornMenuItem = new MenuItem("nimm");
        final MenuItem putCornMenuItem = new MenuItem("gib");

        turnLeftMenuItem.setAccelerator(KeyCombination.keyCombination("Ctrl+Shift+L"));
        moveMenuItem.setAccelerator(KeyCombination.keyCombination("Ctrl+Shift+V"));
        pickCornMenuItem.setAccelerator(KeyCombination.keyCombination("Ctrl+Shift+N"));
        putCornMenuItem.setAccelerator(KeyCombination.keyCombination("Ctrl+Shift+G"));

        hamsterMenu.getItems().addAll(
                numCornsMenuItem,
                turnLeftMenuItem,
                moveMenuItem,
                pickCornMenuItem,
                putCornMenuItem
        );
        
        final Menu simulationMenu = new Menu("Simulation");
        final MenuItem startMenuItem = new MenuItem("Start/Fortsetzen");
        final MenuItem pauseMenuItem = new MenuItem("Pause");
        final MenuItem stopMenuItem = new MenuItem("Stopp");

        startMenuItem.setAccelerator(KeyCombination.valueOf("Ctrl+F11"));
        stopMenuItem.setAccelerator(KeyCombination.valueOf("Ctrl+F12"));

        startMenuItem.setGraphic(new ImageView(new Image(Main.class.getResourceAsStream("/icons/Play16.gif"))));
        pauseMenuItem.setGraphic(new ImageView(new Image(Main.class.getResourceAsStream("/icons/Pause16.gif"))));
        stopMenuItem.setGraphic(new ImageView(new Image(Main.class.getResourceAsStream("/icons/Stop16.gif"))));

        simulationMenu.getItems().addAll(
                startMenuItem,
                pauseMenuItem,
                stopMenuItem
        );
        
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
