import java.util.Scanner;

/**
 * Encapsulates any interactions with the user.
 */
public class Ui {
    private final Scanner sc = new Scanner(System.in);

    /**
     * Prints welcome message upon starting the bot.
     */
    public void greet() {
        System.out.println("Hello from Duke!");
        System.out.println("I can help you manage your tasks! :\")\n");
    }

    public void goodbye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Prompts the user for input.
     * @return User input for processing
     */
    public String getInput() {
        System.out.println("What can I do for you?");
        return sc.nextLine();
    }

    /**
     * Returns the output of a command
     * @param s Feedback message after executing command
     */
    public void showOutput(String s) {
        System.out.println(s);
    }

    /**
     * Notifies the user of a loading error.
     */
    public void showLoadingError() {
        System.out.println("Unable to load tasks from file.");
        System.out.println("Initializing Duke with an empty list of tasks...");
    }

    /**
     * Prints out an error message for any DukeException.
     * @param err Error message of exception
     */
    public void showError(String err) {
        System.out.println(err);
    }
}
