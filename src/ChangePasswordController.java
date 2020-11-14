/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author Dhanush
 */
public class ChangePasswordController implements Initializable {

    @FXML
    private PasswordField old;
    @FXML
    private PasswordField newField;
    @FXML
    private PasswordField confirm;
    @FXML
    private Label oldLabel;
    @FXML
    private Label confirmLabel;
    @FXML
    private Label newLabel;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        oldLabel.setVisible(false);
        newLabel.setVisible(false);
        confirmLabel.setVisible(false);
    }

    @FXML
    private void changePass(MouseEvent event) throws SQLException {
        String oldPass = old.getText();
        String newPass = newField.getText();
        String confirmPass = confirm.getText();
        if(isValid(newPass))
        {
            newLabel.setVisible(false);
            if(newPass.equals(confirmPass))
            {
                confirmLabel.setVisible(false);
                if(oldPass.equals(AccountData.password))
                {
                    oldLabel.setVisible(false);
                    try {
                        Connection conn = MySQLJDBCUtil.getConnection();
                        Statement stmt  = conn.createStatement();
                        String sql = "UPDATE accounts SET pass=\""+newPass+"\" where email = \""+AccountData.email+"\"";
                        stmt.executeUpdate(sql);
                        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
                        stage.close();
                        Stage window = new Stage();
                        Parent root;
                        SuccessMessageDisplayController.message = "Password changed successfully!.";
                        root = FXMLLoader.load(getClass().getResource("SuccessMessageDisplay.fxml"));
                        Scene scene = new Scene(root);
                        window.setScene(scene);
                        window.initStyle(StageStyle.UNDECORATED);
                        window.show();
                    } catch (IOException ex) {
                        Logger.getLogger(ChangePasswordController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                else
                {
                    oldLabel.setVisible(true);
                }
            }
            else
            {
                confirmLabel.setVisible(true);
            }
        }
        else
        {
            newLabel.setVisible(true);
        }
    }

    @FXML
    private void abort(MouseEvent event) {
        try {
            Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
            stage.close();
            Stage window = new Stage();
            Parent root;
            root = FXMLLoader.load(getClass().getResource("Dashboard.fxml"));
            Scene scene = new Scene(root);
            window.setScene(scene);
            window.initStyle(StageStyle.UNDECORATED);
            window.show();
        } catch (IOException ex) {
            Logger.getLogger(ChangePasswordController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public static boolean isValid(String password) 
    { 
  
        // for checking if password length 
        // is between 8 and 15 
        if (!((password.length() >= 8) 
              && (password.length() <= 15))) { 
            return false; 
        } 
  
        // to check space 
        if (password.contains(" ")) { 
            return false; 
        } 
        if (true) { 
            int count = 0; 
  
            // check digits from 0 to 9 
            for (int i = 0; i <= 9; i++) { 
  
                // to convert int to string 
                String str1 = Integer.toString(i); 
  
                if (password.contains(str1)) { 
                    count = 1; 
                } 
            } 
            if (count == 0) { 
                return false; 
            } 
        } 
  
        // for special characters 
        if (!(password.contains("@") || password.contains("#") 
              || password.contains("!") || password.contains("~") 
              || password.contains("$") || password.contains("%") 
              || password.contains("^") || password.contains("&") 
              || password.contains("*") || password.contains("(") 
              || password.contains(")") || password.contains("-") 
              || password.contains("+") || password.contains("/") 
              || password.contains(":") || password.contains(".") 
              || password.contains(", ") || password.contains("<") 
              || password.contains(">") || password.contains("?") 
              || password.contains("|"))) { 
            return false; 
        } 
  
        if (true) { 
            int count = 0; 
  
            // checking capital letters 
            for (int i = 65; i <= 90; i++) { 
  
                // type casting 
                char c = (char)i; 
  
                String str1 = Character.toString(c); 
                if (password.contains(str1)) { 
                    count = 1; 
                } 
            } 
            if (count == 0) { 
                return false; 
            } 
        } 
  
        if (true) { 
            int count = 0; 
  
            // checking small letters 
            for (int i = 90; i <= 122; i++) { 
  
                // type casting 
                char c = (char)i; 
                String str1 = Character.toString(c); 
  
                if (password.contains(str1)) { 
                    count = 1; 
                } 
            } 
            if (count == 0) { 
                return false; 
            } 
        } 
  
        // if all conditions fails 
        return true; 
    } 
}
