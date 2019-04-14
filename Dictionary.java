import java.util.Scanner;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Collection;
import java.util.HashSet;
/**
*@see dictionary class which contains all of the english valid words.
**/
public class Dictionary{
  HashSet<String> words = new HashSet<String>();
  /**
  *@see parseWords this is the method which parses each of the words and puts it into the HashSet of words
  *@param filename takes in the filename, which in this case is called "words.txt", and parses its contents.
  **/
  public HashSet<String> parseWords(String filename) throws Exception {
    Scanner scanner = new Scanner(new File(filename));
    String line = scanner.nextLine(); //the first line is field headers, we do not want that.
    while(scanner.hasNextLine()){
      String word = scanner.nextLine();
      
      word = word.toLowerCase();

      words.add(word);
    }

    return words;
  }
}