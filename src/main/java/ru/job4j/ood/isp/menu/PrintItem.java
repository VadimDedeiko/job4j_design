package ru.job4j.ood.isp.menu;

public class PrintItem implements MenuPrinter {

    public static final String LINE = "----";

    @Override
    public void print(Menu menu) {
        for (Menu.MenuItemInfo menuItemInfo : menu) {
            String number = menuItemInfo.getNumber();
            String name = menuItemInfo.getName();
            String[] split = menuItemInfo.getNumber().split("\\.");
            int count = split.length - 1;
            String separator = LINE.repeat(count);
            System.out.println(separator + number + name);
        }
    }
}
