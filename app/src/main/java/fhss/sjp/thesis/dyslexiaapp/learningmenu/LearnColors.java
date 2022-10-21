package fhss.sjp.thesis.dyslexiaapp.learningmenu;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import fhss.sjp.thesis.dyslexiaapp.R;

public class LearnColors extends AppCompatActivity {

    ImageView viewColor;
    TextView colorName;
    TextToSpeech textToSpeech;

    Map<String, String> map = new HashMap<>();

    @Override
    protected void onPause() {

        super.onPause();
        if (textToSpeech.isSpeaking()){
            textToSpeech.stop();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.learn_colors);

        viewColor = (ImageView) findViewById(R.id.colorViewer);
        colorName = (TextView) findViewById(R.id.nameOfColor);

        textToSpeech = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status != TextToSpeech.ERROR){
                    textToSpeech.setLanguage(Locale.UK);
                }
            }
        });

        map.put("red","Apple is red");
        map.put("yellow","Bananas are in yellow color");
        map.put("blue","Water and sky are blue color");
        map.put("orange", "Orange color is a nice color ");
        map.put("green", "Green vegetables are very healthy");
        map.put("purple", "Purple is a very rare color in nature");
        map.put("pink","The color pink, is the favourite color of girls");
        map.put("gray","Gray is the color of the elephant");
        map.put("black","Hair is black");
        map.put("white"," White is the lightest color");

    }

    public void putColour(View v){

        ImageButton imageButton =(ImageButton) v;
        int color = ((ColorDrawable) imageButton.getBackground()).getColor();

        viewColor.setBackgroundColor(color);
        colorName.setText(String.valueOf(v.getTag()).toUpperCase());
        colorName.setVisibility(View.VISIBLE);
        String text = String.valueOf(v.getTag());

        textToSpeech.setPitch(1);
        textToSpeech.setSpeechRate(0.20f);
        textToSpeech.speak(text,TextToSpeech.QUEUE_FLUSH,null );
        textToSpeech.speak(map.get(text), TextToSpeech.QUEUE_ADD,null);
    }
}
