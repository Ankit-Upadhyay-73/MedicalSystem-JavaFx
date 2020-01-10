package MainWindow;
import Customer.RemoveStock.RemoveDrug;
import Customer.RemoveCustomer;
import Customer.ViewCustomer;
import MainWindow.Sale.MainSold;
import box.AlertBoxController;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.transitions.hamburger.HamburgerSlideCloseTransition;
import company.RemoveCompany;
import database.DatabaseManager;

import java.io.IOException;
import java.net.URL;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.view.JasperViewer;

public class MainController implements Initializable
{
    @FXML
    private AnchorPane loginAnchor;
    @FXML
    private MenuItem menu_RemoveCustomer;

    @FXML
    private MenuItem menu_RemoveCompany;

    @FXML
    private MenuItem menu_ViewCustomer;

    @FXML
    private JFXButton btn_addStock;

    @FXML
    private JFXButton btn_addCustomer;

    @FXML
    private JFXButton btn_addCompany;

    @FXML
    private JFXButton btn_viewStock;

    @FXML
    private JFXButton btn_viewCompany;

    @FXML
    private JFXTextField searchdrugtf;

    @FXML
    private TableView<Search_View> searchtableview;

    @FXML
    private TableColumn<Search_View, String> productnameclm;

    @FXML
    private TableColumn<Search_View, String> companynameclm;

    @FXML
    private TableColumn<Search_View, Integer> priceclm;

    @FXML
    private TableColumn<Search_View,Date> manufacturedateclm;

    @FXML
    private TableColumn<Search_View, Date> expirydateclm;

    @FXML
    private TableColumn<Search_View, String >availabilityclm;

    @FXML
    private TableColumn<Search_View, String >informclm;

    @FXML
    private JFXDrawer drawer;

    @FXML
    private JFXHamburger hamburger;

