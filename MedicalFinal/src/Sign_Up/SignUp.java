package Sign_Up;
import java.sql.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SignUp extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("SignUpFx.fxml"));
        primaryStage.setTitle("Sign Up Here");
        primaryStage.setScene(new Scene(root, 600  , 550));
        primaryStage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}
