package com.example.projekt;

import android.Manifest;
import android.app.AppComponentFactory;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleObserver;

public class MessageFragment extends Fragment  {

    private final static int REQUEST_SMS = 1;
    private EditText editTextNumber, editTextMessage;
    private Button buttonSend;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_message, container, false);
        editTextNumber = view.findViewById(R.id.inputNumber);
        editTextMessage = view.findViewById(R.id.inputMessage);
        buttonSend = view.findViewById(R.id.buttonSend);

        buttonSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeSmsSend();
            }
        });

        return view;
    }

    private void makeSmsSend(){
        String number = editTextNumber.getText().toString();
        String msg = editTextMessage.getText().toString();
        if(number.trim().length() > 0 ){
            if(ContextCompat.checkSelfPermission(getContext(), Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED){
                ActivityCompat.requestPermissions(getActivity(), new String[] {Manifest.permission.SEND_SMS}, REQUEST_SMS);
            } else {
                SmsManager smsManager = SmsManager.getDefault();
                smsManager.sendTextMessage(number,null,msg,null,null);
                Toast.makeText(getContext(), "Message Send!", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(getContext(), "Enter Phone Number", Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode == REQUEST_SMS){
            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                makeSmsSend();
            } else {
                Toast.makeText(getContext(), "Permission Denied", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
