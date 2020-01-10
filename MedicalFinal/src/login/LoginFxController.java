package login;
import MainWindow.MainController;
import box.AlertBoxController;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import database.DatabaseManager;
import forget.ForgetFxController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class LoginFxController implements Initializable{

    @FXML
    private JFXButton signup_login;

    @FXML
    private JFXTextField username_login;

    @FXML
    private JFXPasswordField password_login;

    @FXML
    private JFXButton loginbtn_login;

    String un;
    String mn;
    String email;

    Stage window=new Stage();
    public void getCurrentWindow()
    {
        window= (Stage) loginbtn_login.getScene().getWindow();
    }
    public void onLogin()
    {
        Connection con= DatabaseManager.returnConnection();
        try {
            Statement st=con.createStatement();
            boolean userAvail=false;
            ResultSet rstbool=st.executeQuery("select count(*) from Admin where UserName='" + username_login.getText() + "' and Password='" + password_login.getText()+ "'");
            if(rstbool.next())
                if(rstbool.getInt(1)==1)
                    userAvail=true;
            ResultSet rstUserAuth=st.executeQuery("select count(*) from Admin where UserName='" + username_login.getText() + "'");
            boolean userAuth=false;
            if(rstUserAuth.next())
                if(rstUserAuth.getInt(1)==1)
                {
                    userAuth = true;
                }
            if(userAvail && validateDetails())
            {
                ResultSet rst=st.executeQuery("select UserName,MobileNumber,Email from Admin where UserName='" + username_login.getText() + "' and Password='" + password_login.getText()+ "'");
                if(rst.next()) {
                    System.out.println("Hello");
                    un = rst.getString(1);
                    mn = rst.getString(2);
                    email = rst.getString(3);
                    new AlertBoxController().createAlert("Login Successfully","INFORMATION");
//                    new AlertBoxController().createAlert("Login Successfully","INFORMATION");
                    getCurrentWindow();
                    window.close();
                    FXMLLoader Loader = new FXMLLoader();
                    Loader.setLocation (getClass().getResource("/MainWindow/MainFxml.fxml"));
                    Parent root = Loader.load();
                    MainController mc = Loader.getController();
//                  mc.setValue(un,mn,email);
                    Stage stage = new Stage();
                    stage.setScene(new Scene(root));
                    stage.show();
                }
            }
            else {
                if(!validateDetails())
                    new AlertBoxController().createAlert("Fill Details ","ERROR");
                 else
                 {
                     if(userAuth)
                         new AlertBoxController().createAlert("Invalid Password","ERROR");
                     else
                         new AlertBoxController().createAlert("Account doesnot Exists","ERROR");
                 }
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean validateDetails() {
        if (username_login.getText().equals("") || password_login.getText().equals(""))
            return false;
        else
            return true;
    }

    public void setOnSignUp()
    {
        FXMLLoader Loader=new FXMLLoader();
        Loader.setLocation(getClass().getResource("/Sign_Up/SignUpFx.fxml"));
        try {
            Parent par=Loader.load();
            getCurrentWindow();
            window.setScene(new Scene(par));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

/*
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
*/

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        DatabaseManager.createTables("Admin","create table Admin(UserName varchar(20) primary key,MobileNumber varchar(20),Email varchar(20),Password varchar(20))");
    }
    public void setOnForget() throws IOException {
        FXMLLoader Loader=new FXMLLoader();
        Loader.setLocation(getClass().getResource("/forget/ForgetFx.fxml"));
        Parent forg=Loader.load();
        Stage stage=new Stage();
        getCurrentWindow();
        stage.setScene(new Scene(forg));
        ForgetFxController control=Loader.getController();
        control.getUserName(username_login.getText());
        stage.show();
        window.close();
    }

}