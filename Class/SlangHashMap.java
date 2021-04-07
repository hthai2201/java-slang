package Class;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

    public void importData(String path) {
        if (path.equals("") || path == null) {
            path = "slang.txt";
        }
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
                    System.out.println("Du lieu khong hop le tai dong " + index + 1);
                }
                index++;
            }
            System.out.println(this.keySet().size());
            br.close();

        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void exportData(String path) {
        if (path.equals("") || path == null) {
            path = "slang.txt";
        }
        try {
        FileWriter fw = new FileWriter(path, false);
        BufferedWriter bw = new BufferedWriter(fw);
        PrintWriter pw = new PrintWriter(bw);
        List<String> test = new ArrayList<String>();
        this.entrySet().stream().forEach(e -> {
                
            test.add(e.getKey() + "`" +  e.getValue()) ;
        });
            
        pw.println(String.join("\n", test));
        // for (int i = 1; i < 15;i++) {
        //    for (String row : test) {
            
        //     String temp = i+row; 
        //     pw.println(temp);
        //     //test.add(temp);
        //    }    
        // }
        
        pw.flush();
        pw.close();

        } catch (FileNotFoundException e) {
        System.out.println(e.getMessage());
        } catch (IOException e) {
        e.printStackTrace();
        }

    }

    public String getValue(String value) {
        long start = System.nanoTime();
        List<String> test = new ArrayList<String>();

        // call the method
        this.entrySet().stream().forEach(e -> {
                if (e.getValue().contains(value)) {
                //Utils.printlnPair(e);
                test.add(e.getKey() + " - " +  e.getValue()) ;
            }
        });
        System.out.println(String.join("\n", test));
        // get the end time
        long end = System.nanoTime();

        // execution time
        long execution = end - start;
        System.out.println("Execution time: " + execution/1000000 + " nanoseconds");

        return null;
    }

}
