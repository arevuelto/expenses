package com.android.expenses;

/**
 * Created by Paola on 12/06/2016.
 */
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;

public class ExpenseDetail extends AppCompatActivity implements android.view.View.OnClickListener{

    Button btnSave ,  btnDelete;
    Button btnClose;
    EditText editTextName;
    EditText editTextDetail;
    EditText editTextPrize;
    EditText editTextDateExpense;
    private int _Expense_Id=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expense_detail);

        btnSave = (Button) findViewById(R.id.btnSave);
        btnDelete = (Button) findViewById(R.id.btnDelete);
        btnClose = (Button) findViewById(R.id.btnClose);

        editTextName = (EditText) findViewById(R.id.editTextName);
        editTextDetail = (EditText) findViewById(R.id.editTextDetail);
        editTextPrize = (EditText) findViewById(R.id.editTextPrize);
        editTextDateExpense = (EditText) findViewById(R.id.editTextDateExpense);

        btnSave.setOnClickListener(this);
        btnDelete.setOnClickListener(this);
        btnClose.setOnClickListener(this);


        _Expense_Id =0;
        Intent intent = getIntent();
        _Expense_Id =intent.getIntExtra("expense_Id", 0);
        ExpenseRepo repo = new ExpenseRepo(this);
        Expense expense = new Expense();
        expense = repo.getExpenseById(_Expense_Id);

        editTextPrize.setText(String.valueOf(expense.prize));
        editTextName.setText(expense.name);
        editTextDetail.setText(expense.detail);
        editTextDateExpense.setText(String.valueOf(expense.dateExpense)); //TODO: Parse date
    }



    public void onClick(View view) {
        if (view == findViewById(R.id.btnSave)){
            ExpenseRepo repo = new ExpenseRepo(this);
            Expense expense = new Expense();
            expense.prize= Double.parseDouble(editTextPrize.getText().toString());
            expense.detail= editTextDetail.getText().toString();
            expense.name=editTextName.getText().toString();
            expense.dateExpense= new Date(); //TODO: editTextDateExpense.getText().toString();
            expense.expense_ID=_Expense_Id;

            if (_Expense_Id==0){
                _Expense_Id = repo.insert(expense);

                Toast.makeText(this,"New Expense Insert",Toast.LENGTH_SHORT).show();
            }else{

                repo.update(expense);
                Toast.makeText(this,"Expense Record updated",Toast.LENGTH_SHORT).show();
            }
        }else if (view== findViewById(R.id.btnDelete)){
            ExpenseRepo repo = new ExpenseRepo(this);
            repo.delete(_Expense_Id);
            Toast.makeText(this, "Expense Record Deleted", Toast.LENGTH_SHORT);
            finish();
        }else if (view== findViewById(R.id.btnClose)){
            finish();
        }


    }

}
