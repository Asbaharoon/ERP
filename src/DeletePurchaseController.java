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
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author Dhanush
 */
public class DeletePurchaseController implements Initializable {

    @FXML
    private TextField idInput;
    @FXML
    private Label idInvalid;
    @FXML
    private Label promptLabel;
    @FXML
    private TableView<TableDataPurchase> purchaseTable;
    @FXML
    private TableColumn<TableDataPurchase,String> orderColumn;
    @FXML
    private TableColumn<TableDataPurchase,String> supplierColumn;
    @FXML
    private TableColumn<TableDataPurchase,String> descriptionColumn;
    @FXML
    private TableColumn<TableDataPurchase,String> valueColumn;
    @FXML
    private TableColumn<TableDataPurchase,String> unitsColumn;
    @FXML
    private TableColumn<TableDataPurchase,String> totalColumn;
    @FXML
    private TableColumn<TableDataPurchase,String> taxColumn;
    @FXML
    private Button proceedButton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        idInvalid.setVisible(false);
        promptLabel.setVisible(false);
        purchaseTable.setVisible(false);
        proceedButton.setVisible(false);
    }    

    @FXML
    private void searchButton(MouseEvent event) throws SQLException {
        String id = idInput.getText();
        Connection conn = MySQLJDBCUtil.getConnection();
        Statement stmt  = conn.createStatement();
        String sql = "SELECT * from purchase where order_id = "+id;
        ResultSet rs    = stmt.executeQuery(sql);
        if(rs.next())
        {
        orderColumn.setCellValueFactory(new PropertyValueFactory<>("order"));
        supplierColumn.setCellValueFactory(new PropertyValueFactory<>("supplier"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("desc"));
        valueColumn.setCellValueFactory(new PropertyValueFactory<>("unitValue"));
        unitsColumn.setCellValueFactory(new PropertyValueFactory<>("units"));
        totalColumn.setCellValueFactory(new PropertyValueFactory<>("totalValue"));
        taxColumn.setCellValueFactory(new PropertyValueFactory<>("totalTax"));
        purchaseTable.setItems(getData(id));
        purchaseTable.setVisible(true);
        promptLabel.setVisible(true);
        idInvalid.setVisible(false);
        proceedButton.setVisible(true);
        }
        else
        {
            idInvalid.setVisible(true);
            promptLabel.setVisible(false);
            purchaseTable.setVisible(false); 
            proceedButton.setVisible(false);
        }
    }

    @FXML
    private void backButton(MouseEvent event) {
        try {
            Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
            stage.close();
            Stage window = new Stage();
            Parent root;
            root = FXMLLoader.load(getClass().getResource("Purchase.fxml"));
            Scene scene = new Scene(root);
            window.setScene(scene);
            window.initStyle(StageStyle.UNDECORATED);
            window.show();
        } catch (IOException ex) {
            Logger.getLogger(SalesController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void confirmDelete(MouseEvent event) throws SQLException {
       String id = idInput.getText();
       Connection conn = MySQLJDBCUtil.getConnection();
       Statement stmt  = conn.createStatement();
       String sql = "DELETE from purchase where order_id = "+id;
       stmt.executeUpdate(sql);
       try {
                    Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
                    stage.close();
                    Stage window = new Stage();
                    Parent root;
                    SuccessMessageDisplayController.message = "Purchase record deleted successfully";
                    root = FXMLLoader.load(getClass().getResource("SuccessMessageDisplay.fxml"));
                    Scene scene = new Scene(root);
                    window.setScene(scene);
                    window.initStyle(StageStyle.UNDECORATED);
                    window.show();
        } catch (IOException ex) {
            Logger.getLogger(ModifyTaxRatesController.class.getName()).log(Level.SEVERE, null, ex);
        }   
    }

    private ObservableList<TableDataPurchase> getData(String id) throws SQLException {
        ObservableList<TableDataPurchase> data = FXCollections.observableArrayList();
       Connection conn = MySQLJDBCUtil.getConnection();
       Statement stmt  = conn.createStatement();
       String sql = "SELECT * from purchase where order_id = "+id;
       ResultSet rs    = stmt.executeQuery(sql);
       while(rs.next())
       {
           String to = rs.getString("company");
           String des = rs.getString("descript");
           double inv = rs.getDouble("indiv_value");
           int units = rs.getInt("units");
           double total = rs.getDouble("total_value");
           double tax = rs.getDouble("tax_paid");
           data.add(new TableDataPurchase(id,to,des,inv,units,total,tax));
       }
       return data;
    }
    
}
