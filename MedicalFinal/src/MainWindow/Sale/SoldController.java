package MainWindow.Sale;
import box.AlertBoxController;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import database.DatabaseManager;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;

import java.io.IOException;
import java.sql.*;
public class SoldController
{

    @FXML
    private JFXTextField companynametf_sale;

    @FXML
    private JFXTextField productnametf_sale;

    @FXML
    private JFXTextField batchnotf_sale;

    @FXML
    private JFXTextField customernametf_sale;

    @FXML
    private JFXTextField quantitytf_sale;

    @FXML
    private JFXTextField pricetf_sale;

    @FXML
    private JFXComboBox<?> inform_sale;

    @FXML
    private JFXButton salebtn;

    public void onSale()
    {
        Connection con= DatabaseManager.returnConnection();
        try {
            int quantity = 0;
            boolean CustAvailability = false;
            int userQuantityValue = Integer.parseInt(quantitytf_sale.getText());
            Statement st = con.createStatement();
            st.execute("insert into sale values('" + companynametf_sale.getText() + "','" + productnametf_sale.getText() + "','" + batchnotf_sale.getText() + "','" + customernametf_sale.getText() + "','" + Integer.parseInt(quantitytf_sale.getText()) + "','" + Integer.parseInt(pricetf_sale.getText()) + "','" + inform_sale.getValue() + "','" + getCurrentDate() + "')");
            ResultSet rs1 = st.executeQuery("select quantity from AvailableStock where productname='" + productnametf_sale.getText() + "'");
            while (rs1.next()) {
                quantity = rs1.getInt(1);
            }
            ResultSet rsc = st.executeQuery("select CustomerName from CustomerIn where CustomerName='" + customernametf_sale.getText() + "'");
            while (rsc.next()) {
                if ((rsc.getString(1)).equals(customernametf_sale.getText()))
                    CustAvailability = true;
                else
                    CustAvailability = false;
            }
            int countst = 0;
            ResultSet rsst = st.executeQuery("select count(*) from AvailableStock where companyname='" + companynametf_sale.getText() + "' and productname='" + productnametf_sale.getText() + "' and BatchNo='" + batchnotf_sale.getText() + "' and inform='"+inform_sale.getValue()+"'");
            while (rsst.next()) {
                countst = rsst.getInt(1);
            }
            if (CustAvailability == false)
                new AlertBoxController().createAlert("Customer Not Exists","ERROR");
            else {
                if (countst == 0)
                    new AlertBoxController().createAlert("Product is Not Available","ERROR");
                else {
                    if ((quantity - userQuantityValue) >= 0) {
                        if ((quantity - userQuantityValue) == 0) {
                            st.execute("delete from AvailableStock where " + "ProductName='" + productnametf_sale.getText() + "'");
                        }
                        else {
                            st.execute("update AvailableStock  set quantity=quantity-('" + userQuantityValue + "') where productname='" + productnametf_sale.getText() + "'");
                        }
                        st.execute("insert into sold values('"+productnametf_sale.getText()+"','"+companynametf_sale.getText()+"','"+quantitytf_sale.getText()+"','"+pricetf_sale.getText()+"')");
                        setInfo("Sale Successfully");
                        new AlertBoxController().createAlert("Sale Successfully","INFORMATION");
                    } else {
                        if (userQuantityValue > quantity && quantity > 0)
                            setInfo("Only " + quantity + " is Left");
                        else
                            setInfo("Out Of Stock");
                    }
                }
            }
        }catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setInfo(String Content) throws IOException
    {
        new AlertBoxController().createAlert(Content,"INFORMATION");
    }

    public void setError(String Content) throws IOException
    {
        new AlertBoxController().createAlert(Content,"ERROR");
    }

    public String getCurrentDate()
    {
        Connection  con=DatabaseManager.returnConnection();
        try {
            Statement st=con.createStatement();
            ResultSet rs= st.executeQuery("select sysdate()");
            String dt = null;
            while(rs.next())
            {
                dt= String.valueOf(rs.getDate(1));
            }
            return dt;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
