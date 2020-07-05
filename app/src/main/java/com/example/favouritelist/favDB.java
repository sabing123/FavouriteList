package com.example.favouritelist;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class favDB extends SQLiteOpenHelper {
    private static int DB_Version = 1;
    private static String DATABASE_NAME = "favouriteDB";
    private static String TABLE_NAME = "favouriteTable";
    public  static String KEY_ID = "id";
    public  static String ITEM_NAME = "ItemName";
    public  static String ITEM_IMAGE = "Itemimage";
    public static String FAVORITE_STATUS = "fStatus";

    // dont forget write this spaces
    private static String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "("
            + KEY_ID + " TEXT," + ITEM_NAME + " TEXT,"
            + ITEM_IMAGE + " TEXT," + FAVORITE_STATUS + " TEXT)";

    public favDB(Context context) {
        super(context, DATABASE_NAME, null, DB_Version);

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
    }

    // create empty table
    public void insertEmpty() {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        // enter your value
        for (int x = 1; x < 11; x++) {
            cv.put(KEY_ID, x);
            cv.put(FAVORITE_STATUS, "0");
            db.insert(TABLE_NAME,null, cv);
        }
    }

    //inserting values
public void insertintoDatabase(String item_Name, int item_image, String id, String fav_status)
{
    SQLiteDatabase db;
    db =this.getWritableDatabase();
    ContentValues cv = new ContentValues();
    cv.put(ITEM_NAME, item_Name);
    cv.put(ITEM_IMAGE, item_image);
    cv.put(KEY_ID, id);
    cv.put(FAVORITE_STATUS, fav_status);
    db.insert(TABLE_NAME,null, cv);
    Log.d("FavDB Status", item_Name + ", favstatus - "+fav_status+" - . " + cv);
}
public Cursor read_all_data(String id) {
    SQLiteDatabase db = this.getWritableDatabase();
    String sql = "UPDATE " + TABLE_NAME + " SET  " + FAVORITE_STATUS + " ='0' WHERE " + KEY_ID + "=" + id + "";
    db.execSQL(sql);
    Log.d("remove", id.toString());
    return db.rawQuery(sql,null,null);
}


    // select all favorite list

    public Cursor select_all_favorite_list() {
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT * FROM "+TABLE_NAME+" WHERE "+FAVORITE_STATUS+" ='1'";
        return db.rawQuery(sql,null,null);
    }

    public void remove_fav(String id) {

        SQLiteDatabase db = this.getWritableDatabase();
        String sql = "UPDATE " + TABLE_NAME + " SET  "+ FAVORITE_STATUS+" ='0' WHERE "+KEY_ID+"="+id+"";
        db.execSQL(sql);
        Log.d("remove", id.toString());

    }
}

