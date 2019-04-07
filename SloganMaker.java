import java.io.Serializable;
import java.util.Collection;
import java.util.ArrayList;
import java.util.TreeMap;
import java.util.Map;
import java.util.Iterator;

public class SloganMaker{

  Collection<Token> tokens;

  public SloganMaker(Collection<Token> tokens){
    this.tokens = tokens;
  }

  /**
   * Get a slogan for a given acronym. The slogan must satisfy the following
   * constraints:
   *   1. The first character of each String corresponds with a character in the acronym passed as a parameter to the method in the same order.
   *   2. Adjacent tokens must be bigrams in the corpus of text used to generate the list of tokens.
   * @param  acronym The acronym that will be used to create a slogan
   * @return         A list of Strings that satisfies the above constraints.
   */
  public TreeMap<Token, ArrayList<Token>> getSlogan(String acronym){
   // TreeMap<Token, Integer> options = new TreeMap<Token, Integer>();
    TreeMap<Token, ArrayList<Token>> bigram = new TreeMap<Token, ArrayList<Token>>();

    String[] eachletter = acronym.split(""); // we will use it in step 5

    ArrayList<Token> toke = new ArrayList<Token>(tokens);
    for(int i=0; i < tokens.size()-1; i++) {
      int j = i + 1;
      if (toke.get(i).word.charAt(0) != '@' && toke.get(i).word.charAt(0) != '!' && toke.get(i).word.charAt(0) != '"' && toke.get(i).word.charAt(0) != '$' && !toke.get(i).word.contains("http") && toke.get(i).word.charAt(0) != '#' && toke.get(i).word.charAt(0) != '?' && toke.get(i).word.charAt(0) != '.' && toke.get(i).word.charAt(0) != ',' && toke.get(i).word.charAt(0) != '-') {
        ArrayList<Token> val = new ArrayList<Token>();
        if (bigram.containsKey(toke.get(i))) {
          if (toke.get(j).word.charAt(0) != '@' && toke.get(j).word.charAt(0) != '!' && toke.get(j).word.charAt(0) != '"' && toke.get(j).word.charAt(0) != '$' && !toke.get(j).word.contains("http") && toke.get(j).word.charAt(0) != '#' && toke.get(j).word.charAt(0) != '?' && toke.get(j).word.charAt(0) != '.' && toke.get(j).word.charAt(0) != ',' && toke.get(i).word.charAt(0) != '-') {
            bigram.get(toke.get(i)).add((toke.get(j)));
          }
        } 
        else {
          if (toke.get(j).word.charAt(0) != '@' && toke.get(j).word.charAt(0) != '!' && toke.get(j).word.charAt(0) != '"' && toke.get(j).word.charAt(0) != '$' && !toke.get(j).word.contains("http") && toke.get(j).word.charAt(0) != '#' && toke.get(j).word.charAt(0) != '?' && toke.get(j).word.charAt(0) != '.' && toke.get(j).word.charAt(0) != ',' && toke.get(i).word.charAt(0) != '-') {
            val.add(toke.get(j));
            bigram.put(toke.get(i), val);
          }
        }
      }
    }
    return bigram;
  }
}
