package MainWindow;

public class Search_View {
    String Availability;
    String companyname;
    String productName;
    int quantity, price;
    String manufacturedate;
    String expirydate;
    String inform;
    String BatchNo;

    public Search_View(String productName, String companyname, int price, String manufacturedate, String expirydate, String Availability,String inform) {
        this.companyname = companyname;
        this.productName = productName;
        this.price = price;
        this.manufacturedate = manufacturedate;
        this.expirydate = expirydate;
        this.Availability = Availability;
        this.inform=inform;
    }

    public Search_View(String productName, String companyname, String BatchNo, int price, String Availability) {
        this.productName = productName;
        this.companyname = companyname;
        this.Availability = Availability;
        this.BatchNo = BatchNo;
        this.price = price;
    }

    public String getAvailability() {
        return Availability;
    }

    public String getProductName() {
        return productName;
    }

    public String getCompanyname() {
        return companyname;
    }

    public String getBatchNo() {
        return BatchNo;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getPrice() {
        return price;
    }

    public String getManufacturedate() {
        return manufacturedate;
    }

    public String getExpirydate() {
        return expirydate;
    }
    public String getInform() {
        return inform;
    }
}

