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
     * Constructs the class.
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
        return messagePrinter.getPrintMessage("Your " + size + " " + temp + " been loaded successfully\n"
                + "Type [list] to view your tasks");
    }

    /**
     * Returns whether this command terminates Duke.
     * @return Returns whether this command terminates Duke.
     */
    @Override
    public boolean isTerminating() {
        return false;
    }
}
