package Food;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class food {
    private int foodId;
    private String name;
    private int price;
    private TextField quantity;
    private Button btn;

    public food(int foodId, String name, int price) {
        this.foodId= foodId;
        this.name = name;
        this.price = price;
    }

    public food(int foodId, String name, int price, TextField textField) {
        this.foodId= foodId;
        this.name = name;
        this.price = price;
        this.quantity = textField;
    }

    public food(int foodId, String name, int price, TextField textField, Button btn) {
        this.foodId= foodId;
        this.name = name;
        this.price = price;
        this.quantity = textField;
        this.btn = btn;
    }

    public int getFoodId() {
        return foodId;
    }

    public void setFoodId(int foodId) {
        this.foodId = foodId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public TextField getQuantity() {
        return quantity;
    }

    public void setQuantity(TextField quantity) {
        this.quantity = quantity;
    }

    public Button getBtn() {
        return btn;
    }

    public void setBtn(Button btn) {
        this.btn = btn;
    }


    public String getInfor() {
        return this.foodId + " " + this.name + "  vnd" + this.price;
    }

    public String toString() {
        return  this.name + "\n"
                + "Giá: " + this.price + " VNĐ" + "\n"
                + "Số lượng: " + this.quantity.getText();
    }
}