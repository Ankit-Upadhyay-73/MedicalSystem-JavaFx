package forget;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import database.DatabaseManager;
import javafx.application.Application;
import javafx.event.ActionEvent;
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

public class ForgetFxController implements Initializable
{
    @FXML
    private JFXButton resetpasswordbtn;

    @FXML
    private JFXComboBox<String> recovery_combobox;

    @FXML
    private JFXTextField username_recovery;

    @FXML
    private JFXTextField recoverycriteria;

    @FXML
    private JFXPasswordField newpassword_recovery;

    String getVal;
    String UserName;
    public void setOnReset() throws SQLException, IOException
    {
        Connection con= DatabaseManager.returnConnection();
        Statement st =con.createStatement();
        ResultSet rst=null;
        rst = st.executeQuery("select * from Admin where UserName='" + username_recovery.getText() + "' and  " + getVal + " ='" + recoverycriteria.getText() + "'");
        if (rst.next())
            {
                if (validateDetails()) {
                    System.out.println("OK");
                    st.execute("update Admin set password='" + newpassword_recovery.getText() + "' where " + getVal + "='" + recoverycriteria.getText() + "'");
                    showInfo("Password Reset Successfully");
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("/login/LoginFx.fxml"));
                    Parent par = loader.load();
                    Stage window = (Stage) resetpasswordbtn.getScene().getWindow();
                    Stage stage = new Stage();
                    stage.setScene(new Scene(par));
                    stage.show();
                    window.close();
                } else
                    showError("Enter Recovery Criteria");
            }
    }
    private void showError(String Content) {
        Alert al=new Alert(Alert.AlertType.ERROR);
        al.setContentText(Content);
        al.showAndWait();
    }

    private boolean validateDetails() {
        if(username_recovery.getText().equals("") || recoverycriteria.getText().equals("") || newpassword_recovery.getText().equals(""))
            return false;
        else
            return true;
    }

    public void getUserName(String UserName)
    {
        this.UserName=UserName;
        username_recovery.setText(UserName);
    }
    void showInfo(String Content)
    {
        Alert al=new Alert(Alert.AlertType.INFORMATION);
        al.setContentText(Content);
        al.showAndWait();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        recovery_combobox.getItems().addAll("Email","MobileNumber");
    }
    public void setTitle() {
        getVal=recovery_combobox.getValue();
        recoverycriteria.setPromptText(getVal);
        System.out.println(getVal);
    }
}