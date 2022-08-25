package duke.ui;

import java.util.Scanner;

public class Ui {

    private Scanner s = new Scanner(System.in);
    private String input;

    public void showWelcome() {
        System.out.println("Just a moment...\nHello! I am Duke.");
        System.out.println("What can I do for you?");
    }

    public void showLoadingError() {
        System.out.println("Unable to read the specified file.");
        System.out.println("New file created.");
    }

    public String readCommand() {
        this.input = s.nextLine();
        return this.input;
    }

    public void showError(String message) {
        System.out.println(message);
    }

    public void showLine() {
        System.out.println("-------------------");
    }

    public void showSuccessMessage(String message) {
        System.out.println(message);
    }
}
