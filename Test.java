import java.util.ArrayList;
import java.util.SortedSet;
import java.util.Scanner;

public class Test{
  public static void main(String[] args) throws Exception {
  	//This is just a starter token. The way we implemented everything, we just need a stoken to start the getSlogan function, but this has nothing to do with the final product.
    Token starter = new Token("soy");
    //Creates the ArrayList<Tokens> from the TweetParser class. This is NOT the bigram list. The actual bigram TreeMap is created and sorted during the getSlogan() method of the 
    //SloganMaker class.
    ArrayList<Token> tokens = TweetParser.parseTweets("full-corpus.csv");
    System.out.println("Tokens parsed: " + tokens.size());
    SloganMaker maker = new SloganMaker(tokens);  // initiating sloganmaker
    Scanner scan = new Scanner(System.in);
    System.out.println("Enter an Acronym: "); // Entering acronym
    String s = scan.next();
    maker.splitAcronym(s);// splitting the acronym
    maker.getSlogan(starter); // running the recursive code
    //Prompt user for an acronym.
    //Report slogan found in text corpus bigrams, if one exists.
    //Prompt user to keep going or quit.
  }
}