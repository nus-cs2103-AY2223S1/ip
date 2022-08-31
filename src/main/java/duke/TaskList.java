package duke;

import java.util.ArrayList;

import duke.task.Task;

/**
 * Represents class regarding a list of tasks.
 *
 * @author benjytan45678
 * @version 0.1
 */
public class TaskList {
    private ArrayList<Task> tasks = null;

    /**
     * Creates a TaskList with input list of tasks.
     *
     * @param tasks A list of tasks.
     * @throws DukeException error pertaining to initialisation of tasks
     */
    public TaskList(ArrayList<Task> tasks) throws DukeException {
        try {
            this.tasks = tasks;
        } catch (Exception e) {
            throw new DukeException(e.getMessage());
        }
    }

    /**
     * Creates a TaskList with an empty ArrayList.
     */
    public TaskList() {
        if (this.tasks == null) {
            ArrayList<Task> taskList = new ArrayList<>();
            this.tasks = taskList;
        }
    }

    /**
     * Returns a list of tasks.
     *
     * @return A list of tasks.
     */
    public ArrayList<Task> getTaskList() {

        return this.tasks;
    }

    /**
     * Deletes specified task into the list.
     *
     * @param number The specified task at the particular position(number) in the list
     *
     */
    public void delete(int number) {

        tasks.remove(number - 1);
    }

    /**
     * Returns the size of the list of tasks.
     *
     * @return Total size of the list.
     */
    public int totalSize() {

        return tasks.size();
    }

    /**
     * Adds specified task into the list.
     *
     * @param task The specified task to be added.
     *
     */
    public void add(Task task) {

        this.tasks.add(task);
    }

    /**
     * Finds specified task in the list.
     *
     * @param keyword The specified task to be added.
     * @return list of tasks that fit the keyword
     *
     */
    public ArrayList<Task> find(String keyword) {
        ArrayList<Task> filteredList = new ArrayList<>();
        for (Task task: tasks) {
            if (task.toString().contains(keyword)) {
                filteredList.add(task);
            }
        }
        return filteredList;
    }

}
