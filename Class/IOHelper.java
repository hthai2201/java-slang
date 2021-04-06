package Class;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class IOHelper {
    BufferedReader reader;
    public IOHelper(){
        reader = new BufferedReader(
            new InputStreamReader(System.in));
  
    }
    
    public String readLine() throws IOException{
        return reader.readLine();
    }
    public String readLineNoCatch(){
       try {
        return this.readLine();
       } catch (Exception e) {
           //TODO: handle exception
           System.out.println(e);
       }
       return null;
    }
    public Integer readInt() throws NumberFormatException, IOException{
        return Integer.parseInt(reader.readLine());
    }
}
