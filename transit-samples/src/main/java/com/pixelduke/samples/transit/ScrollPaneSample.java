package com.pixelduke.samples.transit;

import com.pixelduke.transit.Style;
import com.pixelduke.transit.TransitTheme;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ScrollPaneSample extends Application {

    private static final String RESOURCE = "ScrollPane Sample.fxml";

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws IOException {
        System.setProperty("prism.lcdtext", "false");

        Parent root = FXMLLoader.load(getClass().getResource(RESOURCE));

        Scene scene = new Scene(root);
        stage.setTitle("ScrollPane Sample");

        new TransitTheme(scene, Style.LIGHT);

        stage.setScene(scene);
        stage.show();
    }
}