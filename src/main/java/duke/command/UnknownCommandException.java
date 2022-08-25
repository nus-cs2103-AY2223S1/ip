package duke.command;

import duke.exception.DukeException;

/**
 * An unknown command exception for the Duke chatbot which extends from DukeException.
 */
public class UnknownCommandException extends DukeException {

    /** A string of all the recognized commands. */
    private String commands = "\ttodo - adds the task to the list\n" +
            "\tdeadline - adds the task with a deadline, e.g. deadline x /by Sunday\n" +
            "\t\tthe deadline can be in a date form of either \"DD-MM-YYYY\" or \"YYYY-MM-DD\"\n" +
            "\tevent - adds the task that happens at a specific time, e.g. event x /at Mon 2-4pm\n" +
            "\t\tthe event time/date can be in a form of either \"DD-MM-YYYY HH:MM\" or \"YYYY-MM-DD HH:MM\"\n" +
            "\tmark - marks task number n as completed, e.g. mark n\n" +
            "\tunmark - marks task number n as uncompleted, e.g. unmark n\n" +
            "\tdelete - deletes task number n, e.g. delete n\n" +
            "\tlist - lists out all your current tasks\n" +
            "\tbye - exits the program:(\n";

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
        return super.toString() + " \"" + this.cmd + "\" is not a valid command. Here are the list of commands:\n\n" +
                this.commands;
    }
}
