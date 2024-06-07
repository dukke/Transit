package com.pixelduke.samples.transit;

import com.pixelduke.transit.TransitTheme;
import com.pixelduke.transit.Style;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

//public class JMetroAppliedToParent extends Application {
//
//    @Override
//    public void start(Stage primaryStage) throws Exception {
//        final MenuBar top = new MenuBar();
//        top.getMenus().add(
//            new Menu("File", null,
//                new MenuItem("We want to have JMetro style"),
//                new MenuItem("Item 2"),
//                new MenuItem("Item 3")
//            )
//        );
//
//        final StackPane center = new StackPane(new Button("I don't want to have JMetro Style"));
//
//        final BorderPane root = new BorderPane();
//        root.setTop(top);
//        root.setCenter(center);
//
//        //we only want to add the JMetro style to the MenuBar, not to the whole BorderPane
//        new TransitTheme(Style.DARK).setParent(top);
//        //new JMetro(Style.DARK).setParent(root) -> it works, but it's not what I want
//
//        primaryStage.setScene(new Scene(root));
//        primaryStage.setTitle("Demo");
//        primaryStage.show();
//    }
//
//    public static void main(String[] args) {
//        launch(args);
//    }
//}