import java.util.Scanner;

public class Ui {

    private final String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    private Scanner sc;

    public Ui() {
        sc = new Scanner(System.in);
    }

    public void showWelcome() {
        System.out.println("Hello from\n" + logo + "\nHow can I help you?");
    }

    public void showExit() {
        System.out.println("Many thanks from \n" + logo + "\nHave a nice day!");
    }

    public String readCommand() {
        return sc.nextLine();
    }

    public void showMessage(String message) {
        System.out.println(message);
    }

    public void showLoadingError() {
        System.out.println("File not found, initialising new task list...");
    }

    public void showError(String errorMessage) {
        System.out.println(errorMessage);
    }
}
