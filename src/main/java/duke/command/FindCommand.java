package duke.command;

import java.io.IOException;

import duke.storage.Storage;
import duke.Ui;
import duke.task.TaskList;

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

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws IOException {
        TaskList matchingTaskList = taskList.findMatchingTasks(this.query);
        if (matchingTaskList.getTaskList().isEmpty()) {
            System.out.println("No matching tasks found in list.");
        } else {
            String output = String.format(
                    "Found %d matches in your todo list !", matchingTaskList.getTaskList().size());
            System.out.println(output);
            matchingTaskList.listTasks();
        }
    }
}
