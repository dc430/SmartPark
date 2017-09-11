package sample;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;


import java.sql.*;

/**
 * Created by deepakchauhan on 14/04/17.
 */
public class checkOut {
    @FXML private JFXTextField vehicleNumber;
    @FXML private JFXTextField checkOutTime;
    @FXML private Label charge;
    @FXML private Button checkOut;
    @FXML private Button back;
    public void calculate(ActionEvent event) throws Exception {
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:8889/details", "root", "root");
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM vehicle");
        String n = vehicleNumber.getText();
        String t = checkOutTime.getText();
        String h="", m="";
        String h1="", m1="";
        int cx = 0;
        for (int i = 0; i<t.length(); i++) {
            if(!t.substring(i,i+1).equals(":") && cx == 0)
                h = h.concat(t.substring(i,i+1));
            else if(t.substring(i,i+1).equals(":"))
                cx = 1;
            else if(cx==1)
                m = m.concat(t.substring(i, i+1));
        }
        while(resultSet.next()) {
            if (resultSet.getString("Vehicle Number").equals(n))
                break;
        }
        String t2 = resultSet.getString("T");
        cx = 0;
        for (int i = 0; i<t2.length(); i++) {
            if(!t2.substring(i,i+1).equals(":") && cx == 0)
                h1 = h1.concat(t2.substring(i,i+1));
            else if(t2.substring(i,i+1).equals(":"))
                cx = 1;
            else if(cx==1)
                m1 = m1.concat(t2.substring(i, i+1));
        }
        int hB = Integer.parseInt(h1), mB = Integer.parseInt(m1);
        int hA = Integer.parseInt(h), mA = Integer.parseInt(m), hD;
        if (hA <= 12 && hA > 0)
            hD = (24 - hB) + hA;
        else
            hD = hA - hB;
        if (hD<0)
            hD = hD*(-1);
        int sum = 0;

        String type = resultSet.getString("Vehicle Type");
        while(hD!=0) {
            if (type.equalsIgnoreCase("car")) {
                sum += 20;
                hD--;
            }
            else if (type.equalsIgnoreCase("bike")) {
                sum += 10;
                hD--;
            }
        }
        charge.setText("You have to pay ₹"+sum);
        resultSet.deleteRow();
    }
    public void back(ActionEvent event) throws Exception{
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("select.fxml"));
        Scene scene = new Scene(root, 1280, 720);
        scene.getStylesheets().add(getClass().getResource("css/style.css").toExternalForm());
        stage.setTitle("Smart Park - Main Page");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }
}

