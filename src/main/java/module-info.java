module com.example.mailmanagementsystem {
    requires javafx.controls;
    requires javafx.fxml;
            
                    requires org.kordamp.ikonli.javafx;
                
    opens com.example.mailmanagementsystem to javafx.fxml;
    opens com.example.mailmanagementsystem.controllers to javafx.fxml;
    exports com.example.mailmanagementsystem;
    exports com.example.mailmanagementsystem.controllers;
}