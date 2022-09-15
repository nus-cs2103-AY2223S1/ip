package duke.util;

/**
 * Text UI for Duke app.
 */
public class Ui {
    /**
     * Prints a greeting to the user.
     */
    public String greet() {
        String logo = "_________                     ___\n"
                + "\\    ___ \\  ___________   ____\\_ |_________  ____\n"
                + "/    \\  \\/_/ __ \\_  __ \\_/ __ \\| __ \\_  __ \\/  _ \\\n"
                + "\\     \\___\\  ___/|  | \\/\\  ___/| \\_\\ \\  | \\(  (_) )\n"
                + " \\________/\\_____>__|    \\_____>_____/__|   \\____/\n";
        String output = "Hello from\n" + logo;
        output += "What can I do for you?";
        return output;
    }

    /**
     * Prints a goodbye message to the user.
     */
    public String bye() {
        return "Goodbye! See you soon!";
    }

    /**
     * Displays the error to the user.
     * 
     * @param error Error message to be shown to user.
     */
    public String showError(String error) {
        return "OOPS! " + error;
    }

    /**
     * Displays the error to the user.
     * 
     * @param e Exception to be shown to user.
     */
    public String showError(Exception e) {
        return "OOPS! " + e.getMessage();
    }

    /**
     * Displays the start of the input line to user.
     */
    public void showInputLine() {
        System.out.print("--> ");
    }
}
