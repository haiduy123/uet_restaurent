package test;

import Food.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    private TableColumn<food, Number> fID_Column;

    @FXML
    private TableColumn<food, String> fName_Column;

    @FXML
    private TableColumn<food, Number> fPrice_Column;

    @FXML
    private TableView<food> tableView;

    private ObservableList<food> tableView_list;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        tableView_list = FXCollections.observableArrayList(
                new food (1, "haha", 3)
        );

        fID_Column.setCellValueFactory(new PropertyValueFactory<food,Number>("fID"));
        fName_Column.setCellValueFactory(new PropertyValueFactory<food,String>("fName"));
        fPrice_Column.setCellValueFactory(new PropertyValueFactory<food,Number>("fPrice"));

        tableView.setItems(tableView_list);
    }
}