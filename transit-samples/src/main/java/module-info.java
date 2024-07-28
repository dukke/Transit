module com.pixelduke.samples.transit {
    /* requires */
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;
    requires javafx.swing;
    requires javafx.graphics;

    requires java.logging;

    requires com.pixelduke.fxcomponents;
    requires com.pixelduke.fxthemes;

    requires org.controlsfx.controls;

    requires com.pixelduke.transit;

    /* exports */
    exports com.pixelduke.samples.transit to javafx.graphics;
    exports com.pixelduke.samples.transit.themetester to javafx.graphics;

    opens com.pixelduke.samples.transit.controlssample to javafx.fxml;
    opens com.pixelduke.samples.transit.panessample to javafx.fxml;
    opens com.pixelduke.samples.transit to javafx.base;
    opens com.pixelduke.samples.transit.themetester;

    exports com.pixelduke.samples.transit.sampler to javafx.graphics, javafx.fxml;
    opens com.pixelduke.samples.transit.sampler to javafx.base, javafx.fxml;

    /* Scenic View */
//    requires org.scenicview.scenicview;
}