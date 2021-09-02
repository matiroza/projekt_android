package com.example.projekt;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class ViewScrollContents extends AppCompatActivity {
    ViewGroup scrollViewgroup;
    ImageView imageSelected;

    Integer[] thumbnails = {R.drawable.img1, R.drawable.img2, R.drawable.img3,
            R.drawable.img4, R.drawable.img5};
    String[] items = {"jakaś góra 1", "jakaś góra 2", "jakaś góra 3", "jakaś góra 4", "jakaś góra 5"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_scroll_contents);

        scrollViewgroup = (ViewGroup) findViewById(R.id.viegroup);
        for (int crrImg = 0; crrImg < items.length; crrImg++) {
            addImage(crrImg);
        }

    }

    private void addImage(int crrImg) {
        @SuppressLint("InflateParams") final View singleFrame = getLayoutInflater().inflate(R.layout.frame_icon_caption, null);
        singleFrame.setId(crrImg);
        TextView caption = (TextView) singleFrame.findViewById(R.id.caption);
        ImageView icon = (ImageView) singleFrame.findViewById(R.id.icon);

        icon.setImageResource(thumbnails[crrImg]);
        caption.setText(items[crrImg]);

        scrollViewgroup.addView(singleFrame);

    }
}