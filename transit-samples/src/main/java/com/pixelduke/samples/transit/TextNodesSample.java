package com.pixelduke.samples.transit;

import com.pixelduke.transit.TransitTheme;
import com.pixelduke.transit.Style;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

public class TextNodesSample extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        Style startingStyle = Style.LIGHT;
        TransitTheme transitTheme = new TransitTheme(startingStyle);

        BorderPane mainContainer = new BorderPane();
        mainContainer.getStyleClass().add("background");

        VBox vBox = new VBox();
        vBox.getStyleClass().add("background");

        stage.setTitle("Text Sample");
        Scene scene = new Scene(mainContainer, 400, 350);

        Label label = new Label("This is a label");
        VBox.setMargin(label, new Insets(30, 0, 0, 0));

        Text text = new Text("This is a Text node");
        TextFlow textFlow = new TextFlow(text);

        vBox.getChildren().addAll(label, textFlow);
        vBox.setSpacing(10);

        ComboBox<Style> jmetroStyleComboBox = new ComboBox<>();
        jmetroStyleComboBox.getItems().addAll(Style.DARK, Style.LIGHT);
        jmetroStyleComboBox.setValue(startingStyle);
        jmetroStyleComboBox.valueProperty().addListener(observable -> transitTheme.setStyle(jmetroStyleComboBox.getValue()));

        mainContainer.setTop(jmetroStyleComboBox);
        mainContainer.setCenter(vBox);

        transitTheme.setScene(scene);

        stage.setScene(scene);
        stage.show();
    }
}
