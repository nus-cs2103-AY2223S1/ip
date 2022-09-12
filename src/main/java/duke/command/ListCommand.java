package duke.command;

import duke.TaskList;

/**
 * Concrete class representing LIST
 */
public class ListCommand extends Command {
    private final TaskList taskList;

    /**
     * Constructs an instance of ListCommand
     * @param taskList The tasks list
     */
    public ListCommand(TaskList taskList) {
        this.taskList = taskList;
    }
    @Override
    public String execute() {
        String tasks = taskList.getTextRepresentationOfAllTasks();
        return tasks.length() > 0 ? "Here are the tasks in your list:"
                + tasks : "No existing tasks found :(";
    }
}
