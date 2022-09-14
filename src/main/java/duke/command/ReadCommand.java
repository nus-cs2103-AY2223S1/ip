package duke.command;

import duke.Duke;
import duke.util.MessagePrinter;
import duke.util.Parser;
import duke.util.Storage;
import duke.util.TaskList;

/**
 * Represents a Command to read Tasks from Storage in Duke.
 */
public class ReadCommand extends Command {
    /**
     * The constructor of the Class.
     */
    public ReadCommand() {
        super(Action.READ);
    }

    /**
     * Executes the Command with given Duke.
     * @param duke The target duke that the command takes effect.
     * @return The response of Duke.
     */
    @Override
    public String execute(Duke duke) {
        MessagePrinter messagePrinter = duke.getMessagePrinter();
        Storage storage = duke.getStorage();
        TaskList newTaskList = Parser.parseTaskList(storage.read());
        duke.setTaskList(newTaskList);
        int size = newTaskList.size();
        String temp = size == 1 ? "task has" : "tasks have";
        return messagePrinter.printMessage("Your " + size + " " + temp + " been loaded successfully\n"
                + "Type [list] to view your tasks");
    }

    /**
     * Returns the standard format of the Command.
     * @return String of standard format.
     */
    @Override
    public String getFormat() {
        return "read";
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
        return obj instanceof ReadCommand;
    }
}
