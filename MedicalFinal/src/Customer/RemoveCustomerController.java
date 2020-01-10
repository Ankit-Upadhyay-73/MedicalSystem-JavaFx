package Customer;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import database.DatabaseManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class RemoveCustomerController {

    @FXML
    private JFXTextField remove_customerName;

    @FXML
    private JFXTextField remove_customerId;

    @FXML
    private JFXButton remove_btn;

    public void  onRemoveCust() throws SQLException {
        if(validateRemoveCust())
        {
            Connection con= DatabaseManager.returnConnection();
            Statement st =con.createStatement();
            boolean flag=false;
            ResultSet rst=st.executeQuery("select CustomerName from CustomerIn where CustomerName='"+remove_customerName.getText()+"' and CustomerId='"+remove_customerId.getText()+"'");
            while(rst.next())
            {
                if(rst.getString(1).equals(remove_customerName.getText()))
                    flag=true;
            }
            if(flag)
            {
                st.execute("delete from CustomerIn where CustomerName='"+remove_customerName.getText()+"' and CustomerId='"+remove_customerId.getText()+"'");
                setInfo("Customer Removed Successfully");
            }
            else
            {
                setError("Customer doesn't Exist");
            }
        }
    }
    public boolean validateRemoveCust()
    {
        if((remove_customerName.getText()).equals("") || (remove_customerId.getText()).equals(""))
            return  false;
        else
            return true;
    }
    public void setInfo(String Content)
    {
        Alert al=new Alert(Alert.AlertType.INFORMATION);
        al.setContentText(Content);
        al.showAndWait();
    }
    public void setError(String Content)
    {
        Alert al=new Alert(Alert.AlertType.ERROR);
        al.setContentText(Content);
        al.showAndWait();
    }

}
