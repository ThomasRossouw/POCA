package com.example.poca;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ItemData extends AppCompatActivity {
    DBHandler db;

    ArrayList<String> listItem;
    ArrayAdapter adapter;
    ListView listView;
    String itemRef;
    TextView itemName;
    String value;
    ImageView myImg;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_data);

        db = new DBHandler(this);
        listItem = new ArrayList<>();
        listView = findViewById(R.id.lstViewItemData);
        myImg=findViewById(R.id.imgIt);

        itemName=findViewById(R.id.txtItemNameHere);
        value = getIntent().getExtras().getString("data");
        itemRef = getIntent().getExtras().getString("ref");
        Toast.makeText(this, itemRef, Toast.LENGTH_SHORT).show();

        itemName.setText(value);
        viewRefItems();
    }


    private void viewRefItems() {

        Cursor cursor = db.viewItemData(itemRef);

        if (cursor.getCount() == 0) {
            Toast.makeText(this, "No Data To Display", Toast.LENGTH_SHORT).show();
        } else {
            while (cursor.moveToNext()) {
                listItem.add(("Item Name: ").concat("    "+(cursor.getString(2))));
                listItem.add(("Category: ").concat("    "+(cursor.getString(3))));
                listItem.add(("Date Acquired: ").concat("    "+(cursor.getString(4))));
                listItem.add(("Description: ").concat("    "+(cursor.getString(5))));
                listItem.add(("Value:   ZAR").concat(" "+(cursor.getString(6))));
                Bitmap bm = StringToBitMap(cursor.getString(7));
                myImg.setImageBitmap(bm);


                //listItem.add(cursor.getString(3));
            }


            adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listItem);
            listView.setAdapter(adapter);

        }


    }

    public Bitmap StringToBitMap(String encodedString) {
        try {
            byte[] encodeByte = Base64.decode(encodedString, Base64.DEFAULT);
            Bitmap bitmap = BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
            return bitmap;
        } catch (Exception e) {
            e.getMessage();
            return null;
        }
    }


    public void Back (View view)
    {
        Intent intent = new Intent(ItemData.this,MainActivity.class);
        startActivity(intent);
    }

}