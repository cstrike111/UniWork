package com.budgee.budgeev1;

/**
 * Created by Will on 16/02/2017.
 */

public class Item {
    private int itemID;
    private String itemName;
    private int itemPrice;

    public int getItemID() {
        return itemID;
    }

    public String getItemName() {
        return itemName;
    }

    public int getItemPrice() {
        return itemPrice;
    }

    public void setItemID(int itemID){
        this.itemID = itemID;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public void setItemPrice(int itemPrice) {
        this.itemPrice = itemPrice;
    }
}
