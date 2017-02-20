package com.budgee.budgeev1;

/**
 * Created by Will on 20/02/2017.
 */

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class PurchaseDataSource {
    private SQLiteDatabase database;
    private DBHelper dbHelper;
    private String[] allColumns = { DBHelper.columnPurchaseID,
            DBHelper.columnPurchaseDate};

    public PurchaseDataSource(Context context) {
        dbHelper = new DBHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public Purchase createPurchase(Date purchaseDate){
        ContentValues values = new ContentValues();
        values.put(DBHelper.columnPurchaseDate, purchaseDate.getTime());
        long insertId = database.insert(DBHelper.tablePurchases, null, values);
        Cursor cursor = database.query(DBHelper.tablePurchases,
                allColumns, DBHelper.columnPurchaseID + " = " + insertId, null,
                null, null, null);
        cursor.moveToFirst();
        Purchase newPurchase = cursorToPurchase(cursor);
        cursor.close();
        return newPurchase;
    }

    public void deletePurchase(Purchase purchase) {
        long id = purchase.getPurchaseID();
        System.out.println("Purchase deleted with id: " + id);
        database.delete(DBHelper.tablePurchases,DBHelper.columnPurchaseID
                + " = " + id, null);
    }

    public List<Purchase> getAllPurchases() {
        List<Purchase> purchaseList = new ArrayList<Purchase>();

        Cursor cursor = database.query(DBHelper.tablePurchases,
                allColumns, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Purchase purchase = cursorToPurchase(cursor);
            purchaseList.add(purchase);
            cursor.moveToNext();
        }
        // make sure to close the cursor
        cursor.close();
        return purchaseList;
    }

    private Purchase cursorToPurchase(Cursor cursor) {
        Purchase purchase = new Purchase();
        Date date = new Date((long)cursor.getInt(1) * 1000); //Must convert from int to Date
        purchase.setPurchaseID(cursor.getInt(0));
        purchase.setPurchaseDate(date);
        return purchase;
    }
}
