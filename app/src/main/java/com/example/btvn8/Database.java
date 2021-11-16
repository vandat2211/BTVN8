package com.example.btvn8;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class Database extends SQLiteOpenHelper {
    private Context context;

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "contact.db";

    private static final String TABLE_NAME = "contact";
    private static final String ID = "id";
    private static final String NAME = "name";
    private static final String ADDRESS = "address";
    private static final String PHONE = "phone";

    public Database(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE " + TABLE_NAME + " (" +
                ID + " INTEGER PRIMARY KEY," +
                NAME + " TEXT, " +
                ADDRESS + " TEXT, " +
                PHONE + " TEXT)";
        db.execSQL(sql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("drop table if exists "+TABLE_NAME);
        onCreate(db);
    }
    public void addContact(Contact contact) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(NAME, contact.getmName());
        contentValues.put(ADDRESS, contact.getmAddress());
        contentValues.put(PHONE, contact.getmPhone());
        db.insert(TABLE_NAME, null, contentValues);
        db.close();
    }
    public List<Contact> getAllContacts() {
        List<Contact> contactList = new ArrayList<>();

        String selectQuery = "SELECT * FROM " + TABLE_NAME;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                Contact contact = new Contact();
                contact.setmId(cursor.getInt(0));
                contact.setmName(cursor.getString(1));
                contact.setmAddress(cursor.getString(2));
                contact.setmPhone(cursor.getString(3));
                contactList.add(contact);
            } while (cursor.moveToNext());
        }
        db.close();
        return contactList;
    }

    public void deleteAll() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from " + TABLE_NAME);
    }
    public long updatedb(Contact contact) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(NAME, contact.getmName());
        contentValues.put(ADDRESS, contact.getmAddress());
        contentValues.put(PHONE, contact.getmPhone());

        return db.update(TABLE_NAME, contentValues, ID + "=?", new String[]{String.valueOf(contact.getmId())});

    }
    public int deleteContact(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, ID + "=?", new String[]{String.valueOf(id)});
    }
}

