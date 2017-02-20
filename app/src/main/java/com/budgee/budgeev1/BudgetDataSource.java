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

public class BudgetDataSource {
    private SQLiteDatabase database;
    private DBHelper dbHelper;
    private String[] allColumns = { DBHelper.columnBudgetID,
            DBHelper.columnBudgetStartDate, DBHelper.columnBudgetFinDate};

    public BudgetDataSource(Context context) {
        dbHelper = new DBHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public Budget createBudget(Date budgetStartDate, Date budgetFinDate){
        ContentValues values = new ContentValues();
        values.put(DBHelper.columnPurchaseDate, budgetStartDate.getTime());
        values.put(DBHelper.columnPurchaseDate, budgetFinDate.getTime());
        long insertId = database.insert(DBHelper.tableBudgets, null, values);
        Cursor cursor = database.query(DBHelper.tableBudgets,
                allColumns, DBHelper.columnBudgetID + " = " + insertId, null,
                null, null, null);
        cursor.moveToFirst();
        Budget newBudget = cursorToBudget(cursor);
        cursor.close();
        return newBudget;
    }

    public void deleteBudget(Budget budget) {
        long id = budget.getBudgetID();
        System.out.println("Budget deleted with id: " + id);
        database.delete(DBHelper.tablePurchases,DBHelper.columnBudgetID
                + " = " + id, null);
    }

    public List<Budget> getAllBudgets() {
        List<Budget> budgetList = new ArrayList<Budget>();

        Cursor cursor = database.query(DBHelper.tableBudgets,
                allColumns, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Budget budget = cursorToBudget(cursor);
            budgetList.add(budget);
            cursor.moveToNext();
        }
        // make sure to close the cursor
        cursor.close();
        return budgetList;
    }

    private Budget cursorToBudget(Cursor cursor) {
        Budget budget = new Budget();
        Date startDate = new Date((long)cursor.getInt(1) * 1000);
        Date finDate = new Date((long)cursor.getInt(2) * 1000);
        budget.setBudgetID(cursor.getInt(0));
        budget.setBudgetStartDate(startDate);
        budget.setBudgetFinishDate(finDate);
        return budget;
    }
}
