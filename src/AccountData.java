

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
public class AccountData {
    static String aid,name,email,role,company;
    public static void getAccountData() throws SQLException{
        Connection conn = MySQLJDBCUtil.getConnection();
        Statement stmt  = conn.createStatement();
        String sql = "SELECT * from accounts";
        ResultSet rs    = stmt.executeQuery(sql);
        if(rs.next()){
             aid = rs.getString("aid");
             name = rs.getString("name");
             email = rs.getString("email");
             role = rs.getString("acc_role");
             company = rs.getString("company");
        }
}
    
}
