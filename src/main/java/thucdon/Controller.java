package thucdon;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static Order.Controller.table_list;

public class Controller {

    @FXML
    private Label mon1_price;

    @FXML
    private Button mon2_btn;

    @FXML
    private TextField mon2;

    @FXML
    private TextField mon1;

    @FXML
    private Button quayLai_btn;

    @FXML
    private ListView<String> listVIew;

    @FXML
    private Label mon2_price;

    @FXML
    private Button mon1_btn;

    @FXML
    private Label bill;

    @FXML
    private TextField maBan;

    @FXML
    private Button ketthuc_btn;


    public List<Integer> list_sum = new ArrayList<>();

    private List<sanPham> list_product = new ArrayList<>();

    public static String code;

    @FXML
    void quayLai_btn(ActionEvent event) throws IOException {
        URL url = new File("src/main/resources/Order.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);

        File f = new File("C:\\Users\\duyhai\\IdeaProjects\\demo1\\src\\main\\resources\\Style.css");
        root.getStylesheets().add("file:///" + f.getAbsolutePath().replace("\\", "/"));

        Stage window = (Stage) quayLai_btn.getScene().getWindow();
        window.setScene(new Scene(root));
    }

    @FXML
    public void Order(Button btn, TextField textField, Label label, List<Integer> list) {
        String name = btn.getText();
        int soLuong = Integer.parseInt(textField.getText());
        int price = Integer.parseInt(label.getText());

        Integer sum = price * soLuong;
        sanPham sp = new sanPham(name,soLuong,price,sum);
        for(ban table : table_list) {
            if (table.getMaBan().equals(code)) {
                table.addSanPham(sp);
            }
        }
        list.add(sum);

        String tmp = sp.toString(sp);
        // cài đặt listView
        listVIew.getItems().add(tmp);

        // tính tổng bill
        long bill_sum = 0;
        for (Integer i : list) {
            bill_sum += i;
        }

//        String code = maBan.getText();

        for (ban table : table_list) {
            if (table.getMaBan().equals(code)) {
                table.setBill(bill_sum);
            }
        }

        bill.setText(String.valueOf(bill_sum));
    }

    // cài đặt nút quay lại

    @FXML
    void mon1_btn(ActionEvent event) {
        Order(mon1_btn, mon1, mon1_price,list_sum);
        add_product(mon1_btn, mon1_price, mon1);
    }

    @FXML
    void mon2_btn(ActionEvent event) {
        Order(mon2_btn, mon2, mon2_price,list_sum);
        add_product(mon2_btn, mon2_price, mon2);
    }

    void add_product(Button btn, Label label, TextField textField2) {
        String tenMon = btn.getText();
        int price = Integer.parseInt(label.getText());
        int soLuong = Integer.parseInt(textField2.getText());
        int sum = soLuong * price;
        sanPham sp = new sanPham(tenMon, soLuong, sum);
        list_product.add(sp);
    }

    @FXML
    void ketthuc_btn(ActionEvent event) {
        int sum = 0;
        for (ban table : table_list) {
            if (table.getMaBan().equals(code) && table.getBill() != 0) {
                for(sanPham sp : table.getSanpham()) {
                    sum += sp.getTonggia();
                    String tmp = sp.toString(sp);
                    listVIew.getItems().add(tmp);
                }
            }
        }

        bill.setText(String.valueOf(sum));
        ketthuc_btn.setVisible(false);
    }
}