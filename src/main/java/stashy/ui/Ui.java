package stashy.ui;

import stashy.data.task.TaskList;

import java.util.Scanner;

/**
 * Representing the UI of the application, including
 * all methods that handle any changes to the UI.
 */
public class Ui {
    private static Scanner sc = new Scanner(System.in);

    /**
     * The length of the horizontal line that is printed within the chatbot UI.
     */
    private static final int HORIZONTAL_LINE_LENGTH = 50;

    /**
     * Prints multiple lines, each indented by 4 spaces.
     *
     * @param obj Any printable object
     */
    public static void showIndented(Object obj) {
        System.out.println("    " + obj.toString().replace("\n", "\n    "));
    }

    /**
     * Prints a horizontal line, good to separate the input command and the output.
     */
    public static void showLine() {
        showIndented("_".repeat(HORIZONTAL_LINE_LENGTH));
    }

    /**
     * Prints the welcome message.
     */
    public void showWelcome() {
        System.out.println("\n" +
                " ____  ____  __   ____  _  _  _  _ \n" +
                "/ ___)(_  _)/ _\\ / ___)/ )( \\( \\/ )\n" +
                "\\___ \\  )( /    \\\\___ \\) __ ( )  / \n" +
                "(____/ (__)\\_/\\_/(____/\\_)(_/(__/  \n" +
                "\n Beep boop! Stashy here! What can I do for you?");
    }

    /**
     * Prints a loading error message.
     */
    public void showLoadingError() {
        showIndented("Issues found when loading the file, starting with an empty task list...");
    }

    /**
     * Prints an error message.
     *
     * @param message The error message
     */
    public void showError(String message) {
        showIndented("☹ BEEP BOOP BUZZ ERROR!!! " + message);
    }

    /**
     * Prints the task list in a tidy manner.
     *
     * @param tasks The list of tasks
     */
    public static void showTasks(TaskList tasks) {
        showIndented("Listing all task(s) in your list...\n" + tasks);
    }

    /**
     * Prints the end message.
     */
    public static void sayGoodbye() {
        System.out.println(" --                                                  -++-  \n" +
                ":-:+:-                                               ++::+ \n" +
                "+++-::-    ::+:                                       ++:::\n" +
                " ++:++:::-+::++            :-  ++              -::-   :+++:\n" +
                "  +++::::++++-           :=*::--:*#=+-         +:::: -+:--+\n" +
                "  :*+++::::::         :*+:::::::+*+:::+*:       :+::-::+:::\n" +
                "   ++++::::+-       :++=+:+++++++++:+*+++*-     -+++:::++++\n" +
                "   -+++::+++:     -++*+++++*=###=*++++++++++    :++::++++++\n" +
                "    -+++:-+:     -+++++++=##########=++++++++    +*::++++: \n" +
                "      :+++:      ++++++*###@@###@@####++++++::     +++:    \n" +
                "                ::++++*##@@@@@#@@@@####+++++:+             \n" +
                "                ::+++*###@@@@@@@@@@@####+++++:-            \n" +
                "                ::+++####@@@@@@@@@@@####=++++:-            \n" +
                "                ::++*####@#+:::::+=@#####*++++             \n" +
                "                 +++#####+++::::::++=####=+++:             \n" +
                "                  **###=+++++++++++++*####+*+              \n" +
                "                  ++*=+++++++++++++++++*#=+*               \n" +
                "                  -::*+++++++++++++++++*+::+               \n" +
                "                      -+++++++++++++++*:  -                \n" +
                "                          -:++***++:                       \n" +
                "\nGood bye then, see you some time! - Stashy, 2022");
    }

    /**
     * Reads the command given by the user.
     *
     * @return The (whitespace-trimmed) command supplied by user
     */
    public String readCommand() {
        return sc.nextLine().strip();
    }
}
