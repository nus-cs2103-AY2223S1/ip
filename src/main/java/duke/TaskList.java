package duke;

import duke.task.Task;

import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * Stores and manages the tasks
 */
public class TaskList {

    private final List<Task> tasks;
    private final Storage storage;
    private boolean isClosed;

    /**
     * A constructor for the TaskList class
     *
     * @param tl The list of tasks
     * @param s The storage that manages a file
     */
    public TaskList(List<Task> tl, Storage s) {
        this.tasks = tl;
        this.storage = s;
        this.isClosed = false;
    }

    /**
     * Adds a <code>Task</code> to the list of tasks
     *
     * @param t Task to add
     */
    public void addTask(Task t) {
        this.tasks.add(t);
    }

    /**
     * Obtains the number of tasks in the <code>TaskList</code>
     *
     * @return The total number of tasks
     */
    public int getSize() {
        return this.tasks.size();
    }

    /**
     * Obtains the list of tasks that is stored in the <code>TaskList</code>
     *
     * @return The list of tasks
     */
    public List<Task> getTasks() {
        return this.tasks;
    }

    /**
     * Obtain the status of the <code>TaskList</code>
     *
     * @return True if the <code>TaskList</code> is closed; otherwise false
     */
    public boolean getTaskListStatus() {
        return this.isClosed;
    }

    /**
     * Marks a specific task in the list of tasks as done
     *
     * @param index The specific task number
     * @return Task that is marked as done
     * @throws IndexOutOfBoundsException If the task index is invalid
     */
    public Task markTask(int index) throws IndexOutOfBoundsException {
        Task taskChosen = this.tasks.get(index);
        taskChosen.markAsDone();
        return taskChosen;
    }

    /**
     * Marks a specific task in the list of tasks as not done
     *
     * @param index The specific task number
     * @return Task that is marked as not done
     * @throws IndexOutOfBoundsException If the task index is invalid
     */
    public Task unmarkTask(int index) throws IndexOutOfBoundsException {
        Task taskChosen = this.tasks.get(index);
        taskChosen.markAsUndone();
        return taskChosen;
    }

    /**
     * Deletes a specific task in the list of tasks
     *
     * @param index The specific task number
     * @return The task that is deleted
     * @throws IndexOutOfBoundsException If the task index is invalid
     */
    public Task deleteTask(int index) throws IndexOutOfBoundsException {
        Task taskToRemove = this.tasks.get(index);
        this.tasks.remove(index);
        return taskToRemove;
    }

    /**
     * Finds all tasks that has descriptions containing the keyword
     *
     * @param keyword Keyword to match
     * @return List of all tasks that has descriptions containing the keyword
     * @throws DukeException If the <code>TaskList</code> is empty
     */
    public List<Task> findTasks(String keyword) throws DukeException {
        if (this.tasks.size() == 0) { // List is empty
            throw new DukeException("Your list is empty! Why not add a task to it first?");
        }
        List<Task> matchingTasks = new ArrayList<>();
        for (int i = 0; i < this.tasks.size(); i++) {
            Task curTask = this.tasks.get(i);
            String taskDescription = curTask.getDescription().toUpperCase();
            if (taskDescription.contains(keyword)) {
                matchingTasks.add(curTask);
            } else {
                matchingTasks.add(null);
            }
        }

        return matchingTasks;
    }

    /**
     * Snooze a task that has a DateTime
     *
     * @param index The index of the task
     * @param newDateTime The new DateTime to set
     * @return Message to display
     * @throws DateTimeParseException If the DateTime cannot be parsed correctly
     * @throws DukeException If the DateTime cannot be set correctly
     */
    public String snoozeTask(int index, String newDateTime) throws DateTimeParseException, DukeException {
        String response;
        Task chosenTask = this.tasks.get(index);
        try {
            response = chosenTask.setDatetime(newDateTime);
        } catch (DateTimeParseException err) {
            response = "I don't recognise this time format."
                    + "\nThe format for your new DateTime should be as follows: "
                    + "dd/MM/yyyy HHmm";
        }
        storage.refreshList(this.tasks);
        return response;
    }

    /**
     * Marks the status of the <code>TaskList</code> as closed
     */
    public void closeTaskList() {
        this.isClosed = true;
    }

}
