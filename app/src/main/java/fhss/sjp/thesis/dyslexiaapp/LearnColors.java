package fhss.sjp.thesis.dyslexiaapp;
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

        viewColor = (ImageView) findViewById(R.id.viewColor);
        colorName = (TextView) findViewById(R.id.colorName);

        textToSpeech = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status != TextToSpeech.ERROR){
                    textToSpeech.setLanguage(Locale.UK);
                }
            }
        });

        map.put("red","Red is the color that is on the outside edge of the rainbow. It is one of the three primary colors, along with blue and yellow. Red is the color of some apples and mostly, raspberries.\n" +
                "\n" +
                "Red is the color of some blood and the occasional cherry.\n" +
                "\n" +
                "It is sometimes used to mark things that are wrong, important or dangerous.\n" +
                "\n" +
                "Red is also commonly used as a warning to stop.");

        map.put("yellow","It's the color of happiness, and optimism, of enlightenment and creativity, sunshine and spring. Lurking in the background is the dark side of yellow");
        map.put("blue","Blue is the favorite color of all people. It's nature's color for water and sky, but is rarely found in fruits and vegetables.");
        map.put("orange", "Orange. Orange is softer and simpler in comparison to red. It represents happiness, sociability,");
        map.put("green", "Green stands for balance, nature, spring, and rebirth. It's the symbol of prosperity, freshness, and progress. ");
        map.put("purple", "Purple is associated with wisdom, dignity, independence, creativity, mystery and magic. Purple is a very rare color in nature, though the lavender flower");
    }

    public void putColour(View v){

        ImageButton imageButton =(ImageButton) v;
        int color = ((ColorDrawable) imageButton.getBackground()).getColor();

        viewColor.setBackgroundColor(color);
        colorName.setText(String.valueOf(v.getTag()).toUpperCase());
        colorName.setVisibility(View.VISIBLE);
        String text = String.valueOf(v.getTag());

        textToSpeech.setPitch(0);
        textToSpeech.setSpeechRate(0.25f);
        textToSpeech.speak(text,TextToSpeech.QUEUE_FLUSH,null   );
        textToSpeech.speak(map.get(text), TextToSpeech.QUEUE_ADD,null);
    }
}
