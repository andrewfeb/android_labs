package com.example.retrofit;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

public class AlertFragment extends DialogFragment {

    public interface OnAlertListener{
        void onPositiveButtonClicked();
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setTitle("Warning")
                .setMessage("Apakah Anda ingin menghapus item ini?")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(getActivity(), "Clicked OK", Toast.LENGTH_SHORT).show();
                        OnAlertListener listener = (OnAlertListener) getActivity();
                        if (listener != null) {
                            listener.onPositiveButtonClicked();
                        }
                        //Toast.makeText(getActivity(), "How to use dialog in Android", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
        return builder.create();
    }
}
