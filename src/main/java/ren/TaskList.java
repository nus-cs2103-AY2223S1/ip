package ren;

import java.util.ArrayList;

import ren.task.Deadline;
import ren.task.Event;
import ren.task.Task;
import ren.task.Todo;

/**
 * TaskList contains a list of Tasks as well methods to add, delete, update tasks.
 */
public class TaskList {
    /** ArrayList to store all Tasks. */
    private final ArrayList<Task> tasks;

    /** Storage to synchronize all changes with. */
    private final Storage storage;

    /**
     * Constructor for a TaskList.
     *
     * @param storage Storage to read and write all Tasks to.
     */
    public TaskList(Storage storage) {
        this.tasks = storage.load();
        this.storage = storage;
    }

    /**
     * Adds a Task to the TaskList.
     *
     * @param type The type of the new Task.
     * @param task The information of the new Task.
     * @param dateTime The date and time information of the new Task.
     * @return String containing a message for the user.
     * @throws RenException If task or dateTime is invalid.
     */
    public String addTask(Ren.TaskType type, String task, String dateTime) throws RenException {
        Task newTask = null;
        switch (type) {
        case TODO:
            newTask = new Todo(task);
            break;
        case DEADLINE:
            newTask = new Deadline(task, dateTime);
            break;
        case EVENT:
            newTask = new Event(task, dateTime);
            break;
        default:
        }
        tasks.add(newTask);
        storage.addTask(newTask);
        return " Understood. I have added the following task:\n"
            + "   " + newTask
            + " You now have a total of " + tasks.size() + " task(s).\n";
    }

    /**
     * Removes a Task from the TaskList.
     *
     * @param taskNum The index of the Task to remove.
     * @return String containing a message for the user.
     * @throws RenException If taskNum is invalid.
     */
    public String deleteTask(int taskNum) throws RenException {
        if (taskNum <= tasks.size() && taskNum > 0) {
            Task removedTask = tasks.remove(taskNum - 1);
            storage.deleteTask(taskNum - 1);
            return " Understood. I have removed the following task:\n"
                + "   " + removedTask
                + " You have a total of " + tasks.size() + " task(s) left.\n";
        } else if (tasks.size() == 0) {
            throw new RenException("You have no tasks to delete.");
        } else {
            throw new RenException("Please indicate a task no. between 1 to " + tasks.size() + ".");
        }
    }

    /**
     * Updates a Task in the TaskList.
     *
     * @param status The new status of the Task.
     * @param taskNum The index of the Task to update.
     * @return String containing a message for the user.
     * @throws RenException If taskNum is invalid.
     */
    public String updateTask(boolean status, int taskNum) throws RenException {
        if (taskNum <= tasks.size() && taskNum > 0) {
            String message = tasks.get(taskNum - 1).setDone(status);
            storage.updateTask(tasks.get(taskNum - 1), taskNum - 1);
            return message;
        } else if (tasks.size() == 0) {
            throw new RenException("You have no tasks to mark or unmark.");
        } else {
            throw new RenException("Please indicate a task no. between 1 to " + tasks.size() + ".");
        }
    }

    /**
     * Lists all Tasks in the TaskList.
     *
     * @return String containing the list.
     */
    public String listTasks() {
        if (tasks.size() == 0) {
            return " You have not added any tasks!\n";
        } else {
            StringBuilder result = new StringBuilder(" Here are your current tasks:\n");
            for (int i = 0; i < tasks.size(); i++) {
                result.append(" ").append(i + 1).append(".").append(tasks.get(i).toString());
            }
            return result.toString();
        }
    }

    /**
     * Searches TaskList for Tasks matching a search term.
     *
     * @param term The Search Term to match Tasks with.
     * @return String containing the list of matching Tasks.
     */
    public String findTasks(String term) {
        if (tasks.size() == 0) {
            return " Apologies! I have not found any matching tasks.\n";
        } else {
            int index = 1;
            StringBuilder result = new StringBuilder(" I have found these matching tasks:\n");
            for (Task taskToCheck : tasks) {
                if (taskToCheck.isMatch(term)) {
                    result.append(" ").append(index).append(".").append(taskToCheck);
                    index++;
                }
            }
            return index != 1 ? result.toString() : " Apologies! I have not found any matching tasks.\n";
        }
    }

    /**
     * Removes all Tasks from the TaskList.
     *
     * @return String containing a message for the user.
     */
    public String emptyList() {
        tasks.clear();
        storage.emptyList();
        return " Understood. I have emptied your list of tasks.";
    }
}
