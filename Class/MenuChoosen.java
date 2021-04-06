package Class;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class MenuChoosen {
    ChoosenMenuItem menu;
    List<Integer> choosenMenu = new  ArrayList<Integer>();
    ChoosenMenuItem curRenderMenu;
    Boolean isBack = false;
    Boolean isOut = true;
    public MenuChoosen(ChoosenMenuItem menu){
        this.menu = menu;
    }
    //render
    public ChoosenMenuItem renderMenu() throws IOException {
        int choosenN = this.choosenMenu.size();
        if (choosenN>0) {
            ChoosenMenuItem curItem = this.menu;
            for (int i = 0; i <choosenN ; i++) {
                curRenderMenu = curItem.subMenus.get(i);
            }
            this.isBack=true;
        }else{
            this.isBack = false;
            this.curRenderMenu = this.menu;
        }
       
        System.out.println("------" + this.curRenderMenu.name + "-------");
        List<ChoosenMenuItem> curMenu  =this.curRenderMenu.subMenus;
        Integer  curMenuN = curMenu.size();
        for (int i = 0; i < curMenuN; i++) {
            System.out.println((i + 1) + ". " + curMenu.get(i).name);
        }
        if (this.isBack) {
            System.out.println((curMenuN +1) + ". Quay lai");
        }
        if (this.isOut) {
            System.out.println((curMenuN +2) + ". Thoat");
        }
        System.out.println("----------------");
        System.out.print("Lua chon:");
        Integer choosenIndex = 0;
        BufferedReader reader = new BufferedReader(
            new InputStreamReader(System.in));
  
        // Reading data using readLine
        
        while (choosenIndex == 0) {
            try {
                choosenIndex = Integer.parseInt(reader.readLine());
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        choosenIndex -= 1;
        
        if (choosenIndex < 0 || choosenIndex >= curMenuN) {
            if (choosenIndex == curMenuN) {
                this.choosenMenu.remove(curMenuN-1);
            }
            if (choosenIndex == curMenuN+1) {
                return null;
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
        choosenMenu.remove(choosenMenu.size()-1);
    }
}
