package com.pixelduke.samples.transit.panessample;

import com.pixelduke.transit.TransitTheme;
import com.pixelduke.transit.Style;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;

import java.net.URL;
import java.util.ResourceBundle;

public class PanesSampleController implements Initializable {
    @FXML
    private ComboBox<String> styleComboBox;

    private TransitTheme transitTheme;


    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        styleComboBox.getItems().addAll("Light", "Dark");
    }

    public void init(TransitTheme jmetro) {
        this.transitTheme = jmetro;

        styleComboBox.setValue(jmetro.getStyle().equals(Style.LIGHT) ? "Light" : "Dark");
        styleComboBox.valueProperty().addListener(observable -> {
            if (styleComboBox.getValue().equals("Light")) {
                jmetro.setStyle(Style.LIGHT);
            } else {
                jmetro.setStyle(Style.DARK);
            }
        });
    }
}

