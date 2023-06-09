package com.pixelduke.samples.transit;

import com.pixelduke.transit.TransitTheme;
import com.pixelduke.transit.Style;
import javafx.application.Application;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.util.Arrays;
import java.util.List;

import static com.pixelduke.transit.TransitStyleClass.TABLE_GRID_LINES;
import static com.pixelduke.transit.TransitStyleClass.addIfNotPresent;


public class TreeTableViewWithSubheaders extends Application {

    private static final Style STYLE = Style.DARK;

    List<Employee> employees = Arrays.asList(
            new Employee("Ethan Williams", "ethan.williams@example.com", "25", "Manager", "San Francisco", "3000"),
            new Employee("Emma Jones", "emma.jones@example.com", "30", "Sales Rep", "San Francisco", "3000"),
            new Employee("Michael Brown", "michael.brown@example.com", "50", "Consultant", "New York", "2000"),
            new Employee("Anna Black", "anna.black@example.com", "45", "Consultant", "Miami", "1000"),
            new Employee("Rodger York", "roger.york@example.com", "56", "Manager", "Washington", "5000"),
            new Employee("Susan Collins", "susan.collins@example.com", "32", "Director", "Florida", "6000"));

    List<Employee> itEmployees = Arrays.asList(
            new Employee("Jonathan James", "ethan.williams@example.com", "25", "Manager", "California", "7000"),
            new Employee("Pedro Duque Vieira", "emma.jones@example.com", "30", "Sales Rep", "California", "7000"),
            new Employee("Pavel Erokhin", "michael.brown@example.com", "50", "Consultant", "Florida", "10000"),
            new Employee("James Brown", "anna.black@example.com", "45", "Consultant", "Boston", "20000"),
            new Employee("Linda Gates", "roger.york@example.com", "56", "Manager", "Washington", "5000"));

    final TreeItem<Employee> root =
            new TreeItem<>(new Employee("Employees", "", "", "", "", ""));
    final TreeItem<Employee> salesRoot =
            new TreeItem<>(new Employee("Sales Department", "", "", "", "", ""));
    final TreeItem<Employee> itRoot =
            new TreeItem<>(new Employee("IT Department", "", "", "", "", ""));

    public static void main(String[] args) {
        Application.launch(TreeTableViewWithSubheaders.class, args);
    }

