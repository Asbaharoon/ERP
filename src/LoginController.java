/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.KeyEvent;
import javafx.stage.StageStyle;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javafx.fxml.Initializable;
import java.text.SimpleDateFormat;  
import java.util.Date;  
import javafx.scene.text.Text;
/**
 * FXML Controller class
 *
 * @author Dhanush
 */
public class LoginController implements Initializable {

    @FXML
    private TextField TextField_email;
    @FXML
    private PasswordField TextField_password;
    @FXML
    private Label email;
    @FXML
    private Label invaliEmailLabel;
    @FXML
    public Label tip_label;

    /**
     * Initializes the controller class.
     */ 
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        tip_label.setText(Main.tips[0]);
    }    

    @FXML
    private void validateEmail(KeyEvent event) {
        String email = TextField_email.getText();
        if(isValid(email))
            invaliEmailLabel.setText("");
        else
            invaliEmailLabel.setText("Invalid Email");
    }

    @FXML
    private void loginbtn(MouseEvent event) throws IOException, ClassNotFoundException, SQLException {
        String email =TextField_email.getText();
        String password = TextField_password.getText();
        Connection conn = MySQLJDBCUtil.getConnection();
        if(isValid(email)){
            Statement stmt  = conn.createStatement();
            String sql = "SELECT * from accounts where email=\""+email.toLowerCase()+"\" and pass=\""+password+"\"";
            ResultSet rs    = stmt.executeQuery(sql);
            if(rs.next()){
                String aid = rs.getString("aid");
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");  
                Date date = new Date();
                sql = "update accounts set datelogin = \""+formatter.format(date)+"\"where aid=\""+aid+"\"";
                stmt.executeUpdate(sql);
                Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
                stage.close();
                Stage window = new Stage();
                Parent root;
                root = FXMLLoader.load(getClass().getResource("Dashboard.fxml"));
                Scene scene = new Scene(root);
                window.setScene(scene);
                window.initStyle(StageStyle.UNDECORATED);
                window.show();

            }
            else
            {
                System.out.println("Incorrect email or password");
            }
            
    }
    }
    public static boolean isValid(String email)
    {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";

        Pattern pat = Pattern.compile(emailRegex);
        if (email == null)
            return false;
        return pat.matcher(email).matches();
    }
}
