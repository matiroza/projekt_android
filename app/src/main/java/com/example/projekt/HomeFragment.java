package com.example.projekt;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class HomeFragment extends Fragment {

    private Button btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9;
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        btn1 = view.findViewById(R.id.button_home_1);
        btn2 = view.findViewById(R.id.button_home_2);
        btn3 = view.findViewById(R.id.button_home_3);
        btn4 = view.findViewById(R.id.button_home_4);
        btn5 = view.findViewById(R.id.button_home_5);
        btn6 = view.findViewById(R.id.button_home_6);
        btn7 = view.findViewById(R.id.button_home_7);
        btn8 = view.findViewById(R.id.button_home_8);
        btn9 = view.findViewById(R.id.button_home_9);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent intent = new Intent(getContext(), ViewListContents.class);
               startActivity(intent);
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), ViewScrollContents.class);
                startActivity(intent);
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), AlarmActivity.class);
                startActivity(intent);
            }
        });

        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), GridViewContents.class);
                startActivity(intent);
            }
        });

        return view;
    }
}
