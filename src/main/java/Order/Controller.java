package Order;

import Bill.*;
import Food.*;
import SQL.SQL;
import Alert.*;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import Table.*;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import static Bill.billManagement.billList;
import static Table.checkTable.tableList;

//import static Table.checkTable.table_list;

public class Controller implements Initializable {

    @FXML
    private TableColumn<food, Number> fID_Column;

    @FXML
    private TableColumn<food, String> fName_Column;

    @FXML
    private TableColumn<food, Button> fButton_Column;

    @FXML
    private TableColumn<food, TextField> fQuantity_Column;

    @FXML
    private TableColumn<food, Number> fPrice_Column;

    @FXML
    private TableView<food> tableView;

    @FXML
    private ListView<String> listView;

    @FXML
    private Label billPrice;

    @FXML
    private Button quayLai_btn;

    @FXML
    private Button check_btn;

    private ObservableList<food> tableView_list;

    public static List<food> foodList = new ArrayList<>();
    public static SQL SQL;
    public static int code;
    public static List<Button> foodButtonList = new ArrayList<>(16);
    public static List<Integer> quantityList = new ArrayList<>(16);
    public TableView<food> secondTableView = new TableView<>();
    public List<Integer> sumPrice = new ArrayList<>();
    public List<Integer> listSum = new ArrayList<>();
    public static int codeBill;

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

    private void buttonAction(ActionEvent event) {
        int sum = 0, tmp = 0;

        for(food food : foodList) {
            quantityList.add(0);
        }
        for (int i = 0; i < foodButtonList.size(); i++) {
            if (event.getSource() == foodButtonList.get(i)) {
                int id = Integer.parseInt(tableView.getColumns().get(0).getCellObservableValue(i).getValue().toString());
                String name = tableView.getColumns().get(1).getCellObservableValue(i).getValue().toString();
                int price = Integer.parseInt(tableView.getColumns().get(2).getCellObservableValue(i).getValue().toString());
                String quantityString = fQuantity_Column.getCellObservableValue(i).getValue().getText();
                quantityList.set(i, Integer.valueOf(quantityString));
                int quantity = quantityList.get(i);

                foodList.get(i).getQuantity().setText(quantityString);
                String foodInfor = foodList.get(i).toString();
                listView.getItems().add(foodInfor);
                sum = price * quantity;
                System.out.println(name + " " + price + " " + quantity);
                sumPrice.add(sum);
                Order(Integer.parseInt(tableView.getColumns().get(0).getCellObservableValue(i).getValue().toString()), name, price, quantity, listSum);

                food food = new food(id, name, price);

                for(bill bill : billList) {
                    if(bill.getBillId() == codeBill) {
                        bill.addNewFood(food);
                        bill.setTotalMoney(sum);
                    }
                }
            }
        }

        for (Integer i : sumPrice) {
            tmp += i;
        }

        billPrice.setText(String.valueOf(tmp));
    }
    // thêm food vào food_list
    public void addFood() throws SQLException {
        for (food food : Food.foodManagement.allFood) {
            Button btn = new Button("ĐẶT ĐỒ");
            TextField textField = new TextField();
            food f = new food(food.getFoodId(), food.getName(), food.getPrice(),textField , btn);
            foodList.add(f);
            foodButtonList.add(f.getBtn());
        }
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
        fQuantity_Column.setCellValueFactory(new PropertyValueFactory<food, TextField>("quantity"));
        fButton_Column.setCellValueFactory(new PropertyValueFactory<food, Button>("btn"));


        for (food f : foodList) {
            tableView.getItems().add(f);
        }

        for(int i = 0; i < foodButtonList.toArray().length; i++) {
            foodButtonList.get(i).setOnAction(this::buttonAction);

        }
    }

    @FXML
    public void Order(int id, String name, int price, int quantity, List<Integer> list) {

        TextField textField = new TextField(String.valueOf(quantity));
        Integer sum = price * quantity;
        food sp = new food(id, name, price, textField);
        for(table table : tableList) {
            if (table.getTableId() == code) {
                table.addSanPham(sp);
            }
        }
        list.add(sum);

        String tmp = sp.getInfor();

        // tính tổng bill
        int billSum = 0;
        for (Integer i : list) {
            billSum += i;
        }

        billPrice.setText(String.valueOf(billSum));

        for (table table : tableList) {
            if (table.getTableId() == code) {
                table.setCurPrice(billSum);
            }
        }
    }


    @FXML
    void quayLai_btn(ActionEvent event) throws IOException {
        URL url = new File("src/main/resources/com/example/demo1/Table.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
        File f = new File("C:\\Users\\duyhai\\IdeaProjects\\demo1\\src\\main\\resources\\Style.css");
        root.getStylesheets().add("file:///" + f.getAbsolutePath().replace("\\", "/"));
        Stage window = (Stage) quayLai_btn.getScene().getWindow();
        window.setScene(new Scene(root));
    }

    @FXML
    void check_btn(ActionEvent event) {
        int sum = 0;
        for (table table : tableList) {
            if (table.getTableId() == code && table.getCurPrice() != 0) {
                for(food food : table.getFoods()) {
                    sum += food.getPrice() * Integer.parseInt(food.getQuantity().getText());
                    String tmp = food.toString();
                    listView.getItems().add(tmp);
                }
            }
        }

        billPrice.setText(String.valueOf(sum));
        check_btn.setVisible(false);
    }

    @FXML
    void thanhToan_btn(ActionEvent event) {
        int billID = 0;
        for (table table : tableList) {
            if (table.getTableId() == code) {
                for(bill bill : billList) {
                    if (bill.getBillId() == table.getBillId()) {
                        bill.setPaymentTime(LocalDateTime.now());
                        bill.setTotalMoney(table.getCurPrice());
                        billID = bill.getBillId();
                        SQL.addBill(bill);
                        System.out.println("correct");
                    }
                }
//                System.out.println("correct");
            }
        }

        for(bill bill : billList) {
            System.out.println(bill.getBillId() + " ");
        }

        SQL.payBill(billID, LocalDateTime.now());
        Alerts.showAlertWarning("Thanh Toán", "Thanh Toán thành công");
    }
}