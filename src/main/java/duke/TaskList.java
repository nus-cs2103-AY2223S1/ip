package duke;

import java.io.IOException;
import java.util.List;

import duke.utils.Storage;
import duke.utils.Ui;

/**
 * Stores and manages all the tasks.
 * @author Jason
 */
public class TaskList {
    private List<Task> taskList;

    public TaskList(List<Task> taskList) {
        this.taskList = taskList;
    }

    /**
     * Adds a task to the list.
     * @param task Task to be added into taskList.
     */
    public void addTask(Task task, Storage storage) throws IOException {
        taskList.add(task);
        int numOfTasks = taskList.size();

        String message = "Got it. I've added this task: \n  "
                + task + "\n"
                + "Now you have " + numOfTasks + " tasks in the list.\n";
        Ui.printMessage(message);

        //Update save file after each task is added
        storage.saveData(this.taskList);
    }

    /**
     * Removes the task at index input.
     * @param input Index of task to be removed.
     */
    public void deleteTask(int input) {
        this.taskList.remove(input);
    }

    /**
     * Gives the current size of the taskList.
     * @return Current size of taskList.
     */
    public int size() {
        return this.taskList.size();
    }

    /**
     * Provides the current task at index i.
     * @param i Index of task to get.
     * @return ask at index i.
     */
    public Task get(int i) {
        return this.taskList.get(i);
    }

    /**
     * Provides the list of tasks.
     * @return Current list of tasks.
     */
    public List<Task> getList() {
        return this.taskList;
    }
}
