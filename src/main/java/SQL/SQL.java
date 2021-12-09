package SQL;

import java.sql.*;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

import Bill.*;
import Food.*;
import Table.*;
import employees.*;
import login.mainAccount;

public class SQL {
    /**
     * url: dataname
     */
    public String url = "jdbc:mysql://localhost:3306/rtr";
    public String username = "root";
    public String password = "haiduy39";
    public static String tableEmployee = "employee";
    public static String tableBill = "bill";
    public static String tableBilldetails = "billdetails";
    public static String tableFood = "food";
    public static String tableDtable = "dtable";
    public static Connection connection;
    public static List<String> wordsList = new ArrayList<>();
    public static int num = 0;

    public SQL() throws SQLException {
        connect();
//        mainAccount.logIn();
        getAllEmployees(allEmployees());
        getAllFoodsSQL(allFoods());
//        getAllBill(allBills());
        //getWords(getAllWord());
        //wordList();
    }


    public void connect() {
        try {
            connection = DriverManager.getConnection(url, username, password);
            //num = getNumber();
            System.out.println("Connected to database");
        } catch (Exception e) {
            System.out.println("Error");
            e.printStackTrace();
        }
    }

    public static int getNewBillIdSQL() {
        ResultSet rs = null;
        String sqlCommand = "SELECT * FROM " + tableBill + " ORDER BY bID DESC LIMIT 1";
        Statement st;
        int id = 0;
        try {
            st = connection.createStatement();
            rs = st.executeQuery(sqlCommand);
            rs.next();
            billManagement.newBillId = rs.getInt(1);
            id = rs.getInt(1);
        } catch (SQLException e) {
            System.out.println("Select error");
            e.printStackTrace();
        }
        return id;
    }

//    public static void getNewFoodIdSQL() {
//        ResultSet rs = null;
//        String sqlCommand = "SELECT * FROM " + tableFood + " ORDER BY fID DESC LIMIT 1";
//        Statement st;
//        try {
//            st = connection.createStatement();
//            rs = st.executeQuery(sqlCommand);
//            rs.next();
//            foodManagement.newFoodId = rs.getInt(1);
//        } catch (SQLException e) {
//            System.out.println("Select error");
//            e.printStackTrace();
//        }
//    }

//    public static void getNewTableIdSQL() {
//        ResultSet rs = null;
//        String sqlCommand = "SELECT * FROM " + tableDtable + " ORDER BY tId DESC LIMIT 1";
//        Statement st;
//        try {
//            st = connection.createStatement();
//            rs = st.executeQuery(sqlCommand);
//            rs.next();
//            tableManagement.newTableId = rs.getInt(1);
//        } catch (SQLException e) {
//            System.out.println("Select error");
//            e.printStackTrace();
//        }
//    }
//
    public static int getNewEmployeeIdSQL() {
        int id = 0;
        ResultSet rs = null;
        String sqlCommand = "SELECT * FROM " + tableEmployee + " ORDER BY eID DESC LIMIT 1";
        Statement st;
        try {
            st = connection.createStatement();
            rs = st.executeQuery(sqlCommand);
            rs.next();
            id = rs.getInt(1);
        } catch (SQLException e) {
            System.out.println("Select error");
            e.printStackTrace();
        }
        return id;
    }

    public ResultSet allEmployees() {
        ResultSet rs = null;
        String sqlCommand = "select * from " + tableEmployee;
        Statement st;
        try {
            st = connection.createStatement();
            rs = st.executeQuery(sqlCommand);
        } catch (SQLException e) {
            System.out.println("Select error");
            e.printStackTrace();
        }
        return rs;
    }

    // thêm tất cả nhân viên vào danh sách
    public void getAllEmployees(ResultSet rs) throws SQLException {
        while (rs.next()) {
            employeeManagement.addEmployee(new employee(rs.getInt(1)
                    , rs.getString(2)
                    , rs.getString(3)
                    , Instant.ofEpochMilli(rs.getDate(4).getTime()).atZone(ZoneId.systemDefault()).toLocalDate()
                    , rs.getString(5)
                    , rs.getString(6)
                    , Instant.ofEpochMilli(rs.getDate(7).getTime()).atZone(ZoneId.systemDefault()).toLocalDate()));
//                    , rs.getString(8);
        }
    }

