package duke.ui;

/**
 * User interface for the application
 *
 * @author Pontakorn Prasertsuk
 */
public class Ui {

    private static final String LOGO = "______       _     \n" + "| ___ \\     | |    \n"
            + "| |_/ / ___ | |__  \n" + "| ___ \\/ _ \\| '_ \\ \n" + "| |_/ / (_) | |_) |\n"
            + "\\____/ \\___/|_.__/ \n";

    /**
     * Shows the logo of the application
     */
    public void showGreeting() {
        System.out.println("Hello from");
        System.out.println(LOGO);
        System.out.println("What can I do for you?");
    }

    /**
     * Shows goodbye message
     */
    public void showGoodbye() {
        System.out.println("Goodbye!");
    }

    /**
     * Shows the divider
     */
    public void showLine() {
        System.out.println("----------------------------------------------------");
    }

    /**
     * Shows the input cursor
     */
    public void showInput() {
        System.out.print("> ");
    }

    /**
     * Shows the output message
     *
     * @param output the output to be shown
     */
    public void showOutput(String message) {
        System.out.println(message);
    }

    /**
     * Shows the error message
     *
     * @param error the error to be shown
     */
    public void showError(String error) {
        System.out.println("Error: " + error);
    }
}
