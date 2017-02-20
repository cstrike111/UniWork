package com.budgee.budgeev1;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Will on 15/02/2017.
 */

    public class DBHelper extends SQLiteOpenHelper {

    //ItemTable Name/Column Names
    public static final String tableItems = "Items";
    public static final String columnItemID = "_id";
    public static final String columnItemName = "ItemName";
    public static final String columnItemPrice = "ItemPrice";

    //PurchaseTable Name/Column Names
    public static final String tablePurchases = "Purchases";
    public static final String columnPurchaseID = "_id";
    public static final String columnPurchaseDate = "PurchaseDate";

    //BudgetTable Name/Column Names
    public static final String tableBudgets = "Budgets";
    public static final String columnBudgetID = "_id";
    public static final String columnBudgetStartDate = "PurchaseDate";
    public static final String columnBudgetFinDate = "PurchaseDate";

    //CategoryTable Name/Column Names
    public static final String tableCategories = "Categories";
    public static final String columnCategoryID = "_id";
    public static final String columnCategoryName = "CategoryName";

    //Database Name/Version
    private static final String dbName = "Budgee.db";
    private static final int dbVer = 1;

    //ItemTable creation sql statement
    private static final String itemTableCreate = "create table "
            + tableItems + "( " + columnItemID
            + " integer primary key autoincrement, " + columnItemName
            + " text not null " + columnItemPrice + " integer not null check (ItemPrice > 0));";

    //PurchaseTable creation sql statement
    private static final String purchaseTableCreate = "create table "
            + tablePurchases + "( " + columnPurchaseID
            + " integer primary key autoincrement, " + columnPurchaseDate
            + " integer not null);";

    //BudgetTable creation sql statement
    private static final String budgetTableCreate = "create table "
            + tableBudgets + "( " + columnBudgetID
            + " integer primary key autoincrement, " + columnBudgetStartDate
            + " integer not null " + columnBudgetFinDate + " integer not null);";

    //CategoryTable creation sql statement
    private static final String categoryTableCreate = "create table "
            + tableCategories + "( " + columnCategoryID
            + " integer primary key autoincrement, " + columnCategoryName
            + " text not null);";

    public DBHelper(Context context) {
        super(context, dbName, null, dbVer);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(itemTableCreate);
        database.execSQL(purchaseTableCreate);
        database.execSQL(budgetTableCreate);
        database.execSQL(categoryTableCreate);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(DBHelper.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + tableItems);
        onCreate(db);
    }
}