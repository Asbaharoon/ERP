/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author Dhanush
 */
public class DeleteAccountController implements Initializable {

    @FXML
    private TextField nameInput;
    @FXML
    private TextField emailInput;
    @FXML
    private PasswordField passwordInput;
    @FXML
    private Label passwordError;
    @FXML
    private Label error;
    @FXML
    private Label notFound;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        error.setVisible(false);
        passwordError.setVisible(false);
        notFound.setVisible(false);
        // TODO
    }    


    @FXML
    private void exitButton(MouseEvent event) {
        try {
            Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
            stage.close();
            Stage window = new Stage();
            Parent root;
            root = FXMLLoader.load(getClass().getResource("Account.fxml"));
            Scene scene = new Scene(root);
            window.setScene(scene);
            window.initStyle(StageStyle.UNDECORATED);
            window.show();
        } catch (IOException ex) {
            Logger.getLogger(ModifyTaxRatesController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void confirmButton(MouseEvent event) throws SQLException, InterruptedException {
        String name = nameInput.getText().toLowerCase();
        String email = emailInput.getText().toLowerCase();
        String password = passwordInput.getText();
        if(password.equals(AccountData.password))
        {
            passwordError.setVisible(false);
        if(name.equals("")&&email.equals(""))
        {
            error.setVisible(true);
        }
        else
        {
            error.setVisible(false);
            if(!(name.equals("")))
            {
                Connection conn  = MySQLJDBCUtil.getConnection();
                Statement stmt = conn.createStatement();
                String sql = "Select aid from accounts where name=\""+name+"\"";
                ResultSet rs    = stmt.executeQuery(sql);
                if(rs.next())
                {
                    notFound.setVisible(false);
                    sql = "Delete from accounts where aid=\""+rs.getString("aid").toLowerCase()+"\"";
                    stmt.executeUpdate(sql);
                    SuccessMessageDisplayController.message = "Account removed successfully!";try {
                        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
                        stage.close();
                        Stage window = new Stage();
                        Parent root;
                        root = FXMLLoader.load(getClass().getResource("SuccessMessageDisplay.fxml"));
                        Scene scene = new Scene(root);
                        window.setScene(scene);
                        window.initStyle(StageStyle.UNDECORATED);
                        window.show();
                    } catch (IOException ex) {
                        Logger.getLogger(ModifyTaxRatesController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                else
                {
                    notFound.setVisible(true);
                    nameInput.setText("");
                    emailInput.setText("");
                }
            }
            else
            {
                Connection conn  = MySQLJDBCUtil.getConnection();
                Statement stmt = conn.createStatement();
                String sql = "Select aid from accounts where email=\""+email+"\"";
                ResultSet rs    = stmt.executeQuery(sql);
                if(rs.next())
                {
                    notFound.setVisible(false);
                    sql = "Delete from accounts where email=\""+rs.getString("email").toLowerCase()+"\"";
                    stmt.executeUpdate(sql);
                    SuccessMessageDisplayController.message = "Account removed successfully!";
                }
                else
                {
                    notFound.setVisible(true);
                    nameInput.setText("");
                    emailInput.setText("");
                }
            }
        }
    }
        else{
            passwordError.setVisible(true);
            passwordInput.setText("");
    }
 }
}
