/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package huylnh.product;

import java.io.Serializable;

/**
 *
 * @author HuyDev
 */
public class ProductDTO implements Serializable{
    private String productid;
    private String nameproduct;
    private float price;
    private int quantity;
    private boolean available;

    public ProductDTO() {
    }

    public ProductDTO(String productid, String nameproduct, float price, int quantity, boolean available) {
        this.productid = productid;
        this.nameproduct = nameproduct;
        this.price = price;
        this.quantity = quantity;
        this.available = available;
    }
    
    
    /**
     * @return the productid
     */
    public String getProductid() {
        return productid;
    }

    /**
     * @param productid the productid to set
     */
    public void setProductid(String productid) {
        this.productid = productid;
    }

    /**
     * @return the nameproduct
     */
    public String getNameproduct() {
        return nameproduct;
    }

    /**
     * @param nameproduct the nameproduct to set
     */
    public void setNameproduct(String nameproduct) {
        this.nameproduct = nameproduct;
    }

    /**
     * @return the price
     */
    public float getPrice() {
        return price;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(float price) {
        this.price = price;
    }

    /**
     * @return the quantity
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * @param quantity the quantity to set
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * @return the available
     */
    public boolean isAvailable() {
        return available;
    }

    /**
     * @param available the available to set
     */
    public void setAvailable(boolean available) {
        this.available = available;
    }
    
    
}
