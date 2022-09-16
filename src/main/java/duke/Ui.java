package duke;

/**
 * Encapsulates the Ui that initialises the user interface
 */
public class Ui {

    public static String start() {
        return "Hello! I'm Duke\n" + "What can I do for you?\n";
    }

    public static String end() {
        return "\nBye. Hope to see you again soon!";
    }

    public static String showLoadingError(DukeException e) {
        return e.getMessage();
    }

}
