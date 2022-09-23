package duke.command;

import duke.Duke;
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
    public SaveCommand() {
        super(Action.SAVE);
    }

    /**
     * Executes the Command with given Duke.
     * @param duke The target duke that the command takes effect.
     * @return The response of Duke.
     */
    @Override
    public String execute(Duke duke) {
        TaskList taskList = duke.getTaskList();
        MessagePrinter messagePrinter = duke.getMessagePrinter();
        Storage storage = duke.getStorage();
        storage.write(taskList);
        int size = taskList.size();
        String temp = size == 1 ? "task has" : "tasks have";
        return messagePrinter.getPrintMessage("Your " + size + " " + temp + " been saved successfully");
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
     * Returns boolean indicating whether this object
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
