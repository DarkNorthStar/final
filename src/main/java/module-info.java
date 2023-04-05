module ca.tabletop {
    requires javafx.controls;
    requires javafx.fxml;
    requires transitive javafx.graphics;
    requires java.desktop; // For javax imports
    requires java.sql; //  For JDBC 

    opens ca.tabletop to javafx.fxml;
    exports ca.tabletop;
}
