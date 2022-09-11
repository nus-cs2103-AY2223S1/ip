package duke.command;

import duke.TaskList;
import duke.task.Task;


public class AddCommand implements Command {
    protected TaskList tasks;
    private String taskType;
    protected String description;
    public AddCommand(duke.TaskList taskList, String taskType, String description) {
        this.tasks = taskList;
        this.taskType = taskType;
        this.description = description;
    }
    public void execute(Task t) {
        tasks.addTask(t);
    }
}
