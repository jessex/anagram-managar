package anagrams;

/**
 * Node for the Binary tree which represents a single word in the provided text.
 * Contains data fields for the actual word and its alphabetized version.
 */
public class Word<String> {

    private String word; //Actual word
    private String alpha; //Alphabetized version
    //Left and Right children of node
    public Word left;
    public Word right;

    /**
     * Constructs the node with the actual words, the alphabetized version and
     * the left and right children.
     */
    public Word(String word, String alpha, Word<String> left, Word<String> right) {
        this.word = word;
        this.alpha = alpha;
        this.left = left;
        this.right = right;
    }

    //Accessors
    public String getWord() {
        return word;
    }
    public String getAlpha() {
        return alpha;
    }
    
    //Mutators
    public void setWord(String data) {
        this.word = data;
    }
    public void setAlpha(String data) {
        this.alpha = data;
    }
}
