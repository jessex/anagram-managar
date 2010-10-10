package anagrams;

import java.io.IOException;

public class Main {

    public static void main(String[] args) {

        if (args != null && args.length > 0) {
            String fileName = args[0];
            Tree tree = new Tree(); //Initialize Binary tree
            
            try {
                String[] al = Reader.readFromFile(fileName);
                for(int j=0; j<al.length; j++){
                    //If alphabetized string is null or blank, not added into tree
                    if(Reader.alphabetize(al[j]) != null &&
                            al[j].length() > 1){  //If string is length 1, not added
                        tree.insert(al[j], Reader.alphabetize(al[j]));
                    }
                }
                tree.findAnagrams(tree.getRoot()); //Find the anagrams in the tree
                tree.printAnagrams(); //Print out all found anagrams
            } catch (IOException ex) {
                System.out.println("File could not be read.");
                System.exit(1);
            }

        }
        else {
            System.out.println("Please include the name of the file to analyze" +
                    " as a command line argument.");
        }
        System.exit(0);
    }
}
