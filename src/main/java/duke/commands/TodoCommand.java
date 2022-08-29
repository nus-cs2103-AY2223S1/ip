package duke.commands;

import duke.tasks.TodoTask;
import duke.TaskList;
import duke.Storage;
import duke.DukeException;

/**
 * Command that represents the adding of a todo task.
 */
public class TodoCommand implements Command {
    private String taskName;

    /**
     * Default constructor of the Todo command.
     * @param taskName Name of the todo task.
     */
    public TodoCommand(String taskName) {
        this.taskName = taskName;
    }

    /**
     * Runs the todo command by adding the todo task into the tasklist and storage.
     * @param tasks TaskList that contains the temporary tasks.
     * @param storage Storage that the tasks are saved at.
     * @throws DukeException If any error occur.
     */
    @Override
    public void execute(TaskList tasks, Storage storage) throws DukeException {
        TodoTask newTask = new TodoTask(taskName);
        tasks.add(newTask);
        System.out.println("Got it. I've added this task:");
        System.out.println(newTask);
        System.out.println("Now you have " + tasks.getSize() + " tasks in the list");
        storage.writeAll(tasks);
    }
}
