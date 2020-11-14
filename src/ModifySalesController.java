/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
import javafx.scene.control.DatePicker;
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
public class ModifySalesController implements Initializable {

    @FXML
    private TextField inInput;
    @FXML
    private Label idInvalid;
    @FXML
    private Label inputLabel;
    @FXML
    private TextField toInput;
    @FXML
    private TextField descInput;
    @FXML
    private TextField valueInput;
    @FXML
    private TextField unitsInput;
    @FXML
    private Label toInvalid;
    @FXML
    private DatePicker dateInput;
    @FXML
    private Label descInvalid;
    @FXML
    private Label valueInvalid;
    @FXML
    private Label unitInvalid;
    @FXML
    private TextField totalValue;
    @FXML
    private TextField totalTax;
    @FXML
    private Button comfirmButton;
    String id,to,des,date;
    double value,total,tax; 
    int units;
    @FXML
    private Button calculateTax;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        idInvalid.setVisible(false);
        inputLabel.setVisible(false);
        toInput.setVisible(false);
        descInput.setVisible(false);
        valueInput.setVisible(false);
        unitsInput.setVisible(false);
        toInvalid.setVisible(false);
        comfirmButton.setVisible(false);
        dateInput.setVisible(false);
        idInvalid.setVisible(false);
        toInvalid.setVisible(false);
        descInvalid.setVisible(false);
        valueInvalid.setVisible(false);
        unitInvalid.setVisible(false);
        totalValue.setVisible(false);
        totalTax.setVisible(false);
        calculateTax.setVisible(false);
        // TODO
    }    

    @FXML
    private void searchButton(MouseEvent event) throws SQLException {
        String id = inInput.getText();
        Connection conn = MySQLJDBCUtil.getConnection();
       Statement stmt  = conn.createStatement();
       String sql = "SELECT * from sales where id = \""+id+"\"";
       ResultSet rs    = stmt.executeQuery(sql);
       if(rs.next())
       {
           to = rs.getString("company");
           des = rs.getString("descript");
           value = rs.getDouble("inv_value");
           units = rs.getInt("units");
           total = rs.getDouble("total_value");
           tax = rs.getDouble("tax");
           inInput.setEditable(false);
           inputLabel.setVisible(true);
           toInput.setText(to);
           toInput.setVisible(true);
           descInput.setText(des);
           descInput.setVisible(true);
           valueInput.setText(String.valueOf(value));
           valueInput.setVisible(true);
           unitsInput.setText(String.valueOf(units));
           unitsInput.setVisible(true);
           dateInput.setValue(LocalDate.now());
           dateInput.setVisible(true);
           calculateTax.setVisible(true);
       }
       else
       {
           idInvalid.setVisible(true);
       }
    }

    @FXML
    private void calculateTax(MouseEvent event) throws SQLException {
            date = String.valueOf(dateInput.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            to = toInput.getText();
            des = descInput.getText();
           if(!(to.equals("")))
            {
                toInvalid.setVisible(false);
                if(!(des.equals("")))
                {
                    
                    TaxData.getTaxData();
                    descInvalid.setVisible(false);
                    try{
                    value = Double.parseDouble(valueInput.getText());
                    valueInvalid.setVisible(false);
                    units = Integer.parseInt(unitsInput.getText());
                    unitInvalid.setVisible(false);
                    total = value*units;
                    tax = (total*TaxData.sgst)/100+(total*TaxData.cgst)/100+(total*TaxData.cess)/100;
                    totalValue.setText(String.valueOf(total));
                    totalValue.setVisible(true);
                    totalTax.setText(String.valueOf(tax));
                    totalTax.setVisible(true);
                    comfirmButton.setVisible(true);
                    }
                    catch(Exception ex)
                    {
                     valueInvalid.setVisible(true);
                     unitInvalid.setVisible(true);
                    }
                }
                else
                {
                    descInvalid.setVisible(true);
                }
            }
            else
            {
                toInvalid.setVisible(true);
            }
        }

    @FXML
    private void backButton(MouseEvent event) {
        try {
            Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
            stage.close();
            Stage window = new Stage();
            Parent root;
            root = FXMLLoader.load(getClass().getResource("Sales.fxml"));
            Scene scene = new Scene(root);
            window.setScene(scene);
            window.initStyle(StageStyle.UNDECORATED);
            window.show();
        } catch (IOException ex) {
            Logger.getLogger(SalesController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void confirmButton(MouseEvent event) throws SQLException {
        id = inInput.getText();
        Connection conn = MySQLJDBCUtil.getConnection();
                    String sql = "UPDATE sales SET company=\""+to+"\" where id ="+id;
                    Statement stmt  = conn.createStatement();
                    stmt.executeUpdate(sql);
                    sql = "UPDATE sales SET descript=\""+des+"\" where id ="+id;
                    stmt.executeUpdate(sql);
                    sql = "UPDATE sales SET inv_value="+value+" where id ="+id;
                    stmt.executeUpdate(sql);
                    sql = "UPDATE sales SET units ="+units+" where id ="+id;
                    stmt.executeUpdate(sql);
                    sql = "UPDATE sales SET total_value ="+total+" where id ="+id;
                    stmt.executeUpdate(sql);
                    sql = "UPDATE sales SET tax ="+tax+" where id ="+id;
                    stmt.executeUpdate(sql);
                    try {
                    Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
                    stage.close();
                    Stage window = new Stage();
                    Parent root;
                    SuccessMessageDisplayController.message = "Sales record updated successfully";
                    root = FXMLLoader.load(getClass().getResource("SuccessMessageDisplay.fxml"));
                    Scene scene = new Scene(root);
                    window.setScene(scene);
                    window.initStyle(StageStyle.UNDECORATED);
                    window.show();
        } catch (IOException ex) {
            Logger.getLogger(ModifyTaxRatesController.class.getName()).log(Level.SEVERE, null, ex);
        }   
    }
    
}
    

