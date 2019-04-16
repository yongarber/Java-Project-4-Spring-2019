import java.util.ArrayList;
import java.util.SortedSet;
import java.util.Scanner;

public class Test{
  public static void main(String[] args) throws Exception {
    Token starter = new Token("soy");
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