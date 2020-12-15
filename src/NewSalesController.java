/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
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
public class NewSalesController implements Initializable {

    @FXML
    private TextField inInput;
    @FXML
    private TextField toInput;
    @FXML
    private TextField descInput;
    @FXML
    private TextField valueInput;
    @FXML
    private TextField unitsInput;
    @FXML
    private Label idInvalid;
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
    int value = 0,units = 0;
    String id,date,to,desc;
    double total,tax;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        comfirmButton.setVisible(false);
        dateInput.setValue(LocalDate.now());
        idInvalid.setVisible(false);
        toInvalid.setVisible(false);
        descInvalid.setVisible(false);
        valueInvalid.setVisible(false);
        unitInvalid.setVisible(false);
    }    

    @FXML
    private void calculateTax(MouseEvent event) throws SQLException {
        id = inInput.getText();
        date = String.valueOf(dateInput.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        to = toInput.getText();
        desc = descInput.getText();
        Connection conn = MySQLJDBCUtil.getConnection();
       Statement stmt  = conn.createStatement();
       String sql = "SELECT * from sales where id = "+id;
       ResultSet rs    = stmt.executeQuery(sql);
       if(rs.next())
       {
           idInvalid.setVisible(true);
       }
       else
       {
        if(!(id.equals(""))){
            idInvalid.setVisible(false);
            if(!(to.equals("")))
            {
                toInvalid.setVisible(false);
                if(!(desc.equals("")))
                {
                    
                    TaxData.getTaxData();
                    descInvalid.setVisible(false);
                    try{
                    value = Integer.parseInt(valueInput.getText());
                    valueInvalid.setVisible(false);
                    units = Integer.parseInt(unitsInput.getText());
                    unitInvalid.setVisible(false);
                    total = value*units;
                    tax = (total*TaxData.sgst)/100+(total*TaxData.cgst)/100+(total*TaxData.cess)/100;
                    totalValue.setText(String.valueOf(total));
                    totalTax.setText(String.valueOf(tax));
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
        else
        {
            idInvalid.setVisible(true);
        }
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
                    Connection conn = MySQLJDBCUtil.getConnection();
                    String sql = "INSERT into sales "+"VALUES(?,?,?,?,?,?,?,?)";
                    PreparedStatement pstmt = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
                    pstmt.setString(1,id) ;
                    pstmt.setString(2, date);
                    pstmt.setString(3, to);
                    pstmt.setString(4, desc);
                    pstmt.setString(5, String.valueOf(value));
                    pstmt.setString(6, String.valueOf(units));
                    pstmt.setString(7, String.valueOf(total));
                    pstmt.setString(8, String.valueOf(tax));
                    int rows = pstmt.executeUpdate();
                    Statement stmt  = conn.createStatement();
                    try {
                    Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
                    stage.close();
                    Stage window = new Stage();
                    Parent root;
                    SuccessMessageDisplayController.message = "New Sales record created successfully";
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
