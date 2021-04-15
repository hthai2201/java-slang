package Class;

import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class MenuChoosen {
    ChoosenMenuItem menu;
    List<Integer> choosenMenu = new ArrayList<Integer>();
    ChoosenMenuItem curRenderMenu;
    Boolean isBack = false;
    Boolean isOut = true;

    public MenuChoosen(ChoosenMenuItem menu) {
        this.menu = menu;
    }

    public MenuChoosen(ChoosenMenuItem menu, Boolean isOut) {
        this(menu);
        this.isOut = isOut;
    }

    public MenuChoosen(ChoosenMenuItem menu, Boolean isOut, Boolean isBack) {
        this(menu, isOut);
        this.isBack = isBack;
    }

    // render
    public ChoosenMenuItem renderMenu() throws IOException {
        PrintStream out = new PrintStream(System.out, true, "UTF-8");

        int choosenN = this.choosenMenu.size();
        if (choosenN > 0) {
            ChoosenMenuItem curItem = this.menu;
            for (int i = 0; i < choosenN; i++) {
                curRenderMenu = curItem.subMenus.get(i);
            }
            this.isBack = true;
        } else {
            this.isBack = false;
            this.curRenderMenu = this.menu;
        }

        out.println("------" + this.curRenderMenu.name + "-------");
        List<ChoosenMenuItem> curMenu = this.curRenderMenu.subMenus;
        Integer curMenuN = curMenu.size();
        for (int i = 0; i < curMenuN; i++) {
            out.println((i + 1) + ". " + curMenu.get(i).name);
        }
        if (this.isBack) {
            out.println((curMenuN + 1) + ". Quay lai");
        }
        if (this.isOut) {
            if (this.isBack) {
                out.println((curMenuN + 2) + ". Thoát");
            } else {
                out.println((curMenuN + 1) + ". Thoát");
            }

        }
        out.println("----------------");

        Integer choosenIndex = 0;
        IOHelper io = new IOHelper();

        // Reading data using readLine

        while (true) {
            out.print("Lựa chọn:");
            try {
                choosenIndex = io.readInt();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (NumberFormatException e) {
                //
            }
            if (choosenIndex < 1 || choosenIndex > curMenuN + 1 && !this.isBack
                    || (this.isBack && choosenIndex > curMenuN + 2)) {
                out.println("Lựa chọn không hợp lệ!!");
            } else {
                break;
            }
        }
        choosenIndex -= 1;

        if (choosenIndex < 0 || choosenIndex > curMenuN - 1) {
            if (this.isBack) {
                if (choosenIndex == curMenuN) {
                    this.choosenMenu.remove(curMenuN - 1);
                    return null;
                }
                if (choosenIndex == curMenuN + 1) {
                    return null;
                }
            } else {
                if (choosenIndex == curMenuN) {
                    return null;
                }
            }

            return this.renderMenu();
        }
        ChoosenMenuItem item = curMenu.get(choosenIndex);
        this.choosenMenu.add(choosenIndex);
        if (item != null) {
            if (item.subMenus != null) {
                item = this.renderMenu();
            }

        }
        return item;
    }

    public void popChoosen() {
        choosenMenu.remove(choosenMenu.size() - 1);
    }
}
