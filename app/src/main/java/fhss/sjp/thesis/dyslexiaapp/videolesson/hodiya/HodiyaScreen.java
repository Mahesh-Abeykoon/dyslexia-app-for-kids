package fhss.sjp.thesis.dyslexiaapp.videolesson.hodiya;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import fhss.sjp.thesis.dyslexiaapp.R;

public class HodiyaScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hodiya_screen);
    }

    public void openHodiya1Screen(View v){
        Intent intent = new Intent(this, Hodiya1.class);
        startActivity(intent);
    }

    public void openHodiya2Screen(View v){
        Intent intent = new Intent(this, Hodiya2.class);
        startActivity(intent);
    }

    public void openHodiya3Screen(View v){
        Intent intent = new Intent(this, Hodiya3.class);
        startActivity(intent);
    }



}
