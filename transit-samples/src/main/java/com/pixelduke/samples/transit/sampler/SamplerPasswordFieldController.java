package com.pixelduke.samples.transit.sampler;

import com.pixelduke.control.skin.FXPasswordFieldSkin;
import com.pixelduke.control.skin.FXTextFieldSkin;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;

public class SamplerPasswordFieldController extends SamplerBaseController {

    @FXML
    private Label normalSectionText;
    @FXML
    private PasswordField normalPasswordField;

    @FXML
    private CheckBox enableRightButtonCheckBox;

    public void initialize() {
        enableRightButtonCheckBox.setSelected(true);
        normalSectionText.setText("The PasswordField in this theme uses FXSkins FXPasswordFieldSkin. FXSkins new skin adds a " +
                "'show password' button to the right of the PasswordField. This button can be enabled or disabled by setting the " +
                "'-right-button-visible' CSS property on the PasswordField to 'true' or 'false'.");
    }

    public void onRightButtonEnabledChanged(ActionEvent actionEvent) {
        if (enableRightButtonCheckBox.isSelected()) {
            normalPasswordField.setStyle("-right-button-visible: true;");
        } else {
            normalPasswordField.setStyle("-right-button-visible: false;");
        }
    }
}