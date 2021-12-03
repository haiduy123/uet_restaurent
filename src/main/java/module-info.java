module com.example.demo1 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.demo1 to javafx.fxml;
    exports com.example.demo1;

    opens login to javafx.fxml;
    exports login;

    opens Order to javafx.fxml;
    exports Order;

    opens thucdon to javafx.fxml;
    exports thucdon;

    opens start to javafx.fxml;
    exports start;
}