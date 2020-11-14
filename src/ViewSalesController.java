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
import javafx.beans.property.SimpleStringProperty;
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
public class ViewSalesController implements Initializable {

    @FXML
    private TableView<TableDataSales> salesTable;
    @FXML
    private TableColumn<TableDataSales,String> invoiceColumn;
    @FXML
    private TableColumn<TableDataSales,String> toColumn;
    @FXML
    private TableColumn<TableDataSales,String> descriptionColumn;
    @FXML
    private TableColumn<TableDataSales,String> valueColumn;
    @FXML
    private TableColumn<TableDataSales,String> unitsColumn;
    @FXML
    private TableColumn<TableDataSales,String> totalColumn;
    @FXML
    private TableColumn<TableDataSales,String> taxColumn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO
            invoiceColumn.setCellValueFactory(new PropertyValueFactory<>("invoice"));
            toColumn.setCellValueFactory(new PropertyValueFactory<>("to"));
            descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("desc"));
            valueColumn.setCellValueFactory(new PropertyValueFactory<>("unitValue"));
            unitsColumn.setCellValueFactory(new PropertyValueFactory<>("units"));
            totalColumn.setCellValueFactory(new PropertyValueFactory<>("totalValue"));
            taxColumn.setCellValueFactory(new PropertyValueFactory<>("totalTax"));
            salesTable.setItems(getData());
        } catch (SQLException ex) {
            Logger.getLogger(ViewSalesController.class.getName()).log(Level.SEVERE, null, ex);
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

    private ObservableList<TableDataSales> getData() throws SQLException {
       ObservableList<TableDataSales> data = FXCollections.observableArrayList();
       Connection conn = MySQLJDBCUtil.getConnection();
       Statement stmt  = conn.createStatement();
       String sql = "SELECT * from sales";
       ResultSet rs    = stmt.executeQuery(sql);
       while(rs.next())
       {
           String id = rs.getString("id");
           String to = rs.getString("company");
           String des = rs.getString("descript");
           double inv = rs.getDouble("inv_value");
           int units = rs.getInt("units");
           double total = rs.getDouble("total_value");
           double tax = rs.getDouble("tax");
           data.add(new TableDataSales(id,to,des,inv,units,total,tax));
       }
       return data;
}
}
