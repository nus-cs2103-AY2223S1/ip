import java.util.Scanner;

public class Ui {

    protected Scanner scanner;
    protected String input;

    public Ui() {
        scanner = new Scanner(System.in);
    }

    public void showWelcome() {
        System.out.println("Hello! I'm Fred! What can I do for you?");
    }

    public void showLine() {
        System.out.println("___________________________________________________");
    }

    public void showLoadingError() {
        System.out.println("Loading error!");
    }

    public String readCommand() {
        return scanner.nextLine();
    }

    public void showMessage(String message) {
        System.out.println(message);
    }

    public void showExit() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public void showError(String errorMessage) {
        System.out.println("Error: " + errorMessage);
    }

}
