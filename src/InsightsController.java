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
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Side;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author Dhanush
 */
public class InsightsController implements Initializable {

    @FXML
    private Pane paneView;
    @FXML
    private Pane lineView;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        pieChart();
        try {
            lineChart();
        } catch (SQLException ex) {
            Logger.getLogger(InsightsController.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        window.initStyle(StageStyle.UNDECORATED);
        window.show();
    }

    private void pieChart() {
        paneView.getChildren().clear();
         ObservableList<PieChart.Data> list = FXCollections.observableArrayList();
        try {
            salesData.getData_sales();
            PurchaseData.getDataPurchase();
            TaxData.getTaxData();
        } catch (SQLException ex) {
            Logger.getLogger(InsightsController.class.getName()).log(Level.SEVERE, null, ex);
        }
         list.add(new PieChart.Data("Collected",salesData.total_tax));
         list.add(new PieChart.Data("Paid",PurchaseData.totalTaxPaid));
         list.add(new PieChart.Data("Pending",TaxData.tax_due));
         PieChart chart = new PieChart(list);
         chart.setTitle("Tax Data");
         chart.setLabelLineLength(10);
        chart.setLegendSide(Side.LEFT);
        chart.resize(450, 220);
        final Label caption = new Label("");
        caption.setTextFill(Color.DARKORANGE);
        caption.setStyle("-fx-font: 24 arial;");
        for (final PieChart.Data data : chart.getData()) {
        data.getNode().addEventHandler(MouseEvent.MOUSE_CLICKED,new EventHandler<MouseEvent>() {
            @Override public void handle(MouseEvent e) {
                caption.setTranslateX(e.getSceneX());
                caption.setTranslateY(e.getSceneY());
                caption.setText(String.valueOf(data.getPieValue()) + "%");
             }});
         }
        paneView.getChildren().add(chart);
    }

    private void lineChart() throws SQLException {
        lineView.getChildren().clear();
        CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("Number");
        yAxis.setLabel("Amount");
        LineChart<String,Number> lineChart = new LineChart<String,Number>(xAxis,yAxis);
        lineChart.setTitle("Comparing Sales and Purchase");
        lineChart.resize(400,180);
        XYChart.Series series1 = new XYChart.Series();
        series1.setName("Sales");
        Connection conn = MySQLJDBCUtil.getConnection();
        Statement stmt  = conn.createStatement();
        String sql = "SELECT * from sales";
        ResultSet rs = stmt.executeQuery(sql);
        int count = 0;
        while(rs.next())
        {
            double value = rs.getDouble("total_value");
            series1.getData().add(new XYChart.Data(String.valueOf(++count), value));
            
        }
        XYChart.Series series2 = new XYChart.Series();
        series2.setName("Expenditure");
        sql = "SELECT * from purchase";
        rs = stmt.executeQuery(sql);
        count = 0;
        while(rs.next())
        {
            String id = String.valueOf(rs.getInt("order_id"));
            double value = rs.getDouble("total_value");
            series2.getData().add(new XYChart.Data(String.valueOf(++count), value));
        }
        lineChart.getData().addAll(series1, series2);
        lineView.getChildren().add(lineChart);
        System.out.println(series1.getData());
    }
}
  
