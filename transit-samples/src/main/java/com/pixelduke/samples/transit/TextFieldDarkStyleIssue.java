package com.pixelduke.samples.transit;

import com.pixelduke.transit.TransitTheme;
import com.pixelduke.transit.TransitStyleClass;
import com.pixelduke.transit.Style;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class TextFieldDarkStyleIssue extends Application {

    public final static int LOGIN_WINDOW_WIDTH = 720;
    public final static int LOGIN_WINDOW_HEIGHT = 348;

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("textFieldDarkStyleIssue.fxml"));

        root.getStyleClass().add(TransitStyleClass.BACKGROUND);
        primaryStage.setTitle("Hello World");

        Scene mainScene = new Scene(root, LOGIN_WINDOW_WIDTH, LOGIN_WINDOW_HEIGHT);
        TransitTheme transitTheme = new TransitTheme(Style.DARK);
        transitTheme.setScene(mainScene);
        primaryStage.setScene(mainScene);

        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
