package iana.command;

import iana.exception.IanaException;
import iana.tasks.Task;
import iana.tasks.TaskList;
import iana.ui.Ui;

/**
 * Command that adds a new task to the list.
 */
public class AddTaskCommand extends Command {

    private String taskInput;

    /**
     * Constructor for AddTaskCommand.
     *
     * @param taskInput the full input that describes the task.
     */
    public AddTaskCommand(String taskInput) {
        this.taskInput = taskInput;
    }

    @Override
    public String execute(TaskList tasks, Ui ui) throws IanaException {
        Task newTask = Task.of(this.taskInput, false);
        tasks.add(newTask);
        return ui.sayTaskAdded(newTask);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
