package sample;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.stage.Stage;

import java.sql.*;

/**
 * Created by deepakchauhan on 14/04/17.
 */
public class checkIn {
    @FXML private ToggleButton fourWheeler;
    @FXML private ToggleButton twoWheeler;
    @FXML private ImageView car;
    @FXML private ImageView bike;
    @FXML private JFXTextField vehicleNumber;
    @FXML private JFXTextField date;
    @FXML private JFXTextField time;
    @FXML private JFXButton spotSelection;
    @FXML private Label errorVehicleType;
    @FXML private Label errorDate;
    @FXML private Label errorTime;
    @FXML private Label errorType;
    @FXML private Label reciept;
    @FXML private Label type;
    @FXML private Label number;
    @FXML private Label d;
    @FXML private Label t;
    @FXML private JFXButton r;
    @FXML private Button back;
    public static String n;
    public void authenticate(ActionEvent event) throws Exception {
        int flag = 0;
        String d = date.getText(), t = time.getText();
        n = vehicleNumber.getText();
        String type = "";
        if (fourWheeler.isSelected()) {
            type = "Car";
            errorType.setText("");
            flag = 1;
        }
        else if(twoWheeler.isSelected()) {
            type = "Bike";
            errorType.setText("");
            flag = 1;
        }
        else {
            errorType.setText("Invalid");
            flag = 0;
        }
        /*
        if (n.length() < 4)
                errorVehicleType.setText("Invalid");
        else if(n.length() ==4)
                errorVehicleType.setText("");

        if (d.length()<9)
                errorDate.setText("Invalid");
        else if(d.length() == 9)
                errorDate.setText("");
        if (t.length() < 5)
                errorTime.setText("Invalid");
        else if (t.length() == 5)
                errorTime.setText("");
        */
        if (flag == 1){
            try {
                Connection connection = DriverManager.getConnection("jdbc:mysql://Localhost:8889/details", "root", "root");
                String sql = "INSERT INTO vehicle " + "values (?, ?, ?, ?)";
                PreparedStatement st = connection.prepareStatement(sql);
                st.setString(1, d);
                st.setString(2, t);
                st.setString(3, type);
                st.setString(4, n);
                st.executeUpdate();

            } catch (Exception e) {
                System.out.println(e);
            }
            Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("reciept.fxml"));
            Scene scene = new Scene(root, 1280, 720);
            scene.getStylesheets().add(getClass().getResource("css/r.css").toExternalForm());
            stage.setTitle("Smart Park - Reciept");
            stage.setResizable(false);
            stage.setScene(scene);
            stage.show();
        }
    }
    public void init(ActionEvent event) throws Exception {
        String time, date, typ;
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:8889/details", "root", "root");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM vehicle");
            while (true) {
                resultSet.next();
                if (resultSet.getString("Vehicle Number").equals(n)) {
                    break;
                }

            }
            time = resultSet.getString("T");
            date = resultSet.getString("D");
            typ = resultSet.getString("Vehicle Type");
            t.setText(time);
            d.setText(date);
            type.setText(typ);
            number.setText(n);

        }catch (Exception e) {
            System.out.println("Error :"+e);
        }
    }
    public void back(ActionEvent event) throws Exception {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("select.fxml"));
        Scene scene = new Scene(root, 1280, 720);
        scene.getStylesheets().add(getClass().getResource("css/style.css").toExternalForm());
        stage.setTitle("Smart Park - Check Out Page");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

}
