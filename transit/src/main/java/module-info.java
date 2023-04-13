module com.pixelduke.transit {
    requires transitive javafx.controls;
    requires static org.controlsfx.controls;

    requires com.pixelduke.fxskins;


    exports com.pixelduke.transit;

    opens com.pixelduke.transit;
}