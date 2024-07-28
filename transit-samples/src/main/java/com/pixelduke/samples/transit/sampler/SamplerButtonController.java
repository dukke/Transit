package com.pixelduke.samples.transit.sampler;

import com.pixelduke.transit.Style;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;

public class SamplerButtonController {

    private final ImageView lightThemeImage = new ImageView(SamplerButtonController.class.getResource("icons8-light-16.png").toExternalForm());
    private final ImageView darkThemeImage = new ImageView(SamplerButtonController.class.getResource("icons8-moon-symbol-16.png").toExternalForm());

    @FXML
    private Button changeThemeButton;

    @FXML
    private void onStyleChange() {
        Style currentStyle = SamplerApp.getStyle();
        if (currentStyle == Style.DARK) {
            SamplerApp.setStyle(Style.LIGHT);
            changeThemeButton.setGraphic(darkThemeImage);
        } else {
            SamplerApp.setStyle(Style.DARK);
            changeThemeButton.setGraphic(lightThemeImage);
        }
    }
}
