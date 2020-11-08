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
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author Dhanush
 */
public class ModifyTaxRatesController implements Initializable {

    @FXML
    private TextField sgstInput;
    @FXML
    private TextField cgstInput;
    @FXML
    private Text sgstInvalid;
    @FXML
    private Text cgstInvalid;
    @FXML
    private Text cessInvalid;
    @FXML
    private TextField cessInput;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        sgstInvalid.setVisible(false);
        cgstInvalid.setVisible(false);
        cessInvalid.setVisible(false);
        try {
            TaxData.getTaxData();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        sgstInput.setText(String.valueOf(TaxData.sgst));
        cgstInput.setText(String.valueOf(TaxData.cgst));
        sgstInput.setText(String.valueOf(TaxData.sgst));
        cessInput.setText(String.valueOf(TaxData.cess));
    }    

    @FXML
    private void confirmButton(MouseEvent event) throws SQLException {
        String sgst = sgstInput.getText();
        String cgst = cgstInput.getText();
        String cess = cessInput.getText();
        if(isNumeric(sgst)){
            sgstInvalid.setVisible(false);
            if(isNumeric(cgst))
            {
                cgstInvalid.setVisible(false);
            
                if(isNumeric(cess))
                {
                    cessInvalid.setVisible(false);
                    Connection conn = MySQLJDBCUtil.getConnection();
                    Statement stmt  = conn.createStatement();
                    String sql = "UPDATE tax SET sgst="+Double.parseDouble(sgst);
                    stmt.executeUpdate(sql);
                    sql = "UPDATE tax SET cgst="+Double.parseDouble(cgst);
                    stmt.executeUpdate(sql);
                    sql = "UPDATE tax SET igst="+(Double.parseDouble(sgst)+Double.parseDouble(cgst));
                    stmt.executeUpdate(sql);
                    sql = "UPDATE tax SET cess="+Double.parseDouble(cess);
                    stmt.executeUpdate(sql);
                    try {
                    Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
                    stage.close();
                    Stage window = new Stage();
                    Parent root;
                    SuccessMessageDisplayController.message = "Tax Rates updated successfully";
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
                    cessInvalid.setVisible(false);
                }
            }
            else
            {
                cgstInvalid.setVisible(true);
            }
        }
        else{
            sgstInvalid.setVisible(true);
        }
    }

    @FXML
    private void calcelButton(MouseEvent event) {
        try {
            Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
            stage.close();
            Stage window = new Stage();
            Parent root;
            root = FXMLLoader.load(getClass().getResource("Tax.fxml"));
            Scene scene = new Scene(root);
            window.setScene(scene);
            window.initStyle(StageStyle.UNDECORATED);
            window.show();
        } catch (IOException ex) {
            Logger.getLogger(ModifyTaxRatesController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public static boolean isNumeric(String strNum) {
    if (strNum == null) {
        return false;
    }
    try {
        double d = Double.parseDouble(strNum);
    } catch (NumberFormatException nfe) {
        return false;
    }
    return true;
}
}
