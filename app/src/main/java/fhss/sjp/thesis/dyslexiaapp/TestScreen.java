package fhss.sjp.thesis.dyslexiaapp;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import fhss.sjp.thesis.dyslexiaapp.examinemenu.ImageIdentify;

public class TestScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_screen);
    }

    public void openImageIdentifyScreen(View v){
        Intent intent = new Intent(this, ImageIdentify.class);
        startActivity(intent);

    }
}
