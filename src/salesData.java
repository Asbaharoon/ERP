
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
public class salesData {
    static int invoices;
    static double total_sales;
    static double total_tax ;
    public static void getData_sales() throws SQLException{
        Connection conn = MySQLJDBCUtil.getConnection();
        Statement stmt  = conn.createStatement();
            String sql = "select count(id) from sales";
            ResultSet rs = stmt.executeQuery(sql);
            rs.next();
            invoices = rs.getInt(1);
            sql = "select sum(total_value) from sales";
            rs = stmt.executeQuery(sql);
            rs.next();
            total_sales = rs.getDouble(1);
            sql = "select sum(tax) from sales";
            rs = stmt.executeQuery(sql);
            rs.next();
            total_tax = rs.getDouble(1);
        }
}
