import java.util.ArrayList;
import java.util.SortedSet;

public class Test{
  public static void main(String[] args) throws Exception {
    ArrayList<Token> tokens = TweetParser.parseTweets("full-corpus.csv");
    tokens.forEach(e -> System.out.print(e + "\t"));
    System.out.println("Tokens parsed: " + tokens.size());
    SloganMaker maker = new SloganMaker(tokens);
    System.out.println("Slogan: " + maker.getSlogan("map"));

    //Prompt user for an acronym.
    //Report slogan found in text corpus bigrams, if one exists.
    //Prompt user to keep going or quit.
  }
}