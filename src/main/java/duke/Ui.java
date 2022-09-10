package duke;

public class Ui {

    enum Keywords {
        mark,
        unmark,
        delete,
        todo,
        deadline,
        event
    }

    /**
     * A constructor to initialize the Ui Object.
     */
    public Ui() {}

    public String logo  =         "___   ___    ___  ____                     ___   ___ \n"
                               + "|  |  /  /  |  |  \\  \\      ------      /  /   |  | \n"
                               + "|   /   /   |  |   \\  \\    / / \\ \\    /  /    |  | \n"
                               + "|   \\  \\   |  |     \\  \\  /  /   \\ \\  /  /     |  | \n"
                               + "|    \\  \\  |  |      \\  \\/  /     \\ \\/  /      |  | \n"
                               + "|___| \\__\\ |__|       \\____/       \\____/       |__| \n";

    /**
     * Outputs the greeting message when starting up the project.
     */
    public static String printGreeting() {
        return "Hello! I'm KiwiQE :) \nWhat can I do for you? \n"
                + Duke.getTasks();
    }

    /**
     * Outputs the goodbye message when terminating the project.
     */
    public static String printGoodbyeMessage() {
        return "\n  sayonara, goodbye\n";
    }

    /**
     * Outputs the task list.
     */
    public static String printTaskListEmpty() {
        return "\n" + "  Nothing to do currently ehe\n";
    }

    /**
     * Outputs the corresponding customised Index Out Of Bounds Exception message.
     *
     * @param keyword A keyword object to identify which message to output.
     */
    public String printIndexOutOfBoundsException(Keywords keyword) {
        switch (keyword) {
        case delete:
            return "\n  Can't delete something that isn't there...\n" + "\n";
        case unmark:
            return "\n  HEY THERE'S NO SUCH TASK! >:(\n" + "\n";
        default:
            return "Oh no, unrecognised";
        }
    }

    /**
     * Outputs the corresponding customised String Index Out Of Bounds Exception message.
     *
     * @param keyword A keyword object to identify which message to output.
     */
    public String printInsufficientInfoException(Keywords keyword) {
        switch (keyword) {
        case delete:
            return "\n  Insufficient information to delete! Please input more ;-;\n";
        case mark:
            return "\n  Insufficient information to mark! Please input more ;-;\n";
        case unmark:
            return "\n  Insufficient information to unmark! Please input more ;-;\n";
        case todo:
                return "\n  Insufficient information to make a todo! Please input more ;-;\n";
        case deadline:
                return "\n  Insufficient information to make a deadline! "
                        + "Please input more ;-;\n";
        case event:
                return "\n  Insufficient information to make a event! "
                        + "Please input more ;-;\n";
        default:
            return "This does not make sense, what are you trying to do?";
        }
    }

    /**
     * Outputs the message when input is not recognised.
     */
    public String printUnrecognisedWord() {
        return "\n  What do you mean by Justin Bieber plays~\n";
    }

    /**
     * Outputs the File Not Found Error.
     */
    public static String printFileNotFound() {
        return "File has yet to be created, creating for you now!";
    }

    /**
     * Outputs the error unable to create the file.
     */
    public static String printCannotOpenFile() {
        return "You can't create a file!";
    }

    /**
     * Outputs unable to append information to the file.
     */
    public static String printUnableToAppend() {
        return "You can't append!";
    }

    /**
     * Outputs if no tasks exist when find function used.
     */
    public String printNoMatchingTask() {
        return "\n No tasks exist yet, can't find a match :( \n";
    }

}
