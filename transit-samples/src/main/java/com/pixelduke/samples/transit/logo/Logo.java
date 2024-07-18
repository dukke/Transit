package com.pixelduke.samples.transit.logo;

import javafx.scene.image.Image;

import java.util.List;

public class Logo {
    public static final Image LOGO_16 = new Image(Logo.class.getResource("logo_16px.png").toExternalForm());
    public static final Image LOGO_32 = new Image(Logo.class.getResource("logo_32px.png").toExternalForm());

    public static List<Image> getLogoIcons() { return List.of(LOGO_16, LOGO_32); }
}
