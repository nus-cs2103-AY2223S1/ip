import java.util.Scanner;

// interacts with user
public class Ui {
    private Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public String readCommand() {
        return scanner.nextLine();
    }

    public void showLine() {
        System.out.println("-----------------------------------");
    }

    public void showError(DukeException de) {
        System.out.println(de.getMessage());
    }

    public void showWelcome() {
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
    }
}
