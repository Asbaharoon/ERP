
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Dhanush
 */
public class TaxData {
    static double sgst;
    static double cgst;
    static double igst;
    static double cess;
    static double taxCol;
    static double tax_due;
    public static void getTaxData() throws SQLException{
        Connection conn = MySQLJDBCUtil.getConnection();
        Statement stmt  = conn.createStatement();
        String sql = "SELECT * from tax";
        ResultSet rs    = stmt.executeQuery(sql);
        rs.next();
            sgst = rs.getDouble("sgst");
            cgst = rs.getDouble("cgst");
            igst = rs.getDouble("igst");
            cess = rs.getDouble("cess");
            taxCol = rs.getDouble("taxcol");
        salesData.getData_sales();
        PurchaseData.getDataPurchase();
        tax_due = salesData.total_tax - PurchaseData.totalTaxPaid - taxCol;
    }
    
    
}
