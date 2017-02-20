package com.budgee.budgeev1;

/**
 * Created by Will on 20/02/2017.
 */

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class CategoryDataSource {
    private SQLiteDatabase database;
    private DBHelper dbHelper;
    private String[] allColumns = { DBHelper.columnCategoryID,
            DBHelper.columnCategoryName};

    public CategoryDataSource(Context context) {
        dbHelper = new DBHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public Category createCategory(String categoryName){
        ContentValues values = new ContentValues();
        values.put(DBHelper.columnPurchaseDate, categoryName);
        long insertId = database.insert(DBHelper.tableCategories, null, values);
        Cursor cursor = database.query(DBHelper.tableCategories,
                allColumns, DBHelper.columnCategoryID + " = " + insertId, null,
                null, null, null);
        cursor.moveToFirst();
        Category newCategory = cursorToCategory(cursor);
        cursor.close();
        return newCategory;
    }

    public void deleteCategory(Category category) {
        long id = category.getCategoryID();
        System.out.println("Category deleted with id: " + id);
        database.delete(DBHelper.tableCategories,DBHelper.columnCategoryID
                + " = " + id, null);
    }

    public List<Category> getAllCategories() {
        List<Category> categoryList = new ArrayList<Category>();

        Cursor cursor = database.query(DBHelper.tableCategories,
                allColumns, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Category category = cursorToCategory(cursor);
            categoryList.add(category);
            cursor.moveToNext();
        }
        // make sure to close the cursor
        cursor.close();
        return categoryList;
    }

    private Category cursorToCategory(Cursor cursor) {
        Category category = new Category();
        category.setCategoryID(cursor.getInt(0));
        category.setCategoryName(cursor.getString(1));
        return category;
    }
}
