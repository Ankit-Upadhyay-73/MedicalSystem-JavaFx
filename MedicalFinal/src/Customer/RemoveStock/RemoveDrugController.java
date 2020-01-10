package Customer.RemoveStock;

import box.AlertBoxController;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import database.DatabaseManager;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;

import javax.xml.transform.Result;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class RemoveDrugController implements Initializable {

    @FXML
    private JFXTextField remove_productname;

    @FXML
    private JFXTextField remove_companyname;

    @FXML
    private JFXTextField remove_batchno;

    @FXML
    private JFXTextField remove_quantity;

    @FXML
    private JFXComboBox<String> removedrug_informcombo;

    @FXML
    private JFXButton btn_removedrug;

    public void onRemoveStock() throws SQLException, IOException {
        if(validateRemoveStock())
        {
            Connection connection= DatabaseManager.returnConnection();
            Statement st=connection.createStatement();
            ResultSet resultSet = st.executeQuery("select count(*) from availablestock where productname='"+remove_productname.getText()+"' and companyname='"+remove_companyname.getText()+"' and batchno='"+remove_batchno.getText()+"' ");
            int countdrug=0;
            while(resultSet.next())
            {
                if (resultSet.getInt(1)!=0)
                    countdrug=1;
            }
//            st.execute("delete from StockIn  where productname='"+remove_productname.getText()+"' and companyname='"+remove_companyname.getText()+"' and batchno='"+remove_batchno.getText()+"'");
            if(countdrug!=0) {
                ResultSet rst = st.executeQuery("select quantity from availablestock where productname='"+remove_productname.getText()+"' and companyname='"+remove_companyname.getText()+"' and batchno='"+remove_batchno.getText()+"'");
                int quantity = 0;
                while (rst.next()) {
                    quantity = rst.getInt(1);
                }
//                System.out.println(quantity);
                int requestquan=Integer.parseInt(remove_quantity.getText());
                if (quantity > 0 &&  requestquan<quantity)
                {
                    st.executeUpdate("update availablestock set quantity=quantity- '" + remove_quantity.getText() + "'  where productname='"+remove_productname.getText()+"' and companyname='"+remove_companyname.getText()+"' ");
                    showInfo("Removed Successfully");
                }
                else if(requestquan==quantity)
                {
                    st.execute("delete from availablestock  where productname='"+remove_productname.getText()+"' and companyname='"+remove_companyname.getText()+"' and batchno='"+remove_batchno.getText()+"'");
                    showInfo("Removed Successfully");
                }
                else
                    showInfo("Drug Not found");
                connection.close();
            }
            showInfo("Drug Not found");
        }
        showInfo("Enter values first");
    }
    public boolean validateRemoveStock()
    {
        if((remove_productname.getText()).equals("") || (remove_companyname.getText()).equals("") || (remove_batchno.getText()).equals("") ||  (remove_quantity.getText()).equals("") || (removedrug_informcombo.getValue()).equals(""))
            return  false;
        else
            return true;
    }
    public void showInfo(String Content) throws IOException {
        new AlertBoxController().createAlert(Content,"INFORMATION");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        removedrug_informcombo.getItems().addAll("Tablet","Liquid");
    }
}
