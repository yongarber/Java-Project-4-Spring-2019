import java.io.Serializable;
import java.util.Collection;
import java.util.ArrayList;
import java.util.TreeMap;
import java.util.Map;
import java.util.Iterator;

/**
*@see SloganMaker this is the class which actually gets the slogan. It contains all of the methods which are needed ot ultimately create the acronym for an input.
**/
public class SloganMaker {

  Collection<Token> tokens;
  ArrayList<Token> acronym;
  ArrayList<Character> letters;

  public SloganMaker(Collection<Token> tokens) {
    this.tokens = tokens;
    this.acronym = new ArrayList<Token>();
    this.letters = new ArrayList<Character>();
  }

/**
*@see getBigram() this method is a no-args method which takes in all of the tokens from the constructor of SloganMaker and creates a TreeMap of bigrams.
*@see return returns the TreeMap called "bigrams"
**/
  public TreeMap<Token, ArrayList<Token>> getBigram() {
    // TreeMap<Token, Integer> options = new TreeMap<Token, Integer>();
    TreeMap<Token, ArrayList<Token>> bigram = new TreeMap<Token, ArrayList<Token>>();

    ArrayList<Token> toke = new ArrayList<Token>(tokens);
    for (int i = 0; i < tokens.size()-1; i++) {
      int j = i + 1;
      if (bigram.containsKey(toke.get(i))) {
        bigram.get(toke.get(i)).add((toke.get(j)));
      }
      else {
        ArrayList<Token> val = new ArrayList<Token>();
        val.add(toke.get(j));
        bigram.put(toke.get(i), val);
      }
    }
    return bigram;
  }
/**
*@see splitAcronym this method takes in the input of the scanner from the Test and splits it into its individual characters so that each of the characters may be compared with the TreeMap of bigram.
*@param inputtedvalue takes in a scanner from the Test class and splits it.
*@see return returns the data field this.letters which is then equivalent to the splitted arraylist of characters which is the letters.
**/
  public void splitAcronym(String inputtedvalue){

    ArrayList<Character> characters = new ArrayList<Character>();

    for (int i = 0; i < inputtedvalue.length(); i++){
      characters.add(inputtedvalue.charAt(i));
    }

    this.letters = characters;
  }

/**
*@see getSlogan recursive method which generates the slogan based on the bigram of tokens.
*@param token takes in the previous token and checks whether the first letters of any of the elements in the corresponding ArrayList of tokens matches the letters of the next character in this.letters.
**/
  public void getSlogan(Token token){

    TreeMap<Token, ArrayList<Token>> bigrams = (TreeMap<Token, ArrayList<Token>>) sortByValue(getBigram());

    if (this.letters.size() == 0){
      System.out.println(this.acronym);
    }
    else if (this.letters.size() <= 1 && this.acronym.size() == 0){
      System.out.println("Please enter more than a single character.");
    }
    else if (this.acronym.size() == 0){
      for (Map.Entry<Token, ArrayList<Token>> entry: bigrams.entrySet()){
        Token key = entry.getKey();
        ArrayList<Token> value = entry.getValue();
        if (this.letters.get(0) == key.word.charAt(0) && matchingiteminlist(value, this.letters.get(1)).word != "failed"){
          this.acronym.add(key);
          this.acronym.add(matchingiteminlist(value, this.letters.get(1)));
          this.letters.remove(0);
          this.letters.remove(0);
          break;
        }
      }
      if (this.letters.size() > 0 && this.acronym.size() == 2){
        getSlogan(this.acronym.get(this.acronym.size()-1));
      }
      else{
        System.out.println("No acronyms available!");
      }

    }

    else{
      Character letter = this.letters.get(0);
      Token holder = new Token("Failed");
      if (bigrams.containsKey(token)){
        for (Token tokens: bigrams.get(token)){
          if (tokens.word.charAt(0) == letter){
            this.acronym.add(tokens);
            this.letters.remove(0);
            holder = tokens;
            break;
          }
        }
      }
      if (!holder.word.equals("Failed")){
        getSlogan(holder);
      }
      else if (backtrack(token, this.letters.get(0)).word != "Holder"){
        Token backtracked = backtrack(token, this.letters.get(0));
        this.acronym.add(backtrack(token, this.letters.get(0)));
        this.letters.remove(0);
        getSlogan(backtracked);
      }
      else{
        System.out.println("No acronyms available!");
      }
    }
  }

/**
*@see matchingiteminlist checks to see whether or not any of the first characters in the ArrayList of tokens matches the character of the this.letters list.
*@param token takes in the current token for which the comparison is being made.
*@param character takes in the current character for which the comparison needs to be fulfilled.
**/
  public Token matchingiteminlist(ArrayList<Token> token, char character){
    Token failed = new Token("failed");
    for (Token item: token){
      if (item.word.charAt(0) == (character)){
        return item;
      }
    }
    return failed;
  }

/**
*@see backtrack this is the recusive backtrack method which checks previous tokens in the this.acronym if the current token does not have an element in its ArrayList whose first letter starts with the character in this.letters.
*@param token takes in a token for recursion in backtracking
*@param character takes in the current character to check whether or not the previous token has a token which matches the character.
**/
  public Token backtrack(Token token, Character character){
    Token backtrackkey = new Token("Holder");

    for (Token item: tokens){
      if (item.word.charAt(0) == character){
        backtrackkey = item;
        break;
      }
    }

    if (this.acronym.indexOf(token) == 0){
      return backtrackkey;
    }
    else if (backtrackkey.word != "Holder"){
      return backtrackkey;
    }
    else{
      this.acronym.remove(token);
      return backtrack(this.acronym.get(this.acronym.size()-1), character);
    }
  }

  /**
  *@see sortByValue this method sorts the TreeMap by its ArrayList<Token> size in the value.
  *@param unsortedmap takes in the TreeMap and sorts it.
  **/
  public Map sortByValue(Map unsortedMap) {
    Map sortedMap = new TreeMap(new ValueComparator(unsortedMap));
    sortedMap.putAll(unsortedMap);
    return sortedMap;
  }
}