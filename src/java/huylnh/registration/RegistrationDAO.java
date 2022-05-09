/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package huylnh.registration;

import huylnh.ulti.DBHelper;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;

/**
 *
 * @author HuyDev
 */
public class RegistrationDAO implements Serializable {

    public boolean checkLogin(String username, String password)
            throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            //1.connect DB
            con = DBHelper.makeConnection();
            if (con != null) {
                //2.create SQL string
                String sql = "Select username, password "
                        + "From Registration "
                        + "Where username = ? and password = ?";
                //3.create statement
                stmt = con.prepareStatement(sql);
                stmt.setString(1, username);
                stmt.setString(2, password);
                //4.execute
                rs = stmt.executeQuery();
                //5.process
                if (rs.next()) {
                    return true;
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stmt != null) {
                stmt.close();
            }
            if (con != null) {
                con.close();
            }

        }

        return false;
    }
    private List<RegistrationDTO> account;

    public List<RegistrationDTO> getAccount() {
        return account;
    }

    public RegistrationDTO SearchOneUsername(String username)
            throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            //1.connect DB
            con = DBHelper.makeConnection();
            if (con != null) {
                //2.Create SQL String
                String sql = "Select password,lastname, isAdmin "
                        + "From Registration "
                        + "Where username = ?";

                //3.Create SQL Statement
                stmt = con.prepareStatement(sql);
                stmt.setString(1, username);
                //4. Execute Query
                rs = stmt.executeQuery();
                //5. Process Resultset

                if (rs.next()) {
                    String password = rs.getString("password");
                    String lastName = rs.getString("lastname");
                    boolean role = rs.getBoolean("isAdmin");

                    RegistrationDTO dto = new RegistrationDTO(username, password, lastName, role);
                    return dto;
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stmt != null) {
                stmt.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return null;
    }

    public void searchLastName(String searchValue, String pk)
            throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            //1.connect DB
            con = DBHelper.makeConnection();
            if (con != null) {
                //2.Create SQL String
                String sql = "Select username, password, lastname, isAdmin "
                        + "From Registration "
                        + "Where lastname Like ? and Not username = ?";

                //3.Create SQL Statement
                stmt = con.prepareStatement(sql);
                stmt.setString(1, "%" + searchValue + "%");
                stmt.setString(2, pk);
                //4. Execute Query
                rs = stmt.executeQuery();
                //5. Process Resultset

                while (rs.next()) {
                    String username = rs.getString("username");
                    String password = rs.getString("password");
                    String lastName = rs.getString("lastname");
                    boolean role = rs.getBoolean("isAdmin");

                    RegistrationDTO dto = new RegistrationDTO(username, password, lastName, role);
                    if (this.account == null) {
                        this.account = new ArrayList<>();
                    }
                    this.account.add(dto);
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stmt != null) {
                stmt.close();
            }
            if (con != null) {
                con.close();
            }
        }

    }

    public boolean deleteAccount(String pk)
            throws SQLException, NamingException {///tra ve so dong hieu luc 
        Connection con = null;
        PreparedStatement stmt = null;

        try {
            //connect DB
            con = DBHelper.makeConnection();
            if (con != null) {
                //create SQL string
                String sql = "Delete From Registration "
                        + "Where username = ?";
                //create Statement
                stmt = con.prepareStatement(sql);
                stmt.setString(1, pk);
                //Execute Query
                int row = stmt.executeUpdate();
                //process result
                if (row > 0) {
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

    public boolean updateAccount(String username, String lastname, boolean admin)
            throws SQLException, NamingException {///tra ve so dong hieu luc 
        Connection con = null;
        PreparedStatement stmt = null;

        try {
            //connect DB
            con = DBHelper.makeConnection();
            if (con != null) {
                //create SQL string

                String sql = "Update Registration "
                        + "Set lastname = ?, isAdmin = ? "
                        + "Where username = ?";
                //create Statement
                stmt = con.prepareStatement(sql);
                stmt.setString(1, lastname);
                stmt.setBoolean(2, admin);
                stmt.setString(3, username);

                //Execute Query
                int row = stmt.executeUpdate();
                //process result
                if (row > 0) {
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

    public boolean createAccount(RegistrationDTO dto)
            throws SQLException, NamingException {///tra ve so dong hieu luc 
        if (dto == null) {
            return false;
        }//end dto is not existed

        Connection con = null;
        PreparedStatement stmt = null;

        try {
            //connect DB
            con = DBHelper.makeConnection();
            if (con != null) {
                //create SQL string
                String sql = "Insert Into Registration("
                        + "username, password, lastname, isAdmin"
                        + ") Values(?, ?, ?, ?)";
                //create Statement
                stmt = con.prepareStatement(sql);
                stmt.setString(1, dto.getUsername());
                stmt.setString(2, dto.getPassword());
                stmt.setString(3, dto.getLastname());
                stmt.setBoolean(4, dto.isRole());

                //Execute Query
                int row = stmt.executeUpdate();
                //process result
                if (row > 0) {
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
