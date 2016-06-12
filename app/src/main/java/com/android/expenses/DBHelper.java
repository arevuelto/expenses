package com.android.expenses;

/**
 * Created by Paola on 12/06/2016.
 */
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper  extends SQLiteOpenHelper {
    //version number to upgrade database version
    //each time if you Add, Edit table, you need to change the
    //version number.
    private static final int DATABASE_VERSION = 4;

    // Database Name
    private static final String DATABASE_NAME = "crud.db";

    public DBHelper(Context context ) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //All necessary tables you like to create will create here

        String CREATE_TABLE_EXPENSE = "CREATE TABLE " + Expense.TABLE  + "("
                + Expense.KEY_ID  + " INTEGER PRIMARY KEY AUTOINCREMENT ,"
                + Expense.KEY_name + " TEXT, "
                + Expense.KEY_prize + " DOUBLE, "
                + Expense.KEY_dateExpense + " TIMESTAMP, "
                + Expense.KEY_detail + " TEXT )";

        db.execSQL(CREATE_TABLE_EXPENSE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed, all data will be gone!!!
        db.execSQL("DROP TABLE IF EXISTS " + Expense.TABLE);

        // Create tables again
        onCreate(db);

    }

}