package anandgaur.com.maptesting;

/**
 * Created by Anand Gaur
 */
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler extends SQLiteOpenHelper {


    String TAG="DatabaseHandler";
    private static final int DATABASE_VERSION = 1;
    // Database Name
    private static final String DATABASE_NAME = "loc";
    // Contacts table name
    private static final String TABLE_PLACES = "Place";

    // Contacts Table Columns names
    private static final String KEY_Place = "placename";
    private static final String KEY_Lat = "lat";
    private static final String KEY_Long="lng";
    private static final String KEY_Distance="distance";
    private static final String KEY_Address = "address";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_LOCATION_TABLE = "CREATE TABLE " + TABLE_PLACES + "("
                + KEY_Place + " TEXT," + KEY_Lat + " TEXT,"
                + KEY_Long + " TEXT,"
                + KEY_Distance + " TEXT,"
                + KEY_Address + " TEXT" + ")";
        db.execSQL(CREATE_LOCATION_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PLACES);

        // Create tables again
        onCreate(db);
    }

    /**
     * All CRUD(Create, Read, Update, Delete) Operations
     */

    // Adding new contact
    void addPlaceItem(PumpDetail loc) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_Place, loc.getPlacename());
        values.put(KEY_Lat,loc.getLat());
        values.put(KEY_Long,loc.getLongi());
        values.put(KEY_Distance,loc.getDistance());
        values.put(KEY_Address,loc.getPlacevicinity());


        // Inserting Row
        db.insert(TABLE_PLACES, null, values);
        db.close(); // Closing database connection
    }

    // Getting single contact
    PumpDetail getLocation(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_PLACES, new String[] { KEY_Place,
                        KEY_Lat,KEY_Long, KEY_Address }, KEY_Place + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        PumpDetail list = new PumpDetail(cursor.getString(0),
                cursor.getDouble(1),cursor.getDouble(2), cursor.getString(3),cursor.getString(4));

        return list;
    }


    public List<PumpDetail> getAllPlace() {
        List<PumpDetail> globallist = new ArrayList<PumpDetail>();
        // Select All Query
        String selectQuery = "SELECT * FROM " + TABLE_PLACES+" order by "+KEY_Distance+"  ASC";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                PumpDetail locallist = new PumpDetail();
                locallist.setPlacename(cursor.getString(0));
                locallist.setLat(cursor.getDouble(1));
                locallist.setLongi(cursor.getDouble(2));
                locallist.setDistance(cursor.getString(3));
                locallist.setPlacevicinity(cursor.getString(4));
                // Adding contact to list



                globallist.add(locallist);
            } while (cursor.moveToNext());
        }

        // return contact list
        return globallist;
    }

    // Updating single contact
    public int updateContact(PumpDetail list) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_Place, list.getPlacename());
        values.put(KEY_Lat, list.getLat());
        values.put(KEY_Long, list.getLongi());
        values.put(KEY_Address, list.getPlacevicinity());

        // updating row
        return db.update(TABLE_PLACES, values, KEY_Place + " = ?",
                new String[] { String.valueOf(list.getPlacename()) });
    }

    // Deleting single contact
    public void deleteLocation(PumpDetail list) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_PLACES, KEY_Place + " = ?",
                new String[] { String.valueOf(list.getPlacename()) });
        db.close();
    }



    public void delete_10_Row(int n)
    {

        Log.d(TAG,"Delete "+n);

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.query(TABLE_PLACES, null, null, null, null, null, null);

        int count=0;
        while(cursor.moveToNext()&&count<n) {
            String rowId = cursor.getString(cursor.getColumnIndex(KEY_Place));

            db.delete(TABLE_PLACES, KEY_Place + "=?",  new String[]{rowId});
            count++;
        }
        db.close();
    }

    public int getContactsCount() {
        String countQuery = "SELECT  * FROM " + TABLE_PLACES;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        return cursor.getCount();
    }

}