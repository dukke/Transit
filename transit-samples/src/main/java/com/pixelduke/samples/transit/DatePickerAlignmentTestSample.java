package com.pixelduke.samples.transit;

import com.pixelduke.transit.TransitTheme;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import com.pixelduke.transit.TransitStyleClass;
import com.pixelduke.transit.Style;

public class DatePickerAlignmentTestSample extends Application {

    @Override
    public void start(Stage stage) {
        System.setProperty("prism.lcdtext", "false");

        DatePicker datePicker1 = new DatePicker();
        DatePicker datePicker2 = new DatePicker();

        HBox container = new HBox(datePicker1, datePicker2);
        container.getStyleClass().add(TransitStyleClass.BACKGROUND);
        Scene scene = new Scene(container, 800, 600);
        stage.setScene(scene);
        new TransitTheme(scene, Style.LIGHT);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}