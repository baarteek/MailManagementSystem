module org.example.client {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.client to javafx.fxml;
    opens org.example.client.controllers to javafx.fxml;
    exports org.example.client;
    exports org.example.client.controllers to javafx.fxml;
    opens org.example.client.utils to javafx.base;
}