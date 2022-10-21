package fhss.sjp.thesis.dyslexiaapp.learningmenu;

import fhss.sjp.thesis.dyslexiaapp.Function;
import fhss.sjp.thesis.dyslexiaapp.R;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.Locale;

import pl.droidsonroids.gif.GifImageView;

public class LearnAlphabetWriting extends AppCompatActivity {

    ArrayList<Button> buttons;
    ImageButton buttonSpeak;
    boolean numberView = false;
    ImageButton capsButton,toggleNumeric;
    boolean capsed = false;
    GifImageView imageView3;
    TextToSpeech tts ;
    public static final int VOICE_RECOGNITION_REQUEST_CODE = 1234;

    @Override
    protected void onPause() {

        super.onPause();
        if (tts.isSpeaking()){
            tts.stop();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.learn_alphabet_writing);


        buttons = new ArrayList<Button>();
        buttons.add((Button) findViewById(R.id.buttonA));
        buttons.add((Button) findViewById(R.id.buttonB));
        buttons.add((Button) findViewById(R.id.buttonC));
        buttons.add((Button) findViewById(R.id.buttonD));
        buttons.add((Button) findViewById(R.id.buttonE));
        buttons.add((Button) findViewById(R.id.buttonF));
        buttons.add((Button) findViewById(R.id.buttonG));
        buttons.add((Button) findViewById(R.id.buttonH));
        buttons.add((Button) findViewById(R.id.buttonI));
        buttons.add((Button) findViewById(R.id.buttonJ));
        buttons.add((Button) findViewById(R.id.buttonK));
        buttons.add((Button) findViewById(R.id.buttonL));
        buttons.add((Button) findViewById(R.id.buttonM));
        buttons.add((Button) findViewById(R.id.buttonN));
        buttons.add((Button) findViewById(R.id.buttonO));
        buttons.add((Button) findViewById(R.id.buttonP));
        buttons.add((Button) findViewById(R.id.buttonQ));
        buttons.add((Button) findViewById(R.id.buttonR));
        buttons.add((Button) findViewById(R.id.buttonS));
        buttons.add((Button) findViewById(R.id.buttonT));
        buttons.add((Button) findViewById(R.id.buttonU));
        buttons.add((Button) findViewById(R.id.buttonV));
        buttons.add((Button) findViewById(R.id.buttonW));
        buttons.add((Button) findViewById(R.id.buttonX));

        buttons.add((Button) findViewById(R.id.buttonY));
        buttons.add((Button) findViewById(R.id.buttonZ));

        capsButton = (ImageButton) findViewById(R.id.capsButton2);
        toggleNumeric = (ImageButton) findViewById(R.id.toggleNumeric2);
        buttonSpeak = (ImageButton) findViewById(R.id.btn_speak2);

        tts = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status != TextToSpeech.ERROR){
                    tts.setLanguage(Locale.US);
                }
            }
        });

        buttonSpeak.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                startVoiceRecognitionActivity(v);
            }
        });


        imageView3 = (GifImageView) findViewById(R.id.imageView3);

    }

    public void startVoiceRecognitionActivity(View v) {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT,
                "Speech recognition demo");
        startActivityForResult(intent, VOICE_RECOGNITION_REQUEST_CODE);
    }

    public void showLetterAnimation(View view) {
        Button button = (Button) view;
        String text = (String) button.getText();
        String res = text;
        if (!capsed) res=res+"1";
        else res=res.toLowerCase();

        if (numberView){
            res="number"+res;
        }

        Function.showLetterAnimation(res,imageView3,getApplicationContext());
        tts.speak(text,TextToSpeech.QUEUE_ADD, null  );


    }



    public void capsUp(View v) {
        for (int i = 0; i < 26; i++) {
            String str = (String) buttons.get(i).getText();
            if (!capsed)
                buttons.get(i).setText(str.toUpperCase());
            else
                buttons.get(i).setText(str.toLowerCase());
        }
        capsed = !capsed;
        if (capsed) {
            capsButton.setImageAlpha(70);
            tts.speak("Upper case letters",TextToSpeech.QUEUE_FLUSH, null  );

        } else {
            capsButton.setImageAlpha(200);
            tts.speak("Lower Case Letters",TextToSpeech.QUEUE_ADD, null  );
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == VOICE_RECOGNITION_REQUEST_CODE && resultCode == RESULT_OK) {

            ArrayList matches = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);

            String alphas = "abcdefghijklmnopqrstuvwxyz1234567890";

            String spoken_words = String.valueOf(matches.get(0));

            String speech;

            String text = Function.parseSpokenWordsToWord(spoken_words, alphas);


            if(text!="") {

                text = String.valueOf(text.charAt(0));
                speech = text;

                if(Function.isNumeric(text)){
                    text = "number"+text;

                }else {
                    if(Character.isUpperCase(text.charAt(0))){
                        text = text.toLowerCase();
                    }
                    else
                    {
                        text = text+"1";
                    }
                }

                String imageName = "@drawable/"+text;

                int immg = getResources().getIdentifier(imageName, null, getPackageName());
                //textView3.setText(immg);
                imageView3.setImageResource(immg);

                tts.speak(speech, TextToSpeech.QUEUE_ADD, null);
            }
            else{
                Toast.makeText(getApplicationContext(),"Can't take any action",Toast.LENGTH_LONG).show();
            }
        }

    }
    public void changeToNumeric(View v){
        String alphas= "abcdefghijklmnopqrstuvwxyz";
        int i = 0, j=0;
        if(!numberView){
            while (i<26){
                if (i==6 || i>10){
                    buttons.get(i).setText("");
                }
                else{
                    buttons.get(i).setText(String.valueOf(j));
                    j++;
                }
                i++;
            }
            capsButton.setEnabled(!capsButton.isEnabled());
            toggleNumeric.setImageResource(R.drawable.numtoalpha);
            numberView = true;
            capsed = true;
            tts.speak("Numbers",TextToSpeech.QUEUE_ADD, null  );
        }else{

            for(int x = 0; x<alphas.length(); x++){
                buttons.get(x).setText(String.valueOf(alphas.charAt(x)));
            }
            capsButton.setEnabled(!capsButton.isEnabled());
            toggleNumeric.setImageResource(R.drawable.i_sort);
            numberView = false;
            capsed = false;
            tts.speak("Alphabets",TextToSpeech.QUEUE_ADD, null  );


        }


    }

}
