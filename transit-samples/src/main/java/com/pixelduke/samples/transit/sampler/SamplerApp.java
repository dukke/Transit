package com.pixelduke.samples.transit.sampler;

import com.pixelduke.control.NavigationPane;
import com.pixelduke.samples.transit.logo.Logo;
import com.pixelduke.transit.Style;
import com.pixelduke.transit.TransitTheme;
import com.pixelduke.window.ThemeWindowManagerFactory;
import com.pixelduke.window.Win11ThemeWindowManager;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class SamplerApp extends Application {
    private static final String BUTTON_SAMPLER = "Sampler_Button.fxml";
    private static TransitTheme transitTheme;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        System.setProperty("prism.lcdtext", "false"); // nicer fonts (not necessary for this sample)

        NavigationPane navigationPane = new NavigationPane();

        addItems(navigationPane);

        navigationPane.selectedMenuItemProperty().addListener(observable -> {
            MenuItem selectedMenuItem = navigationPane.getSelectedMenuItem();
            if (selectedMenuItem != null) {
                System.out.println("Selected menu item changed to -> " + selectedMenuItem.getText());
            }
        });

        stage.setTitle("NavigationPane Sample");
        stage.getIcons().addAll(Logo.getLogoIcons());

        // Setup conditions for background blur to show through
        StackPane root = new StackPane(navigationPane);
        root.setStyle("-fx-background-color: transparent; "); // needs to be transparent so window background blur shows through
        Scene scene = new Scene(root, 1250, 630);
        scene.setFill(Color.TRANSPARENT); // needs to be transparent so window background blur shows through

        stage.initStyle(StageStyle.UNIFIED); // for some reason this StageStyle needs to be applied for window background blur to work

        transitTheme = new TransitTheme(scene, Style.LIGHT);

        // Show stage
        stage.setScene(scene);
        stage.show();

        // The following is what enables background blur through the use of the FXThemes library (stage must be showing at this point)
        Win11ThemeWindowManager win11ThemeWindowManager = (Win11ThemeWindowManager) ThemeWindowManagerFactory.create();
        win11ThemeWindowManager.setWindowBackdrop(stage, Win11ThemeWindowManager.Backdrop.MICA);
    }

    public static void setStyle(Style style) {
        transitTheme.setStyle(style);
    }

    public static Style getStyle() {
        return transitTheme.getStyle();
    }

    private static void addItems(NavigationPane navigationPane) {
        // menu items
        navigationPane.getMenuItems().add(new MenuItem("Home"/*, menuItem1Graphic*/));

        // Basic controls menu
        Menu basicControlsMenu = new Menu("Basic controls"/*, menuItem4Graphic*/);
        // -- button
        MenuItem buttonMenuItem = new MenuItem("Button"/*, menuItem5Graphic*/);
        buttonMenuItem.setOnAction(event -> {
            Parent root = null;
            try {
                root = FXMLLoader.load(SamplerApp.class.getResource(BUTTON_SAMPLER));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            navigationPane.setContent(root);
        });
        basicControlsMenu.getItems().add(buttonMenuItem);


        navigationPane.getMenuItems().add(basicControlsMenu);

        // footer menu items

        // settings
        navigationPane.setSettingsVisible(true);
    }
}
