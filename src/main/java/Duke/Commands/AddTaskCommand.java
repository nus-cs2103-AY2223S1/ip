package Duke.Commands;

import Duke.Tasks.Task;
import Duke.Tasks.TaskList;

/**
 * AddTaskCommand
 * Provides methods to handle commands of adding new tasks to the task list.
 * Types of class: ToDo, Deadline, Event
 */
public class AddTaskCommand extends UserCommand {

    private Task newTask;

    public AddTaskCommand(Task newTask, TaskList tasks) {
        super(tasks);
        this.newTask = newTask;
    }

    @Override
    public String execute() {
        tasks.addTask(this.newTask);
        String output = String.format("Nice! You have successfully added the task in your list:\n %s\n", this.newTask);
        return output;

    }
}
