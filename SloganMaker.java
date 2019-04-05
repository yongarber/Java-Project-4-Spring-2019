import java.util.Collection;
import java.util.List;
import java.util.ArrayList;
import java.util.TreeMap;

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
  public TreeMap<Token, Integer> getSlogan(String acronym){
    TreeMap<Token, Integer> options = new TreeMap<Token, Integer>();

    for (Token token: tokens){
      if (token.word.charAt(0) != '@' && !token.word.contains("http")){
        if (options.containsKey(token)){
          options.replace(token, options.get(token), options.get(token)+1);
        }
        else{
          options.put(token, 1);
        }
      }
    }
    return options;
  }
}