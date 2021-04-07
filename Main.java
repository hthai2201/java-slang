import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.AbstractMap.SimpleEntry;

import Class.ChoosenMenuItem;
import Class.IOHelper;
import Class.MenuChoosen;
import Class.SlangHashMap;
import Class.Utils;

public class Main {
    private static SlangHashMap slang = new SlangHashMap();
    private static MenuChoosen mainMenu;
    private static IOHelper io = new IOHelper();
    private static final String slangFilePath = "slang2.txt";

    public static void main(String[] args) {
        slang.importData(slangFilePath);
        mainMenu = Main.getMainMenu();
        while (true) {
            try {
                ChoosenMenuItem choosen = mainMenu.renderMenu();
                if (choosen != null) {
                    Main.navigate(choosen);
                } else {
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
        l1Menu.add(new ChoosenMenuItem("Xuất danh sách từ", "export", null));
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
                    Utils.printlnPair(key, result);
                } else {
                    Utils.printlnPair(key, "Không tìm thấy từ trong hệ thống");
                }

            } catch (Exception e) {
                // TODO: handle exception
                System.out.println("Đã có lỗi xãy ra");
            }
            nextAction();
            break;
        }

        case "findMean": {

            try {
                System.out.print("Nhập từ cần tìm: ");
                String value = io.readLine();
                String result = slang.getValue(value);
                if (result != null) {
                    Utils.printlnPair(value, result);
                } else {
                    Utils.printlnPair(value, "Không tìm thấy từ trong hệ thống");
                }

            } catch (Exception e) {
                // TODO: handle exception
                System.out.println("Đã có lỗi xãy ra");
            }
            nextAction();
            break;
        }
        case "add": {

            try {
                Boolean isOverride = false;
                Boolean isDuplicate = false;
                Boolean isContinue = true;
                String key = "";
                String value = "";
                Integer i = 10;
                List<ChoosenMenuItem> keyErrorMenu = new ArrayList<ChoosenMenuItem>();
                keyErrorMenu.add(new ChoosenMenuItem("Ghi đè từ cũ", "override", null));
                keyErrorMenu.add(new ChoosenMenuItem("Nhân bản ra từ mới", "duplicate", null));
                keyErrorMenu.add(new ChoosenMenuItem("Nhập lại", "rewrite", null));
                MenuChoosen keyErrorMenuChoosen = new MenuChoosen(
                        new ChoosenMenuItem("Từ đã tồn tại, vui lòng chọn: ", "menu", keyErrorMenu));
                while (i > 0) {
                    i--;

                    System.out.print("Nhập từ: ");
                    key = io.readLine();
                    if (!key.equals("")) {
                        if (slang.containsKey(key)) {
                            String oldValue = slang.get(key);
                            // key exisit
                            Utils.printlnPair(key,oldValue);
                            ChoosenMenuItem choosenMenu = keyErrorMenuChoosen.renderMenu();
                            if (choosenMenu == null) {
                                isContinue = false;
                                break;  
                            }
                            if (choosenMenu.value.equals("override")) {
                                isOverride = true;
                                break;
                            }
                            if (choosenMenu.value.equals("duplicate")) {
                                isDuplicate= true;
                                break;
                            }

                        }else{
                            break;
                        }

                    } else {
                        System.out.println("Bạn chưa nhập từ cần thêm");
                    }
                }
               
                if (!isContinue || key.equals("")) {
                    nextAction();
                    break;
                }
                i = 10;
                while (i > 0) {
                    i--;

                    System.out.print("Nhập nghĩa của từ: ");
                    value = io.readLine();
                    if (!value.equals("")) {
                        break;
                    } else {
                        System.out.println("Bạn chưa nhập nghĩa từ cần thêm");
                    }

                }
                if (value.equals("")) {
                    nextAction();
                    break;
                }
                if (isDuplicate) {
                    String oldValue = slang.get(key);
                    value = oldValue + " | " + value;
                }
                slang.put(key, value);
                Utils.printlnPair(key, value);
                if (isOverride) {
                    System.out.println("Cập nhật từ  thành công");
                } else {
                    System.out.println("Thêm từ mới thành công");
                }

            } catch (Exception e) {
                // TODO: handle exception
                System.out.println("Đã có lỗi xãy ra");
            }
            nextAction();
            break;
        }
        case "del": {

            try {
                String key;
                Integer i = 10;
                while (i > 0) {
                    i--;

                    System.out.print("Nhập từ: ");
                    key = io.readLine();
                    if (!key.equals("")) {
                        if (slang.containsKey(key)) {
                            // key exisit
                            String value = slang.get(key);
                            Utils.printlnPair(key, value);
                            if (Main.renderMenuConfirm("Bạn có chắc muốn xoá từ này")) {
                                slang.remove(key);
                                Utils.printlnPair(key, value);
                                System.out.println("Xoá từ thành công");
                            } 
                            break;

                        } else {
                            Utils.printlnPair(key, "Không tìm thấy từ trong hệ thống");
                        }

                    } else {
                        System.out.println("Bạn chưa nhập từ cần thêm");
                    }
                }

            } catch (Exception e) {
                // TODO: handle exception
                System.out.println("Đã có lỗi xãy ra");
            }
            nextAction();
            break;
        }

        case "reset": {
            slang.importData(slangFilePath);
            nextAction();
            break;
        }
        case "export": {
            slang.exportData(slangFilePath);
            nextAction();
            break;
        }
        case "ramdonWord": {
            SimpleEntry<String, String> result = slang.ramdonWord();
            Utils.printlnPair(result);
            nextAction();
            break;
        }

        default:
            nextAction();
            break;
        }
    }

    public static void nextAction() {
        System.out.println("Ấn phím bất kì để tiếp tục");
        io.readLineNoCatch();
        mainMenu.popChoosen();
    }

    public static Boolean renderMenuConfirm(String message) throws IOException {
        String trueText = "Có";
        String falseText = "Không";

        List<ChoosenMenuItem> l1Menu = new ArrayList<ChoosenMenuItem>();
        l1Menu.add(new ChoosenMenuItem(trueText, "true", null));
        l1Menu.add(new ChoosenMenuItem(falseText, "false", null));
        MenuChoosen menu = new MenuChoosen(new ChoosenMenuItem(message, "confirm", l1Menu),false);
        ChoosenMenuItem choose = menu.renderMenu();

        return choose.value == "true";

    }

}