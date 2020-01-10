package box;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import de.jensd.fx.glyphs.materialicons.MaterialIcon;
import de.jensd.fx.glyphs.materialicons.MaterialIconView;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class AlertBoxController
{
    public int getAct;
    @FXML
    private Label lblalert;

    @FXML
    private AnchorPane alertAnchor;

    @FXML
    private FontAwesomeIconView fontAlert;

    @FXML
    private MaterialIconView alerticon;

    public void setContent(String content,String type)
    {
        if(type.equals("ERROR"))
        {
            fontAlert.setVisible(false);
            alerticon.setGlyphName("ERROR");
            lblalert.setText(content);
        }
        else
            if(type.equals("INFORMATION"))
            {
                alerticon.setVisible(false);
                fontAlert.setGlyphName("INFO_CIRCLE");
                lblalert.setText(content);
            }
    }
    public void createAlert(String Content,String Type) throws IOException {
        Stage alertWind=new Stage();
        FXMLLoader loader= new FXMLLoader();
        loader.setLocation(getClass().getResource("AlertBox.fxml"));
        Parent getAlertWind=loader.load();
        AlertBoxController al=loader.getController();
        al.setContent(Content,Type);
        alertWind.setScene(new Scene(getAlertWind,403,112));
        alertWind.setResizable(false);
        alertWind.showAndWait();
    }
    public void setOnOk() throws IOException
    {
        Stage st;
        st= (Stage) lblalert.getScene().getWindow();
        st.close();
        getAct=1;
    }
}
