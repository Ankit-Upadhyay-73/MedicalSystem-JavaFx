package MainWindow.ViewStock;

import MainWindow.Search_View;
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
import java.util.Date;
import java.util.ResourceBundle;

public class ViewStockController implements Initializable
{
    ObservableList<Search_View> obs= FXCollections.observableArrayList();
    @FXML
    private TableView<Search_View> tableview;

    @FXML
    private TableColumn<Search_View, String > productnameclm_StockView;

    @FXML
    private TableColumn<Search_View, String > companynameclm_StockView;

    @FXML
    private TableColumn<Search_View, Integer > priceclm_StockView;

    @FXML
    private TableColumn<Search_View, Date> manufacturedateclm_StockView;

    @FXML
    private TableColumn<Search_View,Date  > expirydateclm_StockView;

    @FXML
    private TableColumn<Search_View, String > informclm_StockView;

    @FXML
    private TableColumn<Search_View, String > availabilityclm_StockView;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Connection con = DatabaseManager.returnConnection();
        obs.clear();
        try {
            ResultSet rs = con.createStatement().executeQuery("select inform,productName,companyname,price,manufacturedate,expirydate,Availability from AvailableStock");
            while (rs.next()) {
                obs.add(new Search_View(rs.getString("productName"), rs.getString("companyname"), rs.getInt("price"), rs.getString("manufacturedate"), rs.getString("expirydate"), rs.getString("Availability"), rs.getString("inform")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        productnameclm_StockView.setCellValueFactory(new PropertyValueFactory<>("productName"));
        companynameclm_StockView.setCellValueFactory(new PropertyValueFactory<>("companyname"));
        priceclm_StockView.setCellValueFactory(new PropertyValueFactory<>("price"));
        manufacturedateclm_StockView.setCellValueFactory(new PropertyValueFactory<>("manufacturedate"));
        expirydateclm_StockView.setCellValueFactory(new PropertyValueFactory<>("expirydate"));
        expirydateclm_StockView.setCellValueFactory(new PropertyValueFactory<>("expirydate"));
        informclm_StockView.setCellValueFactory(new PropertyValueFactory<>("inform"));
        availabilityclm_StockView.setCellValueFactory(new PropertyValueFactory<>("Availability"));
        tableview.setItems(obs);
    }
}

