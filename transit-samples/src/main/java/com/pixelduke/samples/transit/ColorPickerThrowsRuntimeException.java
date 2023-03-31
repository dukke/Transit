package com.pixelduke.samples.transit;

import com.pixelduke.transit.TransitTheme;
import com.pixelduke.transit.Style;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.ColorPicker;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class ColorPickerThrowsRuntimeException extends Application {

    @Override
        public void start(Stage stage) {
            System.setProperty("prism.lcdtext", "false");

            Scene scene = new Scene(new Pane(new ColorPicker()), 800, 600);
            stage.setScene(scene);
            TransitTheme transitTheme = new TransitTheme(scene, Style.DARK);
            stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}

