package Bill;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import Food.*;

public class billManagement {
    public static List<bill> billList = new ArrayList<>();
    public static int newBillId = 0;

    public static void createNewBill(bill bill) {
        ++newBillId;
        bill.setBillId(newBillId);
    }

    //thanh toán hóa đơn cũ
//    public static void payBill(int tableId){
//        if(tableManagement.tables.get(tableManagement.getIdxTableById(tableId)).billId == 0) System.out.println("Bàn này hiện tại không có hóa đơn");
//        else{
//            SQL.payBillSQL(tableManagement.tables.get(tableManagement.getIdxTableById(tableId)).billId);
//            SQL.payBillTableSQL(tableId, 0);
//            tableManagement.clearTable(tableId);
//        }
//    }

    //thêm món ăn vào hóa đơn cũ + cập nhật lại giá bằng
//    public static void addNewFoodToBill(int tableId) {
//        Scanner scan = new Scanner(System.in);
//        System.out.println("Xin chọn món: ");
//        String foods = scan.nextLine();
//        String[] ff = foods.split(" ");
//        int[] fff = new int[ff.length];
//        for (int i = 0; i < ff.length; i++) {
//            fff[i] = Integer.parseInt(ff[i]);
//        }
//        for (int j : fff) {
//            food x = foodManagement.allFood.get(foodManagement.getFoodIdxById(j));
//            tableManagement.tables.get(tableId - 1).foods.add(x);
//            SQL.addBilldetails(x, tableManagement.tables.get(tableId-1).getBillId());
//        }
//
//        int sum = 0;
//        for(food f : tableManagement.tables.get(tableId - 1).foods) {
//            sum += f.price;
//        }
//
//        SQL.updateBill(tableManagement.tables.get(tableId-1).getBillId(), sum);
//    }

}