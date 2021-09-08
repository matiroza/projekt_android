package com.example.projekt;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

public class VideoMusicActivity extends AppCompatActivity {

    private VideoView videoView1, videoView2, videoView3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_music);

        videoView1 = findViewById(R.id.videoView1);
        videoView2 = findViewById(R.id.videoView2);

        videoView1.setVideoPath("android.resource://"+getPackageName()+"/"+R.raw.red_bull_ski);
        videoView2.setVideoPath("android.resource://"+getPackageName()+"/"+R.raw.red_bull_speedriding);

        MediaController mediaController1 = new MediaController(this);
        mediaController1.setAnchorView(videoView1);
        videoView1.setMediaController(mediaController1);

        MediaController mediaController2 = new MediaController(this);
        mediaController2.setAnchorView(videoView2);
        videoView2.setMediaController(mediaController2);

    }
}