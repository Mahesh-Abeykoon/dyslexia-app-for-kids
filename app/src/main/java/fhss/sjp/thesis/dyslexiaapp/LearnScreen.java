package fhss.sjp.thesis.dyslexiaapp;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;


import androidx.appcompat.app.AppCompatActivity;

import fhss.sjp.thesis.dyslexiaapp.learningmenu.LearnAlphabet;
import fhss.sjp.thesis.dyslexiaapp.learningmenu.LearnAlphabetWriting;
import fhss.sjp.thesis.dyslexiaapp.learningmenu.LearnColors;

public class LearnScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.learn_screen);
    }

    public void openColorsLearnScreen(View v){
        Intent intent = new Intent(this, LearnColors.class);
        startActivity(intent);
    }

    public void openAlphabetLearnScreen(View v){
        Intent intent = new Intent(this, LearnAlphabet.class);
        startActivity(intent);
    }
    public void openAlphabetLearnWritingScreen(View v){
        Intent intent = new Intent(this, LearnAlphabetWriting.class);
        startActivity(intent);
    }

}
