package forget;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainForget extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception
    {
        Parent forget = FXMLLoader.load(getClass().getResource("ForgetFx.fxml"));
        primaryStage.setTitle("Password Recovery");
        primaryStage.setScene(new Scene(forget,500,450));
        primaryStage.show();
    }
}
