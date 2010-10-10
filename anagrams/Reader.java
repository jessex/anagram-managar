package anagrams;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class Reader {

    /**
     * Reads in a file of text and returns a String array of each "word" where
     * a "word" is any contiguous string of characters. Words are separated by
     * whitespace. Returned words are lower case to maintain case-insensitivity
     * and trailing punctuation is discarded.
     * @param fileName - String
     * @return words from file
     * @throws IOException
     */
    public static String[] readFromFile(String fileName) throws IOException {
        String[] txt = {};
        String str = "";
        try {
            Scanner input = new Scanner(new File(fileName));
            //Reads in entire file of text and creates one large string
            String text = "";

            while(input.hasNextLine()){
                text += input.nextLine() + " ";
            }
            // Splits large string of text into substrings by whitespace
            txt = text.split(" ");

            for(int i=0; i<txt.length; i++){
                char[] check = txt[i].toCharArray();
                for(int j=0; j<check.length; j++){
                    //Converts any upper case chars in each word to lower case
                    if(Character.isUpperCase(check[j])){
                        check[j] = Character.toLowerCase(check[j]);
                    }
                    //Rids string of any trailing punctuation
                    if(!Character.isLetter(check[j])){
                        if(j==(check.length-1)){
                            char[] newAlpha = new char[check.length-1];
                            System.arraycopy(check, 0, newAlpha, 0, check.length - 1);
                            check = newAlpha;
                        }
                    }
                }
                String checked = new String(check);
                txt[i] = checked;
            }
            return txt;
        } catch (FileNotFoundException ex) {
            System.out.println("File not found. Terminating program.");
            System.exit(0);
        }
        return txt;
    }

    /**
     * Returns the alphabetized version of the passed in String. Example: name
     * returns aemn, apple returns aelpp. Disregards contractions, keeps words
     * with trailing punctuation with it removed.
     * @param str - String
     * @return alphabetized String
     */
    public static String alphabetize(String str) {
        String alpha = "";
        //Converts passed string to character array
        char[] alphaBet = str.toCharArray();
        int i = alphaBet.length;
        //Checks for non-letter characters
        for(int j = 0; j<i; j++){
            if(!Character.isLetter(alphaBet[j])){
                //If there is punctuation in word, but at very end
                if(j==(i-1)){
                    //Adds all characters up until punctuation into new place-
                    //Holding array and sets values to old array
                    char[] newAlpha = new char[alphaBet.length-1];
                    for(int a = 0; a<(i-1); a++){
                        newAlpha[a] = alphaBet[a];
                    }
                    alphaBet = newAlpha;
                }
                else{
                    //Returns a null string if there is punctuation within the
                    //string, such as a contraction, which will be disregarded
                    alpha = null;
                    return alpha;
                }
            }
        }
        //Sorts the character array alphabetically and converts to string
        Arrays.sort(alphaBet);
        alpha = new String(alphaBet);
        return alpha;
    }
}
