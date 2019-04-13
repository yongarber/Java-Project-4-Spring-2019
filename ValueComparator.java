import java.util.*;
import java.io.Serializable;

public class ValueComparator implements Comparator {
    TreeMap map;

    public ValueComparator(Map map) {
        this.map = (TreeMap) map;
    }

    public int compare(Object keyA, Object keyB) {
        ArrayList<Token> valueA = (ArrayList<Token>) map.get(keyA);
        ArrayList<Token> valueB = (ArrayList<Token>) map.get(keyB);
        Integer valASize = valueA.size();
        Integer valBSize = valueB.size();
        return valASize.compareTo(valBSize);
    }
}

