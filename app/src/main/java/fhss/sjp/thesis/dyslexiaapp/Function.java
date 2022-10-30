package fhss.sjp.thesis.dyslexiaapp;


import android.content.Context;
import android.widget.Button;

import com.google.mlkit.vision.digitalink.DigitalInkRecognitionModel;
import com.google.mlkit.vision.digitalink.DigitalInkRecognitionModelIdentifier;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import pl.droidsonroids.gif.GifImageView;

public class Function {

        public static ArrayList<Integer> rand_gen = new ArrayList<Integer>();
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

        public static String quickResponse(boolean isCorrect){
            String[] replyIfCorrect = {"Yes you are Correct","Yes It is","You are Right", "Great go on","Excellent"};
            String[] replyIfIncorrect ={"No it is not correct","It is Wrong","No Try again"};
            Random random = new Random();
            int length;
            if (isCorrect){
                length = replyIfCorrect.length;
                return replyIfCorrect[random.nextInt(length-1)];
            }
            else{
                length=replyIfIncorrect.length;
                return replyIfIncorrect[random.nextInt(length-1)];
            }
        }


        public static void enableDisableButton(ArrayList<Button> buttons, boolean status){

            for (Button but : buttons){
                but.setEnabled(status);
            }
        }

        public static void showLetterAnimation(String letter, GifImageView imageView, Context context) {


            String imageName = "@drawable/" + letter;

            int image = context.getResources().getIdentifier(imageName, null, context.getPackageName());

            imageView.setImageResource(image);

        }

}
