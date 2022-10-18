package com.example.poca;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class CatData extends AppCompatActivity {

    ImageButton btnNewDataItem;
    //ImageButton btnReturnHome;
    TextView catalogName;
    DBHandler db;
    String test;

    ArrayList<String> listItem;
    ArrayAdapter adapter;
    ListView listView;
    //Integer myNum;
    String catRef;
    String clearText;
    String refName;
    TextView goalData;
    ProgressBar progressBar;
    int goal;
    int current;
    String nameref;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cat_data);

        btnNewDataItem = findViewById(R.id.btnAddNewDataItem);

        catalogName = findViewById(R.id.txtCatNameHere);

        goalData = findViewById(R.id.txtGoalView);

        db = new DBHandler(this);
        String value = getIntent().getExtras().getString("data");
        catRef = getIntent().getExtras().getString("ref");
        nameref = value;

       CatSelected selectedCat = new CatSelected();
        test = selectedCat.getSelectedCat();
        catalogName.setText(value);

        listItem = new ArrayList<>();
        listView = findViewById(R.id.lstViewItems);

        progressBar = findViewById(R.id.progressBar);

        Toast.makeText(this, value.toString(), Toast.LENGTH_SHORT).show();

        viewRefItems();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long l) {

                String text = listView.getItemAtPosition(i).toString();
                clearText = text.replaceAll("[^0-9]", "");
                refName = text.replaceAll("\\d", "");
                CatSelected selectedCat = new CatSelected();
                selectedCat.setSelectedCat(text);
                Toast.makeText(CatData.this, refName, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(CatData.this, ItemData.class);
                intent.putExtra("data", refName);
                intent.putExtra("ref", clearText);
                startActivity(intent);

            }
        });


    }

    public void returnToHome(View view) {
        Intent intent = new Intent(CatData.this, MainActivity.class);
        startActivity(intent);
    }

    public void newCatalogDataInput(View view) {
        Intent intent = new Intent(CatData.this, Items.class);
        intent.putExtra("ref", catRef);
        startActivity(intent);
    }



    private void viewRefItems() {

        Cursor cursor = db.viewSpecificItems(catRef);
        Cursor cursor1 = db.viewDataGoal(catRef);



        if (cursor.getCount() == 0) {
            Toast.makeText(this, "No Data To Display", Toast.LENGTH_SHORT).show();
        } else {
            while (cursor.moveToNext()) {
                listItem.add(cursor.getString(0).concat("    " + (cursor.getString(2))));

            }


            adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listItem);
            listView.setAdapter(adapter);

            if (cursor1.getCount() == 0) {
                Toast.makeText(this, "No Catalog goal to display", Toast.LENGTH_SHORT).show();
            } else {
                while (cursor1.moveToNext()) {

                    int itemCount = listView.getAdapter().getCount();
                    String quantity = String.valueOf(itemCount);
                    goalData.setText(quantity.concat(" / " + (cursor1.getString(2))));
                    progressBar.setMax(cursor1.getInt(2));
                    progressBar.setProgress(itemCount);

                    goal = cursor1.getInt(2);
                    current = itemCount;
                }

            }


        }

    }


    public void toGraph(View view) {
        if (current == 0) {
            Toast.makeText(this, "Please populate collection..", Toast.LENGTH_SHORT).show();
        } else {
            Intent intent = new Intent(CatData.this, Graph.class);
            intent.putExtra("goal", goal);
            intent.putExtra("current", current);
            intent.putExtra("name", nameref);
            startActivity(intent);
        }
    }
}