package info;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
public class Info {
    private boolean running = false;
    private List<InfoItem> infoItems = new ArrayList<>();
    public Info(List<InfoItem> infoItems) {
        this.infoItems.addAll(infoItems);
    }
    public void show() {
        for (int i = 0; i < infoItems.size(); i++) {
            System.out.println(i + " - " + infoItems.get(i).getName());
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
                if (select > infoItems.size() - 1 || select < 0) {
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
        this.infoItems.get(select).runMethod();
        if (!running){
            System.exit(0);
        }
        System.out.println();
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
