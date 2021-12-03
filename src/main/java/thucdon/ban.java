package thucdon;

import java.util.ArrayList;
import java.util.List;

public class ban {
    private String maBan;
    private List<sanPham> sanpham = new ArrayList<>();
    private long bill;

    public ban() {

    }

    public ban(String maBan, long bill, List<sanPham> sanpham) {
        this.maBan = maBan;
        this.bill = bill;
        this.sanpham = sanpham;
    }

    public String getMaBan() {
        return maBan;
    }

    public void setMaBan(String maBan) {
        this.maBan = maBan;
    }

    public long getBill() {
        return bill;
    }

    public void setBill(long bill) {
        this.bill = bill;
    }

    public void addSanPham(sanPham sp) {
        this.sanpham.add(sp);
    }

    public List<sanPham> getSanpham() {
        return sanpham;
    }
}
