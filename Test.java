import java.util.ArrayList;
import java.util.SortedSet;
import java.util.Scanner;

public class Test{
  public void main(String[] args) throws Exception {
    ArrayList<Token> tokens = TweetParser.parseTweets("full-corpus.csv");
//    tokens.forEach(e -> System.out.print(e + "\t"));
    System.out.println("Tokens parsed: " + tokens.size());
    SloganMaker maker = new SloganMaker(tokens);
    Scanner scan = new Scanner(System.in);
    System.out.println("Enter an Acronym: ");
    String s = scan.next();
    System.out.println("Slogan: " + SloganMaker.getSlogan(s));

    //Prompt user for an acronym.
    //Report slogan found in text corpus bigrams, if one exists.
    //Prompt user to keep going or quit.
  }
}