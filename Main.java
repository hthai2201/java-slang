import java.util.ArrayList;
import java.util.List;
import java.util.AbstractMap.SimpleEntry;

import Class.ChoosenMenuItem;
import Class.MenuChoosen;
import Class.SlangHashMap;

public class Main {
    private static SlangHashMap slang = new SlangHashMap();
    private static MenuChoosen mainMenu;
    public static void main(String[] args) {
        slang.importData("slang.txt");
        mainMenu = Main.getMainMenu();
        while (true) {
            try {
                ChoosenMenuItem choosen  = mainMenu.renderMenu();
            Main.navigate(choosen);
            } catch (Exception e) {
                //TODO: handle exception
                System.out.println(e);
            }
            
        }
    }
    public static MenuChoosen getMainMenu(){
        List<ChoosenMenuItem> l1Menu = new ArrayList<ChoosenMenuItem>();
        l1Menu.add(new ChoosenMenuItem("Tim kiem theo tu","findWord",null));
        l1Menu.add(new ChoosenMenuItem("Tim kiem theo Nghia","findMean",null));
        l1Menu.add(new ChoosenMenuItem("Lich su tim kiem","history",null));
        l1Menu.add(new ChoosenMenuItem("Them tu","add",null));
        l1Menu.add(new ChoosenMenuItem("Sua tu","edit",null));
        l1Menu.add(new ChoosenMenuItem("Xoa tu","del",null));
        l1Menu.add(new ChoosenMenuItem("Reset ve danh sach tu goc","reset",null));
        l1Menu.add(new ChoosenMenuItem("Tu ngau nhien","ramdonWord",null));
        l1Menu.add(new ChoosenMenuItem("Do vui,Tim nghia cua tu","findMeanQuiz",null));
        l1Menu.add(new ChoosenMenuItem("Do vui,Tim Tu theo nghia","findWordQuiz",null));
        
        ChoosenMenuItem menu = new ChoosenMenuItem("Danh sach chuc nang","main",
        l1Menu);
        MenuChoosen mainMenu = new MenuChoosen(menu);
        return  mainMenu;
    }
    public static void navigate(ChoosenMenuItem item) {
        switch (item.value) {
            case "import":
                slang.importData("slang.txt");
                System.out.println(slang);
                break;
        
            case "ramdonWord":
                SimpleEntry result =  slang.ramdonWord();
                System.out.println(result);
                mainMenu.popChoosen();
                break;
        
            default:
            mainMenu.popChoosen();
                break;
        }
    }
    }