package com.pixelduke.samples.transit.sampler;

import com.pixelduke.transit.Style;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;

public class SamplerBaseController {

    private final ImageView lightThemeImage = new ImageView(SamplerBaseController.class.getResource("icons8-light-16.png").toExternalForm());
    private final ImageView darkThemeImage = new ImageView(SamplerBaseController.class.getResource("icons8-moon-symbol-16.png").toExternalForm());

    @FXML
    private Button changeThemeButton;

    @FXML
    private void onStyleChange() {
        Style currentStyle = SamplerApp.INSTANCE.getStyle();;
        if (currentStyle == Style.DARK) {
            SamplerApp.INSTANCE.setStyle(Style.LIGHT);
            changeThemeButton.setGraphic(darkThemeImage);
        } else {
            SamplerApp.INSTANCE.setStyle(Style.DARK);
            changeThemeButton.setGraphic(lightThemeImage);
        }
    }
}
