package duke.exception;

/**
 * An unknown command exception for the Duke chatbot which extends from DukeException.
 */
public class UnknownCommandException extends DukeException {

    /** A string of all the recognized commands. */
    private String commands = "Commands to add tasks:\n"
            + "- todo - adds the task to the list\n"
            + "- deadline - adds the task with a deadline, e.g. deadline x /by Sunday\n"
            + "   -The deadline can also be in a date form of either \"DD-MM-YYYY\" or \"YYYY-MM-DD\"\n"
            + "- event - adds the task that happens at a specific time, e.g. event x /at Mon 2-4pm\n"
            + "   -The event time/date can also be in a form of either \"DD-MM-YYYY HH:MM\" or \"YYYY-MM-DD HH:MM\"\n"
            + "Commands to edit tasks:\n"
            + "- mark - marks task number n as completed, e.g. mark n\n"
            + "- unmark - marks task number n as uncompleted, e.g. unmark n\n"
            + "- delete - deletes task number n, e.g. delete n\n"
            + "Utility commands:\n"
            + "- find - finds all tasks which match the search input, e.g. find book\n"
            + "- list - lists out all your current tasks\n"
            + "- bye - exits the program:(\n";

    /** The command that threw the exception. */
    private String cmd;

    /**
     * Constructor for an UnknownCommandException.
     *
     * @param cmd The command that threw the exception.
     */
    public UnknownCommandException(String cmd) {
        this.cmd = cmd;
    }

    /**
     * Returns the string representation of the UnknownCommandException object.
     *
     * @return The string representation of the UnknownCommandException object.
     */
    @Override
    public String toString() {
        return super.toString() + " \"" + this.cmd + "\" is not a valid command. "
                + "Here is the list of valid commands:\n\n" + this.commands;
    }
}
