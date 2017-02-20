package com.budgee.budgeev1;

import java.util.Date;

/**
 * Created by Will on 16/02/2017.
 */

public class Purchase {
    private int purchaseID;
    private Date purchaseDate;

    public int getPurchaseID(){
        return purchaseID;
    }

    public Date getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(Date purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public void setPurchaseID(int purchaseID) {
        this.purchaseID = purchaseID;
    }
}
