package ui;

import java.util.Scanner;

public class Ui {

    private static final String LINE = "__________________________________________________________________________";

    private Scanner sc = new Scanner(System.in);

    public String readCommand() {
        return sc.nextLine().trim();
    }

    public void showGreetingMessage() {
        drawLine();
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        drawLine();
    }

    public void showErrorMessage(String errMsg) {
        System.out.println("Oops! I don't understand that :v");
        System.out.println(errMsg);
    }

    public void showMessage(String msg) {
        System.out.println(msg);
    }

    public void drawLine() {
        System.out.println(LINE);
    }
}
