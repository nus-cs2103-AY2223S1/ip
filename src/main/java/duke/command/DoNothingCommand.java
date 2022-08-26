package duke.command;

import duke.MessagePrinter;
import duke.Storage;
import duke.TaskList;

/**
 * Represents a Command to do nothing in Duke.
 */
public class DoNothingCommand extends Command {
    /**
     * The constructor of the class.
     */
    protected DoNothingCommand() {
        super(Action.DONOTHING);
    }

    /**
     * Execute the Command with given Duke Segments.
     * @param taskList TaskList of the Duke.
     * @param messagePrinter MessagePrinter of the Duke.
     * @param storage Storage of the Duke.
     */
    @Override
    public void execute(TaskList taskList, MessagePrinter messagePrinter, Storage storage) {
        //            do nothing
    }

    /**
     * Returns the standard format of the Command.
     * @return String of standard format.
     */
    @Override
    public String getFormat() {
        return "";
    }

    /**
     * Returns whether this command terminates Duke.
     * @return Returns whether this command terminates Duke.
     */
    @Override
    public boolean isTerminated() {
        return false;
    }

    /**
     * Return boolean indicating whether this object
     * is equivalent to another object.
     *
     * @param obj The object to be checked.
     * @return The boolean whether the given object is equivalent to this object.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return obj instanceof DoNothingCommand;
    }
}

