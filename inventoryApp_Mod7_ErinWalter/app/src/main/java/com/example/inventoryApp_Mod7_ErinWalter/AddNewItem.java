package com.example.inventoryApp_Mod7_ErinWalter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.login_screen.R;

public class AddNewItem extends AppCompatActivity {
    private TextView itemNameText;
    private TextView itemDescriptionText;
    private TextView itemAmountText;
    private Button addItemButton;
    private Button cancelButton;
    private InventoryAppDatabase database;
    private int currentUserId;

    private TextWatcher enableButtonsTextWatcher = new TextWatcher() {

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            String itemNameText_text = itemNameText.getText().toString();
            String itemDescriptionText_text = itemDescriptionText.getText().toString();
            String itemAmountText_text = itemAmountText.getText().toString();

            //addbutton will only be enabled if all three of the fields are filled in
            addItemButton.setEnabled(!itemNameText_text.isEmpty() && !itemDescriptionText_text.isEmpty()
                                    && !itemAmountText_text.isEmpty());

        }

        @Override
        public void afterTextChanged(Editable editable) {}
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_item);

        //associate all variables above with their associated UI elements
        itemNameText = findViewById(R.id.nameText);
        itemDescriptionText = findViewById(R.id.descriptionText);
        itemAmountText = findViewById(R.id.numberInStockText);
        addItemButton = findViewById(R.id.updateButton);
        cancelButton = findViewById(R.id.cancelButton);
        currentUserId = getIntent().getExtras().getInt("currentUserId");

        database = new InventoryAppDatabase(getApplicationContext());

        //add listener to enable/disable login and create account buttons
        itemNameText.addTextChangedListener(enableButtonsTextWatcher);
        itemDescriptionText.addTextChangedListener(enableButtonsTextWatcher);
        itemAmountText.addTextChangedListener(enableButtonsTextWatcher);

    }

    public void onAddItemButtonClick(View view){
        //get values for all items
        String itemNameText_text = itemNameText.getText().toString();
        String itemDescriptionText_text = itemDescriptionText.getText().toString();
        int itemAmountText_text = Integer.parseInt(itemAmountText.getText().toString());


        Long id = database.addInventoryItem(itemNameText_text, itemDescriptionText_text, itemAmountText_text);

        if(id != -1)
        {
            //add was successful so make a toast then redirect to dashboard
            Toast.makeText(getApplicationContext(), "Success!",Toast.LENGTH_SHORT).show();

            Intent dashboard = new Intent(this, MainDashboard.class);
            startActivity(dashboard);
        }
        else
        {
            //if fails then make toast that says error and do not redirect to dashbboard
            Toast.makeText(getApplicationContext(), "Error: Unable to add item, try again.",Toast.LENGTH_SHORT).show();
        }


    }

    public void onCancelButtonClick(View view)
    {
        //redirect user back to main dashboard
        Intent dashboard = new Intent(this, MainDashboard.class);
        startActivity(dashboard);

    }

}