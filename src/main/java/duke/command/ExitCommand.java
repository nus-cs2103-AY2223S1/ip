package duke.command;

import duke.MessagePrinter;
import duke.Storage;
import duke.TaskList;

/**
 * Represents a Command to terminates the Duke.
 */
public class ExitCommand extends Command {
    /**
     * The constructor of the Class.
     */
    protected ExitCommand() {
        super(Action.EXIT);
    }

    /**
     * Execute the Command with given Duke Segments.
     * @param taskList TaskList of the Duke.
     * @param messagePrinter MessagePrinter of the Duke.
     * @param storage Storage of the Duke.
     */
    @Override
    public void execute(TaskList taskList, MessagePrinter messagePrinter, Storage storage) {
        String farewellMsg = "Bye. Hope to see you again soon!";
        messagePrinter.printMessage(farewellMsg);
    }

    /**
     * Returns the standard format of the Command.
     * @return String of standard format.
     */
    @Override
    public String getFormat() {
        return "bye";
    }

    /**
     * Returns whether this command terminates Duke.
     * @return Returns whether this command terminates Duke.
     */
    @Override
    public boolean isTerminated() {
        return true;
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
        return obj instanceof ExitCommand;
    }
}
