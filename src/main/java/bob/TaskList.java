package bob;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Represents TaskList object, a list to store all tasks inputted by user
 */
public class TaskList {

    private ArrayList<Task> tasks;
    private int length;

    /**
     * Constructor for TaskList object, an empty list of size 100
     */
    public TaskList() {
        this.tasks = new ArrayList<>(100);
        this.length = 0;
    }

    /**
     * Adds a task to the list
     *
     * @param task the task to be added
     */
    public void addTask(Task task) {
        this.tasks.add(task);
        this.length = this.length + 1;
    }

    /**
     * Removes a task from the list
     *
     * @param index the index of task to be removed
     */
    public void removeTask(int index) {
        ArrayList<Task> temp = new ArrayList<>(100);
        for (int i = 0; i < this.length; i++) {
            if (i == (index - 1)) {
                continue;
            } else {
                temp.add(tasks.get(i));
            }
        }
        this.tasks = temp;
        this.length = this.length - 1;
    }

    /**
     * Marks or Unmarks a task from the list
     *
     * @param index the index of task to be marked/unmarked
     * @param marker boolean value to indicate if task should be marked or unmarked
     */
    public void markTask(int index, boolean marker) {
        this.tasks.get(index - 1).mark(marker);
    }


    /**
     * Filters out tasks of a specific date from list
     *
     * @param dateString string format of the date to be filtered
     * @return a filtered TaskList
     */
    public TaskList filterTask(String dateString) {
        LocalDate date = LocalDate.parse(dateString);
        TaskList filteredTasks = new TaskList();
        for (Task task: tasks) {
            if (task instanceof Event && ((Event) task).at.equals(date)) {
                filteredTasks.addTask(task);
            } else if (task instanceof Deadline && ((Deadline) task).by.equals(date)) {
                filteredTasks.addTask(task);
            }
        }
        return filteredTasks;
    }

    /**
     * Finds all tasks in list containing a specific word
     *
     * @param searchWord word to search for in task list
     * @return a filtered TaskList
     */
    public TaskList findTask(String searchWord) {
        TaskList foundTasks = new TaskList();
        for (Task task: tasks) {
            if (task.description.contains(searchWord)) {
                foundTasks.addTask(task);
            }
        }
        return foundTasks;
    }

    /**
     * Retrieves a task from list
     *
     * @param index the index of task to be retrieved
     * @return retrieved task
     */
    public Task getTask(int index) {
        return this.tasks.get(index - 1);
    }

    /**
     * Retrieves the number of tasks in list
     *
     * @return number of tasks in the task list
     */
    public int getLength() {
        assert this.length >= 0 : "length of list should never be negative";
        return this.length;
    }
}
