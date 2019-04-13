import java.util.Scanner;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Collection;

public class Dictionary{
  public static ArrayList<String> parseWords(String filename) throws Exception {
    ArrayList<String> words = new ArrayList<String>();
    Scanner scanner = new Scanner(new File(filename));
    String line = scanner.nextLine(); //the first line is field headers, we do not want that.
    while(scanner.hasNextLine()){
      String word = scanner.nextLine();

      words.add(word);
    }

    return words;
  }
}