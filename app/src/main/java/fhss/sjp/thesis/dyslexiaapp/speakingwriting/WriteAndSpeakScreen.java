package fhss.sjp.thesis.dyslexiaapp.speakingwriting;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;


import androidx.appcompat.app.AppCompatActivity;

import fhss.sjp.thesis.dyslexiaapp.R;
import fhss.sjp.thesis.dyslexiaapp.learningmenu.LearnColors;


public class WriteAndSpeakScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.write_speak_screen);
    }

    public void openWordPronunciationScreen(View v){
        Intent intent = new Intent(this, WordPronunciationScreen.class);
        startActivity(intent);
    }






}
