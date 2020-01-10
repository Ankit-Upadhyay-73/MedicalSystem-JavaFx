package company;
import database.DatabaseManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class ViewCompanyFXController implements Initializable
{
    @FXML
    TableColumn<ViewCompany_TableView, String> viewcompany_companyname;

    @FXML
    TableColumn<ViewCompany_TableView, String> viewcompany_companyholdername;

    @FXML
    TableColumn<ViewCompany_TableView, String> viewcompany_companycontact;

    @FXML
    TableColumn<ViewCompany_TableView, String> viewcompany_companybranch;

    @FXML
    TableView<ViewCompany_TableView> viewcompany_view;

    ObservableList <ViewCompany_TableView> obcom= FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Connection con= DatabaseManager.returnConnection();
        obcom.clear();
        try
        {
            ResultSet rst=con.createStatement().executeQuery("select CompanyName,CompanyHolderName,CompanyContact,CompanyBranch from Company");
            while(rst.next())
                obcom.add(new ViewCompany_TableView(rst.getString(1), rst.getString(2), rst.getString(3), rst.getString(4)));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        viewcompany_companyname.setCellValueFactory(new PropertyValueFactory<>("CompanyName"));
        viewcompany_companyholdername.setCellValueFactory(new PropertyValueFactory<>("CompanyHolderName"));
        viewcompany_companycontact.setCellValueFactory(new PropertyValueFactory<>("CompanyContact"));
        viewcompany_companybranch.setCellValueFactory(new PropertyValueFactory<>("CompanyBranch"));
        viewcompany_view.setItems(obcom);
    }
}
