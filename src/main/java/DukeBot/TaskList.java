package DukeBot;

import java.util.ArrayList;

/**
 * Encapsulates the list containing tasks as a class.
 */
public class TaskList extends ArrayList<Task> {

    public TaskList() {
        super();
    }

    /**
     * Adds a new task to the list of tasks.
     *
     * @param newTask Task to be added to the list.
     */
    public void addTask(Task newTask) {
        this.add(newTask);
    }

    /**
     * Deletes a task from the list of tasks.
     *
     * @param taskToDelete Task to be deleted from the list.
     * @return Task that was deleted from the list
     */
    public Task deleteTask(int taskToDelete) {
        Task deletedTask = this.remove(taskToDelete);
        Task.reduceTaskCount();
        return deletedTask;
    }
}
