import java.util.ArrayList;
import java.util.List;
import java.util.AbstractMap.SimpleEntry;

import Class.ChoosenMenuItem;
import Class.IOHelper;
import Class.MenuChoosen;
import Class.SlangHashMap;

public class Main {
    private static SlangHashMap slang = new SlangHashMap();
    private static MenuChoosen mainMenu;
    private static IOHelper io = new IOHelper();

    public static void main(String[] args) {
        slang.importData("slang.txt");
        mainMenu = Main.getMainMenu();
        while (true) {
            try {
                ChoosenMenuItem choosen = mainMenu.renderMenu();
                if (choosen != null) {
                    Main.navigate(choosen);
                }else {
                    break;
                }
                
            } catch (Exception e) {
                // TODO: handle exception
                System.out.println(e);
            }

        }
    }

    public static MenuChoosen getMainMenu() {
        List<ChoosenMenuItem> l1Menu = new ArrayList<ChoosenMenuItem>();
        l1Menu.add(new ChoosenMenuItem("Tìm kiếm theo từ", "findWord", null));
        l1Menu.add(new ChoosenMenuItem("Tìm kiếm theo nghĩa của từ", "findMean", null));
        l1Menu.add(new ChoosenMenuItem("Lịch sử tìm kiếm", "history", null));
        l1Menu.add(new ChoosenMenuItem("Thêm từ", "add", null));
        l1Menu.add(new ChoosenMenuItem("Sửa từ", "edit", null));
        l1Menu.add(new ChoosenMenuItem("Xoá từ", "del", null));
        l1Menu.add(new ChoosenMenuItem("Khôi phục danh sách từ gốc", "reset", null));
        l1Menu.add(new ChoosenMenuItem("Hiển thị từ ngẫu nhiên", "ramdonWord", null));
        l1Menu.add(new ChoosenMenuItem("Đố vui,Tìm nghĩa của từ", "findMeanQuiz", null));
        l1Menu.add(new ChoosenMenuItem("Đố vui,Tìm từ theo nghĩa", "findWordQuiz", null));

        ChoosenMenuItem menu = new ChoosenMenuItem("Danh sách chức năng", "main", l1Menu);
        MenuChoosen mainMenu = new MenuChoosen(menu);
        return mainMenu;
    }

    public static void navigate(ChoosenMenuItem item) {

        switch (item.value) {
        case "findWord": {

            try {
                System.out.print("Nhập từ cần tìm: ");
                String key = io.readLine();
                String result = slang.get(key);
                if (result != null) {
                    printlnPair(key, result);
                } else {
                    printlnPair(key, "Không tìm thấy từ trong hệ thống");
                }

            } catch (Exception e) {
                // TODO: handle exception
                System.out.println("Đã có lỗi xãy ra");
            }
            nextAction();
            break;
        }
        
        case "reset": {
            slang.importData("slang.txt");
            nextAction();
            break;
        }
        case "ramdonWord": {
            SimpleEntry<String, String> result = slang.ramdonWord();
            printlnPair(result);
            nextAction();
            break;
        }

        default:
            nextAction();
            break;
        }
    }

    public static void printlnPair(String key, String value) {
        System.out.println(key + " - " + value);
    }

    public static void printlnPair(SimpleEntry<String, String> entry) {
        System.out.println(entry.getKey() + " - " + entry.getValue());
    }

    public static void nextAction() {
        System.out.println("Ấn phím bất kì để tiếp tục");
        io.readLineNoCatch();
        mainMenu.popChoosen();
    }
}