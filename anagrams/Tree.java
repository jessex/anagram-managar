package anagrams;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * A simple Binary tree implementation designed for anagram searching. Each node
 * represents a single "word" in the examined text and has both the word and the
 * alphabetized word as data fields.
 */
public class Tree<T extends Comparable> {

    private Word root; //Root node of the tree
    //HashMap matching alphabetized String (key) to list of anagrams (value)
    private HashMap groupings;

    /**
     * Constructs tree by initializing root node to null.
     */
    public Tree() {
        root = null;
        groupings = new HashMap();
    }

    /**
     * Return the root of the tree.
     * @return root
     */
    public Word getRoot() {
        return this.root;
    }

    public HashMap getGroupings() {
        return this.groupings;
    }


    /**
     * Public interface to insert method. Calls an insert on the root node
     * to start searching for the proper insertion place within the tree.
     * @param word - String
     * @param alpha - String
     */
    public void insert(String word, String alpha) {
        root = insert(word, alpha, root);
    }

    /**
     * Inserts the node (or, word) into the Binary tree in its proper
     * alphabetical location. Traverses tree until it reaches the end of a path
     * and then finally inserts.
     * @param word - String
     * @param alpha - String
     * @param node - Word<T>
     * @return inserted node
     */
    private Word<T> insert(String word, String alpha, Word<T> node) {
        //Enter new node into tree if finally reaches end of path
        if(node == null){
            node = new Word(word, alpha, null, null);
        }
        //Moves to left child in subtree if comparison yields lesser than
        else if(alpha.compareTo((String) node.getAlpha()) < 0){
            node.left = insert(word, alpha, node.left);
        }
        //Moves to right child in subtree if comparison yields greater than
        else if(alpha.compareTo((String) node.getAlpha()) >= 0){
            node.right = insert(word, alpha, node.right);
        }
        else{

        }
        //Returns newly created node
        return node;
    }

    /**
     * Finds and returns a node with the desired alphabetized String. To be used
     * as a means of locating another word with an equivalent alphabetized
     * ordering, an anagram.
     * @param word - String
     * @param alpha - String
     * @param node - Word<T>
     * @return desired node
     */
    private Word<T> find(String word, String alpha, Word<T> node) {
        //Cycles through until reaches end of tree
        while(node != null){
            //Moves to left subtree if comparison is lesser than
            if(alpha.compareTo((String)node.getAlpha()) < 0)
                node = node.left;
            //Moves to right subtree if comparison is greater than
            else if(alpha.compareTo((String)node.getAlpha()) > 0 )
                node = node.right;
            //If comparison is equal, has found node with same alpha string...
            else{
                //Checks if duplicate, returns node if not and null if so
                if(!node.getWord().equals(word)) return node;
                else return null;
            }
        }
        //If never found, return null
        return null;
    }

    /**
     * An in order traversal of the Binary tree to find any and all anagrams.
     * Starts at the current node and recurses to the left child,
     * then searches the entire tree for matches to the current node, then
     * recurses to the right child.
     * @param node - Word<T>
     */
    public void findAnagrams(Word<T> node) {
        if(node != null){
            //Recursive call of left child
            findAnagrams(node.left);

            //Sets temporary node to any node found to have same alpha string
            Word<T> temp = find((String)node.getWord(),
                    (String)node.getAlpha(), root);
            
            //Prints anagrams if anagram of current node was found
            if(temp != null) {
                //An anagram grouping with this alpha already exists
                if (groupings.containsKey(node.getAlpha())) {
                    //Now check if this word has been added to this grouping
                    ArrayList<String> words =
                            (ArrayList<String>) groupings.get(node.getAlpha());
                    //If not yet added, insert into the ArrayList and write Map
                    if (!words.contains(node.getWord())) {
                        words.add((String) node.getWord());
                        groupings.put(node.getAlpha(), words);
                    }
                }
                //Anagram grouping does not yet exist, create it
                else {
                    ArrayList<String> words = new ArrayList<String>();
                    words.add((String) node.getWord());
                    words.add((String) temp.getWord());
                    //Add the alpha as key and a list with these words as value
                    groupings.put(node.getAlpha(), words);
                }
            }
            
            //Recursive call of right child
            findAnagrams(node.right);
        }
    }

    /**
     * Iterates through the HashMap of found anagrams, printing each grouping of
     * anagrams including its alphabetized match and each anagram.
     */
    public void printAnagrams() {
        int count = groupings.size();
        if (count == 0) {
            System.out.println("No anagrams found.");
        }
        else {
            //Iterate through HashMap of groupings
            Iterator it = groupings.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry pairs = (Map.Entry)it.next();
                //Print the group heading (alphabetized match String)
                System.out.println("Grouping: " + pairs.getKey());
                //Iterate through each anagram in grouping and print
                ArrayList<String> words = (ArrayList<String>) pairs.getValue();
                for (int i=0; i<words.size(); i++) {
                    System.out.print(words.get(i) + " ");
                }
                System.out.println("\n");
            }

        }
    }

}
