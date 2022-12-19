package com.example.inventoryApp_Mod7_ErinWalter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import com.example.login_screen.R;

import java.util.List;

public class MainDashboard extends AppCompatActivity {
    private TextView inventoryListEmptyText;
    private InventoryAppDatabase database;
    private ListView inventoryList;
    private int selectedItemId;
    private long currentUserId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_dashboard);

        inventoryListEmptyText = findViewById(R.id.inventoryListTest);
        inventoryList = findViewById(R.id.inventoryListItems);
        database = new InventoryAppDatabase(getApplicationContext());
        currentUserId = getIntent().getExtras().getLong("userId");

        SimpleCursorAdapter itemsInInventory =  database.getInventoryItems();

        inventoryList.setAdapter(itemsInInventory);
        inventoryList.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Cursor cursor = (Cursor) itemsInInventory.getItem(i);
                selectedItemId = cursor.getInt(0);
                onListItemClick();
            }
        });

        String listText = "";
        List<InventoryItem> allItems = database.getAllInventoryItems();
        if(allItems.isEmpty()) {
            listText = "No items in inventory to display. Click button below to add something to the inventory!";

        }
        else {
            listText = "";
        }
        inventoryListEmptyText.setText(listText);
    }

    public void onAddItemButtonClick(View view){
        //on click of add item button, go to add item view to add new item
        Intent addItem = new Intent(this, AddNewItem.class);
        addItem.putExtra("currentUserId", currentUserId);
        startActivity(addItem);
    }

    public void onListItemClick()
    {
        //on click of individual list item view
        Intent updateItem = new Intent(this, UpdateItem.class);
        updateItem.putExtra("itemId", selectedItemId);
        updateItem.putExtra("currentUserId", currentUserId);
        startActivity(updateItem);
    }

    public void onNotifyMeTextClicked(View view)
    {
        Intent notifyMe = new Intent(this, sms_consent.class);
        notifyMe.putExtra("currentUserId", currentUserId);
        startActivity(notifyMe);
    }

}