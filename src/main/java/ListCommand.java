package duke;

/**
 * Represents a command to list all the task in the current tasklist.
 */
public class ListCommand extends Command {

    ListCommand() {

    }

    /**
     * Execute the list command.
     * @param tasks current tasklist.
     * @param ui .
     * @param storage .
     * @return boolean false (true if exit and false if not exit).
     */
    boolean execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.getPrintedList();
        return false;
    }

}
