package Customer;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
public class RemoveCustomer extends Application
{
    public void start(Stage primaryStage) throws Exception
    {
        Parent remove = FXMLLoader.load(getClass().getResource("RemoveCustomerFx.fxml"));
        primaryStage.setTitle("Remove Customer");
        primaryStage.setScene(new Scene(remove,466,276));
        primaryStage.initModality(Modality.APPLICATION_MODAL);
        primaryStage.show();
    }
    public static void main(String[] args)
    {
        launch(args);
    }
}
