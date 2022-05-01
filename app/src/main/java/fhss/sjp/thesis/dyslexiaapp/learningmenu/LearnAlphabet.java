package fhss.sjp.thesis.dyslexiaapp.learningmenu;
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

import java.util.ArrayList;
import java.util.Locale;

public class LearnAlphabet extends AppCompatActivity {


    ArrayList<Button> buttons;
    ImageButton btnSpeak;
    TextView textView;
    ImageButton capsButton, toggleNumeric, backSpace;
    boolean capsed = false, numview=false;
    TextView textView4;
    String alphas = "abcdefghijklmnopqrstuvwxyz";
    public static final int VOICE_RECOGNITION_REQUEST_CODE = 1234;

    TextToSpeech tts ;

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
        setContentView(R.layout.learn_alphabet);

        buttons = new ArrayList<Button>();
        buttons.add( (Button) findViewById(R.id.buttonA));
        buttons.add( (Button) findViewById(R.id.buttonB));
        buttons.add( (Button) findViewById(R.id.buttonC));
        buttons.add( (Button) findViewById(R.id.buttonD));
        buttons.add( (Button) findViewById(R.id.buttonE));
        buttons.add( (Button) findViewById(R.id.buttonF));
        buttons.add( (Button) findViewById(R.id.buttonG));
        buttons.add( (Button) findViewById(R.id.buttonH));
        buttons.add( (Button) findViewById(R.id.buttonI));
        buttons.add( (Button) findViewById(R.id.buttonJ));
        buttons.add( (Button) findViewById(R.id.buttonK));
        buttons.add( (Button) findViewById(R.id.buttonL));
        buttons.add( (Button) findViewById(R.id.buttonM));
        buttons.add( (Button) findViewById(R.id.buttonN));
        buttons.add( (Button) findViewById(R.id.buttonO));
        buttons.add( (Button) findViewById(R.id.buttonP));
        buttons.add( (Button) findViewById(R.id.buttonQ));
        buttons.add( (Button) findViewById(R.id.buttonR));
        buttons.add( (Button) findViewById(R.id.buttonS));
        buttons.add( (Button) findViewById(R.id.buttonT));
        buttons.add( (Button) findViewById(R.id.buttonU));
        buttons.add( (Button) findViewById(R.id.buttonV));
        buttons.add( (Button) findViewById(R.id.buttonW));
        buttons.add( (Button) findViewById(R.id.buttonX));

        buttons.add( (Button) findViewById(R.id.buttonY));
        buttons.add( (Button) findViewById(R.id.buttonZ));
        //buttons.add( (Button) findViewById(R.id.button28));

        capsButton = (ImageButton) findViewById(R.id.capsButton2) ;
        toggleNumeric =  (ImageButton) findViewById(R.id.toggleNumeric2);

        btnSpeak = (ImageButton) findViewById(R.id.btn_speak2);



        textView = (TextView) findViewById(R.id.imageView3);


        tts = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status != TextToSpeech.ERROR){
                    tts.setLanguage(Locale.US);
                }
            }
        });

        btnSpeak.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                startVoiceRecognitionActivity(v);
            }
        });
    }

    public void startVoiceRecognitionActivity(View v) {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT,
                "Speech recognition demo");
        startActivityForResult(intent, VOICE_RECOGNITION_REQUEST_CODE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == VOICE_RECOGNITION_REQUEST_CODE && resultCode == RESULT_OK) {

            ArrayList matches = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);

            String spoken_words = String.valueOf(matches.get(0));

            String text = Global.parseSpokenWordsToWord(spoken_words,alphas+"1234567890");

            textView.setText(textView.getText()+text);
            tts.speak(text,TextToSpeech.QUEUE_ADD,null);



        }
    }
    public void displayLetter(View ae){
        Button but = (Button) ae;
        String res = (String) but.getText();
        textView.setText(textView.getText()+res);
        tts.speak(res,TextToSpeech.QUEUE_ADD,null);

    }

    public void capsUp(View v){
        for (int i = 0; i < 26; i++){
            String str = (String) buttons.get(i).getText();
            if(!capsed)
                buttons.get(i).setText(str.toUpperCase());
            else
                buttons.get(i).setText(str.toLowerCase());
        }
        //capsButton.setTextColor(Color.argb(100,109,128,143));
        capsed = !capsed;
        if(capsed){
            capsButton.setImageAlpha(70);
            tts.speak("Capital Letters?",TextToSpeech.QUEUE_ADD, null  );


        }
        else {
            capsButton.setImageAlpha(200);
            tts.speak("Small case letters",TextToSpeech.QUEUE_ADD, null  );

        }
    }

    public void deleteForward(View v){
        int len = textView.getText().length();

        String text = (String)textView.getText();
        if(len > 0){
            text = text.substring(0, len-1);
            textView.setText(text);
        }

    }

    public void changeToNumeric(View v){
        String res= "";
        int i = 0, j=0;
        if(!numview){
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
            numview = true;
            tts.speak("Numbers", TextToSpeech.QUEUE_ADD,null);
        }else{

            for(int x = 0; x<alphas.length(); x++){
                buttons.get(x).setText(String.valueOf(alphas.charAt(x)));
            }
            capsButton.setEnabled(!capsButton.isEnabled());
            toggleNumeric.setImageResource(R.drawable.sort_numeric_descending_icon_136185);
            numview = false;
            tts.speak("Alphabets",TextToSpeech.QUEUE_ADD, null  );


        }


    }




}


