package menu;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
public class Menu {
    private boolean running = false;
    private List<MenuItem> menuItems = new ArrayList<>();
    public Menu(List<MenuItem> menuItems) {
        this.menuItems.addAll(menuItems);
    }
    public void show() {
        for (int i = 0; i < menuItems.size(); i++) {
            System.out.println(i + " - " +
                    menuItems.get(i).getName());
        }
    }

    public int getInput() {
        int select = 0;
        Scanner scanner = new Scanner(System.in);
        boolean validInput = false;
        do {
            System.out.println("Оберіть функцію: ");
            try {
                select = scanner.nextInt();
                scanner.nextLine();
                if (select > menuItems.size() - 1 || select < 0) {
                    System.out.println("Будь-ласка оберіть те, що є у списку!");
                } else {
                    validInput = true;
                }
            } catch (InputMismatchException err) {
                System.out.println("Будь-ласка оберіть те, що є у списку!");
                scanner.nextLine();
            }
        } while (!validInput);
        return select;
    }

    public void perform(int select) {
        running = true;
        this.menuItems.get(select).runMethod();
        running = quit();
        if (!running){
            System.exit(0);
        }
    }

    public boolean quit() {
        int choose = 0;
        Scanner scanner = new Scanner(System.in);
        boolean inputValid = false;
        do {
            System.out.println("1 - Продовжити, 0 - Вихід");
            try {
                choose = scanner.nextInt();
                scanner.nextLine();
                if (choose == 1 || choose == 0) {
                    inputValid = true;
                } else {
                    System.out.println("Будь-ласка оберіть продовжити або вийти");
                }
            } catch (InputMismatchException err) {
                System.out.println("Некоректний ввід данних!");
                scanner.nextLine();
            }
        } while (!inputValid);
        return choose == 1;
    }

    public void run() {
        int select;
        running = true;
        while (running) {
            show();
            select = getInput();
            perform(select);
        }
    }
}
