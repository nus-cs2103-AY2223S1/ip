package byu;

import java.util.Scanner;

public class Ui {

    private Scanner sc = new Scanner(System.in);

    public void showWelcome() {
        String logo = "*\\(^o^)/*";
        System.out.println(logo);
        System.out.println("Bonjour~~ I'm Byu! How can I help you?");
    }

    public String readCommand() {
        return this.sc.nextLine();
    }

    public void showError(String message) {
        System.out.print("Ohno!" + message);
    }

    public void exit() {
        System.out.print("Awww see you soon!!");
        sc.close();
    }

}
