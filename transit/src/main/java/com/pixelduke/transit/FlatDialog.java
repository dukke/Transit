/*
 *  Copyright (c) 2023 Pixel Duke (Pedro Duque Vieira - www.pixelduke.com)
 *  All rights reserved.
 *
 *  Redistribution and use in source and binary forms, with or without
 *  modification, are permitted provided that the following conditions are met:
 *    * Redistributions of source code must retain the above copyright
 *  notice, this list of conditions and the following disclaimer.
 *    * Redistributions in binary form must reproduce the above copyright
 *  notice, this list of conditions and the following disclaimer in the
 *  documentation and/or other materials provided with the distribution.
 *    * Neither the name of Pixel Duke, any associated website, nor the
 *  names of its contributors may be used to endorse or promote products
 *  derived from this software without specific prior written permission.
 *
 *  THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 *  ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 *  WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 *  DISCLAIMED. IN NO EVENT SHALL PIXEL DUKE BE LIABLE FOR ANY
 *  DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 *  (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 *  LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 *  ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 *  (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 *  SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package com.pixelduke.transit;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.ListChangeListener;
import javafx.scene.control.Dialog;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * A dialog with a flat style.
 * For example, by default it will appear without a window icon (can be configured via iconless property) and without
 * a window title.
 *
 * @param <R> The return type of the dialog, via the result property.
 */
public class FlatDialog<R> extends Dialog<R> {
    private final BooleanProperty iconless = new SimpleBooleanProperty(true);

    private boolean isResettingIcon;

    public static void initDialog(Dialog<?> dialog, boolean iconless) {
        dialog.setTitle("");
        initDialogIcon(dialog, iconless);
    }

    public static void initDialogIcon(Dialog<?> dialog, boolean isIconless) {
        if (!isIconless) {
            return;
        }
        Stage stage = (Stage) dialog.getDialogPane().getScene().getWindow();
        Image whiteIcon = new Image(TransitTheme.class.getResource("whiteIcon.png").toExternalForm());
        stage.getIcons().add(whiteIcon);
    }

    public FlatDialog() {
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
