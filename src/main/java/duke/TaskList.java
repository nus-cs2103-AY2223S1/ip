package duke;

import java.util.ArrayList;

import duke.task.Task;

/**
 * Represents a list of tasks in the application.
 */
public class TaskList {

    private final ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public int size() {
        return tasks.size();
    }

    public Task get(int i) {
        return tasks.get(i);
    }

    public void delete(int index) {
        tasks.remove(index);
    }

    public void add(Task task) {
        tasks.add(task);
    }

    /**
     * Returns a list of all tasks that contains the keyword
     *
     * @param keyword word that user wants to find
     * @return arraylist of tasks that contains keyword.
     */
    public TaskList findKeyWord(String keyword) {
        TaskList findList = new TaskList();
        for (Task task : tasks) {
            if (task.contains(keyword)) {
                findList.add(task);
            }
        }
        return findList;
    }
}
