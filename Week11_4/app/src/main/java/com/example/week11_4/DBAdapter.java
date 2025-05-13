package com.example.week11_4;

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

    public static final String KEY_ROWID = "_id";
    public static final String KEY_NAME = "name";
    public static final String KEY_SURNAME = "surname";
    public static final String KEY_SALARY = "salary";
    public static final String KEY_PHOTO = "photo";

    private static final String TAG = "DBAdapter";

    private static final String DATABASE_NAME = "testDB";
    private static final String DATABASE_TABLE = "ceo";
    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_CREATE = "create table ceo (_id integer primary key autoincrement, "
            + "name text, surname text,"
            + "salary numeric, photo blob);";

    // Constructor
    public DBAdapter(Context context) {
        this.context = context;
        DBHelper = new DatabaseHelper(context);
    }

    // To create and upgrade a database in an Android application SQLiteOpenHelper subclass is usually created
    private static class DatabaseHelper extends SQLiteOpenHelper {
        DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            // onCreate() is only called by the framework, if the database does not exist
            Log.d("Create", "Creating the database");

            try {
                db.execSQL(DATABASE_CREATE);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            // onUpgrade() is only called by the framework, if one changes the database version number

            // Sends a Warn log message
            Log.w(TAG, "Upgrading database from version " + oldVersion + " to "
                    + newVersion + ", which will destroy all old data");

            // Method to execute an SQL statement directly
            db.execSQL("DROP TABLE IF EXISTS contacts");
            onCreate(db);
        }
    }

    // Opens the database
    public DBAdapter open() throws SQLException {
        // Create and/or open a database that will be used for reading and writing
        db = DBHelper.getWritableDatabase();

        // Use if you only want to read data from the database
        //db = DBHelper.getReadableDatabase();
        return this;
    }

    // Closes the database
    public void close() {
        // Closes the database
        DBHelper.close();
    }

    // Insert a contact into the database
    public long insertContact(String name, String surname, int salary, byte [] photo) {
        // The class ContentValues allows to define key/values. The "key" represents the
        // table column identifier and the "value" represents the content for the table
        // record in this column. ContentValues can be used for inserts and updates of database entries.
        ContentValues initialValues = new ContentValues();
        initialValues.put(KEY_NAME, name);
        initialValues.put(KEY_SURNAME, surname);
        initialValues.put(KEY_SALARY, salary);
        initialValues.put(KEY_PHOTO, photo);
        return db.insert(DATABASE_TABLE, null, initialValues);
    }

    // Retrieves a particular contact
    public Cursor getContact(String name) throws SQLException {
        // rawQuery() directly accepts an SQL select statement as input.
        // query() provides a structured interface for specifying the SQL query.

        // A query returns a Cursor object. A Cursor represents the result of a query
        // and basically points to one row of the query result. This way Android can buffer
        // the query results efficiently; as it does not have to load all data into memory

        Cursor mCursor = db.query(true, DATABASE_TABLE, new String[] {
                KEY_NAME, KEY_SURNAME, KEY_SALARY, KEY_PHOTO}, KEY_NAME + "='" + name +"'", null, null, null, null, null);

        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;
    }

}