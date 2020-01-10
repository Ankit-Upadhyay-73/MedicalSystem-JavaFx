package Customer;

public class Customer_TableView
{
    String CustomerName;
    int CustomerId;
    String CustomerContact;
    String CustomerAddress;

    public Customer_TableView(String CustomerName, int CustomerId, String CustomerContact, String CustomerAddress) {
        this.CustomerName = CustomerName;
        this.CustomerId = CustomerId;
        this.CustomerContact = CustomerContact;
        this.CustomerAddress = CustomerAddress;
    }

    public String getCustomerName() {
        return CustomerName;
    }

    public int getCustomerId() {
        return CustomerId;
    }

    public String getCustomerContact() {
        return CustomerContact;
    }

    public String getCustomerAddress() {
        return CustomerAddress;
    }
}
