/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package huylnh.orderdetail;


import huylnh.ulti.DBHelper;
import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import javax.naming.NamingException;

/**
 *
 * @author HuyDev
 */
public class OrderDetailDAO implements Serializable {

      public boolean CheckOutOrder(String insertOrder, String username)
            throws SQLException, NamingException {
        Connection con = null;
        CallableStatement stmt = null;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "{call dbo.CheckOutOrder(?,?)}";
                stmt = con.prepareCall(sql);
                stmt.setString(1, insertOrder);
                stmt.setString(2, username);
                int row = stmt.executeUpdate();
                System.out.println(row);
                if(row > 0) {
                    return true;
                }
            }
        } finally {
            if (stmt != null) {
                stmt.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;
    }

}
