package duke.exception;

/**
 * An unknown command exception for the Duke chatbot which extends from DukeException.
 */
public class UnknownCommandException extends DukeException {

    /** A string of all the recognized commands. */
    private String commands = "\tCommands to add tasks:\n"
            + "\t  todo - adds the task to the list\n"
            + "\t  deadline - adds the task with a deadline, e.g. deadline x /by Sunday\n"
            + "\t\tthe deadline can be in a date form of either \"DD-MM-YYYY\" or \"YYYY-MM-DD\"\n"
            + "\t  event - adds the task that happens at a specific time, e.g. event x /at Mon 2-4pm\n"
            + "\t\tthe event time/date can be in a form of either \"DD-MM-YYYY HH:MM\" or \"YYYY-MM-DD HH:MM\"\n"
            + "\n\tCommands to edit tasks:\n"
            + "\t  mark - marks task number n as completed, e.g. mark n\n"
            + "\t  unmark - marks task number n as uncompleted, e.g. unmark n\n"
            + "\t  delete - deletes task number n, e.g. delete n\n"
            + "\n\tUtility commands:\n"
            + "\t  find - finds all tasks which match the search input, e.g. find book\n"
            + "\t  list - lists out all your current tasks\n"
            + "\t  bye - exits the program:(\n";

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
