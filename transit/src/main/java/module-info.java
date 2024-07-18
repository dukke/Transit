module com.pixelduke.transit {
    requires transitive javafx.controls;
    requires static org.controlsfx.controls;

    requires com.pixelduke.fxskins;
    requires com.pixelduke.fxthemes;

    exports com.pixelduke.transit;
    opens com.pixelduke.transit;
}