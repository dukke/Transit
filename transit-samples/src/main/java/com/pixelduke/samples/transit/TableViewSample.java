package com.pixelduke.samples.transit;

import com.pixelduke.samples.transit.logo.Logo;
import com.pixelduke.transit.TransitTheme;
import com.pixelduke.transit.Style;
import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import static com.pixelduke.transit.TransitStyleClass.*;

public class TableViewSample extends Application {

    private static final Style STYLE = Style.LIGHT;

    private final TableView<Person> table = new TableView<>();
    private final ObservableList<Person> data =
            FXCollections.observableArrayList(
                    new Person("Jacob", "Smith", "jacob.smith@example.com"),
                    new Person("Isabella", "Johnson", "isabella.johnson@example.com"),
                    new Person("Ethan", "Williams", "ethan.williams@example.com"),
                    new Person("Emma", "Jones", "emma.jones@example.com"),
                    new Person("Michael", "Brown", "michael.brown@example.com")
            );

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        Style startingStyle = Style.LIGHT;
        TransitTheme transitTheme = new TransitTheme(startingStyle);

        System.setProperty("prism.lcdtext", "false");

        VBox vbox = new VBox();
        Scene scene = new Scene(vbox);
        stage.setTitle("Table View Sample");
        stage.setWidth(650);
        stage.setHeight(610);


//        final Label header = new Label("Table View");
//        header.setPadding(new Insets(0, 0, 0, 5));
//        header.getStyleClass().add("header");

        table.setEditable(true);
        table.getSelectionModel().setCellSelectionEnabled(true);
        table.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        TableColumn firstNameCol = new TableColumn("First Name");
        firstNameCol.setMinWidth(150);
        firstNameCol.setCellValueFactory(new PropertyValueFactory<Person, String>("firstName"));

        TableColumn lastNameCol = new TableColumn("Last Name");
        lastNameCol.setMinWidth(150);
        lastNameCol.setCellValueFactory(new PropertyValueFactory<Person, String>("lastName"));

        TableColumn emailCol = new TableColumn("Email");
        emailCol.setMinWidth(250);
        emailCol.setCellValueFactory(new PropertyValueFactory<Person, String>("email"));

        table.setItems(data);
        table.getColumns().addAll(firstNameCol, lastNameCol, emailCol);

        table.getSelectionModel().setCellSelectionEnabled(false);
        CheckBox cellSelection = new CheckBox("Cell Selection");
        cellSelection.setOnAction(event -> table.getSelectionModel().setCellSelectionEnabled(cellSelection.isSelected()));
        cellSelection.setSelected(table.getSelectionModel().isCellSelectionEnabled());

        CheckBox tableButtonCheckBox = new CheckBox("Table Menu Button");
        tableButtonCheckBox.setOnAction(event -> {
            table.setTableMenuButtonVisible(tableButtonCheckBox.isSelected());
        });
        tableButtonCheckBox.setSelected(table.isTableMenuButtonVisible());

        CheckBox alternatingRowColors = new CheckBox("Alternating Row Colors");
        alternatingRowColors.setOnAction(event -> {
            boolean isSelected = alternatingRowColors.isSelected();
            if (isSelected) {
                addIfNotPresent(table.getStyleClass(), ALTERNATING_ROW_COLORS);
            } else {
                table.getStyleClass().remove(ALTERNATING_ROW_COLORS);
            }
        });

        CheckBox columnGridLines = new CheckBox("Grid Lines");
        columnGridLines.setOnAction(event -> {
            boolean isSelected = columnGridLines.isSelected();
            if (isSelected) {
                addIfNotPresent(table.getStyleClass(), TABLE_GRID_LINES);
            } else {
                table.getStyleClass().remove(TABLE_GRID_LINES);
            }
        });

        vbox.setSpacing(35);
        vbox.setPadding(new Insets(10, 10, 20, 10));

        addIfNotPresent(vbox.getStyleClass(), BACKGROUND);

        BorderPane controlsBorderPane = new BorderPane();
        VBox controlsVBox = new VBox();
        controlsVBox.getChildren().addAll(cellSelection, tableButtonCheckBox, alternatingRowColors, columnGridLines);
        controlsVBox.setSpacing(10);

        ComboBox<Style> jmetroStyleComboBox = new ComboBox<>();
        jmetroStyleComboBox.getItems().addAll(Style.DARK, Style.LIGHT);
        jmetroStyleComboBox.setValue(startingStyle);
        jmetroStyleComboBox.valueProperty().addListener(observable -> transitTheme.setStyle(jmetroStyleComboBox.getValue()));

        controlsBorderPane.setLeft(controlsVBox);
        controlsBorderPane.setRight(jmetroStyleComboBox);

        vbox.getChildren().addAll(/*header,*/ table, controlsBorderPane);

        transitTheme.setScene(scene);

        stage.getIcons().addAll(Logo.getLogoIcons());
        stage.setScene(scene);
        stage.show();
    }

    public static class Person {

        private final SimpleStringProperty firstName;
        private final SimpleStringProperty lastName;
        private final SimpleStringProperty email;

        public Person(String fName, String lName, String email) {
            this.firstName = new SimpleStringProperty(fName);
            this.lastName = new SimpleStringProperty(lName);
            this.email = new SimpleStringProperty(email);
        }

        public String getFirstName() {
            return firstName.get();
        }

        public void setFirstName(String fName) {
            firstName.set(fName);
        }

        public String getLastName() {
            return lastName.get();
        }

        public void setLastName(String fName) {
            lastName.set(fName);
        }

        public String getEmail() {
            return email.get();
        }

        public void setEmail(String fName) {
            email.set(fName);
        }
    }
}
