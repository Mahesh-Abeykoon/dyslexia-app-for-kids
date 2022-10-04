package fhss.sjp.thesis.dyslexiaapp.speakingwriting;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Locale;

import fhss.sjp.thesis.dyslexiaapp.R;


public class WordPronunciationScreen extends AppCompatActivity {

    EditText Text;
    Button buttonText;
    TextToSpeech textToSpeech;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_pronunciation_screen);
        Text = findViewById(R.id.Text);
        buttonText = findViewById(R.id.buttonText);

        // here create an object textToSpeech and adding features into it
        textToSpeech = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int i) {

                // if there is No any error is found then it will run
                if(i!=TextToSpeech.ERROR){
                    // Here can be Chosen language of speech which we prefer
                    textToSpeech.setLanguage(Locale.UK);
                }
            }
        });

        buttonText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textToSpeech.speak(Text.getText().toString(),TextToSpeech.QUEUE_FLUSH,null);
            }
        });

    }
}
