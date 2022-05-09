/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package huylnh.orderdetail;

import java.io.Serializable;

/**
 *
 * @author HuyDev
 */
public class OrderDetailDTO implements Serializable{
    private int OrderDetailID;
    private int OrderDetailOrderid;
    private String OrderDetailProductid;
    private int quantity;
    private float total;

    public OrderDetailDTO() {
    }

    public OrderDetailDTO(int OrderDetailID, int OrderDetailOrderid, String OrderDetailProductid, int quantity, float total) {
        this.OrderDetailID = OrderDetailID;
        this.OrderDetailOrderid = OrderDetailOrderid;
        this.OrderDetailProductid = OrderDetailProductid;
        this.quantity = quantity;
        this.total = total;
    }

    
    
    /**
     * @return the OrderDetailID
     */
    public int getOrderDetailID() {
        return OrderDetailID;
    }

    /**
     * @param OrderDetailID the OrderDetailID to set
     */
    public void setOrderDetailID(int OrderDetailID) {
        this.OrderDetailID = OrderDetailID;
    }

    /**
     * @return the OrderDetailProductid
     */
    public String getOrderDetailProductid() {
        return OrderDetailProductid;
    }

    /**
     * @param OrderDetailProductid the OrderDetailProductid to set
     */
    public void setOrderDetailProductid(String OrderDetailProductid) {
        this.OrderDetailProductid = OrderDetailProductid;
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
     * @return the total
     */
    public float getTotal() {
        return total;
    }

    /**
     * @param total the total to set
     */
    public void setTotal(float total) {
        this.total = total;
    }

    /**
     * @return the OrderDetailOrderid
     */
    public int getOrderDetailOrderid() {
        return OrderDetailOrderid;
    }

    /**
     * @param OrderDetailOrderid the OrderDetailOrderid to set
     */
    public void setOrderDetailOrderid(int OrderDetailOrderid) {
        this.OrderDetailOrderid = OrderDetailOrderid;
    }
    
}
