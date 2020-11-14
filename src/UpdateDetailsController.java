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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author Dhanush
 */
public class UpdateDetailsController implements Initializable {

    @FXML
    private TextField nameInput;
    @FXML
    private TextField emailInput;
    @FXML
    private TextField roleInput;
    @FXML
    private TextField companyInput;
    @FXML
    private Label nameError;
    @FXML
    private Label emailError;
    @FXML
    private Label companyError;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        nameError.setVisible(false);
        emailError.setVisible(false);
        companyError.setVisible(false);
        // TODO
        nameInput.setText(AccountData.name);
        emailInput.setText(AccountData.email);
        roleInput.setText(AccountData.role);
        companyInput.setText(AccountData.company);
    }    


    @FXML
    private void changePassword(MouseEvent event) {
    }

    @FXML
    private void confirmButton(MouseEvent event) throws SQLException {
        String name = nameInput.getText();
        String email = emailInput.getText();
        String role = roleInput.getText();
        String company = companyInput.getText();
        Connection conn = MySQLJDBCUtil.getConnection();
        Statement stmt  = conn.createStatement();
        if(!(name.equals("")))
        {
            nameError.setVisible(false);
            if(LoginController.isValid(email))
            {
                emailError.setVisible(false);
                if(!(company.equals("")))
                {
                    companyError.setVisible(false);
                    String sql = "UPDATE accounts set name = \""+name+"\" where aid =\""+AccountData.aid+"\"";
                    stmt.executeUpdate(sql);
                    sql = "UPDATE accounts set email = \""+email+"\" where aid =\""+AccountData.aid+"\"";
                    stmt.executeUpdate(sql);
                    sql = "UPDATE accounts set company = \""+company+"\"";
                    stmt.executeUpdate(sql);
                    SuccessMessageDisplayController.message = "Account updated successfully!";
                    try {
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
                        System.out.println(ex);
                    }
                }
                else
                {
                    companyError.setVisible(true);
                }
            }
            else
            {
                emailError.setVisible(true);
            }
        }
        else
        {
            nameError.setVisible(true);
        }
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
    
}
