package fhss.sjp.thesis.dyslexiaapp.videolesson.alphabet;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import fhss.sjp.thesis.dyslexiaapp.R;


public class EngAlpScreen extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.eng_alp_screen);
    }
    public void openAplhaScreen(View v){
        Intent intent = new Intent(this, AlphaWords.class);
        startActivity(intent);
    }


}
