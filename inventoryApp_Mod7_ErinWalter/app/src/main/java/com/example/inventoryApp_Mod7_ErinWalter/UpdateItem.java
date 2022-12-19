package com.example.inventoryApp_Mod7_ErinWalter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.login_screen.R;

public class UpdateItem extends AppCompatActivity {
    private TextView itemNameText;
    private TextView itemDescriptionText;
    private TextView itemAmountText;
    private InventoryAppDatabase database;

    private InventoryItem currentItem;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_item);

        //set it up to pass current item in to this child
       // currentItem = getIntent();

        //associate all variables above with their associated UI elements
        itemNameText = findViewById(R.id.nameText);
        itemDescriptionText = findViewById(R.id.descriptionText);
        itemAmountText = findViewById(R.id.numberInStockText);

        database = new InventoryAppDatabase(getApplicationContext());

        int selectedItemId = getIntent().getExtras().getInt("itemId");
        currentItem = database.getItemById(selectedItemId);

        itemNameText.setText(currentItem.Name);
        itemDescriptionText.setText(currentItem.Description);
        itemAmountText.setText(Integer.toString(currentItem.Amount));

    }

    public void onCancelButtonClick(View view)
    {
        //redirect back to dashboard activity
        Intent dashboard = new Intent(this, MainDashboard.class);
        startActivity(dashboard);

    }


    public void onDeleteButtonClick(View view){
        boolean success = database.deleteItem(currentItem.Id);
        if(success)
        {
            //if successfully deleted then redirect back to dashboard after showing toast
            Toast.makeText(getApplicationContext(), "Successfully deleted item.",Toast.LENGTH_SHORT).show();

            Intent dashboard = new Intent(this, MainDashboard.class);
            startActivity(dashboard);
        }
        else {
            //if unable to delete then display toast
            Toast.makeText(getApplicationContext(), "Error: unable to delete",Toast.LENGTH_SHORT).show();
        }
    }

    public void onUpdateButtonClick(View view){
        //get values for all items
        String itemNameText_text = itemNameText.getText().toString();
        String itemDescriptionText_text = itemDescriptionText.getText().toString();
        int itemAmountText_text = Integer.parseInt(itemAmountText.getText().toString());

        boolean success = database.updateItem(currentItem.Id, itemNameText_text, itemDescriptionText_text, itemAmountText_text);

        if(success)
        {
            //add was successful so make a toast then redirect to dashboard
            Toast.makeText(getApplicationContext(), "Success!",Toast.LENGTH_SHORT).show();

            Intent dashboard = new Intent(this, MainDashboard.class);
            startActivity(dashboard);
        }
        else
        {
            //if fails then make toast that says error and do not redirect to dashboard
            Toast.makeText(getApplicationContext(), "Error: unable to update item, try again.",Toast.LENGTH_SHORT).show();
        }
    }

}
