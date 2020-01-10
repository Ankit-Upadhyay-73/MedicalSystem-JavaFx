package Customer;
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
import java.util.ResourceBundle;

public class ViewCustomerFXController implements Initializable {
    @FXML
    private TableColumn<Customer_TableView,Integer> customerid_clm;

    @FXML
    private TableColumn<Customer_TableView, String> customeraddress_clm;

    @FXML
    private TableColumn<Customer_TableView,String> customername_clm;

    @FXML
    private TableColumn<Customer_TableView, String> customercontact_clm;

    @FXML
    private TableView<Customer_TableView> customer_view;

    ObservableList<Customer_TableView> ols= FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Connection conn = DatabaseManager.returnConnection();
        try {
            ResultSet rs = conn.createStatement().executeQuery("select CustomerName,CustomerId,CustomerContact,CustomerAddress from CustomerIn");
            while (rs.next()) {
                ols.add(new Customer_TableView(rs.getString("CustomerName"), rs.getInt("CustomerId"), rs.getString("CustomerContact"), rs.getString("CustomerAddress")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        customername_clm.setCellValueFactory(new PropertyValueFactory<>("CustomerName"));
        customerid_clm.setCellValueFactory(new PropertyValueFactory<>("CustomerId"));
        customercontact_clm.setCellValueFactory(new PropertyValueFactory<>("CustomerContact"));
        customeraddress_clm.setCellValueFactory(new PropertyValueFactory<>("CustomerAddress"));
        customer_view.setItems(ols);
    }
}
