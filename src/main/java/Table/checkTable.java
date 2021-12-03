package Table;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import thucdon.ban;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class checkTable {

    @FXML
    private Button ban1_btn;

    @FXML
    private Button ban2_btn;

    @FXML
    private Button ban3_btn;

    @FXML
    private Button ban4_btn;

    @FXML
    private Button ban5_btn;

    @FXML
    private Button ban6_btn;

    @FXML
    private Button ban7_btn;

    @FXML
    private Button ban8_btn;

    @FXML
    private Button check_btn;

    @FXML
    private Button logout_btn;

    @FXML
    private ListView<String> test;

    public static ArrayList<ban> table_list = new ArrayList<>();

    private ArrayList<Button> button_list = new ArrayList<>();
    URL url = new File("src/main/resources/com/example/demo1/thucdon.fxml").toURI().toURL();
    File f = new File("C:\\Users\\duyhai\\IdeaProjects\\demo1\\src\\main\\resources\\Style.css");

    public checkTable() throws MalformedURLException {
    }

    @FXML
    void pickthrows (URL url, File f, Button btn) throws IOException {
        Parent root = FXMLLoader.load(url);
        root.getStylesheets().add("file:///" + f.getAbsolutePath().replace("\\", "/"));
        Stage window = (Stage) btn.getScene().getWindow();
        window.setScene(new Scene(root));
    }

    @FXML
    void ban1_btn(ActionEvent event) throws IOException {
        pickthrows(url, f, ban1_btn);
        thucdon.Controller.code = ban1_btn.getText();
    }

    @FXML
    void ban2_btn(ActionEvent event) throws IOException {
        pickthrows(url, f, ban2_btn);
        thucdon.Controller.code = ban2_btn.getText();
    }

    @FXML
    void ban3_btn(ActionEvent event) throws IOException {
        pickthrows(url, f, ban3_btn);
        thucdon.Controller.code = ban3_btn.getText();
    }

    @FXML
    void ban4_btn(ActionEvent event) throws IOException {
        pickthrows(url, f, ban4_btn);
        thucdon.Controller.code = ban4_btn.getText();
    }

    @FXML
    void ban5_btn(ActionEvent event) throws IOException {
        pickthrows(url, f, ban5_btn);
        thucdon.Controller.code = ban5_btn.getText();
    }

    @FXML
    void ban6_btn(ActionEvent event) throws IOException {
        pickthrows(url, f, ban6_btn);
        thucdon.Controller.code = ban6_btn.getText();
    }

    @FXML
    void ban7_btn(ActionEvent event) throws IOException {
        pickthrows(url, f, ban7_btn);
        thucdon.Controller.code = ban7_btn.getText();
    }

    @FXML
    void ban8_btn(ActionEvent event) throws IOException {
        pickthrows(url, f, ban8_btn);
        thucdon.Controller.code = ban8_btn.getText();
    }


    @FXML
    void check_btn(ActionEvent event) {
        int dem = 0;
        add_button();
        add_table();
        for(ban table : table_list) {
            ban tmp = table;
            if (table.getBill() == 0) {
                for(Button btn : button_list) {
                    if (btn.getText().equals(tmp.getMaBan())) {
                        btn.setStyle("-fx-background-color: green;");
                        dem++;
                    }
                }
            }
            else {
                for(Button btn : button_list) {
                    if (btn.getText().equals(tmp.getMaBan())) {
                        btn.setStyle("-fx-background-color: red;");
                    }
                }
            }
        }
        for (int i = 0; i < table_list.size(); i++) {
            String tmp = "Bàn" + table_list.get(i).getMaBan() + ": " + table_list.get(i).getBill() + "\n";
            test.getItems().add(tmp);
        };
        check_btn.setVisible(false);
    }

    void add_button() {
        check_button(ban1_btn);
        check_button(ban2_btn);
        check_button(ban3_btn);
        check_button(ban4_btn);
        check_button(ban5_btn);
        check_button(ban6_btn);
        check_button(ban7_btn);
        check_button(ban8_btn);
    }

    void check_button(Button btn) {
        int dem = 0;
        for(Button tmp : button_list) {
            if(tmp.getText().equals(btn.getText())) {
                dem++;
            }
        }
        if(dem == 0) {
            button_list.add(btn);
        }
    }

    void check_table(ban tmp) {
        int dem = 0;
        for(ban table : table_list) {
            if(table.getMaBan().equals(tmp.getMaBan())) {
                dem++;
            }
        }
        if(dem == 0) {
            table_list.add(tmp);
        }
    }

    public void add_table() {
        ban table1 = new ban();
        table1.setMaBan(ban1_btn.getText());
        check_table(table1);

        ban table2 = new ban();
        table2.setMaBan(ban2_btn.getText());
        check_table(table2);

        ban table3 = new ban();
        table3.setMaBan(ban3_btn.getText());
        check_table(table3);

        ban table4 = new ban();
        table4.setMaBan(ban4_btn.getText());
        check_table(table4);

        ban table5 = new ban();
        table5.setMaBan(ban5_btn.getText());
        check_table(table5);

        ban table6 = new ban();
        table6.setMaBan(ban6_btn.getText());
        check_table(table6);

        ban table7 = new ban();
        table7.setMaBan(ban7_btn.getText());
        check_table(table7);

        ban table8 = new ban();
        table8.setMaBan(ban8_btn.getText());
        check_table(table8);
    }
}

//tạo 1 array stt chính là stt của bàn
