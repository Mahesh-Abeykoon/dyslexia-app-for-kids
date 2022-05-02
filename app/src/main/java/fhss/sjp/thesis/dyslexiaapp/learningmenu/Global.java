package fhss.sjp.thesis.dyslexiaapp.learningmenu;


import android.content.Context;
import android.widget.Button;

import java.util.ArrayList;
import pl.droidsonroids.gif.GifImageView;

public class Global {

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
