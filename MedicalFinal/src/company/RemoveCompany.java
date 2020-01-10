package company;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import static javafx.application.Application.launch;
public class RemoveCompany
{
    public void start(Stage primaryStage) throws Exception
    {
        Parent remove = FXMLLoader.load(getClass().getResource("RemoveCompanyFx.fxml"));
        primaryStage.setTitle("Remove Company");
        primaryStage.setScene(new Scene(remove,466,276));
        primaryStage.initModality(Modality.APPLICATION_MODAL);
        primaryStage.show();
    }
    public static void main(String[] args)
    {
        launch(args);
    }
}
