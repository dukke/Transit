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
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.HashMap;

public class SamplerApp extends Application {
    private static final String SAMPLER_APP_BASE_STYLESHEET = SamplerApp.class.getResource("sampler-app-base.css").toExternalForm();
    private static final String SAMPLER_APP_DARK_THEME = SamplerApp.class.getResource("sampler-app-dark.css").toExternalForm();
    private static final String SAMPLER_APP_LIGHT_THEME = SamplerApp.class.getResource("sampler-app-light.css").toExternalForm();

    private static final String BUTTON_SAMPLER = "Sampler_Button.fxml";
    private static final String TOGGLE_BUTTON_SAMPLER = "Sampler_ToggleButton.fxml";
    private static final String CHECKBOX_SAMPLER = "Sampler_CheckBox.fxml";
    private static final String RADIOBUTTON_SAMPLER = "Sampler_RadioButton.fxml";
    private static final String COMBOBOX_SAMPLER = "Sampler_ComboBox.fxml";
    private static final String TEXT_FIELD_SAMPLER = "Sampler_TextField.fxml";
    private static final String PASSWORD_FIELD_SAMPLER = "Sampler_PasswordField.fxml";
    private static final String PROGRESS_BAR_SAMPLER = "Sampler_ProgressBar.fxml";
    private static final String SLIDER_SAMPLER = "Sampler_Slider.fxml";


    private static TransitTheme transitTheme;

    private final HashMap<MenuItem, ImageView> menuItemLightGraphic = new HashMap<>();
    private final HashMap<MenuItem, ImageView> menuItemDarkGraphic = new HashMap<>();

    public static SamplerApp INSTANCE;

    public static void main(String[] args) {
        launch(args);
    }

    private NavigationPane navigationPane;

    @Override
    public void start(Stage stage) {
        System.setProperty("prism.lcdtext", "false"); // nicer fonts (not necessary for this sample)
        INSTANCE = this;

        navigationPane = new NavigationPane();

        addItemsToNavigationPane();

        navigationPane.selectedMenuItemProperty().addListener(observable -> {
            MenuItem selectedMenuItem = navigationPane.getSelectedMenuItem();
            if (selectedMenuItem != null) {
                System.out.println("Selected menu item changed to -> " + selectedMenuItem.getText());
            }
        });

        stage.setTitle("Transit Theme Sampler");
        stage.getIcons().addAll(Logo.getLogoIcons());

        // Setup conditions for background blur to show through
        StackPane root = new StackPane(navigationPane);
        root.setStyle("-fx-background-color: transparent; "); // needs to be transparent so window background blur shows through
        Scene scene = new Scene(root, 1250, 630);
        scene.setFill(Color.TRANSPARENT); // needs to be transparent so window background blur shows through

        stage.initStyle(StageStyle.UNIFIED); // for some reason this StageStyle needs to be applied for window background blur to work

        transitTheme = new TransitTheme();
        transitTheme.setScene(scene);
        transitTheme.getSceneStylesheets().add(SAMPLER_APP_BASE_STYLESHEET);
        setStyle(Style.LIGHT);

        // Show stage
        stage.setScene(scene);
        stage.show();

        // The following is what enables background blur through the use of the FXThemes library (stage must be showing at this point)
        Win11ThemeWindowManager win11ThemeWindowManager = (Win11ThemeWindowManager) ThemeWindowManagerFactory.create();
        win11ThemeWindowManager.setWindowBackdrop(stage, Win11ThemeWindowManager.Backdrop.MICA);
    }

    public void setStyle(Style style) {
        transitTheme.setStyle(style);
        if (style == Style.DARK) {
            transitTheme.getSceneStylesheets().remove(SAMPLER_APP_LIGHT_THEME);
            transitTheme.getSceneStylesheets().add(SAMPLER_APP_DARK_THEME);
            menuItemDarkGraphic.keySet().forEach(key -> {
                key.setGraphic(menuItemDarkGraphic.get(key));
            });

        } else {
            transitTheme.getSceneStylesheets().remove(SAMPLER_APP_DARK_THEME);
            transitTheme.getSceneStylesheets().add(SAMPLER_APP_LIGHT_THEME);
            menuItemLightGraphic.keySet().forEach(key -> {
                key.setGraphic(menuItemLightGraphic.get(key));
            });
        }
    }

    public Style getStyle() {
        return transitTheme.getStyle();
    }

    private void addItemsToNavigationPane() {
        // menu items
        navigationPane.getMenuItems().add(createMenuItem("Home", "icons8-home-20.png", "icons8-home-white-20.png"));

        // Basic controls menu
        Menu basicControlsMenu = createMenu("Basic controls", "icons8-alt-20.png", "icons8-alt-white-20.png");
        basicControlsMenu.getItems().add(createMenuItem("Button", BUTTON_SAMPLER));
        basicControlsMenu.getItems().add(createMenuItem("Toggle Button", TOGGLE_BUTTON_SAMPLER));
        basicControlsMenu.getItems().add(createMenuItem("CheckBox", CHECKBOX_SAMPLER));
        basicControlsMenu.getItems().add(createMenuItem("RadioButton", RADIOBUTTON_SAMPLER));
        basicControlsMenu.getItems().add(createMenuItem("ComboBox", COMBOBOX_SAMPLER));
        basicControlsMenu.getItems().add(createMenuItem("TextField", TEXT_FIELD_SAMPLER));
        basicControlsMenu.getItems().add(createMenuItem("PasswordField", PASSWORD_FIELD_SAMPLER));
        basicControlsMenu.getItems().add(createMenuItem("ProgressBar", PROGRESS_BAR_SAMPLER));
        basicControlsMenu.getItems().add(createMenuItem("Slider", SLIDER_SAMPLER));

        navigationPane.getMenuItems().add(basicControlsMenu);

        // footer menu items

        // settings
        navigationPane.setSettingsVisible(true);
    }

    private Menu createMenu(String labelText, String lightImageFilename, String darkImageFilename) {
        return (Menu) createMenuItemOrMenu(labelText, lightImageFilename, darkImageFilename, null, true);
    }

    private MenuItem createMenuItem(String labelText, String contentFileName) {
        return createMenuItemOrMenu(labelText, null, null, contentFileName,false);
    }

    private MenuItem createMenuItem(String labelText, String lightImageFilename, String darkImageFilename) {
        return createMenuItemOrMenu(labelText, lightImageFilename, darkImageFilename, null, false);
    }

    private MenuItem createMenuItemOrMenu(String labelText, String lightImageFilename, String darkImageFilename, String contentFileName, boolean isMenu) {
        ImageView lightImageView;
        ImageView darkImageView;

        MenuItem menuItem;

        if (!isMenu) {
            menuItem = new MenuItem();
        } else {
            menuItem = new Menu();
        }

        if (lightImageFilename != null) {
            lightImageView = new ImageView(SamplerApp.class.getResource(lightImageFilename).toExternalForm());
            darkImageView = new ImageView(SamplerApp.class.getResource(darkImageFilename).toExternalForm());

            menuItemLightGraphic.put(menuItem, lightImageView);
            menuItemDarkGraphic.put(menuItem, darkImageView);

            menuItem.setGraphic(lightImageView);
        }

        menuItem.setText(labelText);

        // Set content when item selected
        if (contentFileName != null) {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(SamplerApp.class.getResource(contentFileName));

                final Parent root = fxmlLoader.load();
                final SamplerBaseController samplerBaseController = fxmlLoader.getController();

                menuItem.setOnAction(event -> {
                    navigationPane.setContent(root);
                    samplerBaseController.onShown();
                });
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        return menuItem;
    }

}
