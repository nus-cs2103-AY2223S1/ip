package duke.command;

import java.io.IOException;

import duke.Ui;
import duke.storage.Storage;
import duke.task.TaskList;

/**
 * Represents a FindCommand which extends Command
 */
public class FindCommand extends Command {
    public static final String COMMAND_ID = "FIND";
    private final String query;

    /**
     * Constructs an instance of FindCommand which inherits from Command.
     * @param query is the target search term
     * @return an instance of FindCommand.
     */
    public FindCommand(String query) {
        super();
        this.query = query;
    }

    /**
     * Returns a string of the task that has just been executed
     * @param taskList
     * @param ui
     * @param storage
     * @return string of executed task information.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws IOException {
        TaskList matchingTaskList = taskList.findMatchingTasks(this.query);
        if (matchingTaskList.getTaskList().isEmpty()) {
            return "No matching tasks found in list.";
        } else {
            String output = String.format(
                    "Found %d matches in your todo list !", matchingTaskList.getTaskList().size());
            String result = matchingTaskList.listTasks();
            return String.format("%s\n%s", output, result);
        }
    }
}
