
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
public class PurchaseData {
    static int orders = 0;
    static double total_purchase = 0.0;
    static double tax_paid = 0.0;
    public static void getDataPurchase() throws SQLException{
        Connection conn = MySQLJDBCUtil.getConnection();
        Statement stmt  = conn.createStatement();
        String sql = "SELECT * from purchase";
        ResultSet rs    = stmt.executeQuery(sql);
        if(rs.next()){
            sql = "select count(id) from sales";
            rs = stmt.executeQuery(sql);
            rs.next();
            orders = rs.getInt(1);
            
            sql = "select sum(inv_value) from sales";
            rs = stmt.executeQuery(sql);
            rs.next();
            total_purchase = rs.getDouble(1);
            
            
            sql = "select sum(tax) from sales";
            rs = stmt.executeQuery(sql);
            rs.next();
            tax_paid = rs.getDouble(1);
            
        }
                
    }
}
