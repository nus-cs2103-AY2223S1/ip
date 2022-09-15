package duke.task;

import java.util.List;

/**
 * Class which deals with the task list.
 *
 * @author Shawn Chew
 * @version CS2103T AY 22/23 Sem 1
 */
public class TaskList {
    /**
     * The list of tasks.
     */
    private List<Task> tasks;

    /**
     * A constructor with no arguments for TaskList.
     */
    public TaskList() {
    }

    /**
     * A constructor for Tasklist when provided a list of tasks.
     *
     * @param Tasks The list of tasks.
     */
    public TaskList(List<Task> Tasks) {
        this.tasks = Tasks;
    }

    /**
     * Get the task at a specific index of the TaskList.
     *
     * @param i The index of the Task to be retrieved.
     * @return The Task at index i of the TaskList.
     */
    public Task get(int i) {
        return tasks.get(i);
    }

    /**
     * Add a task to the TaskList.
     *
     * @param t The task to be added.
     */
    public void add(Task t) {
        tasks.add(t);
    }

    /**
     * Remove a task from the TaskList specified by the index.
     *
     * @param i Index of the task to be removed.
     * @return The task removed.
     */
    public Task remove(int i) {
        return tasks.remove(i);
    }

    /**
     * Get the size of the TaskList.
     *
     * @return Size of the TaskList.
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Get the string of the tasks in the TaskList.
     * String format is the format which is used to be saved in the file.
     *
     * @return String of tasks in the Tasklist.
     */
    public String getString() {
        String newText = "";
        for (int i = 0; i < tasks.size(); i++) {
            String s = tasks.get(i).getStringToSave();
            if (i == 0) {
                newText += s;
            } else {
                newText += '\n' + s;
            }
        }
        return newText;
    }
}
