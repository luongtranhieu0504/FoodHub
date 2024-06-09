package com.hieult.foodhub.data;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.hieult.foodhub.model.FoodCartVerModel;


import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 3;
    public DatabaseHelper(Context context) {
        super(context, "cart.db", null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE IF NOT EXISTS cart2("
                + "foodName TEXT ,"
                + "foodPrice TEXT ,"
                + "foodImageUrl TEXT ,"
                + "foodNumberOrder TEXT )";
        db.execSQL(createTable);

        String createCompleteTable = "CREATE TABLE IF NOT EXISTS complete ("
                + "foodName TEXT ,"
                + "foodPrice TEXT ,"
                + "foodImageUrl TEXT ,"
                + "foodNumberOrder TEXT )";
        db.execSQL(createCompleteTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void addToCart(String foodName, String foodPrice, String foodImageUrl,String numberOrder) {
        if (!isFoodInCart(foodName)) {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("foodName", foodName);
            values.put("foodPrice ", foodPrice);
            values.put("foodImageUrl ", foodImageUrl);
            values.put("foodNumberOrder", numberOrder);
            if (foodName != null && !foodName.isEmpty()) {
                db.insert("cart2", null, values);
            }
            db.close();
        }
    }

    public boolean isFoodInCart(String foodName) {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] selectionArgs = {foodName};
        Cursor cursor = null;
        try {
            cursor = db.rawQuery("SELECT * FROM cart2 WHERE foodName = ?", selectionArgs);
            return cursor.getCount() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cursor != null) {
                cursor.close();
            }
            db.close();
        }
        return false;
    }

    public List<FoodCartVerModel> getCartItems() {
        // Lấy danh sách món ăn trong giỏ hàng
        List<FoodCartVerModel> cartItems = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM cart2", null);
        if (cursor.moveToFirst()) {
            do {
                String foodName = cursor.getString(cursor.getColumnIndex("foodName"));
                String foodPrice = cursor.getString(cursor.getColumnIndex("foodPrice"));
                String foodImageUrl = cursor.getString(cursor.getColumnIndex("foodImageUrl"));
                String numberOrder = cursor.getString(cursor.getColumnIndex("foodNumberOrder"));
                FoodCartVerModel foodItem = new FoodCartVerModel(foodName, foodPrice,foodImageUrl, numberOrder);
                cartItems.add(foodItem);} while (cursor.moveToNext());
        }
        db.close();
        return cartItems;
    }


    public void deleteFromCart(String foodName) {
        SQLiteDatabase db = this.getWritableDatabase();
        String[] whereArgs = {foodName};
        db.delete("cart2", "foodName = ?", whereArgs);
        db.close();
    }
    //table complete
    public void addToComplete(String foodName, String foodPrice, String foodImageUrl, String numberOrder) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("foodName", foodName);
        values.put("foodPrice ", foodPrice);
        values.put("foodImageUrl ", foodImageUrl);
        values.put("foodNumberOrder", numberOrder);
        if (foodName != null && !foodName.isEmpty()) {
            db.insert("complete", null, values);
        }
        db.close();
    }

    public List<FoodCartVerModel> getCompleteItems() {
        List<FoodCartVerModel> completeItems = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM complete", null);

        if (cursor.moveToFirst()) {
            do {
                String foodName = cursor.getString(cursor.getColumnIndex("foodName"));
                String foodPrice = cursor.getString(cursor.getColumnIndex("foodPrice"));
                String foodImageUrl = cursor.getString(cursor.getColumnIndex("foodImageUrl"));
                String numberOrder = cursor.getString(cursor.getColumnIndex("foodNumberOrder"));
                FoodCartVerModel foodItem = new FoodCartVerModel(foodName, foodPrice, foodImageUrl, numberOrder);
                completeItems.add(foodItem);
            } while (cursor.moveToNext());
        }

        cursor.close();
        return completeItems;
    }

    public void deleteFromComplete(String foodName) {
        SQLiteDatabase db = this.getWritableDatabase();
        String[] whereArgs = {foodName};
        db.delete("complete", "foodName = ?", whereArgs);
        db.close();
    }

    public void deleteAllFromCart() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("cart2", null, null);
        db.close();
    }


}
