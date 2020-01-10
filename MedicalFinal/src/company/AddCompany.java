package company;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
public class AddCompany extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("AddCompanyFX.fxml"));
        primaryStage.setTitle("Add Company Here");
        primaryStage.setScene(new Scene(root,480,450));
        primaryStage.setResizable(false);
        primaryStage.initModality(Modality.APPLICATION_MODAL);

        primaryStage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}
