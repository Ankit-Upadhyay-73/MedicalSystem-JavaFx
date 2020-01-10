package company;

import com.jfoenix.controls.*;
import database.DatabaseManager;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class RemoveCompanyFXController
{
    @FXML
    private JFXTextField remove_companyName;

    @FXML
    private JFXTextField remove_companyBranchArea;

    @FXML
    private JFXButton remove_btn;

    public void  onRemoveComp() throws SQLException {
        if(validateRemoveComp())
        {
            Connection con= DatabaseManager.returnConnection();
            Statement st =con.createStatement();
            boolean flag=false;
            ResultSet rst=st.executeQuery("select CompanyName,CompanyBranch from Company where CompanyName='"+remove_companyName.getText()+"' and CompanyBranch='"+remove_companyBranchArea.getText()+"'");
            while(rst.next())
            {
                if(rst.getString(1).equals(remove_companyName.getText()))
                    flag=true;
            }
            if(flag)
            {
                st.execute("delete from Company where CompanyName='"+remove_companyName.getText()+"' and CompanyBranch='"+remove_companyBranchArea.getText()+"'");
                setInfo("Company Removed Successfully");
            }
            else
            {
                setError("Company with branch doesn't Exist");
            }
        }
    }
    public boolean validateRemoveComp()
    {
        if((remove_companyName.getText()).equals("") || (remove_companyBranchArea.getText()).equals(""))
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
