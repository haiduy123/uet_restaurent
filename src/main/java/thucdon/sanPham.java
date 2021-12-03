package thucdon;

public class sanPham {
    private String tenSanPham;
    private int soLuong;
    private int gia;
    private int tonggia = soLuong * gia;


    public sanPham() {

    }

    public sanPham(String tenSanPham, int soLuong, int gia, int tonggia) {
        this.tenSanPham = tenSanPham;
        this.soLuong = soLuong;
        this.gia = gia;
        this.tonggia = tonggia;
    }

    public sanPham(String tenSanPham, int soLuong, int tonggia) {
        this.tenSanPham = tenSanPham;
        this.soLuong = soLuong;
        this.tonggia = tonggia;
    }

    public String getTenSanPham() {
        return tenSanPham;
    }

    public void setTenSanPham(String tenSanPham) {
        this.tenSanPham = tenSanPham;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public int getGia() {
        return gia;
    }

    public void setGia(int gia) {
        this.gia = gia;
    }

    public int getTonggia() {
        return tonggia;
    }

    public void setTonggia(int tonggia) {
        this.tonggia = tonggia;
    }

    public String toString(sanPham sp) {
        String tmp = sp.getTenSanPham() + "\n"
                + "Số lượng: " + sp.getSoLuong() + "\n"
                + "Giá: " + sp.getTonggia() + "\n";
        return tmp;
    }
}