    // thêm nhân viên
    public static void addEmployeeSQL(employee e) {
        String sqlCommand = "insert into " + tableEmployee + " value( ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement pst = null;
        try {
            pst = connection.prepareStatement(sqlCommand);
            pst.setInt(1, e.getEmployeeId());
            pst.setString(2, e.getLastName());
            pst.setString(3, e.getFirstName());
            pst.setDate(4, java.sql.Date.valueOf(e.getBirthday()));
            pst.setString(5, e.getJobTitle());
            pst.setString(6, e.getPhoneNumber());
            pst.setDate(7, java.sql.Date.valueOf(e.getJoinDate()));
            pst.setString(8, e.getPassCode());
            if (pst.executeUpdate() > 0) {
                employeeManagement.addEmployee(e);
                System.out.println("Thêm nhân viên thành công: " + e.getEmployeeId());
            } else {
                System.out.println("Chưa thể thêm nhân viên!");
            }
        } catch (SQLException x) {
            x.printStackTrace();
        }
    }

    //xóa nhân viên
    public static void deleteEmployees(int maNV) {
        String sqlCommand = "delete from " + tableEmployee + " where eID = ?";
        PreparedStatement pst = null;
        try {
            pst = connection.prepareStatement(sqlCommand);
            pst.setInt(1, maNV);
            if (pst.executeUpdate() > 0) {
                System.out.println("Đã xóa");
                employeeManagement.removeEmployee(maNV);
            } else {
                System.out.println("Không có nhân viên cần xóa!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
//            return false;
        }
//        return false;
    }

    public static boolean fixInfoEmployee(int id, String lastName, String firstName, LocalDate birth, String jobTitle, String phone) {
        String sqlCommand = "UPDATE " + tableEmployee + " SET lastName = ?, firstName = ?, birthday = ?,jobTitle = ?, numberPhone = ? WHERE eID = " + id;
        PreparedStatement pst = null;
        try {
            pst = connection.prepareStatement(sqlCommand);
            pst.setString(1, lastName);
            pst.setString(2, firstName);
            pst.setDate(3, java.sql.Date.valueOf(birth));
            pst.setString(4, jobTitle);
            pst.setString(5, phone);
            if (pst.executeUpdate() > 0) {
                System.out.println("Đã sửa nhân viên" + id);
                return true;
            } else {
                System.out.println("Không có nhân viên cần sửa!");
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // In món ăn
    public ResultSet allFoods() {
        ResultSet rs = null;
        String sqlCommand = "select * from " + tableFood;
        Statement st;
        try {
            st = connection.createStatement();
            rs = st.executeQuery(sqlCommand);
        } catch (SQLException e) {
            System.out.println("Select error");
            e.printStackTrace();
        }
        return rs;
    }

    public void getAllFoodsSQL(ResultSet rs) throws SQLException {
        while (rs.next()) {
            foodManagement.addFood(new food(rs.getInt(1), rs.getString(2), rs.getInt(3)));
        }
    }

    //xóa món ăn
    public void deleteFood(int maMon) {
        String sqlCommand = "delete from " + tableFood + " where fID = ?";
        PreparedStatement pst = null;
        try {
            pst = connection.prepareStatement(sqlCommand);
            pst.setInt(1, maMon);
            if (pst.executeUpdate() > 0) {
                System.out.println("Đã xóa món ăn");
                foodManagement.removeFoodByID(maMon);
            } else {
                System.out.println("Không có món ăn cần xóa!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static boolean checkID(int id, String pc) throws SQLException {
        ResultSet rs = checkID2(id, pc);
        //if(rs == null) return false;
        while (rs.next()) {
            if(Objects.equals(rs.getString(8), pc)) return true;
        }
        return false;
    }

    public static ResultSet checkID2(int id, String pc) {
        ResultSet rs = null;
        String sqlCommand = "select * from " + tableEmployee + " where eID = " + id;
        Statement st;
        try {
            st = connection.createStatement();
            rs = st.executeQuery(sqlCommand);
        } catch (SQLException e) {
            System.out.println("Select error");
            e.printStackTrace();
        }
        return rs;
    }

    public static void addBillDetails(food x, int bID) {
        ResultSet rs = null;
        String sqlCommand = "INSERT INTO billdetails (fID, fPrice, bID) " +
                "VALUES (?, ?, ?)";
        PreparedStatement pst = null;
        try {
            pst = connection.prepareStatement(sqlCommand);
            pst.setInt(1, x.getFoodId());
            pst.setInt(2, x.getPrice());
            //pst.setString(3, "none");
            pst.setInt(3, bID);
            if (pst.executeUpdate() > 0) {
                System.out.println("update success :" + bID);
            } else {
                System.out.println("update billdetails error");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void addBill(bill nBi) {
        ResultSet rs = null;
        String sqlCommand = "INSERT INTO bill (bID, totalMoney, discount, timeIn, eID) " +
                "VALUES (?, ?, ?, ?, ?)";
        PreparedStatement pst = null;
        try {
            pst = connection.prepareStatement(sqlCommand);
            pst.setInt(1, nBi.getBillId());
            pst.setInt(2, nBi.getTotalMoney());
            pst.setInt(3, 0);
            pst.setTimestamp(4, java.sql.Timestamp.valueOf(nBi.getTimeIn()));
            pst.setInt(5, nBi.getEmployeeId());
            if (pst.executeUpdate() > 0) {
                System.out.println("update success :" + nBi.getBillId());
            } else {
                System.out.println("update billdetails error");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    // lúc thanh toán thì update thời gian thanh toán
    public static void payBill(int bID, LocalDateTime payTime) {
        ResultSet rs = null;
        String sqlCommand = "UPDATE " + tableBill + " SET timePayment = ?" + " WHERE bID = ? ";
        PreparedStatement pst = null;
        try {
            pst = connection.prepareStatement(sqlCommand);
            pst.setInt(2, bID);
            pst.setTimestamp(1, java.sql.Timestamp.valueOf(payTime) );
            if (pst.executeUpdate() > 0) {
                System.out.println("update success :" + pst.executeUpdate());
            } else {
                System.out.println("update bill error");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

//    public ResultSet allBills() {
//        ResultSet rs = null;
//        String sqlCommand = "select * from " + tableBill;
//        Statement st;
//        try {
//            st = connection.createStatement();
//            rs = st.executeQuery(sqlCommand);
//        } catch (SQLException e) {
//            System.out.println("Select error");
//            e.printStackTrace();
//        }
//        return rs;
//    }
//
//    // thêm tất cả nhân viên vào danh sách
//    public void getAllBill(ResultSet rs) throws SQLException {
//        while (rs.next()) {
//            billManagement.addBill(new bill(rs.getInt(1)
//                    , rs.getInt(2)
//                    , rs.getInt(3)
//                    , Instant.ofEpochMilli(rs.getDate(4).getTime()).atZone(ZoneId.systemDefault()).toTimeSpam()
//                    , Instant.ofEpochMilli(rs.getDate(5).getTime()).atZone(ZoneId.systemDefault()).toLocalDateTime()
//                    , rs.getInt(6)));
//        }
//    }

    // Bàn
    public static ResultSet allTables() {
        ResultSet rs = null;
        String sqlCommand = "select * from " + tableDtable;
        Statement st;
        try {
            st = connection.createStatement();
            rs = st.executeQuery(sqlCommand);
        } catch (SQLException e) {
            System.out.println("Select error");
            e.printStackTrace();
        }
        return rs;
    }

    public static void getAllTablesSQL(ResultSet rs) throws SQLException {
        while (rs.next()) {
//            List<food> f = new ArrayList<>();
//            if(rs.getInt(3) != 0) {
//                ResultSet rs1 = getFoodByBillId(rs.getInt(3));
//                while (rs1.next()) {
//                    f.add(foodManagement.allFood.get(foodManagement.getFoodIdxById(rs1.getInt(1))));
//                }
//            }
            tableManagement.addTableToList(new table(rs.getInt(1), rs.getInt(3)));
        }
    }

    public static void main(String[] args) throws SQLException {
        SQL myconnect = new SQL();
        billManagement bl = new billManagement();
//        bl.newBill();
        myconnect.payBill(90, LocalDateTime.now());
//        myconnect.printAllEmployees(myconnect.allEmployees());
//        myconnect.addEmployee("Trần Thủy", "Nhân viên", 4635);
//        myconnect.printAllFoods(myconnect.allFoods());
//        myconnect.deleteEmployees(4);
//        myconnect.printAllEmployees(myconnect.allEmployees());
    }

}
