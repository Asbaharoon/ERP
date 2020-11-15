/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.swing.text.StyleConstants.FontConstants;

/**
 * FXML Controller class
 *
 * @author Dhanush
 */
public class DocumentsController implements Initializable {

    @FXML
    private ComboBox<String> comboBox;
    @FXML
    private Label promptLabel;
    @FXML
    private TextField input;
    ObservableList<String> choice = FXCollections.observableArrayList("SALES DATA","PURCHASE DATA");
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        comboBox.setItems(choice);
        comboBox.getSelectionModel().select(0);
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

    @FXML
    private void generatePDF(MouseEvent event) throws DocumentException, FileNotFoundException, BadElementException, IOException, SQLException {
        String ch = comboBox.getValue();

        String dest = "/Users/Dhanush/Desktop/pdf/"; 
        Document document = new Document(PageSize.A4, 0f, 0f, 0f, 0f);
        if(ch.equals("SALES DATA"))
        {
            PdfWriter.getInstance(document,new FileOutputStream(dest+"sales.pdf"));
        }
        else
        {
            PdfWriter.getInstance(document,new FileOutputStream(dest+"purchases.pdf"));
        }
        PdfPTable table = new PdfPTable(8);
        if(ch.equals("SALES DATA"))
        {
            PdfWriter.getInstance(document,new FileOutputStream(dest+"sales.pdf"));
            Connection conn = MySQLJDBCUtil.getConnection();
            Statement stmt  = conn.createStatement();
            String sql = "SELECT * from sales";
            ResultSet rs = stmt.executeQuery(sql);
            PdfPCell cell1 = new PdfPCell(new Paragraph("Invoice"));
            PdfPCell cell2 = new PdfPCell(new Paragraph("Date"));
            PdfPCell cell3 = new PdfPCell(new Paragraph("To"));
            PdfPCell cell4 = new PdfPCell(new Paragraph("Description"));
            PdfPCell cell5 = new PdfPCell(new Paragraph("Unit Value"));
            PdfPCell cell6 = new PdfPCell(new Paragraph("Units"));
            PdfPCell cell7 = new PdfPCell(new Paragraph("Total Value"));
            PdfPCell cell8 = new PdfPCell(new Paragraph("Tax"));
            table.addCell(cell1);
            table.addCell(cell2);
            table.addCell(cell3);
            table.addCell(cell4);
            table.addCell(cell5);
            table.addCell(cell6);
            table.addCell(cell7);
            table.addCell(cell8);
            table.setHeaderRows(1);
            while(rs.next()){
                cell1 = new PdfPCell(new Paragraph(String.valueOf(rs.getInt("id"))));
                cell2 = new PdfPCell(new Paragraph(String.valueOf(rs.getDate("dos"))));
                cell3 = new PdfPCell(new Paragraph(rs.getString("company")));
                cell4 = new PdfPCell(new Paragraph(rs.getString("descript")));
                cell5 = new PdfPCell(new Paragraph(String.valueOf(rs.getDouble("inv_value"))));
                cell6 = new PdfPCell(new Paragraph(String.valueOf(rs.getInt("units"))));
                cell7 = new PdfPCell(new Paragraph(String.valueOf(rs.getDouble("total_value"))));
                cell8 = new PdfPCell(new Paragraph(String.valueOf(rs.getDouble("tax"))));
                table.addCell(cell1);
                table.addCell(cell2);
                table.addCell(cell3);
                table.addCell(cell4);
                table.addCell(cell5);
                table.addCell(cell6);
                table.addCell(cell7);
                table.addCell(cell8);
            }
        }
        else
        {
            PdfWriter.getInstance(document,new FileOutputStream(dest+"purchase.pdf"));
            Connection conn = MySQLJDBCUtil.getConnection();
            Statement stmt  = conn.createStatement();
            String sql = "SELECT * from purchase";
            ResultSet rs = stmt.executeQuery(sql);

            PdfPCell cell1 = new PdfPCell(new Paragraph("Order"));
            PdfPCell cell2 = new PdfPCell(new Paragraph("Date"));
            PdfPCell cell3 = new PdfPCell(new Paragraph("Supplier"));
            PdfPCell cell4 = new PdfPCell(new Paragraph("Description"));
            PdfPCell cell5 = new PdfPCell(new Paragraph("Unit Value"));
            PdfPCell cell6 = new PdfPCell(new Paragraph("Units"));
            PdfPCell cell7 = new PdfPCell(new Paragraph("Total Value"));
            PdfPCell cell8 = new PdfPCell(new Paragraph("Tax"));
            table.addCell(cell1);
            table.addCell(cell2);
            table.addCell(cell3);
            table.addCell(cell4);
            table.addCell(cell5);
            table.addCell(cell6);
            table.addCell(cell7);
            table.addCell(cell8);
            table.setHeaderRows(1);
            while(rs.next()){
                cell1 = new PdfPCell(new Paragraph(String.valueOf(rs.getInt("order_id"))));
                cell2 = new PdfPCell(new Paragraph(String.valueOf(rs.getDate("order_date"))));
                cell3 = new PdfPCell(new Paragraph(rs.getString("company")));
                cell4 = new PdfPCell(new Paragraph(rs.getString("descript")));
                cell5 = new PdfPCell(new Paragraph(String.valueOf(rs.getDouble("indiv_value"))));
                cell6 = new PdfPCell(new Paragraph(String.valueOf(rs.getInt("units"))));
                cell7 = new PdfPCell(new Paragraph(String.valueOf(rs.getDouble("total_value"))));
                cell8 = new PdfPCell(new Paragraph(String.valueOf(rs.getDouble("tax_paid"))));
                table.addCell(cell1);
                table.addCell(cell2);
                table.addCell(cell3);
                table.addCell(cell4);
                table.addCell(cell5);
                table.addCell(cell6);
                table.addCell(cell7);
                table.addCell(cell8);
            }
        }
        document.open();
        document.add(new Paragraph("\n"));
        Image image = Image.getInstance("/Users/Dhanush/NetBeansProjects/ERPM/src/images/logo.png");
        image.setAlignment(1);
        document.add(image);
        Paragraph paragraph = new Paragraph("Enterprise Resource Planning Management System",FontFactory.getFont(FontFactory.TIMES, 32f));
        paragraph.setAlignment(1);
        paragraph.setPaddingTop(50);
        document.add(paragraph);
        document.add(table);
        document.close();
        System.out.println("ITEXT PDF Executed");
    }

    @FXML
    private void back(MouseEvent event) {
    }
    
}
