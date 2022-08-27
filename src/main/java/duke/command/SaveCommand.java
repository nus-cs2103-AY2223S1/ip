package duke.command;

import duke.util.MessagePrinter;
import duke.util.Storage;
import duke.util.TaskList;

/**
 * Represents a Command to save Tasks to Storage in Duke.
 */
public class SaveCommand extends Command {
    /**
     * The constructor of the Class.
     */
    protected SaveCommand() {
        super(Action.SAVE);
    }

    /**
     * Execute the Command with given Duke Segments.
     * @param taskList TaskList of the Duke.
     * @param messagePrinter MessagePrinter of the Duke.
     * @param storage Storage of the Duke.
     */
    @Override
    public void execute(TaskList taskList, MessagePrinter messagePrinter, Storage storage) {
        storage.write(taskList.toFormattedString());
        int size = taskList.size();
        String temp = size == 1 ? "task has" : "tasks have";
        messagePrinter.printMessage("Your " + size + " " + temp + " been saved successfully");
    }

    /**
     * Returns the standard format of the Command.
     * @return String of standard format.
     */
    @Override
    public String getFormat() {
        return "save";
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
        return obj instanceof SaveCommand;
    }
}
