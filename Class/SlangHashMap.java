package Class;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class SlangHashMap extends HashMap<String, String> {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public SimpleEntry<String,String> ramdonWord() {
        Random random = new Random();
        List<String> keys = new ArrayList<String>(this.keySet());
        String randomKey = keys.get(random.nextInt(keys.size()));
        String value = this.get(randomKey);
        return  new SimpleEntry<String,String>(randomKey, value);
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
            br.close();

        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void renderExport(String path) {
        if (path.equals("") || path == null) {
            path = "data.csv";
        }
        // try {
        // FileWriter fw = new FileWriter(path, false);
        // BufferedWriter bw = new BufferedWriter(fw);
        // PrintWriter pw = new PrintWriter(bw);

        // Date date = new Date();
        // pw.println("Updated time: " + date.toString());
        // int n = this.list.size();
        // pw.println("MHS,TenHS,Diem,HinhAnh,DiaChi,GhiChu");
        // for (int i = 0; i <n; i++) {
        // pw.println(this.list.get(i).toString());
        // }
        // pw.flush();
        // pw.close();

        // } catch (FileNotFoundException e) {
        // System.out.println(e.getMessage());
        // } catch (IOException e) {
        // e.printStackTrace();
        // }

    }

}
