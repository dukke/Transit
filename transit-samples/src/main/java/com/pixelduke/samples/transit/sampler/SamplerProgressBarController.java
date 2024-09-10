package com.pixelduke.samples.transit.sampler;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.InvalidationListener;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.util.Duration;

import java.text.DecimalFormat;

public class SamplerProgressBarController extends SamplerBaseController {
    @FXML
    private Label indeterminateSectionText;
    @FXML
    private ProgressBar determinateProgressBar;
    @FXML
    private Label progressLabel;

    private double progress = 0.1;

    public void initialize()
    {
        progress = 0;

        indeterminateSectionText.setText("The ProgressBar in this theme uses FXSkins PointsProgressBarSkin. FXSkins new skin adds an " +
                "animation of points moving when the ProgressBar is in its indeterminate state.");

        EventHandler<ActionEvent> onTimeChanged = event -> {
            progress += 0.01;
            progress = progress % 1;
            determinateProgressBar.setProgress(progress);
        };
        InvalidationListener onProgressChanged = observable -> {
            DecimalFormat df = new DecimalFormat("00");
            String formattedNumber = df.format(determinateProgressBar.getProgress() * 100);
            progressLabel.setText(formattedNumber);
        };

        determinateProgressBar.progressProperty().addListener(onProgressChanged);

        Timeline timeline = new Timeline();
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.getKeyFrames().add(new KeyFrame(Duration.millis(50), onTimeChanged));
        timeline.play();
    }
}
