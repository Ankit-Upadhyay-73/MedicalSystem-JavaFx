package Customer;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
public class AddCustomer extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent cust_window = FXMLLoader.load(getClass().getResource("AddCustomerFx.fxml"));
        primaryStage.setTitle("Add Customer Here");
        primaryStage.setScene(new Scene(cust_window,533,445));
        primaryStage.initModality(Modality.APPLICATION_MODAL);
        primaryStage.show();
    }
}
