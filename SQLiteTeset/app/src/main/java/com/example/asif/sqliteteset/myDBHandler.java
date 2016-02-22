package com.example.asif.sqliteteset;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.PorterDuff;

import java.sql.SQLInput;

/**
 * Created by asif on 09/02/16.
 */
public class myDBHandler extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION=1;
    public static final String DATABASE_NAME="products.db";
    public static final String TABLE_PRODUCTS="products";
    public static final String COLUMN_ID="_id";
    public static final String COLUMN_PRODUCTNAME="productname";

    public myDBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE "+ TABLE_PRODUCTS + "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "+
                COLUMN_PRODUCTNAME + " TEXT " + ");";

        db.execSQL(query);

        /*db.execSQL(
                "create table products " +
                        "(_id integer primary key autoincrement, productname text);"
        );
*/

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_PRODUCTS);
        onCreate(db);
    }

    // Add a new row
    public void addProduct (Product product){
        ContentValues values = new ContentValues();
        values.put(COLUMN_PRODUCTNAME, product.get_productName());  // Put the value in a column.
       // values.put(COLUMN_ID, 1);
        SQLiteDatabase db = getWritableDatabase();
        long id  = db.insertOrThrow(TABLE_PRODUCTS, null, values);
//        System.out.println("added!"+id);
        db.close();
        product.set_id(id);
    }

    // Delete row
    public void deleteProducts(String productName){
        SQLiteDatabase db = getWritableDatabase();
        System.out.println("deleted! " + productName);
        db.execSQL("DELETE FROM " + TABLE_PRODUCTS + " WHERE " + COLUMN_PRODUCTNAME + " = \""
                + productName + "\"");
    }
    // Print out the database
    public String databaseToString(){
        String dbString = "";
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM "+TABLE_PRODUCTS+";";

        // Cursor point to a location in your results
        Cursor c = db.rawQuery(query, null);
        // Move to first row in your results
        c.moveToFirst();
        if(c.getCount()>0)
            do{
                if(c.getString(c.getColumnIndex("productname"))!=null){
                    dbString += c.getString(c.getColumnIndex("productname"));
                    dbString += "\n";
                }
            }while (c.moveToNext());
        db.close();
        return dbString;
    }
}
