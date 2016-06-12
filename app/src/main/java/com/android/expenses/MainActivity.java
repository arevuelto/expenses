package com.android.expenses;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends ListActivity  implements android.view.View.OnClickListener{

    Button btnAdd,btnGetAll;
    TextView expense_Id;

    @Override
    public void onClick(View view) {
        if (view== findViewById(R.id.btnAdd)){

            Intent intent = new Intent(this,ExpenseDetail.class);
            intent.putExtra("expense_Id",0);
            startActivity(intent);

        }else {

            ExpenseRepo repo = new ExpenseRepo(this);

            ArrayList<HashMap<String, String>> expenseList =  repo.getExpenseList();
            if(expenseList.size()!=0) {
                ListView lv = getListView();
                lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view,int position, long id) {
                        expense_Id = (TextView) view.findViewById(R.id.expense_Id);
                        String expenseId = expense_Id.getText().toString();
                        Intent objIndent = new Intent(getApplicationContext(),ExpenseDetail.class);
                        objIndent.putExtra("expense_Id", Integer.parseInt( expenseId));
                        startActivity(objIndent);
                    }
                });
                ListAdapter adapter = new SimpleAdapter( MainActivity.this,expenseList, R.layout.view_expense_entry, new String[] { "id","name"}, new int[] {R.id.expense_Id, R.id.expense_name});
                setListAdapter(adapter);
            }else{
                Toast.makeText(this,"No expense!",Toast.LENGTH_SHORT).show();
            }

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(this);

        btnGetAll = (Button) findViewById(R.id.btnGetAll);
        btnGetAll.setOnClickListener(this);

    }


}