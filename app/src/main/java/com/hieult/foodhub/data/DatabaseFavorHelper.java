package com.hieult.foodhub.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.hieult.foodhub.model.FoodCartVerModel;
import com.hieult.foodhub.model.FoodPopularHorModel;

import java.util.ArrayList;
import java.util.List;

public class DatabaseFavorHelper extends SQLiteOpenHelper {
    public DatabaseFavorHelper(Context context) {
        super(context, "favor.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE IF NOT EXISTS favor ("
                + "foodName TEXT ,"
                + "foodPrice TEXT ,"
                + "foodImageUrl TEXT ,"
                + "foodRating TEXT )";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void addToCart(String foodName, String foodPrice, String foodImageUrl,String foodRating) {
        if (!isFoodInCart(foodName)) {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("foodName", foodName);
            values.put("foodPrice ", foodPrice);
            values.put("foodImageUrl ", foodImageUrl);
            values.put("foodRating", foodRating);
            if (foodName != null && !foodName.isEmpty()) {
                db.insert("favor", null, values);
            }
            db.close();
        }
    }

    public boolean isFoodInCart(String foodName) {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] selectionArgs = {foodName};
        Cursor cursor = null;
        try {
            cursor = db.rawQuery("SELECT * FROM favor WHERE foodName = ?", selectionArgs);
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

        public List<FoodPopularHorModel> getCartItems() {
            // Lấy danh sách món ăn trong giỏ hàng
            List<FoodPopularHorModel> cartItems = new ArrayList<>();
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor cursor = db.rawQuery("SELECT * FROM favor", null);
            if (cursor.moveToFirst()) {
                do {
                    String foodImageUrl = cursor.getString(cursor.getColumnIndex("foodImageUrl"));
                    String foodPrice = cursor.getString(cursor.getColumnIndex("foodPrice"));
                    String foodRating = cursor.getString(cursor.getColumnIndex("foodRating"));
                    String foodName = cursor.getString(cursor.getColumnIndex("foodName"));
                    FoodPopularHorModel foodItem = new FoodPopularHorModel(foodImageUrl,foodPrice,foodRating,foodName);
                    cartItems.add(foodItem);} while (cursor.moveToNext());
            }
            cursor.close();
            return cartItems;
        }


    public void deleteFromCart(String foodName) {
        SQLiteDatabase db = this.getWritableDatabase();
        String[] whereArgs = {foodName};
        db.delete("favor", "foodName = ?", whereArgs);
        db.close();
    }


}
