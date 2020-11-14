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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author Dhanush
 */
public class ViewPurchaseController implements Initializable {

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
    private TableView<TableDataPurchase> purchaseTable;
    @FXML
    private TableColumn<TableDataPurchase,String> orderColumn;
    @FXML
    private TableColumn<TableDataPurchase,String> supplierColumn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        orderColumn.setCellValueFactory(new PropertyValueFactory<>("order"));
        supplierColumn.setCellValueFactory(new PropertyValueFactory<>("supplier"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("desc"));
        valueColumn.setCellValueFactory(new PropertyValueFactory<>("unitValue"));
        unitsColumn.setCellValueFactory(new PropertyValueFactory<>("units"));
        totalColumn.setCellValueFactory(new PropertyValueFactory<>("totalValue"));
        taxColumn.setCellValueFactory(new PropertyValueFactory<>("totalTax"));
        try {
            purchaseTable.setItems(getData());
        } catch (SQLException ex) {
            Logger.getLogger(ViewPurchaseController.class.getName()).log(Level.SEVERE, null, ex);
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

    private ObservableList<TableDataPurchase> getData() throws SQLException {
        ObservableList<TableDataPurchase> data = FXCollections.observableArrayList();
       Connection conn = MySQLJDBCUtil.getConnection();
       Statement stmt  = conn.createStatement();
       String sql = "SELECT * from purchase";
       ResultSet rs    = stmt.executeQuery(sql);
       while(rs.next())
       {
           String id = rs.getString("order_id");
           String supplier = rs.getString("company");
           String des = rs.getString("descript");
           double inv = rs.getDouble("indiv_value");
           int units = rs.getInt("units");
           double total = rs.getDouble("total_value");
           double tax = rs.getDouble("tax_paid");
           data.add(new TableDataPurchase(id,supplier,des,inv,units,total,tax));
       }
       return data;
    }
    
}
