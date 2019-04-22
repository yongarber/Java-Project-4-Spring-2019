import java.util.Scanner;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Collection;
import java.util.HashSet;
/**
*@see TweetParser this class will make it possible to parse the tweets appropriately.
**/

public class TweetParser{
  /**
  *@see parseTweets this is the method wich actually parses the tweets. It instantiates a dictionary object which contains the file with english exclusive words.
  *@param filename takes in the filename to parse
  **/
  public static ArrayList<Token> parseTweets(String filename) throws Exception {
    ArrayList<Token> tokens = new ArrayList<Token>();
    Twokenizer twokenizer = new Twokenizer();
    //Instantiation of dictionary class
    Dictionary dict = new Dictionary();
    HashSet<String> validinput = dict.parseWords("words.txt");
    Scanner scanner = new Scanner(new File(filename), "UTF-8");
    String line = scanner.nextLine();
    Token prevToken = null;
    while(scanner.hasNextLine()){
      line = scanner.nextLine();
      String[] fields = line.split("\",\"");

      List<String> twokens = twokenizer.twokenize(fields[4]); // here we call the fourth field.

     
      twokens.forEach(e -> {
        Token nt = new Token(e);
        //Checking to see whether or not the token word is in the dictionary.
        if (validinput.contains(nt.word)){
          tokens.add(nt);
        }
      });
    }

    return tokens;
  }
}