package utils;

/**
 * Handles printing messages and errors to the user.
 */
public class Ui {

    /**
     * Prints a welcome message to the user.
     */
    public static String welcomeUser() {
        String s = "Hello! I'm Ditto (._.) \n"
                + "What can I do for you?\n"
                + "(Please enter any datetime inputs in \n"
                + "\"yyyy-mm-dd hhmm\" format)";
        return s;
    }

    /**
     * Prints a goodbye message to the user.
     */
    public static String sayGoodbye() {
        return "Bye. Hope to see you again soon! (._.)";
    }

    /**
     * Prints the error message within the exception thrown.
     * @param e The exception thrown from the method.
     */
    public static void displayErrorMessage(Exception e) {
        e.getMessage();
    }

    /**
     * Prints the error message.
     * @param e The exception whose message is to be printed.
     */
    public static void printErrorWithoutFormatting(Exception e) {
        System.out.print(e.getMessage());
    }
}
