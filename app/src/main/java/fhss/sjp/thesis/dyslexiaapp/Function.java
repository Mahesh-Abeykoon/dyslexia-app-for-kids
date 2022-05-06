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
        public static String[] Options = {
                "leopard",
                "sparrow",
                "cheetah",
                "grapes",
                "rose",
                "cat",
                "flower",
                "apple",
                "guava",
                "orange",
                "buffalo",
                "strawberry",
                "elephant",
                "kangaroo",
                "lioness",
                "parrot",
                "snake",
                "pineapple",
                "cow",
                "bouganvilla",
                "gulmohar",
                "pomegranate",
                "lemon",
                "tiger",
                "kiwi",
        };

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

        public static String responseOnReply(boolean quality){
            String[] positive_text = {"Fantastic","Correct","You are Right", "Great","Going good","Excellent"};
            String[] negative_text ={"Oops","Incorrect","wrong","think again"};
            Random rand = new Random();
            int len;
            if (quality){
                len = positive_text.length;
                return positive_text[rand.nextInt(len-1)];
            }
            else{
                len=negative_text.length;
                return negative_text[rand.nextInt(len-1)];

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
