package Class;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class SlangHashMap extends HashMap<String, String> {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public SimpleEntry<String, String> ramdonWord() {
        Random random = new Random();
        List<String> keys = new ArrayList<String>(this.keySet());
        String randomKey = keys.get(random.nextInt(keys.size()));
        String value = this.get(randomKey);
        return new SimpleEntry<String, String>(randomKey, value);
    }

    public List<SimpleEntry<String, String>> ramdon4Word() {
        Random random = new Random();
        List<String> keys = new ArrayList<String>(this.keySet());
        List<SimpleEntry<String, String>> result = new ArrayList<SimpleEntry<String, String>>();
        for (int i = 0; i < 4; i++) {
            String key = keys.get(random.nextInt(keys.size()));
            String value = this.get(key);
            result.add(new SimpleEntry<String, String>(key, value));
        }

        return result;
    }

    public void importData(String path) throws UnsupportedEncodingException {
        if (path.equals("") || path == null) {
            path = "slang.txt";
        }
        PrintStream out = new PrintStream(System.out, true, "UTF-8");
        try {
            FileReader fr = new FileReader(path);
            BufferedReader br = new BufferedReader(fr);
            String row;
            int index = 0;
            this.clear();
            while ((row = br.readLine()) != null) {

                String[] temp = row.split("`");
                if (temp.length > 1) {
                    String key = temp[0];
                    String value = "";
                    for (int i = 1; i < temp.length; i++) {
                        value += temp[i];
                    }
                    this.put(key, value);
                } else {
                    out.println("Dữ liệu không hợp lệ tại dòng " + index + 1);
                }
                index++;
            }
            out.println(this.keySet().size());
            br.close();

        } catch (FileNotFoundException e) {
            out.println(e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void exportData(String path, Boolean isAppend) throws UnsupportedEncodingException {
        PrintStream out = new PrintStream(System.out, true, "UTF-8");
        if (path.equals("") || path == null) {
            path = "slang.txt";
        }
        try {
            FileWriter fw = new FileWriter(path, isAppend);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);
            List<String> test = new ArrayList<String>();
            this.entrySet().stream().forEach(e -> {

                test.add(e.getKey() + "`" + e.getValue());
            });

            pw.println(String.join("\n", test));
            // for (int i = 1; i < 15;i++) {
            // for (String row : test) {

            // String temp = i+row;
            // pw.println(temp);
            // //test.add(temp);
            // }
            // }

            pw.flush();
            pw.close();

        } catch (FileNotFoundException e) {
            out.println(e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void exportData(String path) throws UnsupportedEncodingException {
        this.exportData(path, true);
    }

    public List<String> searchValue(String value) throws UnsupportedEncodingException {
        long start = System.nanoTime();
        List<String> matched = new ArrayList<String>();
        PrintStream out = new PrintStream(System.out, true, "UTF-8");
        // call the method
        this.entrySet().stream().forEach(e -> {
            if (e.getValue().contains(value)) {
                // Utils.printlnPair(e);
                matched.add(e.getKey() + " - " + e.getValue());
                // matchedReturn.add(new SimpleEntry<String,String>(e));
            }
        });
        out.println(String.join("\n", matched));
        out.println("Tổng số từ: " + matched.size());

        long end = System.nanoTime();
        long execution = end - start;
        out.println("Thời gian tìm kiếm: " + execution / 1000000 + "milliseconds");
        return matched;
    }

}
