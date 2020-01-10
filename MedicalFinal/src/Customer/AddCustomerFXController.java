package Customer;
import com.jfoenix.controls.JFXComboBox;
import database.DatabaseManager;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AddCustomerFXController {

    @FXML
    private JFXTextArea customeraddresstf;

    @FXML
    private JFXTextField customernametf;

    @FXML
    private JFXTextField customeridtf;

    @FXML
    private JFXTextField customercontacttf;

    @FXML
    private JFXButton addcustomer_btn;

    public void onAddCustomer_btn() {
        try {
            Connection con = DatabaseManager.returnConnection();
            Statement st = con.createStatement();
            ResultSet rst = st.executeQuery("select CustomerName,CustomerId from Customer where CustomerName='" + customernametf.getText() + "' and CustomerId='" +(Integer.parseInt(customeridtf.getText()))+ "'");
            if (!rst.next()) {
                st.execute("insert into CustomerIn values('" + customernametf.getText() + "','" + customeridtf.getText() + "','" + customercontacttf.getText() + "','" + customeraddresstf.getText() + "')");
                st.execute("insert into Customer values('" + customernametf.getText() + "','" + customeridtf.getText() + "','" + customercontacttf.getText() + "','" + customeraddresstf.getText() + "')");
                setInfo("Customer Added Successfully");
            }
            else
                setInfo("Customer Already Exists");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void setInfo(String Content)
    {
        Alert al=new Alert(Alert.AlertType.INFORMATION);
        al.setContentText(Content);
        al.showAndWait();
    }
}