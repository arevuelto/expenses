package com.android.expenses;

import java.util.Date;

/**
 * Created by Paola on 12/06/2016.
 */
public class Expense {
    // Labels table name
    public static final String TABLE = "Expense";

    // Labels Table Columns names
    public static final String KEY_ID = "id";
    public static final String KEY_name = "name";
    public static final String KEY_detail = "detail";
    public static final String KEY_dateExpense = "dateExpense";
    public static final String KEY_prize = "prize";

    // property help us to keep data
    public int expense_ID;
    public String name;
    public String detail;
    public Date dateExpense;
    public double prize;
}