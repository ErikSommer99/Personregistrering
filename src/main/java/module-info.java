module org.eriksommer {
    requires javafx.controls;
    requires javafx.fxml;

    opens org.eriksommer to javafx.fxml;
    opens org.eriksommer.gui to javafx.fxml;
    opens org.eriksommer.personregister to javafx.base;
    exports org.eriksommer;
}