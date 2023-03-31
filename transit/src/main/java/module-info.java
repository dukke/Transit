module com.pixelduke.transit {
    requires transitive javafx.controls;
    requires static org.controlsfx.controls;

    exports com.pixelduke.transit;
    exports impl.com.pixelduke.transit to javafx.controls;

    opens com.pixelduke.transit;
}