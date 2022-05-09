/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package huylnh.product;

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
public class ProductDAO implements Serializable {

    private List<ProductDTO> product;

    public List<ProductDTO> getProduct() {
        return product;
    }

    public void loadProduct()
            throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            //1.connect DB
            con = DBHelper.makeConnection();
            if (con != null) {
                //2.create SQL string
                String sql = "Select productid, nameproduct, price, "
                        + "quantity, available "
                        + "From Product "
                        + "Where available = ?";
                //3.create statement
                stmt = con.prepareStatement(sql);
                stmt.setBoolean(1, true);
                //4.execute
                rs = stmt.executeQuery();
                //5.process
                while (rs.next()) {
                    String productid = rs.getString("productid");
                    String nameproduct = rs.getString("nameproduct");
                    float price = rs.getFloat("price");
                    int quantity = rs.getInt("quantity");
                    boolean available = rs.getBoolean("available");
                    ProductDTO dto = new ProductDTO(productid, nameproduct,
                            price, quantity, available);
                    if (this.product == null) {
                        this.product = new ArrayList<>();
                    }
                    product.add(dto);
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

    public ProductDTO getProductById(String id)
            throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
     
        try {
            //1.connect DB
            con = DBHelper.makeConnection();
            if (con != null) {
                //2.create SQL string
                String sql = "Select productid, nameproduct, price, "
                        + "quantity, available "
                        + "From Product "
                        + "Where productid = ? ";
                //3.create statement
                stmt = con.prepareStatement(sql);
                stmt.setString(1, id);
                //4.execute
                rs = stmt.executeQuery();
                //5.process
                if (rs.next()) {
                    String productid = rs.getString("productid");
                    String nameproduct = rs.getString("nameproduct");
                    float price = rs.getFloat("price");
                    int quantity = rs.getInt("quantity");
                    boolean available = rs.getBoolean("available");
                    ProductDTO dto = new ProductDTO(productid, nameproduct,
                            price, quantity, available);
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
}
