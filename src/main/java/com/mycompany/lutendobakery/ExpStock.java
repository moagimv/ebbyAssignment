/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.lutendobakery;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author S2026987
 */
public class ExpStock {
    
    private List<Item> items = new ArrayList<>();
    
    public void addItem(Item item){
        items.add(item);
    }
    
    public void removeItem(Item item){
        items.remove(item);
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }
    
    public void displayExpireStock(){
        if(items.isEmpty()){
            System.out.println("Empty Expired Stock");
        } else {
            System.out.println("Items -> ");
            for(Item theItem: items){
                System.err.println(theItem.getName() + " of " + theItem.getBatch() + " with expiry date of " + theItem.getExpiryDate().getDate());
            }
        }
    }
    
}
