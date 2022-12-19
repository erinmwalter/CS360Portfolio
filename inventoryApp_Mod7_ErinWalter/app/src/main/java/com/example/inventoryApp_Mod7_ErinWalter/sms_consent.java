package com.example.inventoryApp_Mod7_ErinWalter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.login_screen.R;

public class sms_consent extends AppCompatActivity {
    private Button yesButton;
    private Button noButton;
    private long currentUserId;
    private InventoryAppDatabase database;
    private Intent returnHome;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sms_consent);

        yesButton = findViewById(R.id.consentYesButton);
        noButton = findViewById(R.id.consentNoButton);
        database = new InventoryAppDatabase(getApplicationContext());
        returnHome = new Intent(this, MainDashboard.class);

        currentUserId = getIntent().getExtras().getLong("currentUserId");
    }

    public void onYesButtonClick(View view)
    {
        boolean success = database.updateSMSConsent(currentUserId, true);
        if(success)
        {
            Toast.makeText(getApplicationContext(), "Successfully updated SMS Consent.",Toast.LENGTH_SHORT).show();
        }
        startActivity(returnHome);
    }

    public void onNoButtonClick(View view)
    {
        boolean success = database.updateSMSConsent(currentUserId, false);
        if(success)
        {
            Toast.makeText(getApplicationContext(), "Successfully updated SMS Consent.",Toast.LENGTH_SHORT).show();
        }
        startActivity(returnHome);
    }
}