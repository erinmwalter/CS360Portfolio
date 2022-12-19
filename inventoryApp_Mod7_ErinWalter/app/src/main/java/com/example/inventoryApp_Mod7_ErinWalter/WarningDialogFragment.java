package com.example.inventoryApp_Mod7_ErinWalter;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

public class WarningDialogFragment extends DialogFragment {
    public interface OnSMSConsentSelectedListener {
        void onConsentClick(int which);
    }

    private OnSMSConsentSelectedListener mListener;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder =
                new AlertDialog.Builder(getActivity());
        builder.setTitle("SMS Consent");
        builder.setMessage("Do you consent to receiving SMS Notifications to your phone when the stock is low?");
        builder.setPositiveButton("Yes", null);
        builder.setNegativeButton("No", null);
        return builder.create();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mListener = (OnSMSConsentSelectedListener) context;
    }

    @Override
    public void onDetach()
    {
        super.onDetach();
        mListener = null;
    }
}
