import java.util.*;
import java.io.Serializable;
/**
*@see ValueComparator this is the custom comparator which compares based on ArrayList<Token> size
**/
public class ValueComparator implements Comparator {
    TreeMap map;
//Constructor
    public ValueComparator(Map map) {
        this.map = (TreeMap) map;
    }
// Compares object keyA and keyB based on ArrayList<Token> size
    public int compare(Object keyA, Object keyB) {
        ArrayList<Token> valueA = (ArrayList<Token>) map.get(keyA);
        ArrayList<Token> valueB = (ArrayList<Token>) map.get(keyB);
        Integer valASize = valueA.size();
        Integer valBSize = valueB.size();
        return valASize.compareTo(valBSize);
    }
}

