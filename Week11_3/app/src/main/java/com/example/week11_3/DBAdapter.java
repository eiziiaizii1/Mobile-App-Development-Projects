package com.example.week11_3;

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
    public static final String KEY_EMAIL = "email";
    private static final String TAG = "DBAdapter"; // For Logcat

    private static final String DATABASE_NAME = "mydb";
    private static final String DATABASE_TABLE = "contacts";
    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_CREATE = "create table contacts (_id integer primary key autoincrement, "
            + "name text, email text);";

    // Constructor
    public DBAdapter(Context context) {
        this.context = context;
        DBHelper = new DatabaseHelper(context);
    }

    // To create and upgrade a database in an Android application
    // SQLiteOpenHelper subclass is usually created
    private static class DatabaseHelper extends SQLiteOpenHelper {
        DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            // onCreate() is only called by the framework, if the database does
            // not exist
            Log.d("Create", "Creating the database");

            try {
                db.execSQL(DATABASE_CREATE);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            // onUpgrade() is only called by the framework, if one changes the
            // database version number

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
        // Create and/or open a database that will be used for reading and
        // writing
        db = DBHelper.getWritableDatabase();

        // Use if you only want to read data from the database
        // db = DBHelper.getReadableDatabase();
        return this;
    }

    // Closes the database
    public void close() {
        // Closes the database
        DBHelper.close();
    }

    // Insert a contact into the database
    public long insertContact(String name, String email) {
        // The class ContentValues allows to define key/values. The "key"
        // represents the
        // table column identifier and the "value" represents the content for
        // the table
        // record in this column. ContentValues can be used for inserts and
        // updates of database entries.
        ContentValues initialValues = new ContentValues();
        initialValues.put(KEY_NAME, name);
        initialValues.put(KEY_EMAIL, email);
        return db.insert(DATABASE_TABLE, null, initialValues);
    }

    // Insert a contact into the database
    public void insertContact2(String name, String email) {
        //Execute a single SQL statement that is NOT a SELECT or any other SQL statement that returns data.
        //String sql = "insert into contacts (name, email) values ('"+name+"','"+email+"')";
        String sql = "insert into "+DATABASE_TABLE+" ( "+KEY_NAME+","+KEY_EMAIL+") values ('"+name+"','"+email+"')";
        db.execSQL(sql);

    }

    // Deletes a particular contact
    public boolean deleteContact(long rowId) {
        return db.delete(DATABASE_TABLE, KEY_ROWID + "=" + rowId, null) > 0;
    }

    // Retrieves all the contacts
    public Cursor getAllContacts() {
        //query() provides a structured interface for specifying the SQL query.
        return db.query(DATABASE_TABLE, new String[] { KEY_ROWID, KEY_NAME,
                KEY_EMAIL }, null, null, null, null, null);
    }

    public Cursor getAllContacts2() {
        //rawQuery() directly accepts an SQL select statement as input.
        Cursor cursor = db.rawQuery("SELECT * FROM "+DATABASE_TABLE, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }


    // Retrieves a particular contact
    public Cursor getContact(long rowId) throws SQLException {
        // rawQuery() directly accepts an SQL select statement as input.
        // query() provides a structured interface for specifying the SQL query.

        // A query returns a Cursor object. A Cursor represents the result of a
        // query
        // and basically points to one row of the query result. This way Android
        // can buffer
        // the query results efficiently; as it does not have to load all data
        // into memory

        Cursor mCursor = db.query(true, DATABASE_TABLE, new String[] {
                        KEY_ROWID, KEY_NAME, KEY_EMAIL }, KEY_ROWID + "=" + rowId,
                null, null, null, null, null);
        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;
    }

    // Updates a contact
    public boolean updateContact(long rowId, String name, String email) {
        // This methods returns the number of rows affected by the conducted
        // operation
        ContentValues args = new ContentValues();
        args.put(KEY_NAME, name);
        args.put(KEY_EMAIL, email);
        return db.update(DATABASE_TABLE, args, KEY_ROWID + "=" + rowId, null) > 0;
    }
}