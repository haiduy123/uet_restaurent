package Bill;

import Food.food;
import SQL.SQL;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    private TableView<bill> billTableView;

    @FXML
    private TableColumn<bill, LocalDateTime> timePayment_Column;

    @FXML
    private TableColumn<bill, Integer> bID_Column;

    @FXML
    private AnchorPane billContainer;

    @FXML
    private TableColumn<bill, Integer> totalMoney_Column;

    @FXML
    private TableColumn<bill, LocalDateTime> timeIn_Column;

    @FXML
    private TableColumn<bill, Integer> emploteeId_Column;

    public void addBIll() throws SQLException {
        SQL sql = new SQL();
//        sql.getAllBill(sql.allBills());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            addBIll();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        bID_Column.setCellValueFactory(new PropertyValueFactory<bill, Integer>("billId"));
        totalMoney_Column.setCellValueFactory(new PropertyValueFactory<bill, Integer>("totalMoney"));
        timeIn_Column.setCellValueFactory(new PropertyValueFactory<bill, LocalDateTime>("timeIn"));
        timePayment_Column.setCellValueFactory(new PropertyValueFactory<bill, LocalDateTime>("paymentTime"));
        emploteeId_Column.setCellValueFactory(new PropertyValueFactory<bill, Integer>("employeeId"));


        for (bill bill : billManagement.allBill) {
            billTableView.getItems().add(bill);
        }

//        for(int i = 0; i < foodButtonList.toArray().length; i++) {
//            foodButtonList.get(i).setOnAction(this::buttonAction);
//        }
    }
}
