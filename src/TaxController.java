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
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author Dhanush
 */
public class TaxController implements Initializable {

    @FXML
    private Label total_purchases;
    @FXML
    private Label total_tax_paid;
    @FXML
    private Label total_sales;
    @FXML
    private Label total_tax;
    @FXML
    private Label tax_due;
    @FXML
    private Label sgst;
    @FXML
    private Label cgst;
    @FXML
    private Label igst;
    @FXML
    private Label cess;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            salesData.getData_sales();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        try {
            PurchaseData.getDataPurchase();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        try {
            TaxData.getTaxData();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        total_tax_paid.setText(String.valueOf(PurchaseData.totalTaxPaid));
        total_purchases.setText(String.valueOf(PurchaseData.SumTotalPurchase));
        total_sales.setText(String.valueOf(salesData.total_sales));
        total_tax.setText(String.valueOf(salesData.total_tax));
        sgst.setText(String.valueOf(TaxData.sgst));
        cgst.setText(String.valueOf(TaxData.cgst));
        sgst.setText(String.valueOf(TaxData.sgst));
        igst.setText(String.valueOf(TaxData.igst));
        cess.setText(String.valueOf(TaxData.cess));
        tax_due.setText(String.valueOf(TaxData.tax_due));
    }    
@FXML
    private void overviewButtonClicked(MouseEvent event) throws IOException {
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

    @FXML
    private void salesButtonClicked(MouseEvent event) throws IOException {
        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        stage.close();
        Stage window = new Stage();
        Parent root;
        root = FXMLLoader.load(getClass().getResource("Sales.fxml"));
        Scene scene = new Scene(root);
        window.setScene(scene);
        window.initStyle(StageStyle.UNDECORATED);
        window.show();
    }

    @FXML
    private void putchasesButtonClicked(MouseEvent event) throws IOException {
        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        stage.close();
        Stage window = new Stage();
        Parent root;
        root = FXMLLoader.load(getClass().getResource("Purchase.fxml"));
        Scene scene = new Scene(root);
        window.setScene(scene);
        window.initStyle(StageStyle.UNDECORATED);
        window.show();
    }

    @FXML
    private void insightsButtonClicked(MouseEvent event) throws IOException {
        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        stage.close();
        Stage window = new Stage();
        Parent root;
        root = FXMLLoader.load(getClass().getResource("Insights.fxml"));
        Scene scene = new Scene(root);
        window.setScene(scene);
        window.initStyle(StageStyle.UNDECORATED);
        window.show();
    }

    @FXML
    private void taxButtonClicked(MouseEvent event) throws IOException {
        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        stage.close();
        Stage window = new Stage();
        Parent root;
        root = FXMLLoader.load(getClass().getResource("Tax.fxml"));
        Scene scene = new Scene(root);
        window.setScene(scene);
        window.initStyle(StageStyle.UNDECORATED);
        window.show();
    }

    @FXML
    private void accountButtonClicked(MouseEvent event) throws IOException {
        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        stage.close();
        Stage window = new Stage();
        Parent root;
        root = FXMLLoader.load(getClass().getResource("Account.fxml"));
        Scene scene = new Scene(root);
        window.setScene(scene);
        window.initStyle(StageStyle.UNDECORATED);
        window.show();
    }

    @FXML
    private void documentsButtonClicked(MouseEvent event) throws IOException {
        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        stage.close();
        Stage window = new Stage();
        Parent root;
        root = FXMLLoader.load(getClass().getResource("Documents.fxml"));
        Scene scene = new Scene(root);
        window.setScene(scene);
        window.initStyle(StageStyle.UNDECORATED);
        window.show();
    }

    @FXML
    private void signoutButtonClicked(MouseEvent event) throws IOException {
        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        stage.close();
        Stage window = new Stage();
        Parent root;
        root = FXMLLoader.load(getClass().getResource("Login.fxml"));
        Scene scene = new Scene(root);
        window.setScene(scene);
        window.show();
    }

    @FXML
    private void modifyRates(MouseEvent event) throws IOException {
        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        stage.close();
        Stage window = new Stage();
        Parent root;
        root = FXMLLoader.load(getClass().getResource("ModifyTaxRates.fxml"));
        Scene scene = new Scene(root);
        window.setScene(scene);
        window.initStyle(StageStyle.UNDECORATED);
        window.show();
    }

    @FXML
    private void duesPaid(MouseEvent event) throws SQLException {
        TaxData.taxCol = TaxData.taxCol+TaxData.tax_due;
        Connection conn = MySQLJDBCUtil.getConnection();
        Statement stmt  = conn.createStatement();
        TaxData.tax_due = 0;
        String sql = "UPDATE tax set taxcol = "+TaxData.taxCol;
        stmt.executeUpdate(sql);
        try {
                    Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
                    stage.close();
                    Stage window = new Stage();
                    Parent root;
                    SuccessMessageDisplayController.message = "Thankyou for being a wonderful citizen.";
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
