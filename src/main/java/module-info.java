module com.example.demo1 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.demo1 to javafx.fxml;
    exports com.example.demo1;

    opens login to javafx.fxml;
    exports login;

    opens Order to javafx.fxml;
    exports Order;

    opens start to javafx.fxml;
    exports start;

    opens Table to javafx.fxml;
    exports Table;

    opens Food to javafx.base;
}