package duke.models;

/**
 * The Ui class contains methods for formatting messages displayed to the user.
 *
 * @author Zhu Yuanxi
 */
public class Ui {
    private static final String logo =
            " ____        _        \n"
                    + "|  _ \\ _   _| | _____ \n"
                    + "| | | | | | | |/ / _ \\\n"
                    + "| |_| | |_| |   <  __/\n"
                    + "|____/ \\__,_|_|\\_\\___|\n";
    private static final String separator = "––––––––––––––––––––––––––––––––––––––––";

    /**
     * Prints greeting messages to the user when the app starts.
     */
    public void greet() {
        System.out.println("Hello from\n" + logo);
        System.out.print("Tell me what you need\n");
    }

    /**
     * Prints goodbye message to the user when the user shuts down the app.
     */
    public void exit() {
        System.out.print("Goodbye!");
    }

    /**
     * Formats the error messages.
     *
     * @param errorMessage The formatted error messages.
     */
    public void showError(String errorMessage) {
        System.out.println(separator);
        System.out.println("OOPS! " + errorMessage);
        System.out.println(separator);
    }

    /**
     * Formats the normal response messages.
     *
     * @param response The formatted response messages.
     */
    public void showResponse(String response) {
        System.out.println(separator);
        System.out.print(response);
        System.out.println(separator);
    }
}
