package com.pixelduke.samples.transit.sampler;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;

public class SamplerTooltipController extends SamplerBaseController {
    @FXML
    private Tooltip multilineTooltip;

    @FXML
    private Tooltip tooltipWithGraphic;

    public void initialize()
    {
        multilineTooltip.setText("This is a multiline\nTooltip.");

        tooltipWithGraphic.setText("This is a Tooltip with graphic.");
    }
}
