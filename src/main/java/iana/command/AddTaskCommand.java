package iana.command;

import iana.exception.IanaException;
import iana.main.Storage;
import iana.main.Ui;
import iana.tasks.Task;
import iana.tasks.TaskList;

/**
 * Command that adds a new task to the list.
 */
public class AddTaskCommand extends Command {
    private String taskInput;

    /**
     * Constructor for AddTaskCommand.
     * @param taskInput the full input that describes the task.
     */
    public AddTaskCommand(String taskInput) {
        this.taskInput = taskInput;
    }

    /**
     * Runs the command to try to add a new task.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            Task newTask = Task.of(this.taskInput, false);
            tasks.add(newTask);
            ui.sayTaskAdded(newTask);
        } catch (IanaException e) {
            ui.echo(e.getMessage());
        }
    }

    /**
     * Returns false since command is not an exit command.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
