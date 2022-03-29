module com.example.proyecto {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
//    requires JFlex;
    requires java.cup;
    requires java.sql;

    opens assets;
    opens com.example.proyecto to javafx.fxml;
    exports com.example.proyecto;
    exports com.example.proyecto.Controller;
    opens com.example.proyecto.Controller to javafx.fxml;
}