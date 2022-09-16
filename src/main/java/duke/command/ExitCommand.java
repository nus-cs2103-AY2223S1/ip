package duke.command;

import duke.Ui;
import duke.storage.Storage;
import duke.task.TaskList;

/**
 * Represents an ExitCommand which extends Command.
 */
public class ExitCommand extends Command {
    public static final String COMMAND_ID = "BYE";
    private static final String EXIT_MSG = "Bye. Hope to see you again soon!";

    /**
     * Returns a string of the exit task that had just been executed
     * @param taskList
     * @param ui
     * @param storage
     * @return a result of the current exit task execution
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        return EXIT_MSG;
    }
}
