package duke;

import java.util.ArrayList;

/**
 * A class containing the list of tasks
 * added by the user.
 *
 * @author Elbert Benedict
 */
public class TaskList {
    private ArrayList<Task> taskList;

    /**
     * Contructs a new instance of Tasklist.
     * The taskList is initially empty.
     */
    public TaskList() {
        taskList = new ArrayList<Task>();
    }

    /**
     * Returns the number of tasks.
     *
     * @return the number of tasks.
     */
    public int getSize() {
        return taskList.size();
    }

    /**
     * Adds a new task to the list of tasks.
     *
     * @param task the task to be added.
     */
    public void add(Task task) {
        taskList.add(task);
    }

    /**
     * Deletes a task from the list of tasks and
     * Returns the deleted task message.
     *
     * @param index the index of the tasks to be deleted.
     * @return the deleted task message.
     * @throws DukeException If index is out of bounds.
     */
    public String delete(int index) throws DukeException {
        //Index out of bounds
        if (index > taskList.size() || index < 1) {
            throw new DukeException("Index Is Not Valid");
        }
        //get the selected task
        Task task = taskList.get(index - 1);
        //remove the task
        taskList.remove(task);
        //print the response to the user
        Storage.saveTasks(this);
        return Ui.getDeletedTaskMessage(task, getSize());
    }

    /**
     * Marks a task a done and returns the marked
     * task message.
     *
     * @param index the index of the task to be marked.
     * @return the marked task message.
     * @throws DukeException If index is out of bounds.
     */
    public String mark(int index) throws DukeException {
        //Index out of bounds
        if (index > taskList.size() || index < 1) {
            throw new DukeException("Index Is Not Valid");
        }
        //get the selected task
        Task task = taskList.get(index - 1);
        task.markAsDone();
        Storage.saveTasks(this);
        return Ui.getMarkedTaskMessage(task);
    }

    /**
     * Unmarks a task from being done and returns
     * the unmarked task message.
     *
     * @param index the index of the task to be unmarked.
     * @return the unmarked task message.
     * @throws DukeException If index is out of bounds.
     */
    public String unmark(int index) throws DukeException {
        //Index out of bounds
        if (index > taskList.size() || index < 1) {
            throw new DukeException("Index Is Not Valid");
        }
        //get the selected task
        Task task = taskList.get(index - 1);
        task.markAsUndone();
        Storage.saveTasks(this);
        return Ui.getUnmarkedTaskMessage(task);
    }

    /**
     * Prints the string representation of
     * the list of tasks.
     *
     * @return the string representation of
     *     the instance.
     */
    @Override
    public String toString() {
        String stringRepresentation = "";
        for (int i = 0; i < taskList.size(); ++i) {
            Task task = taskList.get(i);
            stringRepresentation += (i + 1) + ". " + task.toString() + "\n";
        }

        return stringRepresentation;
    }

    /**
     * Returns the save file string representation of the
     * list of tasks.
     *
     * @return the save file string representation of the
     *     list of tasks.
     */
    public String toSaveFileString() {
        String tasks = "";

        for (int i = 0; i < taskList.size(); ++i) {
            tasks += taskList.get(i).toSaveFileString() + "\n";
        }

        return tasks;
    }

    /**
     * Returns a Tasklist matching a given query string.
     *
     * @param query the query string.
     * @return the filtered TaskList.
     */
    public TaskList filter(String query) {
        TaskList filteredTasks = new TaskList();

        for (int i = 0; i < taskList.size(); ++i) {
            //pass the string which does not fulfill query
            Task task = taskList.get(i);
            if (!task.isStringContained(query)) {
                continue;
            }
            filteredTasks.add(task);
        }

        return filteredTasks;
    }
}
