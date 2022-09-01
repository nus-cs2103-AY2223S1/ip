package duke.utils;

/**
 * Handles the interactions and displays information for the user to see.
 * @author Jason
 */
public class Ui {

    private static final String UI_LOGO = "        / \\     |_   _| | |  / _|                   | |\n"
                                        + "       /   \\      | |   | | | |_   _ __    ___    __| |\n"
                                        + "      / / \\ \\     | |   | | |  _| | '__|  / _ \\  / _` |\n"
                                        + "     / _____ \\   _| |_  | | | |   | |    |  __/ | (_| |\n"
                                        + "    /_/     \\_\\ |_____| |_| |_|   |_|     \\___|  \\__,_|\n";

    private static final String UI_GREETING = "            Hello! I am \n" + UI_LOGO
                                            + "Your personal assistant. What can I do for you?\n";


    private static final String UI_GOODBYE = "☺ Saving your data before you go...\n"
                                           + "Bye. Hope to see you again soon!\n";

    /**
     * Prints the intended message to the console.
     * @param message Message to be printed.
     */
    public static void printMessage(String message) {
        System.out.println("____________________________________________________________\n"
                + message
                + "____________________________________________________________\n");
    }

    /**
     * Prints the greeting message to the console.
     */
    public static String greet() {
        return UI_GREETING;
    }

    /**
     * Prints the goodbye message to the console.
     * @return Goodbye message to exit the application.
     */
    public static String bye() {
        return UI_GOODBYE;
    }
}
