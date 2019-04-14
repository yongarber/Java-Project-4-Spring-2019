import java.util.Scanner;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Collection;
import java.util.HashSet;

public class Dictionary{
  HashSet<String> words = new HashSet<String>();
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