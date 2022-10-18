package com.example.poca;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class dbHelper {
   public  DatabaseReference ref;
   private final DatabaseReference dbRef;


    private String itemName;

    private String catRef;

    private String category;

    private String dates;

    private String Description;

    private String values;

    private String image;

   DatabaseReference mDatabase;
   DatabaseReference mUserRef;

   public dbHelper() {
        FirebaseDatabase FB = FirebaseDatabase.getInstance();
        dbRef = FB.getReference(Testcollection.class.getSimpleName());
    }
    public Task<Void> addItem(Testcollection tc){

        return dbRef.child(tc.getName()).setValue(tc);
    }



    public void addCatItem (String name, String catalog, String date){

        /*this.itemName = name.getItemName();
        this.category = catalog.getCategory();
        this.dates = date.getDate();
        this.Description = desc.getDescription();
        this.values = value.getValue();
        this.image = image.getImage();*/


        /*mDatabase = FirebaseDatabase.getInstance().getReference();
        String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
        mUserRef = mDatabase.child("Testcollection").child(userId);

        //dbHelper data = new dbHelper();

        mUserRef.child("Confirmation").child("Item").push().setValue(name);
        mUserRef.child("Confirmation").child("Category").setValue(catalog);
        mUserRef.child("Confirmation").child("Date_Acquired").setValue(date);*/

        return;

    }

    /*public dbHelper(Testcollection name, Testcollection catalog, Testcollection date, Testcollection desc, Testcollection value, Testcollection image) {
        this.itemName = name.getItemName();
        this.category = catalog.getCategory();
        this.date = date.getDate();
        this.Description = desc.getDescription();
        this.value = value.getValue();
        this.image = image.getImage();

    }*/
}
