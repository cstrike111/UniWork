package com.budgee.budgeev1;

/**
 * Created by Will on 16/02/2017.
 */

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class ItemDataSource {
    private SQLiteDatabase database;
    private DBHelper dbHelper;
    private String[] allColumns = { DBHelper.columnItemID,
            DBHelper.columnItemName, DBHelper.columnItemPrice};

    public ItemDataSource(Context context) {
        dbHelper = new DBHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public Item createItem(String itemName, int itemPrice){
        ContentValues values = new ContentValues();
        values.put(DBHelper.columnItemName, itemName);
        values.put(DBHelper.columnItemPrice, itemPrice);
        long insertId = database.insert(DBHelper.tableItems, null, values);
        Cursor cursor = database.query(DBHelper.tableItems,
                allColumns, DBHelper.columnItemID + " = " + insertId, null,
                null, null, null);
        cursor.moveToFirst();
        Item newItem = cursorToItem(cursor);
        cursor.close();
        return newItem;
    }

    public void deleteItem(Item item) {
        long id = item.getItemID();
        System.out.println("Item deleted with id: " + id);
        database.delete(DBHelper.tableItems,DBHelper.columnItemID
                + " = " + id, null);
    }

    public List<Item> getAllItems() {
        List<Item> itemList = new ArrayList<Item>();

        Cursor cursor = database.query(DBHelper.tableItems,
                allColumns, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Item item = cursorToItem(cursor);
            itemList.add(item);
            cursor.moveToNext();
        }
        // make sure to close the cursor
        cursor.close();
        return itemList;
    }

    private Item cursorToItem(Cursor cursor) {
        Item item = new Item();
        item.setItemID(cursor.getInt(0));
        item.setItemName(cursor.getString(1));
        item.setItemPrice(cursor.getInt(2));
        return item;
    }
}
