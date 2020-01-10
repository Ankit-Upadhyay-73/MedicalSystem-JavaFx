package MainWindow.AddStock;
import box.AlertBoxController;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.*;
import database.DatabaseManager;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class StockController implements Initializable {
    @FXML
    private JFXTextField productnametf_stockin;

    @FXML
    private JFXTextField companynametf_stockin;

    @FXML
    private JFXTextField batchnotf;

    @FXML
    private JFXTextField priceperproducttf;

    @FXML
    private JFXTextField quantitytf;

    @FXML
    private JFXComboBox<String> informtf;

    @FXML
    private DatePicker manufacturedate;

    @FXML
    private DatePicker expirydate;

    @FXML
    private JFXButton addinstockbtn;

    public void addStockBtn()
    {
        String userQuantityValue=quantitytf.getText();
        try
        {
            if(validateStockIn()) {
                Connection con=DatabaseManager.returnConnection();
                Statement st;
                st = con.createStatement();
                st.execute("insert into StockIn values('"+companynametf_stockin.getText()+"','"+productnametf_stockin.getText()+"','"+batchnotf.getText()+"','"+Integer.parseInt(priceperproducttf.getText())+"','"+informtf.getValue()+"','"+Integer.parseInt(quantitytf.getText())+"','"+manufacturedate.getEditor().getText()+"','"+expirydate.getEditor().getText()+"','"+"true"+"','"+getCurrentDate()+"')");
                int chStockAv = 0;
                ResultSet chStockQuan = st.executeQuery("select count(*) from AvailableStock where companyname='" + companynametf_stockin.getText() + "' and productname='" + productnametf_stockin.getText() + "' and BatchNo='" + batchnotf.getText() + "' and inform= '" + informtf.getValue() +"' and  manufacturedate= '" + manufacturedate.getEditor().getText() +"' and  expirydate= '" + expirydate.getEditor().getText() +"'");
                while(chStockQuan.next())
                    chStockAv=chStockQuan.getInt(1);
                if(chStockAv!=0)
                    st.execute("update AvailableStock  set quantity=quantity+('" +userQuantityValue  + "') where companyname='\" + companynametf_stockin.getText() + \"' and productname='\" + productnametf_stockin.getText() + \"' and BatchNo='\" + batchnotf.getText() + \"' and inform= '\" + informtf.getValue() +\"' and  manufacturedate= '\" + manufacturedate.getEditor().getText() +\"' and  expirydate= '\" + expirydate.getEditor().getText() +\"'");
                else
                    st.execute("insert into AvailableStock values('"+companynametf_stockin.getText()+"','"+productnametf_stockin.getText()+"','"+batchnotf.getText()+"','"+Integer.parseInt(priceperproducttf.getText())+"','"+informtf.getValue()+"','"+Integer.parseInt(quantitytf.getText())+"','"+manufacturedate.getEditor().getText()+"','"+expirydate.getEditor().getText()+"','"+"true"+"')");
                new AlertBoxController().createAlert("Stock Added Successfully","INFORMATION");
            }
            else
                setInfo("Fill Details First");
        }
        catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
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

    public boolean validateStockIn()
    {
        if((companynametf_stockin.getText()).equals("") || (productnametf_stockin.getText()).equals("") || (batchnotf.getText()).equals("") || (priceperproducttf.getText()).equals("") || (quantitytf.getText()).equals("") || (expirydate.getEditor().getText()).equals("") || (manufacturedate.getEditor().getText()).equals(""))
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        informtf.getItems().addAll("Tablet", "Liquid");
        informtf.setValue("Tablet");
    }
}