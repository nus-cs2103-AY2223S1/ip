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
        System.out.println("Executing...");
        System.out.println(this.newTask);
        tasks.addTask(this.newTask);
        return String.format("Done Added");
    }
}
