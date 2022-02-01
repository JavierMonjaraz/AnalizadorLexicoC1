module com.example.proyectoc1 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;

    opens com.example.proyectoc1 to javafx.fxml;
    exports com.example.proyectoc1;
    exports com.example.proyectoc1.Controller;
    opens com.example.proyectoc1.Controller to javafx.fxml;
}