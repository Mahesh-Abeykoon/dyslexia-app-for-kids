package fhss.sjp.thesis.dyslexiaapp.learningmenu;


import android.content.Context;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import android.view.View;
import android.widget.Toast;

import com.google.mlkit.common.MlKitException;
import com.google.mlkit.common.model.DownloadConditions;
import com.google.mlkit.common.model.RemoteModelManager;
import com.google.mlkit.vision.digitalink.DigitalInkRecognition;
import com.google.mlkit.vision.digitalink.DigitalInkRecognitionModel;
import com.google.mlkit.vision.digitalink.DigitalInkRecognitionModelIdentifier;
import com.google.mlkit.vision.digitalink.DigitalInkRecognizer;
import com.google.mlkit.vision.digitalink.DigitalInkRecognizerOptions;
import com.google.mlkit.vision.digitalink.Ink;

import java.util.Locale;

import pl.droidsonroids.gif.GifImageView;

public class Global {
    public static String[] Options = {
            "cat",
            "buffalo",
            "cheetah",
            "cow",
            "elephant",
            "kangaroo",
            "leopard",
            "lioness",
            "parrot",
            "snake",
            "sparrow",
            "tiger",
            "kiwi",
            "apple",
            "guava",
            "grapes",
            "pineapple",
            "orange",
            "strawberry",
            "pomegranate",
            "lemon",
            "rose",
            "bouganvilla",
            "gulmohar",
            "flower"
    };

    static ArrayList<Integer> rand_gen = new ArrayList<Integer>();
    static DigitalInkRecognitionModelIdentifier modelIdentifier = null;
    static DigitalInkRecognitionModel model;
    public  static String recognizedObject = "";
    public static void intiatRandGen(int n) {

        for (int i = 0; i < n; i++) {
            rand_gen.add(i);

        }

    }

    public static String toTitleCase(String text) {
        if (text == null || text.isEmpty()) {
            return text;
        }

        StringBuilder converted = new StringBuilder();

        boolean convertNext = true;
        for (char ch : text.toCharArray()) {
            if (Character.isSpaceChar(ch)) {
                convertNext = true;
            } else if (convertNext) {
                ch = Character.toTitleCase(ch);
                convertNext = false;
            } else {
                ch = Character.toLowerCase(ch);
            }
            converted.append(ch);
        }

        return converted.toString();
    }

    public static boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch(NumberFormatException e){
            return false;
        }
    }

    public static String parseSpokenWordsToWord(String spoken_words, String matcher) {

        String[] words = spoken_words.toLowerCase().split(" ");
        String outtext = "";

        boolean iscap = false;

        for(String item : words){
            if (item.compareToIgnoreCase("Capital")==0){
                iscap = true;
            }
        }



        for (String item : words) {



            if (matcher.contains(item)) {
                if(isNumeric(item) || !iscap)
                {
                    outtext = outtext + item;
                }
                else{
                    outtext += item.toUpperCase();
                }

            }
            else{
                if(item.compareToIgnoreCase("zero")==0){
                    outtext += "0";
                }
            }

        }


        return outtext;


    }

//    public static void displayAnimation(GifImageView v, String subject){
//        int immg = getResources().getIdentifier(imageName,null, getPackageName());
//        textView3.setText(immg);
//        imageView3.setImageResource(immg);
//    }

    public static String responseOnReply(boolean quality){
        String[] pos_text = {"Fantastic","Correct","You are Right", "Great","Going good","Excellent"};
        String[] neg_text ={"Oops","Incorrect","wrong","think again"};
        Random rand = new Random();
        int len;
        if (quality){
            len = pos_text.length;
            return pos_text[rand.nextInt(len-1)];
        }
        else{
            len=neg_text.length;
            return neg_text[rand.nextInt(len-1)];

        }


    }

    public static boolean voiceReplyChecker(String checker, String value){
        Map map = new HashMap();
        map.put("kangaroo","kangaru");
        map.put("cheetah","chita,chitah,cheetah,cita,ceeta,cheater");
        map.put("siuli","shivli,shiuli,siuli");


        if(map.containsKey(checker)){
            String[] val = String.valueOf(map.get(checker)).split(",");

            for(String item : val){
                if(value.contains(item)){
                    return true;
                }

            }
            return false;

        }
        else{
            if(value.contains(checker)){
                return true;
            }
            else{
                return false;
            }
        }


    }


    public static void enableDisableButton(ArrayList<Button> buttons, boolean status){

        for (Button but : buttons){
            but.setEnabled(status);
        }
    }


    public static void showLetterAnimation(String letter, GifImageView imageView, Context context) {


        String imageName = "@drawable/" + letter;

        int immg = context.getResources().getIdentifier(imageName, null, context.getPackageName());

        imageView.setImageResource(immg);

    }




}
