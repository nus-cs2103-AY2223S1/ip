package john.data;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import john.task.Deadline;
import john.task.Event;
import john.task.Task;
import john.task.Todo;

/**
 * Represents the entire list of tasks.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Constructor for an empty task list.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructor for a task list initialised with tasks from storage.
     * @param storedTasks The initial tasks in storage
     */
    public TaskList(ArrayList<String> storedTasks) {
        this.tasks = new ArrayList<>();
        for (String task : storedTasks) {
            String[] taskParams = task.split(" \\| ");
            switch (taskParams[0]) {
            case "T":
                tasks.add(new Todo(taskParams[2]));
                break;
            case "D":
                tasks.add(new Deadline(taskParams[2], taskParams[3]));
                break;
            case "E":
                tasks.add(new Event(taskParams[2], taskParams[3]));
                break;
            default:
                break;
            }
            if (taskParams[1].equals("1")) {
                tasks.get(tasks.size() - 1).markAsDone();
            }
        }
    }

    /**
     * Returns an array containing the string representation of the tasks with a specific date.
     * if params are specified, else return an array containing the string representation of all the tasks.
     * @param params The date of the tasks to list.
     * @return An array containing the string representation of the tasks in the task list.
     */
    public String[] listTasks(String params) {
        String[] tasksToShow = new String[tasks.size()];
        if (tasks.size() == 0) {
            return null;
        } else if (params.equals("")) {
            for (int i = 0; i < tasks.size(); i++) {
                tasksToShow[i] = tasks.get(i).toString();
            }
        } else {
            LocalDate date = LocalDate.parse(params,
                    DateTimeFormatter.ofPattern("d/M/yyyy"));
            boolean hasTask = false;
            for (int i = 0; i < tasks.size(); i++) {
                if (tasks.get(i).isEqualDate(date)) {
                    hasTask = true;
                    tasksToShow[i] = tasks.get(i).toString();
                }
            }
            if (!hasTask) {
                return null;
            }
        }
        return tasksToShow;
    }

    /**
     * Returns an array containing the string representation of the tasks with matching keywords.
     * @param params The keyword to match with.
     * @return An array containing the string representation of the tasks with matching keywords.
     */
    public String[] findTasks(String params) {
        if (tasks.size() == 0) {
            return null;
        }
        boolean hasTask = false;
        String[] tasksToShow = new String[tasks.size()];
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).isMatchingKeyword(params)) {
                hasTask = true;
                tasksToShow[i] = tasks.get(i).toString();
            }
        }
        if (!hasTask) {
            return null;
        }
        return tasksToShow;
    }

    /**
     * Adds a to do task to the task list.
     * @param description The description of the to do task.
     * @return A string representation of the task added.
     */
    public String addTodo(String description) {
        Todo todo = new Todo(description);
        tasks.add(todo);
        return todo.toString();
    }

    /**
     * Adds a deadline task to the task list.
     * @param description The description of the deadline task.
     * @param timing The timing the task is due by.
     * @return A string representation of the deadline added.
     */
    public String addDeadline(String description, String timing) {
        Deadline deadline = new Deadline(description, timing);
        tasks.add(deadline);
        return deadline.toString();
    }

    /**
     * Adds a event task to the task list.
     * @param description The description of the event task.
     * @param timing The timing the task is due by.
     * @return A string representation of the event added.
     */
    public String addEvent(String description, String timing) {
        Event event = new Event(description, timing);
        tasks.add(event);
        return event.toString();
    }

    /**
     * Marks the specified task as complete.
     * @param params The position of the task to mark.
     * @return A string representation of the task being marked as complete.
     */
    public String markTask(String params) {
        int pos = Integer.parseInt(params) - 1;
        if (pos < 0 || tasks.size() <= pos) {
            return null;
        }
        tasks.get(pos).markAsDone();
        return tasks.get(pos).toString();
    }

    /**
     * Marks the specified task as incomplete.
     * @param params The position of the task to unmark.
     * @return A string representation of the task being marked as incomplete.
     */
    public String unmarkTask(String params) {
        int pos = Integer.parseInt(params) - 1;
        if (pos < 0 || tasks.size() <= pos) {
            return null;
        }
        tasks.get(pos).markAsUndone();
        return tasks.get(pos).toString();
    }

    /**
     * Deletes the specified task.
     * @param params The position of the task to delete.
     * @return A string representation of the task being deleted.
     */
    public String deleteTask(String params) {
        int pos = Integer.parseInt(params) - 1;
        if (pos < 0 || tasks.size() <= pos) {
            return null;
        }
        String removedTask = tasks.get(pos).toString();
        tasks.remove(pos);
        return removedTask;
    }

    /**
     * Returns an ArrayList containing the tasks for storage.
     * @return ArrayList of tasks in the storage form.
     */
    public ArrayList<String> getTasksToStore() {
        ArrayList<String> storage = new ArrayList<>();
        for (Task task : this.tasks) {
            storage.add(task.toStorageFormat());
        }
        return storage;
    }

    /**
     * Returns the number of tasks in the list.
     * @return An integer representing the number of tasks in the list.
     */
    public int getNumberOfTasks() {
        return tasks.size();
    }

}
