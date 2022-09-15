package Duke.Commands;

import Duke.Tasks.Task;
import Duke.Tasks.TaskList;

/**
 * Class that denotes the command of adding a task to current task list.
 * And it provides methods to handle commands of adding new tasks to the task list.
 * Types of class: ToDo, Deadline, Event
 */
public class AddTaskCommand extends UserCommand {
    private Task newTask;

    /**
     * Public constructor of AddTaskCommand class.
     * @param newTask Task to add into the task list.
     * @param tasks TaskList containing current tasks.
     */
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