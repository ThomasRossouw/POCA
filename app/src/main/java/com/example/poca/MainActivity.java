package com.example.poca;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    DBHandler db;

    ArrayList<String> listItem;
    ArrayAdapter adapter;


    Button btnUpdate;
    //Button btnMovies;
    FloatingActionButton btnNewCatalog;
   // private ProgressBar loadingPB;
    //Button btnBooks;
    //ArrayList<String> listItem;
    //ArrayAdapter adapter;
    ListView listView;
    Integer index;
    String clearText;

    String refName;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new DBHandler(this);



        //btnMovies=findViewById(R.id.btnMovies);
        btnNewCatalog=findViewById(R.id.fbtnAddCatalog);
        //btnUpdate=(Button) findViewById(R.id.btnRefresh);
        listItem = new ArrayList<>();
        listView=findViewById(R.id.listViewHome);
        //btnBooks=findViewById(R.id.btnBooks);

        viewData();


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long l) {

                String text = listView.getItemAtPosition(i).toString();

                clearText = text.replaceAll("[^0-9]", "");
                refName = text.replaceAll("\\d","");

                CharSequence foo = text;
                String bar = foo.toString();
                String desiredString = bar.substring(0,1);





                //Toast.makeText(homeScreen.this, ""+text, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, CatData.class);
                intent.putExtra("data",refName);
                intent.putExtra("ref", clearText);
                startActivity(intent);

            }
        });

        //btnMovies=findViewById(R.id.btnMovies);
        btnNewCatalog = findViewById(R.id.fbtnAddCatalog);
        //  btnUpdate = (Button) findViewById(R.id.btnRefresh);
        listItem = new ArrayList<>();
        listView = findViewById(R.id.listViewHome);


        /*ArrayList<String> list = new ArrayList<>();
        ArrayAdapter adapter = new ArrayAdapter<String>(this, R.layout.list_item, list);
        listView.setAdapter(adapter);

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Catalogs").child("bee");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                    list.add(snapshot.getValue().toString());



                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });*/


    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        // adding a click listener for option selected on below line.

            int id = item.getItemId();
            switch (id) {
                case R.id.idLogOut:
                    // displaying a toast message on user logged out inside on click.
                    Toast.makeText(getApplicationContext(), "User Logged Out", Toast.LENGTH_LONG).show();
                    // on below line we are signing out our user.
                    mAuth.signOut();
                    // on below line we are opening our login activity.
                    Intent i = new Intent(MainActivity.this, Login.class);
                    startActivity(i);
                    this.finish();
                    return true;
                default:
                    return super.onOptionsItemSelected(item);
            }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // on below line we are inflating our menu
        // file for displaying our menu options.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    public void addCatalogButton (View view)
    {
        Intent intent = new Intent(MainActivity.this,Cat.class);
        startActivity(intent);
    }


    public void addItemScreen (View view)
    {
        Intent intent = new Intent(MainActivity.this, MainActivity.class);
        startActivity(intent);
    }


    private void viewData() {

        Cursor cursor = db.viewData();

        if (cursor.getCount()==0)
        {
            Toast.makeText(this, "No Data To Display", Toast.LENGTH_SHORT).show();
        }
        else{
            while (cursor.moveToNext()){
                listItem.add(cursor.getString(0).concat("    "+(cursor.getString(1))));

            }


            adapter = new ArrayAdapter<>(this, android.R.layout.simple_expandable_list_item_1, listItem);
            listView.setAdapter(adapter);
        }
    }










}






