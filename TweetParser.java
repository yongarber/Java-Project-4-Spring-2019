import java.util.Scanner;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Collection;

public class TweetParser{
  public static ArrayList<Token> parseTweets(String filename) throws Exception {
    ArrayList<Token> tokens = new ArrayList<Token>();
    Twokenizer twokenizer = new Twokenizer();
    Scanner scanner = new Scanner(new File(filename), "UTF-8");
    String line = scanner.nextLine(); //the first line is field headers, we do not want that.
    Token prevToken = null;
    while(scanner.hasNextLine()){
      line = scanner.nextLine();
      String[] fields = line.split("\",\"");

      List<String> twokens = twokenizer.twokenize(fields[4]); //You need to call the correct method here.

      //This is going to cause an error until you can call the correct method.
      twokens.forEach(e -> {
        Token nt = new Token(e);
        Dictionary wording = new Dictionary();
        if( wording.checkdic(nt.word)==true);
        tokens.add(nt);
      });
    }

    return tokens;
  }
}