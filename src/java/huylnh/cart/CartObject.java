/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package huylnh.cart;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author HuyDev
 */
public class CartObject implements Serializable {

    private Map<String, Integer> items;

    public Map<String, Integer> getItems() {
        return items;
    }

    //tao ra ban product trong do co id, kh
    public void addItemToCart(String id) {
        //1.check availble items 
        if (this.items == null) {
            this.items = new HashMap<>();
        }//end items are not existed
        //2. Available, drops items to cart 
        int quantity = 1;
        if (this.items.containsKey(id)) {
            quantity = this.items.get(id) + 1;
        }
        this.items.put(id, quantity);
    }

    public void removeItemFromCart(String id) {
        //1.check available items
        if (this.items == null) {
            return;
        }
        //2. remove item from this cart 
        if (this.items.containsKey(id)) {
            this.items.remove(id);
            if (this.items.isEmpty()) {
                this.items = null;
            }
        }
    }
}