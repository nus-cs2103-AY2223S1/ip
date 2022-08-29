package duke.util;

/**
 * Text UI for Duke app
 */
public class Ui {
    /**
     * Prints a greeting to the user.
     */
    public void greet() {
        String logo = "_________                     ___\n" + "\\    ___ \\  ___________   ____\\_ |_________  ____\n"
                + "/    \\  \\/_/ __ \\_  __ \\_/ __ \\| __ \\_  __ \\/  _ \\\n"
                + "\\     \\___\\  ___/|  | \\/\\  ___/| \\_\\ \\  | \\(  (_) )\n"
                + " \\________/\\_____>__|    \\_____>_____/__|   \\____/\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("What can I do for you?");
    }

    /**
     * Printsa goodbye message to the user.
     */
    public void bye() {
        System.out.println("Goodbye! See you soon!");
    }

    /**
     * Displays the error to the user.
     * 
     * @param error Error message to be shown to user
     */
    public void showError(String error) {
        System.out.println("🙁 OOPS! " + error);
    }

    /**
     * Displays the error to the user.
     * 
     * @param e Exception to be shown to user
     */
    public void showError(Exception e) {
        System.out.println("🙁 OOPS! " + e.getMessage());
    }

    /**
     * Displays the start of the input line to user.
     */
    public void showInputLine() {
        System.out.print("--> ");
    }
}
