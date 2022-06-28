package ru.job4j.ood.isp.menu;
import java.util.Scanner;

public class TODOApp {

    public static final ActionDelegate STUB_ACTION = System.out::println;

    public static void main(String[] args) {
        Menu menu = new SimpleMenu();
        MenuPrinter menuPrinter = new PrintItem();
        Scanner scanner = new Scanner(System.in);
        boolean run = true;
        while (run) {
            System.out.println("Enter 1 to add a new menu item"
                    + System.lineSeparator()
                    + "Enter 2 to view the menu"
                    + System.lineSeparator()
                    + "Enter 3 to exit the program");
            int input = scanner.nextInt();
            scanner.nextLine();
            if (input == 1) {
                System.out.println("Menu item is parent: y/n");
                String mainMenu = scanner.nextLine();
                String parentName = Menu.ROOT;
                if ("n".equalsIgnoreCase(mainMenu)) {
                    System.out.println("Enter the name of the parent menu item");
                    parentName = scanner.nextLine();
                }
                System.out.println("Enter the name of the menu item");
                String childName = scanner.nextLine();
                boolean result = menu.add(parentName, childName, STUB_ACTION);
                if (!result) {
                    throw new IllegalArgumentException("Item not on the menu");
                }
            } else if (input == 2) {
                menuPrinter.print(menu);
            } else if (input == 3) {
                run = false;
            } else {
                System.out.println("Input Error");
            }
        }
    }
}
