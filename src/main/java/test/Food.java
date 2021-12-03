package test;

public class Food {
    public int fID;
    public String fName;
    public int fPrice;

    public Food(int fID, String fName, int fPrice) {
        this.fID = fID;
        this.fName = fName;
        this.fPrice = fPrice;
    }

    public int getfID() {
        return fID;
    }

    public void setfID(int fID) {
        this.fID = fID;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public int getfPrice() {
        return fPrice;
    }

    public void setfPrice(int fPrice) {
        this.fPrice = fPrice;
    }

    public String getInfor() {
        return this.fID + " " + this.fName + "  vnd" + this.fPrice;
    }
}