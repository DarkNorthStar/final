module ca.tabletop {
    requires javafx.controls;
    requires javafx.fxml;
    requires transitive javafx.graphics;
    requires java.desktop; // For javax imports

    opens ca.tabletop to javafx.fxml;
    exports ca.tabletop;
}
