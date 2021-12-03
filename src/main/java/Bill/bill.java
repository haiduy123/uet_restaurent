package Bill;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import Food.*;
import login.mainAccount;

public class bill {
    public int billId;
    public List<food> selectedFoods;
    public List<String> foodNotes;
    public int totalMoney;
    public LocalDateTime timeIn;
    public LocalDateTime paymentTime;
    public int employeeId;

    public bill() {
        selectedFoods = new ArrayList<>();
        foodNotes = new ArrayList<>();
        totalMoney = 0;
        this.timeIn = LocalDateTime.now();
        this.paymentTime = null;
        employeeId = mainAccount.getUserID();
    }
    public bill(int bID, List<food> selectedFoods, List<String> foodNotes
            , int totalMoney, LocalDateTime timeIn, LocalDateTime paymentTime, int employeeId) {
        this.billId = bID;
        this.selectedFoods = selectedFoods;
        this.foodNotes = foodNotes;
        this.totalMoney = totalMoney;
        this.timeIn = timeIn;
        this.paymentTime = paymentTime;
        this.employeeId = billId;
    }

    public int getBillId() {
        return billId;
    }

    public void setBillId(int billId) {
        this.billId = billId;
    }

    public List<food> getSelectedFoods() {
        return selectedFoods;
    }

    public void setSelectedFoods(List<food> selectedFoods) {
        this.selectedFoods = selectedFoods;
    }

    public List<String> getFoodNotes() {
        return foodNotes;
    }

    public void setFoodNotes(List<String> foodNotes) {
        this.foodNotes = foodNotes;
    }

    public int getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(int totalMoney) {
        this.totalMoney = totalMoney;
    }

    public LocalDateTime getTimeIn() {
        return timeIn;
    }

    public void setTimeIn(LocalDateTime timeIn) {
        this.timeIn = timeIn;
    }

    public LocalDateTime getPaymentTime() {
        return paymentTime;
    }

    public void setPaymentTime(LocalDateTime paymentTime) {
        this.paymentTime = paymentTime;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }
}