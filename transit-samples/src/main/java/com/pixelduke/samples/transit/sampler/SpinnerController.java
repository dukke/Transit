package com.pixelduke.samples.transit.sampler;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;

public class SpinnerController extends SamplerBaseController {
    @FXML
    private Parent rootContainer;

    public void initialize() {
        initSpinners();
    }

    private void initSpinners() {
        initSpinnerInContainer(rootContainer);
    }

    private void initSpinnerInContainer(Parent root) {
        if (root instanceof Spinner spinner) {
            spinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 10));
        } else {
            for (Node child : root.getChildrenUnmodifiable()) {
                if (child instanceof Parent parent) {
                    initSpinnerInContainer(parent);
                }
            }
        }
    }
}
