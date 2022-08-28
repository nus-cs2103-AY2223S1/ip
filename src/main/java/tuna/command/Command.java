package tuna.command;

import tuna.Storage;
import tuna.TaskList;
import tuna.TunaException;
import tuna.Ui;

/**
 * Represents a command.
 */
public abstract class Command {

    /** Type of the command */
    private CommandType type;

    /**
     * Creates a command.
     *
     * @param type type of the command.
     */
    public Command(CommandType type) {
        this.type = type;
    }

    /**
     * Returns the type of the command.
     *
     * @return type of the command.
     */
    public CommandType getType() {
        return type;
    }

    /**
     * Executes the command.
     *
     * @param tasks TaskList object.
     * @param ui Ui object.
     * @param storage Storage object.
     * @return String indicating the task executed.
     * @throws TunaException exception thrown when commands are missing.
     */
    public abstract String execute(TaskList tasks, Ui ui, Storage storage) throws TunaException;

    /**
     * Finds an element within an array.
     *
     * @param <T> generic type for any type of array and element.
     * @param arr array to be searched.
     * @param elem element to be searched for.
     * @return index of the element in the array.
     */
    protected static <T> int findElem(T[] arr, T elem) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i].equals(elem)) {
                return i;
            }
        }
        return -1;
    }
}
