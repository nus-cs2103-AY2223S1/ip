package duke;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import java.util.ArrayList;
import java.util.List;

/**
 * A class that represents the task list and handles add/delete/update operations.
 */
public class TaskList {
    private static List<Task> tasks = new ArrayList<>();

    /**
     * Gets the total number of tasks in the task list.
     *
     * @return The total number of tasks in the task list.
     */
    public static int getTaskCount() {
        return tasks.size();
    }

    /**
     * Generates a list of string representations of tasks meant for display.
     * Intended to be used by Ui for displaying all tasks.
     *
     * @return A list of strings that represent task information to be displayed.
     */
    public static List<String> getTasksStrings() {
        ArrayList<String> tasksStrings = new ArrayList<>();

        for (int i = 0; i < tasks.size(); i++) {
            tasksStrings.add(tasks.get(i).toString());
        }

        return tasksStrings;
    }

    /**
     * Adds the given task to the list.
     *
     * @param task The task to add to the list.
     * @return The task that was added.
     */
    public static Task addToList(Task task) {
        tasks.add(task);

        return task;
    }

    /**
     * Creates a ToDo task from the given argument string.
     *
     * @param args The argument string to be parsed.
     * @return The task that was added.
     */
    public static Task addToDo(String args) throws DukeException {
        String name = args.strip();
        if (name == "") {
            throw new DukeException("Failed to create todo: No task name given");
        }

        return addToList(new ToDo(name, false));
    }

    /**
     * Creates a Deadline task from the given argument string.
     *
     * @param args The argument string to be parsed.
     * @return The task that was added.
     */
    public static Task addDeadline(String args) throws DukeException {
        String[] argsArr = args.split(" /by ", 2);
        if (argsArr.length < 2) {
            throw new DukeException("Failed to create deadline: Invalid number of arguments");
        }

        String name = argsArr[0].strip();
        String dateStr = argsArr[1].strip();

        if (name == "") {
            throw new DukeException("Failed to create deadline: No task name given");
        }

        if (dateStr == "") {
            throw new DukeException("Failed to create deadline: No date given");
        }

        LocalDate date;
        try {
            date = LocalDate.parse(dateStr);
        } catch (DateTimeParseException e) {
            throw new DukeException("Failed to create deadline: Invalid date given");
        }

        return addToList(new Deadline(name, false, date));
    }

    /**
     * Creates an Event task from the given argument string.
     *
     * @param args The argument string to be parsed.
     * @return The task that was added.
     */
    public static Task addEvent(String args) throws DukeException {
        String[] argsArr = args.split(" /at ", 2);
        if (argsArr.length < 2) {
            throw new DukeException("Failed to create event: Invalid number of arguments");
        }

        String name = argsArr[0].strip();
        String dateStr = argsArr[1].strip();

        if (name == "") {
            throw new DukeException("Failed to create event: No task name given");
        }

        if (dateStr == "") {
            throw new DukeException("Failed to create event: No date given");
        }

        LocalDate date;
        try {
            date = LocalDate.parse(dateStr);
        } catch (DateTimeParseException e) {
            throw new DukeException("Failed to create event: Invalid date given");
        }

        return addToList(new Event(name, false, date));
    }

    /**
     * Marks/unmarks a task based on the given task index string.
     *
     * @param isDone Whether to mark/unmark the task.
     * @param indexString The index of the task to mark/unmark.
     * @return The task that was marked/unmarked.
     * @throws DukeException If indexString is not an integer or out of range.
     */
    public static Task markTask(boolean isDone, String indexString) throws DukeException {
        int index;
        try {
            index = Integer.parseInt(indexString.strip());
        } catch (NumberFormatException e) {
            throw new DukeException("Mark failed, invalid index");
        }
        index--;

        if (index < 0 || index >= tasks.size()) {
            throw new DukeException("Mark failed, index out of range");
        }

        Task task = tasks.get(index);
        if (isDone) {
            task.mark();
        } else {
            task.unmark();
        }

        return task;
    }

    /**
     * Deletes a task based on the given task index string.
     *
     * @param indexString The index of the task to delete.
     * @return The task that was deleted.
     * @throws DukeException If indexString is not an integer or out of range.
     */
    public static Task deleteTask(String indexString) throws DukeException {
        int index;
        try {
            index = Integer.parseInt(indexString.strip());
        } catch (NumberFormatException e) {
            throw new DukeException("Delete failed, invalid index");
        }
        index--;

        if (index < 0 || index >= tasks.size()) {
            throw new DukeException("Delete failed, index out of range");
        }

        Task task = tasks.get(index);
        tasks.remove(index);

        return task;
    }

    /**
     * Searches for tasks based on the given search string, and returns a list of tasks found.
     * If the taskName of a task contains the search string, it is added to the search results.
     *
     * @param searchString The string to be used to search for tasks.
     * @return The list of tasks found in the search.
     */
    public static List<Task> searchTasks(String searchString) {
        searchString = searchString.strip();
        ArrayList<Task> foundTasks = new ArrayList<>();

        for (Task task: tasks) {
            if (task.getTaskName().contains(searchString)) {
                foundTasks.add(task);
            }
        }

        return foundTasks;
    }

    /**
     * Converts all tasks to their save string format and returns it as a list.
     * Intended to be used by Storage to save tasks.
     *
     * @return A list of strings which can be used to reconstruct tasks.
     */
    public static List<String> getTasksSaveStrings() {
        ArrayList<String> saveStrings = new ArrayList<>();

        for (Task task: tasks) {
            saveStrings.add(task.toSaveFormatString());
        }

        return saveStrings;
    }

    /**
     * Removes all tasks from the task list.
     */
    public static void clearTaskList() {
        tasks.clear();
    }
}
