package company;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import database.DatabaseManager;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;

import javax.xml.crypto.Data;
import java.sql.*;

public class AddCompanyFXController {

    @FXML
    private JFXTextField addcompany_companyname;

    @FXML
    private JFXTextField addcompany_companycontact;

    @FXML
    private JFXButton addcompany_btn;

    @FXML
    private JFXTextField addcompany_companybranch;

    @FXML
    private JFXTextField addcompany_companynholderame;

    public void onAddCompany() throws SQLException {
        Connection con= DatabaseManager.returnConnection();
        Statement st=con.createStatement();
        ResultSet rst=st.executeQuery("select CompanyName,CompanyBranch from Company where CompanyName='"+addcompany_companyname.getText()+"' and CompanyBranch='"+addcompany_companybranch.getText()+"'");
        if(!rst.next()) {
            st.execute("insert into Company values('" + addcompany_companyname.getText() + "','" + addcompany_companynholderame.getText() + "','" + addcompany_companycontact.getText() + "','" + addcompany_companybranch.getText() + "')");
            showInfo("Company Added Successfully");
        }
        else
            showInfo("Company Already Exists");
    }
    public void showInfo(String Content)
    {
        Alert al=new Alert(Alert.AlertType.INFORMATION);
        al.setContentText(Content);
        al.showAndWait();
    }

}