package Class;

import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.util.Map;
import java.util.AbstractMap.SimpleEntry;

public class Utils {

    public static void printlnPair(String key, String value) throws UnsupportedEncodingException {
        PrintStream out = new PrintStream(System.out, true, "UTF-8");
        out.println(key + " - " + value);
    }

    public static void printlnPair(SimpleEntry<String, String> entry) throws UnsupportedEncodingException {
        PrintStream out = new PrintStream(System.out, true, "UTF-8");
        out.println(entry.getKey() + " - " + entry.getValue());
    }

    public static void printlnPair(Map.Entry<String, String> entry) throws UnsupportedEncodingException {
        PrintStream out = new PrintStream(System.out, true, "UTF-8");
        out.println(entry.getKey() + " - " + entry.getValue());
    }

}
