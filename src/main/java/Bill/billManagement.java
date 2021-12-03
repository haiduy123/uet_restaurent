package Bill;

import java.util.Scanner;

import Food.*;
import SQL.SQL;

public class billManagement {
    public billManagement(){}
    public void newBill(){
        bill nBill = new bill();
        Scanner scan = new Scanner(System.in);
        System.out.println("Xin chọn món: ");
        String foods = scan.nextLine();
        System.out.println("Mời nhập mã hóa đơn mới: ");
        nBill.billId = scan.nextInt();
        String[] ff = foods.split(" ");
        int[] fff = new int[ff.length];

        for(int i = 0; i < ff.length; i++) {
            fff[i] =Integer.parseInt(ff[i]);
        }

        for(int i = 0; i < fff.length; i++) {
            food x = foodManagement.allFood.get(foodManagement.getFoodIdxById(fff[i]));
            nBill.selectedFoods.add(x);
            nBill.totalMoney += x.price;
            SQL.addBilldetails(x,nBill.billId);
        }

        SQL.addBill(nBill);

    }
}