/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
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
public class PurchaseController implements Initializable {

    @FXML
    private Label total_purchases;
    @FXML
    private Label total_tax_paid;
    @FXML
    private Label orders;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            PurchaseData.getDataPurchase();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        orders.setText(String.valueOf(PurchaseData.orders));
        total_tax_paid.setText(String.valueOf(PurchaseData.totalTaxPaid));
        total_purchases.setText(String.valueOf(PurchaseData.SumTotalPurchase));
        
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
    private void editPurchase(MouseEvent event) {
        try {
            Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
            stage.close();
            Stage window = new Stage();
            Parent root;
            root = FXMLLoader.load(getClass().getResource("ModifyPurchase.fxml"));
            Scene scene = new Scene(root);
            window.setScene(scene);
            window.initStyle(StageStyle.UNDECORATED);
            window.show();
        } catch (IOException ex) {
            Logger.getLogger(PurchaseController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void newPurchase(MouseEvent event) {
        try {
            Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
            stage.close();
            Stage window = new Stage();
            Parent root;
            root = FXMLLoader.load(getClass().getResource("NewPurchase.fxml"));
            Scene scene = new Scene(root);
            window.setScene(scene);
            window.initStyle(StageStyle.UNDECORATED);
            window.show();
        } catch (IOException ex) {
            Logger.getLogger(PurchaseController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @FXML
    private void viewPurchase(MouseEvent event) {
        try {
            Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
            stage.close();
            Stage window = new Stage();
            Parent root;
            root = FXMLLoader.load(getClass().getResource("ViewPurchase.fxml"));
            Scene scene = new Scene(root);
            window.setScene(scene);
            window.initStyle(StageStyle.UNDECORATED);
            window.show();
        } catch (IOException ex) {
            Logger.getLogger(PurchaseController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void removePurchase(MouseEvent event) {
        try {
            Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
            stage.close();
            Stage window = new Stage();
            Parent root;
            root = FXMLLoader.load(getClass().getResource("DeletePurchase.fxml"));
            Scene scene = new Scene(root);
            window.setScene(scene);
            window.initStyle(StageStyle.UNDECORATED);
            window.show();
        } catch (IOException ex) {
            Logger.getLogger(PurchaseController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
