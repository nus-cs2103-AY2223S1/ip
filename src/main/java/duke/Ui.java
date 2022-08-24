package duke;
import java.io.IOException;
import java.util.Scanner;

public class Ui {
    
    private final String WELCOME = "Hello! I'm Luke\nWhat can I do for you?";
    private final String ADDED_TASK = "Got it. I've added this task:";
    private final String REMOVED_TASK = "Noted. I've removed this task:";
    private Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public void showWelcome() {
        System.out.println(WELCOME);
    }

    public String readCommand() {
        String input = scanner.nextLine().strip();
        return input;
    }

    public void add(Task task) {
        System.out.println(String.format("%s\n%s", ADDED_TASK, task));
    }

    public void remove(Task task) {
        System.out.println(String.format("%s\n%s", REMOVED_TASK, task));
    }

    public void showGoodbye() {
        System.out.println("Bye! Thanks for using Luke!");
        scanner.close();
    }

    public void showError(DukeException e) {
        System.out.println(e.getMessage());
    }

    public void showLoadingError(IOException e) {
        System.out.println(e.getMessage());
    }

}
