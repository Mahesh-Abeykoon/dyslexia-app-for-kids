package fhss.sjp.thesis.dyslexiaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import fhss.sjp.thesis.dyslexiaapp.speakingwriting.WriteAndSpeakScreen;
import fhss.sjp.thesis.dyslexiaapp.videolesson.VideoLessonScreen;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        setTheme(R.style.SplashScreenTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void openLearnScreen(View v){
        Intent intent = new Intent(this,LearnScreen.class);
        startActivity(intent);
    }
    public void openVideoLessonScreen(View v){
        Intent intent = new Intent(this, VideoLessonScreen.class);
        startActivity(intent);
    }
    public void openWriteAndSpeakScreen(View v){
        Intent intent = new Intent(this, WriteAndSpeakScreen.class);
        startActivity(intent);
    }
    public void openTestScreen(View v){
        Intent intent = new Intent(this,TestScreen.class);
        startActivity(intent);
    }
}
