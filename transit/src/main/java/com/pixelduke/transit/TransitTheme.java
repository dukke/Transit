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

import com.pixelduke.window.ThemeWindowManager;
import com.pixelduke.window.ThemeWindowManagerFactory;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.stage.Window;

/**
 * This class is used to apply the Transit theme to a {@link Scene}.
 * It also has properties that tweak the theme.
 */
public class TransitTheme {
    private ThemeWindowManager themeWindowManager = ThemeWindowManagerFactory.create();

    /**
     * The {@link Scene} that Transit Theme will be applied to...
     */
    private final ObjectProperty<Scene> scene = new SimpleObjectProperty<>() {
        @Override
        protected void invalidated() {
            if (get() == null) {
                return;
            }

            applyTheme();
        }
    };

    /**
     * The {@link Style} that should be applied
     */
    private final ObjectProperty<Style> style = new SimpleObjectProperty<>(Style.LIGHT) {
        @Override
        protected void invalidated() {
            applyTheme();
        }
    };

    private final ObservableList<String> sceneStylesheets = FXCollections.observableArrayList();

    /*=*************************************************************************
     *                                                                         *
     *                        Constructors                                     *
     *                                                                         *
     *************************************************************************=*/

    public TransitTheme() {
        sceneStylesheets.addListener(this::sceneStylesheetsChanged);
    }

    public TransitTheme(Style style) {
        this();
        this.style.set(style);
    }

    public TransitTheme(Scene scene, Style style) {
        this();
        this.style.set(style);
        this.scene.set(scene);
    }

    /*=*************************************************************************
     *                                                                         *
     *                           Public API                                    *
     *                                                                         *
     *************************************************************************=*/

    /**
     * Reapplies the theme in the specified scene.
     */
    public void applyTheme() {
        if (scene.get() == null) {
            return;
        }

        String stylesheetURL = style.get().equals(Style.LIGHT) ? Style.LIGHT.getStyleStylesheetURL()
                                                               : Style.DARK.getStyleStylesheetURL();
        scene.get().setUserAgentStylesheet(stylesheetURL);

        Window sceneWindow = scene.get().getWindow();
        if (sceneWindow != null) {
            themeWindowManager.setDarkModeForWindowFrame(sceneWindow, style.get() == Style.DARK);
        }
    }

    /*=*************************************************************************
     *                                                                         *
     *                          Private API                                    *
     *                                                                         *
     *************************************************************************=*/

    private void sceneStylesheetsChanged(ListChangeListener.Change<? extends String> changed) {
        if (scene.get() == null) {
            throw new NullPointerException("Scene and Parent can't be null, they must be set by the programmer");
        }

        // Currently this only supports adding and removing of stylesheets of the overriding stylesheets list
        while(changed.next()) {
            if (changed.wasRemoved()) {
                for (String stylesheetURL : changed.getRemoved()) {
                    scene.get().getStylesheets().remove(stylesheetURL);
                }
            }
            if (changed.wasAdded()) {
                // For now, we just add at the bottom of the list
                scene.get().getStylesheets().addAll(changed.getAddedSubList());
            }
        }
    }

    /*=*************************************************************************
     *                                                                         *
     *                            Properties                                   *
     *                                                                         *
     *************************************************************************=*/

    // --- scene stylesheets

    /**
     * These stylesheets will be added to this instance's {@link #scene}.
     * These stylesheets have higher priority than the stylesheets that define this theme (Transit stylehsheets are
     * user agent stylesheets) and thus will override the definitions set by Transit.
     *
     * @return the list of stylesheets that will be added to the {@link #scene}.
     */
    public ObservableList<String> getSceneStylesheets() { return sceneStylesheets; }

    // --- style
    public Style getStyle() { return style.get(); }
    public ObjectProperty<Style> styleProperty() { return style; }
    public void setStyle(Style style) { this.style.set(style); }

    // --- scene
    public Scene getScene() { return scene.get(); }
    public ObjectProperty<Scene> sceneProperty() { return scene; }
    public void setScene(Scene scene) { this.scene.set(scene); }
}

