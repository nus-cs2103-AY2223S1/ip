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
     * Returns a string with the greeting message and saved list when starting up the project.
     *
     * @return a String with greeting message and saved list of tasks.
     */
    public static String printGreeting() {
        return "Hello! I'm KiwiQE :) \nWhat can I do for you? \n" + Duke.getTasks();
    }

    /**
     * Returns a string with the goodbye message when terminating the project.
     *
     * @return a String with the goodbye message.
     */
    public static String printGoodbyeMessage() {
        return "\n  sayonara, goodbye\n";
    }

    /**
     * Returns a string describing empty task list.
     *
     * @return a String informing user task list is empty.
     */
    public static String printTaskListEmpty() {
        return "\n" + "  Nothing to do currently ehe\n";
    }

    /**
     * Returns a string with the corresponding customised Index Out Of Bounds Exception message.
     *
     * @param keyword A keyword object to identify which message to output.
     * @return A String with the customised exception message.
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
     * Returns a string with the corresponding customised String Index Out Of Bounds Exception message.
     *
     * @param keyword A keyword object to identify which message to output.
     * @return A String with the customised exception message.
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
     * Returns a string with the message when input is not recognised.
     *
     * @return a String to inform user input not recognised.
     */
    public String printUnrecognisedWord() {
        return "\n  What do you mean by Justin Bieber plays~\n";
    }

    /**
     * Returns a string with the File Not Found Error.
     *
     * @return a String to inform users File does not exist.
     */
    public static String printFileNotFound() {
        return "File has yet to be created, creating for you now!";
    }

    /**
     * Returns a string with the error unable to create the file.
     *
     * @return a String to inform users about File creation error.
     */
    public static String printCannotOpenFile() {
        return "You can't create a file!";
    }

    /**
     * Returns a string with the message unable to append information to the file.
     *
     * @return a String to inform users about appending error.
     */
    public static String printUnableToAppend() {
        return "You can't append!";
    }

    /**
     * Returns a string to inform users no tasks exist when find function used.
     *
     * @return a String to inform users no task exist when find function used.
     */
    public String printNoMatchingTask() {
        return "\n No tasks exist yet, can't find a match :( \n";
    }

}
