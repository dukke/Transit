package com.pixelduke.samples.transit;

import com.pixelduke.transit.TransitTheme;
import com.pixelduke.transit.Style;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class TextFieldAndPasswordFieldShrinkGlitch extends Application {

    public void start(final Stage primaryStage) {
        VBox container = new VBox(10);
        container.setPadding(new Insets(10));

        TextField textField = new TextField();
        PasswordField passwordField = new PasswordField();
        Button button = new Button("Close");
        button.setOnAction(event -> primaryStage.hide());

        container.getChildren().addAll(textField, passwordField, button);

        Scene scene = new Scene(container);
        TransitTheme metro = new TransitTheme(scene, Style.LIGHT);

        primaryStage.setScene(scene);
        primaryStage.show();
    }

}