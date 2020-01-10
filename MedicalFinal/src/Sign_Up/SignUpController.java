package Sign_Up;
import box.AlertBoxController;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import database.DatabaseManager;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class SignUpController implements  Initializable {

    @FXML
    private JFXPasswordField password_signup;

    @FXML
    private JFXTextField mobilenumber_signup;

    @FXML
    private JFXPasswordField confirmpassword_signup;

    @FXML
    private JFXButton signup_btn;

    @FXML
    private JFXTextField email_signup;

    @FXML
    private JFXTextField username_signup;

    Stage window = new Stage();

    public void onSignUp() {
        Connection con = DatabaseManager.returnConnection();
        try {
            Statement st = con.createStatement();
            ResultSet r = st.executeQuery("select * from Admin where username='" + username_signup.getText() + "'");
            if (!r.next())
            {
                if (validateDetails() && validateEmail() && validateMobileNo() && validatePasswordLength())
                {
                    st.execute("insert into Admin values('" + username_signup.getText() + "','" + mobilenumber_signup.getText() + "','" + email_signup.getText() + "','" + password_signup.getText() + "')");
                    showInfo("Sign Up Successfully");
                    setOnBack();
                }
                else
                    {
                    if (!validateDetails())
                        showError("Enter Details First");
                    else {
                        if (!validateEmail())
                            showError("Email in Form of abc@gmail.com");
                        else {
                            if (!validateMobileNo())
                                showError("Enter Valid Mobile Number");
                            else {
                                if (!validatePassword())
                                    showError("Check password ....Password Mismatch");
                                else
                                {
                                    if(!validatePasswordLength())
                                        showError("Password size should be At least 8");
                                }
                            }
                        }
                    }
                }
            }
            else
                showError("User Already Exists.....Login");
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private boolean validatePasswordLength() {
        if(password_signup.getText().length()>=8)
            return true;
        else
            return false;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        DatabaseManager.createTables("NewLogin", "create table NewLogin(UserName varchar(20) primary key,MobileNumber varchar(20),Email varchar(20),Password varchar(20))");
    }


    public void showInfo(String Content) throws IOException
    {
        new AlertBoxController().createAlert(Content,"INFORMATION");
    }

    public void showError(String Content) throws IOException
    {
        new AlertBoxController().createAlert(Content,"ERROR");
    }


    public boolean validateDetails() {
        if (username_signup.getText().equals("") || mobilenumber_signup.getText().equals("") || email_signup.getText().equals("") || password_signup.getText().equals(""))
            return false;
        else
            return true;
    }

    public boolean validateEmail() {
        String Email = email_signup.getText();
        int emaillength = Email.length();
        int adindex = Email.indexOf("@");
        int dotindex = Email.indexOf(".");
        boolean containsad = false;
        boolean containsdot = false;
        for (int i = 0; i < emaillength; i++) {
            if (Email.charAt(i) == '@')
                containsad = true;
            if (Email.charAt(i) == '.')
                containsdot = true;
        }
        if (Email.charAt(0) != '@' && Email.charAt(0) != '.' && Email.charAt(emaillength - 1) != '.' && Email.charAt(emaillength - 1) != '@' && adindex != (dotindex + 1) && adindex < dotindex && containsad && containsdot)
            return true;
        else
            return false;
    }

    public void getCurrentWindow() {
        window = (Stage) signup_btn.getScene().getWindow();
    }

    public void setOnBack() throws IOException {
        FXMLLoader Loader = new FXMLLoader();
        Loader.setLocation(getClass().getResource("/login/LoginFx.fxml"));
        Parent p = Loader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(p));
        getCurrentWindow();
        stage.show();
        window.close();
    }

    public boolean validatePassword()
    {
        if(password_signup.getText().equals(confirmpassword_signup))
            return true;
        else
            return false;
    }
    public boolean validateMobileNo()
    {
        String mobno = mobilenumber_signup.getText();
        String standNo="0123456789";
        int counter=0;
        int mobLength=mobno.length();
        for(int i=0;i<mobLength;i++)
        {
            for(int j=0;j<standNo.length();j++)
            {
                if(mobno.charAt(i)==standNo.charAt(j))
                    counter++;
            }
        }
        if(counter>=10 && counter<=12)
            return true;
        else
            return false;
    }
}