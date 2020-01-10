package MainWindow.toolbar;

import Customer.AddCustomer;
import MainWindow.AddStock.Stock;
import MainWindow.ViewStock.MainViewStock;
import com.jfoenix.controls.*;
import company.AddCompany;
import company.ViewCompany;
import javafx.fxml.FXML;
import javafx.stage.Stage;

public class toolbarController
{
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

    public void onAddStock() throws Exception {
        Stock stock=new Stock();
        Stage stage=new Stage();
        stock.start(stage);
    }
    public void OnViewCompany()
    {
        ViewCompany vc=new ViewCompany();
        Stage stage = new Stage();
        try {
            vc.start(stage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void setAddCompany()
    {
        AddCompany ac=new AddCompany();
        Stage stage = new Stage();
        try {
            ac.start(stage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void onAddCustomer()
    {
        AddCustomer ad=new AddCustomer();
        Stage st = new Stage();
        try {
            ad.start(st);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void onViewStock()
    {
        Stage stage=new Stage();
        MainViewStock mainViewStock=new MainViewStock();
        try {
            mainViewStock.start(stage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
