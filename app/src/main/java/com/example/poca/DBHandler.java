package com.example.poca;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.Date;

public class DBHandler extends SQLiteOpenHelper {
    // variables for the database and the first catalog table
    // creating a constant variables for our database.
    // below variable is for our database name.
    private static final String DB_NAME = "myCatalogs.db";

    // below int is our database version
    private static final int DB_VERSION = 1;

    // below variable is for our table name.
    private static final String TABLE_NAME = "mycatalogs";

    // below variable is for our id column.
    private static final String ID_COL = "id";

    // below variable is for our course name column
    private static final String NAME_COL = "name";

    private static final String GOAL_COL = "goal";

    // string for selected catalog on homescreen
    String CAT_SELECTION = "";



    // creating variables for catalog items table

    private static final String TABLE_NAME_CAT_ITEMS = "catalog_items";

    private static final String ITEM_ID_COL = "Itemid";

    private static final String ITEM_NAME_COL = "name";

    private static final String ITEM_CATEGORY_COL = "category";  // example fantasy

    private static final String ITEM_DATE_ACQUIRED = "data_acquired";

    private static final String ITEM_DESCRIPTION = "item_description";

    private static final String ITEM_VALUE = "item_value";

    private static final String ITEM_IMAGE="item_Image";

    private static final String CATALOG_KEY = ID_COL;



    // creating a constructor for our database handler.
    public DBHandler(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    // below method is for creating a database by running a sqlite query
    @Override
    public void onCreate(SQLiteDatabase db) {
        // on below line we are creating
        // an sqlite query and we are
        // setting our column names
        // along with their data types.
        String query = "CREATE TABLE " + TABLE_NAME + " ("
                + ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + NAME_COL + " TEXT, "
                + GOAL_COL + " INTEGER)";

        // at last we are calling a exec sql
        // method to execute above sql query
        db.execSQL(query);


        String queryItems = "CREATE TABLE " + TABLE_NAME_CAT_ITEMS + " ("
                + ITEM_ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + ID_COL + " INTEGER,"
                + ITEM_NAME_COL + "  TEXT, "
                + ITEM_CATEGORY_COL + "  TEXT, "
                + ITEM_DATE_ACQUIRED +"  TEXT, "
                + ITEM_DESCRIPTION +"  TEXT, "
                + ITEM_VALUE +" TEXT, "
                + ITEM_IMAGE + " TEXT, "
                + " FOREIGN KEY ("+ID_COL+") REFERENCES "+TABLE_NAME+"("+"ID_COL"+"));";

        db.execSQL(queryItems);







    }

    // this method is use to add new course to our sqlite database.
    public void addNewCourse(String courseName, String itemGoal) {

        // on below line we are creating a variable for
        // our sqlite database and calling writable method
        // as we are writing data in our database.
        SQLiteDatabase db = this.getWritableDatabase();

        // on below line we are creating a
        // variable for content values.
        ContentValues values = new ContentValues();

        // on below line we are passing all values
        // along with its key and value pair.
        values.put(NAME_COL, courseName);

        values.put(GOAL_COL, itemGoal);

        // after adding all values we are passing
        // content values to our table.
        db.insert(TABLE_NAME, null, values);

        // at last we are closing our
        // database after adding database.
        db.close();
    }




    public void addNewItem(String itemID, String itemName, String itemCategory, String itemAcquired, String itemDesc, String itemValue,String itemImage) {

        // on below line we are creating a variable for
        // our sqlite database and calling writable method
        // as we are writing data in our database.
        SQLiteDatabase db = this.getWritableDatabase();

        // on below line we are creating a
        // variable for content values.
        ContentValues values = new ContentValues();

        // on below line we are passing all values
        // along with its key and value pair.
        values.put(ID_COL, itemID);
        values.put(ITEM_NAME_COL, itemName);
        values.put(ITEM_CATEGORY_COL, itemCategory);
        values.put(ITEM_DATE_ACQUIRED, itemAcquired.toString());
        values.put(ITEM_DESCRIPTION, itemDesc);
        values.put(ITEM_VALUE, itemValue);
        values.put(ITEM_IMAGE,itemImage);




        // after adding all values we are passing
        // content values to our table.
        db.insert(TABLE_NAME_CAT_ITEMS, null, values);

        // at last we are closing our
        // database after adding database.
        db.close();
    }





    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // this method is called to check if the table exists already.
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);



        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_CAT_ITEMS);
        onCreate(db);

    }

    // method to view data

    public Cursor viewData(){
        SQLiteDatabase db =this.getReadableDatabase();
        String query = "Select * from "+ TABLE_NAME;
        Cursor cursor = db.rawQuery(query,null);

        return cursor;
    }

    public Cursor viewDataGoal(String i){
        SQLiteDatabase db =this.getReadableDatabase();
        String query = "Select * from "+ TABLE_NAME + " WHERE id="+ i;
        Cursor cursor = db.rawQuery(query,null);

        return cursor;
    }

    public Cursor viewItems(){
        SQLiteDatabase db =this.getReadableDatabase();
        String query = "Select * from "+ TABLE_NAME_CAT_ITEMS;
        Cursor cursor = db.rawQuery(query,null);

        return cursor;
    }

    public Cursor viewSpecificItems(String i){
        SQLiteDatabase db =this.getReadableDatabase();
        String query = "Select * from "+ TABLE_NAME_CAT_ITEMS + " WHERE id="+ i;
        Cursor cursor = db.rawQuery(query,null);

        return cursor;
    }

    public Cursor viewItemData (String i)
    {
        SQLiteDatabase db =this.getReadableDatabase();
        String query = "Select * from "+ TABLE_NAME_CAT_ITEMS + " WHERE Itemid="+ i;
        Cursor cursor = db.rawQuery(query,null);
        return cursor;
    }
}