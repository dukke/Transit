package com.pixelduke.samples.transit;

import com.pixelduke.samples.transit.panessample.PanesWithBackgroundStyleClassController;
import com.pixelduke.transit.TransitTheme;
import com.pixelduke.transit.Style;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

//public class PanesWithBackgroundStyleClassSample extends Application {
//    private static final String PANES_RESOURCE = "Transit PanesWithBackgroundStyleClass.fxml";
//
//    static final private Style STYLE = Style.LIGHT;
//
//    @Override
//    public void start(Stage primaryStage) throws Exception{
//        System.setProperty("prism.lcdtext", "false");
//
//        FXMLLoader fxmlLoader = new FXMLLoader();
//        Parent root = fxmlLoader.load(PanesWithBackgroundStyleClassSample.class.getResource(PANES_RESOURCE).openStream());
//        PanesWithBackgroundStyleClassController controller = fxmlLoader.getController();
//        primaryStage.setTitle("JMetro");
//
//        TransitTheme transitTheme = new TransitTheme(root, STYLE);
//
//        controller.init(transitTheme);
//
//        Scene scene = new Scene(root);
//        primaryStage.setScene(scene);
//        primaryStage.show();
//    }
//
//
//    public static void main(String[] args) {
//        launch(args);
//    }
//
//}

