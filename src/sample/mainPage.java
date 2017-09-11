package sample;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.*;

/**
 * Created by deepakchauhan on 14/04/17.
 */
public class mainPage {
    @FXML private JFXButton checkIn;
    @FXML private JFXButton checkOut;
    @FXML private JFXButton logOut;
    public void In(ActionEvent event) throws Exception {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("checkIn.fxml"));
        Scene scene = new Scene(root, 1280, 720);
        scene.getStylesheets().add(getClass().getResource("css/checkIn.css").toExternalForm());
        stage.setTitle("Smart Park - Check In Page");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }
    public void Out(ActionEvent event) throws Exception {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("checkOut.fxml"));
        Scene scene = new Scene(root, 1280, 720);
        scene.getStylesheets().add(getClass().getResource("css/checkOut.css").toExternalForm());
        stage.setTitle("Smart Park - Check In Page");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }
    public void logOut(ActionEvent event) throws Exception {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        Scene scene = new Scene(root, 1280, 720);
        stage.setTitle("Smart Park - Log In Page");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }
}
