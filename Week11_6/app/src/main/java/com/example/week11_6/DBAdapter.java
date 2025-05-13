package com.example.week11_6;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBAdapter {
    private DatabaseHelper DBHelper;
    private SQLiteDatabase db;
    private final Context context;

    public static final String KEY_ROWID = "id";
    public static final String KEY_NAME = "department";
    public static final String KEY_TEACHERS = "instructors";
    public static final String KEY_STUDENTS = "students";
    private static final String TAG = "DBAdapter";

    private static final String DATABASE_NAME = "mydb2.db";
    private static final String DATABASE_TABLE = "Staff";
    private static final int DATABASE_VERSION = 4;

    private static final String DATABASE_CREATE = "create table Staff (id integer primary key autoincrement, "
            + "name text, teachers numeric, students numeric);";

    // Constructor
    public DBAdapter(Context ctx) {
        this.context = ctx;
        DBHelper = new DatabaseHelper(context);
    }

    // To create and upgrade a database in an Android application SQLiteOpenHelper subclass is usually created
    private static class DatabaseHelper extends SQLiteOpenHelper {
        DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            // onCreate() is called by the framework, if the database does not exist
            Log.d("Create", "Creating the database");

            try {
                db.execSQL(DATABASE_CREATE);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            // Sends a Warn log message
            Log.w(TAG, "Upgrading database from version " + oldVersion + " to "
                    + newVersion + ", which will destroy all old data");

            // Method to execute an SQL statement directly
            db.execSQL("DROP TABLE IF EXISTS Staff");
            onCreate(db);
        }
    }

    // Opens the database
    public DBAdapter open() throws SQLException {
        // Create and/or open a database that will be used for reading only
        db = DBHelper.getReadableDatabase();
        return this;
    }

    // Closes the database
    public void close() {
        // Closes the database
        DBHelper.close();
    }

    // Retrieves all the contacts
    public Cursor getAllContacts() {
        return db.query(DATABASE_TABLE, new String[] { KEY_ROWID, KEY_NAME,
                KEY_TEACHERS, KEY_STUDENTS }, null, null, null, null, null);
    }

    public long insertContact(String name, int teachers, int students) {
        ContentValues initialValues = new ContentValues();
        initialValues.put(KEY_NAME, name);
        initialValues.put(KEY_TEACHERS, teachers);
        initialValues.put(KEY_STUDENTS, students);
        return db.insert(DATABASE_TABLE, null, initialValues);
    }

}