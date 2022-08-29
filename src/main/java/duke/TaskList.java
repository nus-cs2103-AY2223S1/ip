package duke;

import duke.tasks.Task;
import java.util.ArrayList;

/**
 * Task that represent the temporary storage of the Tasks.
 */
public class TaskList {
    private ArrayList<Task> taskList;

    /**
     * Default constructor of the TaskList class.
     */
    public TaskList() {
        taskList = new ArrayList<>();
    }

    /**
     * Adds the task into the TaskList.
     *
     * @param task Task to be added.
     */
    public void add(Task task) {
        taskList.add(task);
    }

    /**
     * Return the Task at the given index.
     *
     * @param index Position of the task.
     * @return Task at the position.
     */
    public Task get(int index) {
        return taskList.get(index);
    }

    /**
     * Remove the Task at the given index.
     *
     * @param index Position of the task.
     */
    public void remove(int index) {
        taskList.remove(index);
    }

    /**
     * Return the size of the TaskList.
     *
     * @return The size of the TaskList.
     */
    public int getSize() {
        return this.taskList.size();
    }

    /**
     * Return the TaskList with Tasks containing the search keyword.
     * @param searchKeyword Keyword found inside the task.
     * @return TaskList with Tasks containing the search keyword.
     */
    public TaskList search(String searchKeyword) {
        TaskList taskListWithKeyword = new TaskList();
        for (int i = 0; i < this.getSize(); i++) {
            Task task = this.get(i);
            if (task.getName().contains(searchKeyword)) {
                taskListWithKeyword.add(task);
            }
        }

        return taskListWithKeyword;
    }
}
