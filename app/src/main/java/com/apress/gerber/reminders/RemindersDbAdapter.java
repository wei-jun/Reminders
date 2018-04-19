package com.apress.gerber.reminders;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.security.PublicKey;

/**
 * Created by weijunzeng on 2018-04-18.
 */

public class RemindersDbAdapter {
    //these are the column names
    public static final String COL_ID = "_id";
    public static final String COL_CONTENT = "_content";
    public static final String COL_IMPORTANT = "_important";

    //these are the corresponding indices
    public static final int INDEX_ID = 0;
    public static final int INDEX_CONTENT = INDEX_ID + 1;
    public static final int INDEX_IMPORTANT = INDEX_ID + 2;

    //used for logging
    private static final String TAG = "RemindersDbAdapter";

    private DatabaseHelper mDbHelper;
    private SQLiteDatabase mDb;

    private static final String DATABSE_NAME = "dba_remdrs";
    private static final String TABLE_NAME = "tbl_remdrs";
    private static final int DATABASE_VERSION = 1;

    private final Context mCtx;

    //SQL statement used to create the database
    private static final String DATABASE_CREATE =
            "CREATE TABLE if not exists " + TABLE_NAME + " ( " +
                    COL_ID + " INTEGER PRIMARY KEY autoincrement, " +
                    COL_CONTENT + " TEXT, " +
                    COL_IMPORTANT + " INTEGER );";

    public RemindersDbAdapter(Context ctx) {
        this.mCtx = ctx;
    }

    //open
    public void open() throws SQLException {
        mDbHelper = new DatabaseHelper(mCtx);
        mDb = mDbHelper.getWritableDatabase();
    }

    //close
    public void close() {
        if (mDbHelper != null) {
            mDbHelper.close();
        }
    }

    private static class DatabaseHelper extends SQLiteOpenHelper {
        DatabaseHelper(Context context) {
            super(context, DATABSE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            Log.w(TAG, DATABASE_CREATE);
            db.execSQL(DATABASE_CREATE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            Log.w(TAG, "Upgrading database from version " + oldVersion + " to " +
                newVersion + ", which will destroy all old data");
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
            onCreate(db);
        }
    }

}