    @Override
    public void start(Stage stage) {
        Style startingStyle = Style.LIGHT;
        TransitTheme transitTheme = new TransitTheme(startingStyle);

        System.setProperty("prism.lcdtext", "false");

        root.getChildren().add(salesRoot);
        root.getChildren().add(itRoot);

        employees.forEach((employee) -> salesRoot.getChildren().add(new TreeItem<>(employee)));
        itEmployees.forEach((employee) -> itRoot.getChildren().add(new TreeItem<>(employee)));

        stage.setTitle("Tree Table View With Subheaders Sample");
        final Scene scene = new Scene(new BorderPane());
        BorderPane sceneRoot = (BorderPane) scene.getRoot();

        TreeTableColumn<Employee, String> empColumn = new TreeTableColumn<>("Employee");
        empColumn.setPrefWidth(170);
        empColumn.setCellValueFactory(
                (TreeTableColumn.CellDataFeatures<Employee, String> param) ->
                        new ReadOnlyStringWrapper(param.getValue().getValue().getName())
        );

        TreeTableColumn<Employee, String> emailColumn = new TreeTableColumn<>("Email");
        emailColumn.setPrefWidth(220);
        emailColumn.setCellValueFactory(
                (TreeTableColumn.CellDataFeatures<Employee, String> param) ->
                        new ReadOnlyStringWrapper(param.getValue().getValue().getEmail())
        );

        TreeTableColumn<Employee, String> ageColumn = new TreeTableColumn<>("Age");
        ageColumn.setPrefWidth(60);
        ageColumn.setCellValueFactory(
                (TreeTableColumn.CellDataFeatures<Employee, String> param) ->
                        new ReadOnlyStringWrapper(param.getValue().getValue().getAge())
        );

        TreeTableColumn<Employee, String> positionColumn = new TreeTableColumn<>("Position");
        positionColumn.setPrefWidth(130);
        positionColumn.setCellValueFactory(
                (TreeTableColumn.CellDataFeatures<Employee, String> param) ->
                        new ReadOnlyStringWrapper(param.getValue().getValue().getPosition())
        );


        TreeTableColumn<Employee, String> cityColumn = new TreeTableColumn<>("City");
        cityColumn.setPrefWidth(130);
        cityColumn.setCellValueFactory(
                (TreeTableColumn.CellDataFeatures<Employee, String> param) ->
                        new ReadOnlyStringWrapper(param.getValue().getValue().getCity())
        );

        TreeTableColumn<Employee, String> postalCodeColumn = new TreeTableColumn<>("Postal Code");
        postalCodeColumn.setPrefWidth(130);
        postalCodeColumn.setCellValueFactory(
                (TreeTableColumn.CellDataFeatures<Employee, String> param) ->
                        new ReadOnlyStringWrapper(param.getValue().getValue().getPostalCode())
        );

        TreeTableColumn<Employee, String> addressColumn = new TreeTableColumn<>("Address");
        addressColumn.getColumns().addAll(cityColumn, postalCodeColumn);

        TreeTableView<Employee> treeTableView = new TreeTableView<>(root);
        treeTableView.getColumns().setAll(empColumn, emailColumn, ageColumn, positionColumn, addressColumn);

        salesRoot.setExpanded(true);
        treeTableView.setShowRoot(false);

        sceneRoot.setCenter(treeTableView);

        // Customization controls
        HBox controlsHBox = new HBox();
        controlsHBox.setSpacing(10);

        CheckBox cellSelectionCheckBox = new CheckBox("Cell Sbelection");
        cellSelectionCheckBox.setOnAction(event -> {
            treeTableView.getSelectionModel().setCellSelectionEnabled(cellSelectionCheckBox.isSelected());
        });
        cellSelectionCheckBox.setSelected(treeTableView.getSelectionModel().isCellSelectionEnabled());

        CheckBox tableButtonCheckBox = new CheckBox("Table Menu Button");
        tableButtonCheckBox.setOnAction(event -> {
            treeTableView.setTableMenuButtonVisible(tableButtonCheckBox.isSelected());
        });
        tableButtonCheckBox.setSelected(treeTableView.isTableMenuButtonVisible());

        CheckBox alternatingRowColors = new CheckBox("Alternating Row Colors");
        alternatingRowColors.setOnAction(event -> {
            String alternatingRowColorsStyleClass = "alternating-row-colors";
            boolean isSelected = alternatingRowColors.isSelected();
            if (isSelected) {
                if (!treeTableView.getStyleClass().contains(alternatingRowColorsStyleClass)) {
                    treeTableView.getStyleClass().add(alternatingRowColorsStyleClass);
                }
            } else {
                treeTableView.getStyleClass().remove(alternatingRowColorsStyleClass);
            }
        });

        CheckBox columnGridLines = new CheckBox("Grid Lines");
        columnGridLines.setOnAction(event -> {
            boolean isSelected = columnGridLines.isSelected();
            if (isSelected) {
                addIfNotPresent(treeTableView.getStyleClass(), TABLE_GRID_LINES);
            } else {
                treeTableView.getStyleClass().remove(TABLE_GRID_LINES);
            }
        });

        ComboBox<Style> jmetroStyleComboBox = new ComboBox<>();
        jmetroStyleComboBox.getItems().addAll(Style.DARK, Style.LIGHT);
        jmetroStyleComboBox.setValue(startingStyle);
        jmetroStyleComboBox.valueProperty().addListener(observable -> transitTheme.setStyle(jmetroStyleComboBox.getValue()));

        controlsHBox.getChildren().addAll(jmetroStyleComboBox, cellSelectionCheckBox, tableButtonCheckBox, alternatingRowColors, columnGridLines);
        sceneRoot.setBottom(controlsHBox);

        controlsHBox.getStyleClass().add("background");

        stage.setScene(scene);

        transitTheme.setScene(scene);

        stage.show();
    }

    public class Employee {

        private SimpleStringProperty name;
        private SimpleStringProperty email;
        private SimpleStringProperty age;
        private SimpleStringProperty position;
        private SimpleStringProperty city;
        private StringProperty postalCode;

        private Employee(String name, String email, String age, String position, String city, String postalCode) {
            this.name = new SimpleStringProperty(name);
            this.email = new SimpleStringProperty(email);
            this.age = new SimpleStringProperty(age);
            this.position = new SimpleStringProperty(position);
            this.city = new SimpleStringProperty(city);
            this.postalCode = new SimpleStringProperty(postalCode);
        }

        public String getName() { return name.get(); }
        public void setName(String fName) { name.set(fName); }
        public SimpleStringProperty nameProperty() {
            if (name == null) {
                name = new SimpleStringProperty(this, "name");
            }
            return name;
        }

        public String getEmail() { return email.get(); }
        public void setEmail(String fName) { email.set(fName); }
        public SimpleStringProperty emailProperty() {
            if (email == null) {
                email = new SimpleStringProperty(this, "email");
            }
            return email;
        }

        public String getAge() { return age.get(); }
        public void setAge(String age) { this.age.set(age); }
        public SimpleStringProperty ageProperty() {
            return age;
        }

        public String getPosition() { return position.get(); }
        public void setPosition(String position) { this.position.set(position); }
        public SimpleStringProperty positionProperty() {
            return position;
        }

        public String getCity() { return city.get(); }
        public void setCity(String city) { this.city.set(city); }
        public SimpleStringProperty cityProperty() {
            return city;
        }

        public String getPostalCode() { return postalCode.get(); }
        public void setPostalCode(String postalCode) { this.postalCode.set(postalCode); }
        public StringProperty postalCodeProperty() { return postalCode; }
    }
}
