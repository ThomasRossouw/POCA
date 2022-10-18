package com.example.poca;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.ByteArrayOutputStream;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

public class Items extends AppCompatActivity {

    Button btnReturnHome;
    Button submitItem;
    ImageButton btnCamera;
    ImageView viewImage;

    DatabaseReference mDatabase;
    DatabaseReference mUserRef;
    FirebaseUser userId;
    DatabaseReference ReportUserDatabase;


    EditText itemName;
    EditText itemCategory;
    View itemDate;
    EditText itemDesc;
    EditText itemValue;
    String itemImage;
    String catalogReference;
    String text;
    String valueItem;
    String imageData;
    ImageView pic;
    String cat_ref_SQL;
    String catRef1;


    private DatePicker datePicker;
    private Calendar calendar;
    private TextView dateView;
    private int year, month, day;



    dbHelper dbhelper = new dbHelper();
    DBHandler db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_items);

        btnReturnHome=findViewById(R.id.btnCancelItemSubmission);
        btnCamera = findViewById(R.id.camImageButton);
        viewImage = findViewById(R.id.camImageView);

        submitItem = findViewById(R.id.btnSubItem);

        itemName = findViewById(R.id.txtItemName);
        itemCategory=findViewById(R.id.txtItemCategory);
        //itemDate=findViewById(R.id.txtDate);
        itemDesc=findViewById(R.id.txtItemDesc);

        itemValue = findViewById(R.id.txtValue);

        dateView = (TextView) findViewById(R.id.txtDate);
        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);

        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        showDate(year, month+1, day);

        db = new DBHandler(Items.this);


        mDatabase = FirebaseDatabase.getInstance().getReference();

        mUserRef = mDatabase.child("Testcollection").child("Confirmation");

        text ="Confirmation";

        pic = findViewById(R.id.camImageView);
        catRef1 = getIntent().getExtras().getString("ref");

        cat_ref_SQL = catRef1;




    }

    public void captureImage (View view)
    {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent,0);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        try {

            super.onActivityResult(requestCode, resultCode, data);

            Bitmap bm = (Bitmap) data.getExtras().get("data");
            viewImage.setImageBitmap(bm);
            getBytes();
            itemImage = getBytes().toString();
            Toast.makeText(this, "Image Saved", Toast.LENGTH_SHORT).show();

        }

        catch (Exception e)
        {
            Toast.makeText(this, "Unable to take pic", Toast.LENGTH_SHORT).show();
        }
    }


    @SuppressWarnings("deprecation")
    public void setDate(View view) {
        showDialog(999);
        Toast.makeText(getApplicationContext(), "Pick a date ..",
                Toast.LENGTH_SHORT)
                .show();
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        // TODO Auto-generated method stub
        if (id == 999) {
            return new DatePickerDialog(this,
                    myDateListener, year, month, day);
        }
        return null;
    }

    private DatePickerDialog.OnDateSetListener myDateListener = new
            DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker arg0,
                                      int arg1, int arg2, int arg3) {
                    // TODO Auto-generated method stub
                    // arg1 = year
                    // arg2 = month
                    // arg3 = day
                    showDate(arg1, arg2+1, arg3);
                }
            };

    private void showDate(int year, int month, int day) {
        dateView.setText(new StringBuilder().append(day).append("/")
                .append(month).append("/").append(year));
    }










    private byte[] getBytes() {

        try {
            Bitmap bitmap = ((BitmapDrawable) viewImage.getDrawable()).getBitmap();
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
            byte[] bytesData = stream.toByteArray();
            imageData = Base64.encodeToString(bytesData, Base64.DEFAULT);;
            stream.close();
            return bytesData;
        } catch(Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void addItem (View view)
    {

        dbhelper.addCatItem(itemName.getText().toString(),itemCategory.getText().toString(),dateView.getText().toString());
        Toast.makeText(this, "Item added", Toast.LENGTH_SHORT).show();
    }




    public void returnToHome(View view)
    {
        Intent intent = new Intent(Items.this,MainActivity.class);
        startActivity(intent);
    }


    public void addCatalogButton (View view)
    {
        // below line is to get data from all edit text fields.
        String cat_id = cat_ref_SQL;
        String itemUserName = itemName.getText().toString();
        String itemCatUser = itemDesc.getText().toString();
        String itemDateUser = dateView.getText().toString();
        String itemDescUser = itemDesc.getText().toString();
        String itemValueUser = itemValue.getText().toString();
        String itemImageUser = imageData;


        // validating if the text fields are empty or not.
        if (itemUserName.isEmpty()& itemCatUser.isEmpty()& itemDateUser.isEmpty()&itemDescUser.isEmpty()&itemValueUser.isEmpty()&itemImageUser.isEmpty() ) {
            Toast.makeText(Items.this, "Please enter all the data..", Toast.LENGTH_SHORT).show();
            return;
        }

        // on below line we are calling a method to add new
        // course to sqlite data and pass all our values to it.
        db.addNewItem(cat_id,itemUserName,itemCatUser,itemDateUser,itemDescUser,itemValueUser,itemImageUser);

        // after adding the data we are displaying a toast message.
        Toast.makeText(Items.this, "Item has been added.", Toast.LENGTH_SHORT).show();
        itemName.setText("");
        itemDesc.setText("");
        dateView.setText("");
        itemDesc.setText("");
        itemValue.setText("");

    }



}