package com.pixelduke.samples.transit.sampler;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class SamplerTextFieldController extends SamplerBaseController {

    @FXML
    private Label normalSectionText;
    @FXML
    private TextField normalTextField;

    @FXML
    private CheckBox enableRightButtonCheckBox;

    public void initialize() {
        enableRightButtonCheckBox.setSelected(true);
        normalSectionText.setText("The TextField in this theme uses FXSkins FXTextFieldSkin. FXSkins new skin adds a " +
                "clear button to the right of the Textfield. This button can be enabled or disabled by setting the " +
                "'-right-button-visible' CSS property on the TextField to 'true' or 'false'.");
    }

    public void onRightButtonEnabledChanged(ActionEvent actionEvent) {
        if (enableRightButtonCheckBox.isSelected()) {
            normalTextField.setStyle("-right-button-visible: true;");
        } else {
            normalTextField.setStyle("-right-button-visible: false;");
        }
    }
}