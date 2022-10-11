package fhss.sjp.thesis.dyslexiaapp.videolesson;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import fhss.sjp.thesis.dyslexiaapp.R;

import fhss.sjp.thesis.dyslexiaapp.videolesson.alphabet.EngAlpScreen;
import fhss.sjp.thesis.dyslexiaapp.videolesson.hodiya.HodiyaScreen;

public class VideoLessonScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.video_lesson_screen);
    }

    public void openHodiyaScreen(View v){
        Intent intent = new Intent(this, HodiyaScreen.class);
        startActivity(intent);
    }

    public void openEngAlpScreen(View v){
        Intent intent = new Intent(this, EngAlpScreen.class);
        startActivity(intent);
    }





}
