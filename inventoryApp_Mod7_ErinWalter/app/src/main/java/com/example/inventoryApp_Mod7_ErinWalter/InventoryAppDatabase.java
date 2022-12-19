package com.example.inventoryApp_Mod7_ErinWalter;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.SimpleCursorAdapter;

import com.example.login_screen.R;

import java.util.ArrayList;
import java.util.List;

public class InventoryAppDatabase extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "inventoryapp.db";
    private static final int VERSION = 2;
    private static  Context context;

    public InventoryAppDatabase(Context context) {
       super(context, DATABASE_NAME, null, VERSION);
       this.context = context;
    }

    private static final class LoginTable {
        private static final String TABLE = "login";
        private static final String COL_ID = "_id";
        private static final String COL_USERNAME = "username";
        private static final String COL_PASSWORD = "`password`";
        private static final String COL_SMSCONSENT = "smsConsent";
    }

    private static final class InventoryTable {
      private static final String TABLE = "inventory";
      private static final String COL_ID = "_id";
      private static final String COL_NAME = "name";
      private static final String COL_DESCRIPTION = "description";
      private static final String COL_AMOUNT = "amount";
    };

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table " + LoginTable.TABLE + " (" +
                LoginTable.COL_ID + " integer primary key autoincrement, " +
                LoginTable.COL_USERNAME + " text not null, " +
                LoginTable.COL_PASSWORD + " text not null, " +
                LoginTable.COL_SMSCONSENT + " integer not null)" );

        sqLiteDatabase.execSQL("create table " + InventoryTable.TABLE + " (" +
                InventoryTable.COL_ID + " integer primary key autoincrement, " +
                InventoryTable.COL_NAME + " text not null, " +
                InventoryTable.COL_DESCRIPTION + " text, " +
                InventoryTable.COL_AMOUNT + " integer not null)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("drop table if exists " + LoginTable.TABLE);
        sqLiteDatabase.execSQL("drop table if exists " + InventoryTable.TABLE);
        onCreate(sqLiteDatabase);
    }

    //LOGIN TABLE METHODS HERE
    //create user
    public long addUser(String username, String password) {
        SQLiteDatabase db = getWritableDatabase();
        //todo, send to a function to hash/encrypt password if time

        //first, need to check vs db to see if username exists
        long userId = -1;
        long existingUserId = loginUser(username, password);
        if(existingUserId == -1) {
            //if no username by that name exists, create it
            ContentValues values = new ContentValues();
            values.put(LoginTable.COL_USERNAME, username);
            values.put(LoginTable.COL_PASSWORD, password);
            values.put(LoginTable.COL_SMSCONSENT, 0);
            userId = db.insert(LoginTable.TABLE, null, values);
        }

        return userId;
    }

    //login user
    public long loginUser(String username, String password)
    {
        SQLiteDatabase db = getReadableDatabase();

        String sql = "select * from " + LoginTable.TABLE +
                " where `username` = ? AND `password` = ?";
       Cursor c = db.rawQuery(sql, new
        String[] {username, password});

        long id = -1;
        Boolean x = c.moveToFirst();
        if(c.moveToFirst()) {
                id = c.getLong(0);
                String name = c.getString(1);
        }
        c.close();
        return id;
    }

    //update user's sms consent
    public boolean updateSMSConsent(long currentUserId, boolean consent)
    {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(LoginTable.COL_SMSCONSENT, Boolean.toString(consent));

        int rowsUpdated = db.update(LoginTable.TABLE, values, "_id = ?",
                new String[] {Long.toString(currentUserId) });
        return rowsUpdated > 0;
    }

    //INVENTORY TABLE METHODS HERE
    //add item, create item, update item, delete item

    //CREATE ITEM
    public long addInventoryItem(String name, String description, int amount)
    {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(InventoryTable.COL_NAME, name);
        values.put(InventoryTable.COL_DESCRIPTION, description);
        values.put(InventoryTable.COL_AMOUNT, amount);

        long inventoryId = db.insert(InventoryTable.TABLE, null, values);
        return inventoryId;
    }

    //READ ALL ITEMS
    public List<InventoryItem> getAllInventoryItems()
    {
        SQLiteDatabase db = getReadableDatabase();
        String sql = "select * from " + InventoryTable.TABLE;

        Cursor c = db.rawQuery(sql, null);
        List<InventoryItem> inventoryItems = new ArrayList<InventoryItem>();
        if(c.moveToFirst()) {
            do {
                inventoryItems.add( new InventoryItem(
                    c.getLong(0),
                    c.getString(1),
                    c.getString(2),
                    c.getInt(3)
                ));
            } while (c.moveToNext());
        }
        c.close();
        return inventoryItems;
    }

    public SimpleCursorAdapter getInventoryItems()
    {
        String[] fromFieldNames = new String[] {"_id", "name", "amount"};
        int[] toViewIDs = new int[]{R.id.itemId, R.id.itemName, R.id.itemInStock};
        SQLiteDatabase db = getReadableDatabase();
        String sql = "select * from " + InventoryTable.TABLE;

        Cursor c = db.rawQuery(sql, null);

        SimpleCursorAdapter simpleCursorAdapter = new SimpleCursorAdapter(context,
                R.layout.single_list_item, c, fromFieldNames, toViewIDs, 0
        );

        return simpleCursorAdapter;
    }

    //READ ONE ITEM BY ID (for child)
    public InventoryItem getItemById(int itemId)
    {
        SQLiteDatabase db = getReadableDatabase();
        String sql = "select * from " + InventoryTable.TABLE +
                " where _id = ?";

        Cursor c = db.rawQuery(sql, new
                String[] {Long.toString(itemId)});

        if(c.moveToFirst()) {
            InventoryItem itemFound = new InventoryItem(
                    c.getLong(0), //id
                    c.getString(1), //name
                    c.getString(2), //description
                    c.getInt(3)); //amount
            return itemFound;
        }
        c.close();

        //if nothing found returns null
        return null;
    }

    //update one item's stock
    public boolean updateNumberInStock(long id, int numInStock)
    {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(InventoryTable.COL_AMOUNT, numInStock);

        int rowsUpdated = db.update(InventoryTable.TABLE, values, "_id = ?",
                new String[] {Long.toString(id) });
        return rowsUpdated > 0;
    }

    //update one item
    public boolean updateItem(long id, String name, String description, int amount)
    {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(InventoryTable.COL_NAME, name);
        values.put(InventoryTable.COL_DESCRIPTION, description);
        values.put(InventoryTable.COL_AMOUNT, amount);

        int rowsUpdated = db.update(InventoryTable.TABLE, values, "_id = ?",
                new String[] {Long.toString(id) });
        return rowsUpdated > 0;
    }

    //delete item
    public boolean deleteItem(long id)
    {
        SQLiteDatabase db = getWritableDatabase();
        int rowsDeleted = db.delete(InventoryTable.TABLE,
                InventoryTable.COL_ID + " = ?",
                new String[] {Long.toString(id)});
        return rowsDeleted > 0 ;
    }
}
