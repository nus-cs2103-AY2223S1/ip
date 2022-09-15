package duke;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import duke.tasks.Task;

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
     * Returns the Task at the given index.
     *
     * @param index Position of the task.
     * @return Task at the position.
     */
    public Task get(int index) {
        return taskList.get(index);
    }

    /**
     * Removes the Task at the given index.
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

    public Task[] toArray() {
        return this.taskList.toArray(new Task[0]);
    }

    /**
     * Return the TaskList with Tasks containing the search keyword.
     * @param searchKeyword Keyword found inside the task.
     * @return TaskList with Tasks containing the search keyword.
     */
    public List search(String searchKeyword) {
        return this.taskList
                .stream()
                .filter((task) -> task.getName().contains(searchKeyword))
                .collect(Collectors.toList());
    }
}
