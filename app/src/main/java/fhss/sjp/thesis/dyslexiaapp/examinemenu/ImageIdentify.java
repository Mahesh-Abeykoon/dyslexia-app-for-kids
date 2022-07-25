package fhss.sjp.thesis.dyslexiaapp.examinemenu;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Dictionary;
import java.util.Locale;
import java.util.Random;

import fhss.sjp.thesis.dyslexiaapp.Function;
import fhss.sjp.thesis.dyslexiaapp.R;
import pl.droidsonroids.gif.GifImageView;


public class ImageIdentify extends AppCompatActivity {

    Random rand;
    GifImageView imageView3;
    WebView webView;
    TextToSpeech tts ;
    TextView footNote ;
    String text, correct_ans, option1,option2;
    String  imageName;
    public static final int VOICE_RECOGNITION_REQUEST_CODE = 1234;
    ImageButton btnSpeak;

    Dictionary dict;
    ArrayList<Button> optionButtons;

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
        setContentView(R.layout.image_identify);

        optionButtons = new ArrayList<Button>();

        footNote = (TextView) findViewById(R.id.footNote) ;
        imageView3 = (GifImageView) findViewById(R.id.imageView3);
        tts = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status != TextToSpeech.ERROR){
                    tts.setLanguage(Locale.US);
                }
            }
        });

        optionButtons.add((Button) findViewById(R.id.button27));
        optionButtons.add((Button) findViewById(R.id.button28));
        optionButtons.add((Button) findViewById(R.id.button29));
        btnSpeak = (ImageButton) findViewById(R.id.btn_speak2);
        webView = new WebView(this);
        webView = (WebView) findViewById(R.id.webView1) ;

        btnSpeak.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                startVoiceRecognitionActivity(v);
            }
        });


        final Activity activity = this;

        webView.setWebViewClient( new WebViewClient(){
            @SuppressWarnings("deprecation")
            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                Toast.makeText(activity, description, Toast.LENGTH_SHORT).show();
            }
            @TargetApi(android.os.Build.VERSION_CODES.M)
            @Override
            public void onReceivedError(WebView view, WebResourceRequest req, WebResourceError rerr) {
                onReceivedError(view, rerr.getErrorCode(), rerr.getDescription().toString(), req.getUrl().toString());
            }
        });


        rand = new Random();

        intitOptions();

    }

    public  void intitOptions(){
        int correct_index;
        String text, option1,option2;

        Function.rand_gen.clear();
        for (int i = 0; i< Function.Options.length; i++){
            Function.rand_gen.add(i);
        }
        Collections.shuffle(Function.rand_gen);


        correct_index = Function.rand_gen.get(0);
        text = Function.Options[correct_index];
        option1  = Function.Options[(correct_index%23)+1].toUpperCase();
        option2 = Function.Options[(correct_index%23)+2].toUpperCase();

        String imageName = "@drawable/"+text;
        correct_ans = text.toUpperCase();
        int immg = getResources().getIdentifier(imageName,null, getPackageName());
        imageView3.setImageResource(immg);

        Function.rand_gen.clear();

        for (int i=0;i<3;i++){
            Function.rand_gen.add(i);

        }
        Collections.shuffle(Function.rand_gen);

        Function.enableDisableButton(optionButtons,true);
        btnSpeak.setEnabled(true);
        optionButtons.get(Function.rand_gen.get(0)).setText(correct_ans);
        optionButtons.get(Function.rand_gen.get(1)).setText(option1);
        optionButtons.get(Function.rand_gen.get(2)).setText(option2);

        tts.speak("What is this ? ", TextToSpeech.QUEUE_FLUSH, null   );

    }

    public void nextQuiz(View v){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        try{
            intitOptions();
        }catch(ArrayIndexOutOfBoundsException e){
            builder.setMessage(e.getMessage());
            builder.show();
        }

    }

    public void checkAnswer(View v){

        Button replied = (Button) v;

        String replytext = (String) replied.getText();
        tts.setSpeechRate(.75f);
        if(replytext == correct_ans){
            tts.speak(Function.responseOnReply(true), TextToSpeech.QUEUE_FLUSH, null);
            Function.enableDisableButton(optionButtons,false);
            btnSpeak.setEnabled(false);

        }
        else {
            tts.speak(Function.responseOnReply(false)+", this is a ," + correct_ans, TextToSpeech.QUEUE_FLUSH, null);

        }
    }


    public void openWiki(View v){
        for (int i =0; i<3 ; i++){
            optionButtons.get(i).setVisibility(v.INVISIBLE);

        }
        webView.setVisibility(v.VISIBLE);

        webView.loadUrl("https://en.wikipedia.org/wiki/"+Function.toTitleCase(correct_ans.toLowerCase()));


    }

    public void closeWebView(View v){
        for (int i =0; i<3 ; i++){
            optionButtons.get(i).setVisibility(v.VISIBLE);


        }

        webView.setVisibility(v.INVISIBLE);

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


            String spoken_words = matches.get(0).toString().toLowerCase();


        }
    }

}