    ObservableList<Search_View> obs= FXCollections.observableArrayList();
    HamburgerSlideCloseTransition task;
    JasperViewer jviewer;
    JasperReport jasperReport;
    Map<String,Object> map;
    public void onSearch() throws IOException {
        if((searchdrugtf.getText()).equals(""))
            new AlertBoxController().createAlert("Name Required","ERROR");
        else
        {
            Connection con = DatabaseManager.returnConnection();
            obs.clear();
            try {
                ResultSet rs = con.createStatement().executeQuery("select inform,productName,companyname,price,manufacturedate,expirydate,Availability from AvailableStock where productName='"+searchdrugtf.getText()+"'");
                while (rs.next()) {
                    obs.add(new Search_View(rs.getString("productName"), rs.getString("companyname"), rs.getInt("price"), rs.getString("manufacturedate"), rs.getString("expirydate"), rs.getString("Availability"),rs.getString("InForm")));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            productnameclm.setCellValueFactory(new PropertyValueFactory<>("productName"));
            companynameclm.setCellValueFactory(new PropertyValueFactory<>("companyname"));
            priceclm.setCellValueFactory(new PropertyValueFactory<>("price"));
            manufacturedateclm.setCellValueFactory(new PropertyValueFactory<>("manufacturedate"));
            expirydateclm.setCellValueFactory(new PropertyValueFactory<>("expirydate"));
            informclm.setCellValueFactory(new PropertyValueFactory<>("inform"));
            availabilityclm.setCellValueFactory(new PropertyValueFactory<>("Availability"));
            searchtableview.setItems(obs);
        }
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

    public void onViewCustomer()
    {
        ViewCustomer vc=new ViewCustomer();
        Stage stage = new Stage();
        try {
            vc.start(stage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onRemoveCustomer()
    {
        RemoveCustomer rc=new RemoveCustomer();
        Stage st = new Stage();
        try {
            rc.start(st);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public  void onRemoveCompany()
    {
        RemoveCompany rc=new RemoveCompany();
        Stage st = new Stage();
        try
        {
            rc.start(st);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        DatabaseManager.createTables("AvailableStock","create table AvailableStock(CompanyName varchar(20),ProductName varchar(20),BatchNo varchar(20),Price int(30),InForm varchar(20),Quantity  int(20),ManufactureDate varchar(20),ExpiryDate varchar(20),Availability varchar(20))");
        DatabaseManager.createTables("Company","create table Company(CompanyName varchar(20) unique key,CompanyHolderName varchar(20),CompanyContact varchar(20),CompanyBranch varchar(20))");
        DatabaseManager.createTables("CustomerIn","create table CustomerIn(CustomerName varchar(20) unique key,CustomerId int(20) unique key,CustomerContact varchar(30),CustomerAddress varchar(89))");
        DatabaseManager.createTables("Customer","create table Customer(CustomerName varchar(20),CustomerId int(30),CustomerContact varchar(20),CustomerAddress varchar(20))");
        DatabaseManager.createTables("StockOut","create table StockOut(CompanyName varchar(20),ProductName varchar(20),BatchNo varchar(20),Price int(20),InForm varchar(20),Quantity int(20),ManufactureDate varchar(20),ExpiryDate varchar(20),Availability varchar(20))");
        DatabaseManager.createTables("StockIn","create table StockIn(CompanyName varchar(20),ProductName varchar(20),BatchNo varchar(20),Price int(20),InForm varchar(20),Quantity int(20),ManufactureDate varchar(20),ExpiryDate varchar(20),Availability varchar(20),StockInDate date)");
        DatabaseManager.createTables("Sale","create table sale(CompanyName varchar(20),ProductName varchar(20),BatchNo varchar(20),CustomerName varchar(20),quantity int(20),Price int(20),InForm varchar(20),SaleDate date)");
        DatabaseManager.createTables("Sold","create table sold(CompanyName varchar(20),ProductName varchar(20),BatchNo varchar(20),quantity int(20),Price int(20))");
        try {
            initDrawer();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            loadReport();
        } catch (JRException e) {
            e.printStackTrace();
        }
    }

    private void initDrawer() throws IOException {
        VBox toolbar = FXMLLoader.load(getClass().getResource("/MainWindow/toolbar/drawer.fxml"));
        drawer.setSidePane(toolbar);
        task = new HamburgerSlideCloseTransition(hamburger);
        task.setRate(-1);
    }

    @FXML
    private void clickHamburger() {
        task.setRate(task.getRate()*-1);
        task.play();
        if(drawer.isClosed())
        {
            drawer.open();
            drawer.setMinWidth(200);
        }
        else
        {
            drawer.close();
            drawer.setMinWidth(0);
        }
    }

    public void onRemoveDrug() throws Exception {
        RemoveDrug rd=new RemoveDrug();
        Stage stage=new Stage();
        rd.start(stage);
    }

    public void onSaleClick() throws Exception {
        MainSold sold=new MainSold();
        Stage stage=new Stage();
        sold.start(stage);
    }
    public void onGenerateReport() throws JRException {
        Connection   connect=null;
        JasperPrint jasperPrint=JasperFillManager.fillReport(jasperReport,map,connect);
        jviewer.setVisible(true);
        jviewer = new JasperViewer(jasperPrint, false); // false to avoid closing the main application and will only close the print preview
    }
    public void loadReport() throws JRException {
         jasperReport= JasperCompileManager.compileReport("D:\\prj\\IdeaProjects\\MedicalFinal\\src\\MainWindow\\Blank_A4_1.jrxml");
        Connection connect=null;
        connect = DatabaseManager.returnConnection();
        JRDataSource jrDataSource=new JREmptyDataSource();
        map=new HashMap<String,Object>();
        JasperPrint jasperPrint=JasperFillManager.fillReport(jasperReport,map,connect);
//        JasperExportManager.exportReportToPdfFile(jasperPrint,"C:\\Users\\Abhishek Upadhyay\\JaspersoftWorkspace\\Reports\\simp1.pdf");
        jviewer = new JasperViewer(jasperPrint, false); // false to avoid closing the main application and will only close the print preview
    }
}