package com.pixelduke.transit;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.ListChangeListener;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import static com.pixelduke.transit.FlatDialog.initDialog;
import static com.pixelduke.transit.FlatDialog.initDialogIcon;

/**
 * An Alert with a flat style.
 * For example, by default it will appear without a window icon (can be configured via iconless property) and without
 * a window title.
 */
public class FlatAlert extends Alert {
    private final BooleanProperty iconless = new SimpleBooleanProperty(true);

    private boolean isResettingIcon;

    public FlatAlert(AlertType alertType) {
        super(alertType);
        init();
    }

    public FlatAlert(AlertType alertType, String contentText, ButtonType... buttons) {
        super(alertType, contentText, buttons);
        init();
    }

    private void init() {
        initDialog(this, isIconless());
        setupDialogIconsListener();
    }

    private void setupDialogIconsListener() {
        Stage stage = (Stage) getDialogPane().getScene().getWindow();
        stage.getIcons().addListener(this::dialogIconsChanged);
    }

    private void dialogIconsChanged(ListChangeListener.Change<? extends Image> c) {
        // When initOwner is called on the Dialog the icon is changed and this event is fired
        if (isResettingIcon) {
            return;
        }
        // When there is an initOwner call the Dialog icon gets reset, we need to reapply the JMetro icon
        isResettingIcon = true;
        initDialogIcon(this, isIconless());
        isResettingIcon = false;
    }

    // --- iconless
    public boolean isIconless() { return iconless.get(); }
    public BooleanProperty iconlessProperty() { return iconless; }
    public void setIconless(boolean iconless) { this.iconless.set(iconless); }
}
