package fhss.sjp.thesis.dyslexiaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void openLearnScreen(View v){
        Intent intent = new Intent(this,LearnScreen.class);
        startActivity(intent);
    }

    public void openTestScreen(View v){
        System.out.println("Test Screen");
    }
}
