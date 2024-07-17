/*
 *  Copyright (c) 2023 Pixel Duke (Pedro Duque Vieira - www.pixelduke.com)
 *  All rights reserved.
 *
 *  Redistribution and use in source and binary forms, with or without
 *  modification, are permitted provided that the following conditions are met:
 *    * Redistributions of source code must retain the above copyright
 *  notice, this list of conditions and the following disclaimer.
 *    * Redistributions in binary form must reproduce the above copyright
 *  notice, this list of conditions and the following disclaimer in the
 *  documentation and/or other materials provided with the distribution.
 *    * Neither the name of Pixel Duke, any associated website, nor the
 *  names of its contributors may be used to endorse or promote products
 *  derived from this software without specific prior written permission.
 *
 *  THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 *  ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 *  WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 *  DISCLAIMED. IN NO EVENT SHALL PIXEL DUKE BE LIABLE FOR ANY
 *  DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 *  (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 *  LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 *  ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 *  (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 *  SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package com.pixelduke.samples.transit;

import com.pixelduke.transit.TransitTheme;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import com.pixelduke.transit.TransitStyleClass;
import com.pixelduke.transit.Style;

public class ControlsSample extends Application {
    private static final String CHECK_BOX_RESOURCE = "Transit CheckBox.fxml";
    private static final String CONTEXT_MENU_RESOURCE = "Transit ContextMenu.fxml";
    private static final String RADIO_BUTTON_RESOURCE = "Transit RadioButton.fxml";
    private static final String SCROLL_BAR_RESOURCE = "Transit ScrollBar.fxml";
    private static final String SCROLL_PANE_RESOURCE = "Transit ScrollPane.fxml";

    private static final String BUTTON_RESOURCE = "Transit Button.fxml";
    private static final String TOGGLE_BUTTON_RESOURCE = "Transit ToggleButton.fxml";

    private static final String COMBOBOX_RESOURCE = "Transit ComboBox.fxml";
    private static final String TOOLTIP_RESOURCE = "Transit Tooltip.fxml";
    private static final String RATING_RESOURCE = "Transit Rating.fxml";
    private static final String TEXT_FIELD_RESOURCE = "Transit TextField.fxml";
    private static final String TEXT_AREA_RESOURCE = "Transit TextArea.fxml";
    private static final String PASSWORD_FIELD_RESOURCE = "Transit PasswordField.fxml";
    private static final String PROGRESS_BAR_RESOURCE = "Transit ProgressBar.fxml";
    private static final String PROGRESS_INDICATOR_RESOURCE = "Transit ProgressIndicator.fxml";
    private static final String SLIDER_RESOURCE = "Transit Slider.fxml";
    private static final String TOGGLE_SWITCH_RESOURCE = "Transit ToggleSwitch.fxml";
    private static final String DATE_PICKER_RESOURCE = "Transit DatePicker.fxml";
    private static final String SPINNER_RESOURCE = "Transit Spinner.fxml";
    private static final String CHOICE_BOX_RESOURCE = "Transit ChoiceBox.fxml";
    private static final String LIST_VIEW_RESOURCE = "Transit ListView.fxml";
    private static final String TITLED_PANE_RESOURCE = "Transit TitledPane.fxml";
    private static final String ACCORDION_RESOURCE = "Transit Accordion.fxml";
    private static final String MENU_BUTTON_RESOURCE = "Transit MenuButton.fxml";
    private static final String HYPERLINK_RESOURCE = "Transit Hyperlink.fxml";
    private static final String SPLIT_MEU_BUTTON_RESOURCE = "Transit SplitMenuButton.fxml";

    private static final String COLOR_PICKER_RESOURCE = "Transit ColorPicker.fxml";

    static final private String RESOURCE = COLOR_PICKER_RESOURCE;
    static final private Style STARTING_STYLE = Style.LIGHT;

    @Override
    public void start(Stage primaryStage) throws Exception{
        Style startingStyle = STARTING_STYLE;
        TransitTheme transitTheme = new TransitTheme(startingStyle);

        System.setProperty("prism.lcdtext", "false");

        BorderPane rootContainer = new BorderPane();

        Parent root = FXMLLoader.load(getClass().getResource(RESOURCE));
        primaryStage.setTitle("Transit Theme");

        rootContainer.setCenter(root);

        CheckBox alternatingRowColors = new CheckBox("Alternating Row Colors");
        alternatingRowColors.setOnAction(event -> {
            boolean isSelected = alternatingRowColors.isSelected();
            ListView<?> listView = (ListView<?>) root.lookup(".list-view");
            if (listView == null) {
                return;
            }

            if (isSelected) {
                TransitStyleClass.addIfNotPresent(listView.getStyleClass(), TransitStyleClass.ALTERNATING_ROW_COLORS);
            } else {
                listView.getStyleClass().remove(TransitStyleClass.ALTERNATING_ROW_COLORS);
            }
        });

        ComboBox<Style> jmetroStyleComboBox = new ComboBox<>();
        jmetroStyleComboBox.getItems().addAll(Style.DARK, Style.LIGHT);
        jmetroStyleComboBox.setValue(startingStyle);
        jmetroStyleComboBox.valueProperty().addListener(observable -> transitTheme.setStyle(jmetroStyleComboBox.getValue()));

        ToolBar controlsToolBar = new ToolBar();
        controlsToolBar.getItems().addAll(alternatingRowColors);

        ToolBar topToolbar = new ToolBar();
        topToolbar.getItems().add(jmetroStyleComboBox);

        rootContainer.setTop(topToolbar);
        rootContainer.setBottom(controlsToolBar);

        Scene scene = new Scene(rootContainer);

        transitTheme.setScene(scene);

//        ScenicView.show(scene);

        primaryStage.setScene(scene);
        primaryStage.show();

        Platform.runLater(() -> {
            if (RESOURCE.equals(LIST_VIEW_RESOURCE)) {
                ListView<?> listView = (ListView<?>) scene.lookup(".list-view");
                listView.getSelectionModel().select(2);
            }
        });
    }


    public static void main(String[] args) {
        launch(args);
    }

}
