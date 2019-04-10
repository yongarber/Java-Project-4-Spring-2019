import java.io.Serializable;
import java.util.Collection;
import java.util.ArrayList;
import java.util.TreeMap;
import java.util.Map;
import java.util.Iterator;

public class SloganMaker{

  Collection<Token> tokens;
  ArrayList<Token> acronym;
  ArrayList<Character> letters;

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
  public TreeMap<Token, ArrayList<Token>> getBigram(){
   // TreeMap<Token, Integer> options = new TreeMap<Token, Integer>();
    TreeMap<Token, ArrayList<Token>> bigram = new TreeMap<Token, ArrayList<Token>>();

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

  public void splitAcronym(String inputtedvalue){

    for (int i = 0; i < inputtedvalue.length(); i++){
      this.letters.add(inputtedvalue.charAt(i));
    }
  }

  public ArrayList<Token> getSlogan(String word){
    TreeMap<Token, ArrayList<Token>> bigrams = getBigram();
    if (this.acronym.size() == this.letters.size()){
      return this.acronym;
    }

    else{
      Character letter = this.letters.get(0);
      for (Map.Entry<Token, ArrayList<Token>> entry: bigrams.entrySet()){
        Token key = entry.getKey();
        ArrayList<Token> value = entry.getValue();
        if (letter.equals(key.word.charAt(0))){
          this.acronym.add(key);
          if (this.acronym.size() == this.letters.size()){
            return this.acronym;
          }
          else {
            for (Token token: value){
              if (token.word.charAt(0) == this.letters.get(1)){
                this.acronym.add(token);
                return getSlogan(token.word);
              }
            }
          }
        }
      }
    }
  }
}