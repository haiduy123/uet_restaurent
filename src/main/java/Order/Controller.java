package Order;

import Food.*;
import SQL.SQL;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    private TableColumn<food, Number> fID_Column;

    @FXML
    private TableColumn<food, String> fName_Column;

//    @FXML
//    private TableColumn<?, ?> fImage;
//
//    @FXML
//    private TableColumn<?, ?> fQuantity;

    @FXML
    private TableColumn<food, Number> fPrice_Column;

    @FXML
    private TableView<food> tableView;

    private ObservableList<food> tableView_list;

    public static List<food> food_list = new ArrayList<>();
    public static SQL SQL;

    static {
        try {
            SQL = new SQL();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public foodManagement foodManagement = new foodManagement();

    public Controller() throws SQLException {
    }

    // thêm food vào food_list
    public static void addFood() throws SQLException {
        SQL.getAllFoodsSQL(SQL.allFoods());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        try {
            addFood();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        fID_Column.setCellValueFactory(new PropertyValueFactory<food, Number>("foodId"));
        fName_Column.setCellValueFactory(new PropertyValueFactory<food, String>("name"));
        fPrice_Column.setCellValueFactory(new PropertyValueFactory<food, Number>("price"));

        for (food f : food_list) {
            tableView.getItems().add(f);
        }
    }
}