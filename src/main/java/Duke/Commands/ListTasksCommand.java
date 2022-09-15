package duke.commands;

import duke.tasks.TaskList;

/**
 * Class that denotes the command of showing all tasks' information.
 */
public class ListTasksCommand extends UserCommand {

    /**
     * Public constructor of ListTasksCommand.
     * @param tasks TaskList containing current tasks.
     */
    public ListTasksCommand(TaskList tasks) {
        super(tasks);
    }
    @Override
    public String execute() {
        String output = "";
        output += this.tasks.showTasks();
        return output;
    }
}
