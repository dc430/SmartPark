package sample;
import com.jfoenix.controls.*;
import com.jfoenix.transitions.JFXFillTransition;
import javafx.event.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.scene.Scene;
public class Controller implements Initializable{
    @FXML private MediaView media;
    @FXML private JFXTextField username;
    @FXML private JFXPasswordField password;
    @FXML private JFXButton logInButton;
    @FXML private Label errorUsername;
    @FXML private Label errorPassword;
    private MediaPlayer mediaPlayer;
    private static final String url = "carpark timelapse.mp4";
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println(location.toString());
        System.out.println(this.getClass().getResource(url).toExternalForm());
        mediaPlayer = new MediaPlayer(new Media(this.getClass().getResource(url).toExternalForm()));
        mediaPlayer.setAutoPlay(true);
        media.setMediaPlayer(mediaPlayer);
    }
    public void authenticate(ActionEvent event) throws Exception {
        String u = username.getText(), p = password.getText();
        if (u.equalsIgnoreCase("Admin") && p.equals("admin")) {
            errorPassword.setText("");
            errorUsername.setText("");
            Parent root = FXMLLoader.load(getClass().getResource("select.fxml"));/* Exception */
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root, 1280, 720);
            scene.getStylesheets().add(getClass().getResource("css/style.css").toExternalForm());
            stage.setTitle("Smart Park - Main Page");
            stage.setScene(scene);
            stage.show();

        }
        else if (!u.equalsIgnoreCase("admin") && !p.equals("admin")){
            errorUsername.setText("Wrong Username.");
            errorPassword.setText("Wrong Password.");
        }
        else if (!u.equalsIgnoreCase("admin")) {
            errorUsername.setText("Wrong Username.");
            errorPassword.setText("");
        }
        else if (!p.equals("admin")) {
            errorPassword.setText("Wrong Password.");
            errorUsername.setText("");
        }

    }
}
