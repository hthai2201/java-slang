package Class;

import java.util.List;

public class ChoosenMenuItem {
    public String name;
    public String value;
    public List<ChoosenMenuItem> subMenus;
    public ChoosenMenuItem(String name,String value,List<ChoosenMenuItem> subMenus){
        this.name = name;
        this.value = value;
        this.subMenus = subMenus;    
    }
}
