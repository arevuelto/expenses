package com.android.expenses;

/**
 * Created by Paola on 12/06/2016.
 */
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.util.ArrayList;
import java.util.HashMap;

public class ExpenseRepo {
    private DBHelper dbHelper;

    public ExpenseRepo(Context context) {
        dbHelper = new DBHelper(context);
    }

    public int insert(Expense expense) {

        //Open connection to write data
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Expense.KEY_prize, expense.prize);
        values.put(Expense.KEY_detail,expense.detail);
        values.put(Expense.KEY_name, expense.name);
        values.put(Expense.KEY_dateExpense, expense.dateExpense.toString()); //TODO: PARSE DATE

        // Inserting Row
        long expense_Id = db.insert(Expense.TABLE, null, values);
        db.close(); // Closing database connection
        return (int) expense_Id;
    }

    public void delete(int expense_Id) {

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        // It's a good practice to use parameter ?, instead of concatenate string
        db.delete(Expense.TABLE, Expense.KEY_ID + "= ?", new String[] { String.valueOf(expense_Id) });
        db.close(); // Closing database connection
    }

    public void update(Expense expense) {

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(Expense.KEY_prize, expense.prize);
        values.put(Expense.KEY_detail,expense.detail);
        values.put(Expense.KEY_name, expense.name);
        values.put(Expense.KEY_dateExpense, expense.KEY_dateExpense);

        // It's a good practice to use parameter ?, instead of concatenate string
        db.update(Expense.TABLE, values, Expense.KEY_ID + "= ?", new String[] { String.valueOf(expense.expense_ID) });
        db.close(); // Closing database connection
    }

    public ArrayList<HashMap<String, String>>  getExpenseList() {
        //Open connection to read only
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String selectQuery =  "SELECT  " +
                Expense.KEY_ID + "," +
                Expense.KEY_name + "," +
                Expense.KEY_detail + "," +
                Expense.KEY_prize + "," +
                Expense.KEY_dateExpense +
                " FROM " + Expense.TABLE;

        //Expense expense = new Expense();
        ArrayList<HashMap<String, String>> expenseList = new ArrayList<HashMap<String, String>>();

        Cursor cursor = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list

        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> expense = new HashMap<String, String>();
                expense.put("id", cursor.getString(cursor.getColumnIndex(Expense.KEY_ID)));
                expense.put("name", cursor.getString(cursor.getColumnIndex(Expense.KEY_name)));
                expenseList.add(expense);

            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return expenseList;

    }

    public Expense getExpenseById(int Id){
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String selectQuery =  "SELECT  " +
                Expense.KEY_ID + "," +
                Expense.KEY_name + "," +
                Expense.KEY_detail + "," +
                Expense.KEY_prize + "," +
                Expense.KEY_dateExpense +
                " FROM " + Expense.TABLE
                + " WHERE " +
                Expense.KEY_ID + "=?";// It's a good practice to use parameter ?, instead of concatenate string

        int iCount =0;
        Expense expense = new Expense();

        Cursor cursor = db.rawQuery(selectQuery, new String[] { String.valueOf(Id) } );

        if (cursor.moveToFirst()) {
            do {
                expense.expense_ID =cursor.getInt(cursor.getColumnIndex(Expense.KEY_ID));
                expense.name =cursor.getString(cursor.getColumnIndex(Expense.KEY_name));
                expense.detail  =cursor.getString(cursor.getColumnIndex(Expense.KEY_detail));
                expense.prize =cursor.getInt(cursor.getColumnIndex(Expense.KEY_prize));
                //.KEY_dateExpense =cursor.getString(cursor.getColumnIndex(Expense.KEY_dateExpense)); //TODO

            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return expense;
    }

}
