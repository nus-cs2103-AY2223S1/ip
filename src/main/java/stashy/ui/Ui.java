package stashy.ui;

import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

import stashy.data.task.TaskList;

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

    /*
     * All available commands to be shown upon welcome message.
     */
    private static final String[] COMMANDS = {
        "todo", "deadline", "event",
        "list", "mark", "unmark",
        "find", "help", "delete",
        "bye"};
    private static final String COMMANDS_STRING = Arrays
        .stream(COMMANDS)
        .map(x -> "- " + x)
        .collect(Collectors.joining("\n"));

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
        System.out.println("\n"
                + " ____  ____  __   ____  _  _  _  _ \n"
                + "/ ___)(_  _)/ _\\ / ___)/ )( \\( \\/ )\n"
                + "\\___ \\  )( /    \\\\___ \\) __ ( )  / \n"
                + "(____/ (__)\\_/\\_/(____/\\_)(_/(__/  \n"
                + "\n" + showWelcomeString());
    }

    /**
     * Gives a welcome message as a String.
     *
     * @return A simple welcome message
     */
    public String showWelcomeString() {
        return "Beep boop! Stashy here! What can I do for you?\n\n"
            + showCommandList();
    }

    /**
     * Prints a loading error message.
     */
    public void showLoadingError() {
        showIndented("Issues found when loading the file, starting with an empty task list...");
    }

    /**
     * Prints the list of commands, String version.
     *
     * @return The list of commands prepended by a special text
     */
    public String showCommandList() {
        return "List of available commands so far:\n"
            + COMMANDS_STRING
            + "\n\nFor more details, type 'help <command>', e.g. 'help todo'";
    }

    /**
     * Prints an error message.
     *
     * @param message The error message
     */
    public void showError(String message) {
        showIndented(showErrorString(message));
    }

    /**
     * Prints an error message, String version.
     *
     * @param message The error message
     * @return The error message prepended by a special text
     */
    public String showErrorString(String message) {
        return "☹ BEEP BOOP BUZZ ERROR!!!\n" + message;
    }

    /**
     * Prints the task list in a tidy manner.
     *
     * @param tasks The list of tasks
     */
    public static void showTasks(TaskList tasks) {
        showIndented(showTasksString(tasks));
    }

    /**
     * Prints the task list in a tidy manner, String version.
     *
     * @param tasks The list of tasks
     * @return The String version of the print statement
     */
    public static String showTasksString(TaskList tasks) {
        return "Listing all task(s) in your list...\n" + tasks;
    }

    /**
     * Prints the filtered task list in a tidy manner.
     *
     * @param tasks The list of (filtered) tasks
     */
    public static void showFilteredTasks(TaskList tasks) {
        showIndented(showFilteredTasksString(tasks));
    }

    /**
     * Prints the filtered task list in a tidy manner, String version.
     *
     * @param tasks The list of (filtered) tasks
     * @return The String version of the print statement
     */
    public static String showFilteredTasksString(TaskList tasks) {
        return "Listing all the matching task(s)...\n" + tasks;
    }

    /**
     * Prints the end message.
     */
    public static void showGoodbye() {
        System.out.println(" --                                                  -++-  \n"
                + ":-:+:-                                               ++::+ \n"
                + "+++-::-    ::+:                                       ++:::\n"
                + " ++:++:::-+::++            :-  ++              -::-   :+++:\n"
                + "  +++::::++++-           :=*::--:*#=+-         +:::: -+:--+\n"
                + "  :*+++::::::         :*+:::::::+*+:::+*:       :+::-::+:::\n"
                + "   ++++::::+-       :++=+:+++++++++:+*+++*-     -+++:::++++\n"
                + "   -+++::+++:     -++*+++++*=###=*++++++++++    :++::++++++\n"
                + "    -+++:-+:     -+++++++=##########=++++++++    +*::++++: \n"
                + "      :+++:      ++++++*###@@###@@####++++++::     +++:    \n"
                + "                ::++++*##@@@@@#@@@@####+++++:+             \n"
                + "                ::+++*###@@@@@@@@@@@####+++++:-            \n"
                + "                ::+++####@@@@@@@@@@@####=++++:-            \n"
                + "                ::++*####@#+:::::+=@#####*++++             \n"
                + "                 +++#####+++::::::++=####=+++:             \n"
                + "                  **###=+++++++++++++*####+*+              \n"
                + "                  ++*=+++++++++++++++++*#=+*               \n"
                + "                  -::*+++++++++++++++++*+::+               \n"
                + "                      -+++++++++++++++*:  -                \n"
                + "                          -:++***++:                       \n"
                + "\n" + showGoodbyeString());
    }

    /**
     * Gives the goodbye message as a String.
     *
     * @return A simple goodbye message
     */
    public static String showGoodbyeString() {
        return "Good bye then, see you some time! - Stashy, 2022";
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
