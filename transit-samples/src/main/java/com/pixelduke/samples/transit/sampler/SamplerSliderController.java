package com.pixelduke.samples.transit.sampler;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;

public class SamplerSliderController extends SamplerBaseController {
    @FXML
    private Label normalHorizontalSliderLabel;
    @FXML
    private Slider normalHorizontalSlider;

    public void initialize()
    {
        normalHorizontalSliderLabel.setText("The Slider in this theme uses FXSkins FXSliderSkin. FXSkins new skin adds a" +
                " popup to the Slider that shows its value whenever it is changed. It also adds a fill color that goes" +
                " from the beginning of the track to the Slider's thumb. All of this is configurable through CSS and the Skin API." +
                "\nBy default the popup will be enabled and the Slider will have a fill color.");

        normalHorizontalSlider.setStyle("-show-value-on-interaction: false;");
    }
}
