import java.util.Scanner;

public class Ui {
    // Solution below adapted from
    // https://stackoverflow.com/questions/5287538/how-to-get-the-user-input-in-java
    private Scanner sc = new Scanner(System.in);

    /**
     * Creates a new UI instance.
     */
    public Ui() {
    }

    public void showWelcome() {
        System.out.println("Hello! I'm Jude.");
        System.out.println("What can I do for you?");
    }

    public void showCommandReadReady() {
        System.out.println();
        System.out.print("> ");
    }

    public String readCommand() {
        return sc.nextLine();
    }
}
