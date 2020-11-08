
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
    static int orders;
    static double total_purchase;
    static double tax_paid;
    static double totalTaxPaid;
    static double SumTotalPurchase;
    static double tax_due;
    public static void getDataPurchase() throws SQLException{
        Connection conn = MySQLJDBCUtil.getConnection();
        Statement stmt  = conn.createStatement();
        String sql = "select count(order_id) from purchase";
        ResultSet rs = stmt.executeQuery(sql);
        rs.next();
        orders = rs.getInt(1);
        sql = "select sum(indiv_value) from purchase";
        rs = stmt.executeQuery(sql);
        rs.next();
        SumTotalPurchase = rs.getDouble(1);
        sql = "select sum(tax_paid) from purchase";
        rs = stmt.executeQuery(sql);
        rs.next();
        totalTaxPaid = rs.getDouble(1);
        }
    }
