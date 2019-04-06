import java.util.Collection;
import java.util.List;
import java.util.ArrayList;
import java.util.TreeMap;
import java.util.*;

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
  public TreeMap<Token, String> getSlogan(String acronym){
   // TreeMap<Token, Integer> options = new TreeMap<Token, Integer>();
    TreeMap<Token, String> bigram = new TreeMap<Token, String>();

    String[] eachletter = acronym.split(""); // we will use it in step 5

    ArrayList<Token> Toke = new ArrayList<>(tokens);
    for(int i=0; i <= tokens.size(); i++) {
      int j = i + 1;
      if (Toke.get(i).word.charAt(0) != '@' && !Toke.get(i).word.contains("http")) {
        if (bigram.containsKey(Toke.get(i))) {
          bigram.replace(Toke.get(i), bigram.get(Toke.get(i)), (Toke.get(i).word + Toke.get(j).word));
        } else {
          bigram.put(Toke.get(i), Toke.get(j).word);
        }
      }
    }
//    for (Token token: tokens){
//      if (token.word.charAt(0) != '@' && !token.word.contains("http")){ // We can change the != "@" to things we want -> 'A'||'B'||'C'||'D'||'E'||'F'||'G'||'H'||'I'||'J'||'K'||'L'||'M'||'N'||'O'||'P'||'Q'||'R'||'S'||'T'||'U'||'V'||'X'||'W'||'Y'||'Z' This way we eliminate the issue you had with the language.
//        if (options.containsKey(token)){
//          options.replace(token, options.get(token), options.get(token)+1);
//        }
//        else{
//          options.put(token, 1);
//        }
//      }
//    }
    return bigram;
  }
}