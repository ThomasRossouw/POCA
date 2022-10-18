package com.example.poca;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Cat extends AppCompatActivity {
    DBHandler db;
    EditText text;
    EditText goal1;
    Button button;
    FirebaseUser userId;

    dbHelper dbhelper = new dbHelper();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cat);


        text = findViewById(R.id.edCatName);
        goal1 = findViewById(R.id.edGoal);
        button = findViewById(R.id.CreateBTN);
        db = new DBHandler(Cat.this);
    }

    public void addItem (View view)
    {
        userId = FirebaseAuth.getInstance().getCurrentUser();
        final String reportUserID = userId.getUid();
        Testcollection test = new Testcollection(text.getText().toString());
        dbhelper.addItem(test);
        Toast.makeText(this, "Item added", Toast.LENGTH_SHORT).show();
    }


    public void addCatalogButton (View view)
    {
        // below line is to get data from all edit text fields.
        String courseName = text.getText().toString();
        String itemGoal = goal1.getText().toString();

        // validating if the text fields are empty or not.
        if (courseName.isEmpty() ) {
            Toast.makeText(Cat.this, "Please enter all the data..", Toast.LENGTH_SHORT).show();
            return;
        }
        if (itemGoal.isEmpty() ) {
            Toast.makeText(Cat.this, "Please enter all the data..", Toast.LENGTH_SHORT).show();
            return;
        }

        // on below line we are calling a method to add new
        // course to sqlite data and pass all our values to it.
        db.addNewCourse(courseName, itemGoal);

        // after adding the data we are displaying a toast message.
        Toast.makeText(Cat.this, "Collection has been added.", Toast.LENGTH_SHORT).show();
        text.setText("");
        goal1.setText("");

    }

    public void cancelCatalogButton (View view)
    {
        Intent intent = new Intent(Cat.this,MainActivity.class);
        startActivity(intent);
    }
}