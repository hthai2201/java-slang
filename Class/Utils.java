package Class;

import java.util.Map;
import java.util.AbstractMap.SimpleEntry;

public class Utils {
    public static void printlnPair(String key, String value) {
        System.out.println(key + " - " + value);
    }

    public static void printlnPair(SimpleEntry<String, String> entry) {
        System.out.println(entry.getKey() + " - " + entry.getValue());
    }
    public static void printlnPair(Map.Entry<String, String> entry) {
        System.out.println(entry.getKey() + " - " + entry.getValue());
    }

    
}
