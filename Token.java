import java.util.ArrayList;
/**
*@see Token this is the class which creates the token object
*@see word this is the string which contains the string of the actual word associated with each token.
**/
public class Token implements Comparable<Token>{
  public String word;
  public ArrayList<Token> grams;

/**
*@see Token this is the constructor for the token object.
*@param word a string is requried to make each of the Token objects. These are all created in the TweetParser class.
**/
  public Token(String word){
    this.word = word;
    this.grams = new ArrayList<Token>();
  }
//method which adds an item to to the existing list of bigrams for each token.
  public boolean addGram(Token t){
    return grams.add(t);
  }
// comparison method
  public int compareTo(Token t){
    return word.compareTo(t.toString());
  }
//toString method
  public String toString(){
    return word;
  }
}