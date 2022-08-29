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
     * @param storedTasks
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
     * Returns a string representation of the tasks with a specific date.
     * if params are specified, else return a string representation of all the tasks.
     * @param params The date of the tasks to list.
     * @return A string representation of the tasks in the task list.
     */
    public String listTasks(String params) {
        StringBuilder sb = new StringBuilder();
        if (tasks.size() == 0) {
            return "|  no tasks found\n";
        } else if (params.equals("")) {
            for (int pos = 0; pos < tasks.size(); pos++) {
                sb.append(String.format("%d ==> %s%n", pos + 1, tasks.get(pos)));
            }
        } else {
            LocalDate date = LocalDate.parse(params,
                    DateTimeFormatter.ofPattern("d/M/yyyy"));
            boolean hasTask = false;
            for (int pos = 0; pos < tasks.size(); pos++) {
                if (tasks.get(pos).isEqualDate(date)) {
                    hasTask = true;
                    sb.append(String.format("%d ==> %s%n", pos + 1, tasks.get(pos)));
                }
            }
            if (!hasTask) {
                return "|  no tasks found\n";
            }
        }
        return sb.toString();
    }

    /**
     * Returns a string representation of the tasks with matching keywords.
     * @param params The keyword to match with.
     * @return A string representation of the tasks with matching keywords.
     */
    public String findTasks(String params) {
        if (tasks.size() == 0) {
            return "|  no tasks found\n";
        }
        boolean hasTask = false;
        StringBuilder sb = new StringBuilder();
        for (int pos = 0; pos < tasks.size(); pos++) {
            if (tasks.get(pos).isMatchingKeyword(params)) {
                hasTask = true;
                sb.append(String.format("%d ==> %s%n", pos + 1, tasks.get(pos)));
            }
        }
        if (!hasTask) {
            return "|  no tasks found\n";
        }
        return sb.toString();
    }

    /**
     * Adds a to do task to the task list.
     * @param description The description of the to do task.
     * @return A string representation of the task added.
     */
    public String addTodo(String description) {
        Todo todo = new Todo(description);
        tasks.add(todo);
        return String.format("|  added task:%n|    %s%n", todo);
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
        return String.format("|  added task:%n|    %s%n", deadline);
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
        return String.format("|  added task:%n|    %s%n", event);
    }

    /**
     * Marks the specified task as complete.
     * @param params The position of the task to mark.
     * @return A string representation of the task being marked as complete.
     */
    public String markTask(String params) {
        int pos = Integer.parseInt(params) - 1;
        if (pos < 0 || tasks.size() <= pos) {
            return String.format("|  invalid task number%n|  max id is %d%n", tasks.size());
        }
        tasks.get(pos).markAsDone();
        return String.format("|  marked task:%n|    %s%n", tasks.get(pos));
    }

    /**
     * Marks the specified task as incomplete.
     * @param params The position of the task to unmark.
     * @return A string representation of the task being marked as incomplete.
     */
    public String unmarkTask(String params) {
        int pos = Integer.parseInt(params) - 1;
        if (pos < 0 || tasks.size() <= pos) {
            return String.format("|  invalid task number%n|  max id is %d%n", tasks.size());
        }
        tasks.get(pos).markAsUndone();
        return String.format("|  unmarked task:%n|    %s%n", tasks.get(pos));
    }

    /**
     * Deletes the specified task.
     * @param params The position of the task to deleted.
     * @return A string representation of the task being deleted.
     */
    public String deleteTask(String params) {
        int pos = Integer.parseInt(params) - 1;
        if (pos < 0 || tasks.size() <= pos) {
            return String.format("|  invalid task number%n|  max id is %d%n", tasks.size());
        }
        String removedTask = tasks.get(pos).toString();
        tasks.remove(pos);
        return String.format("|  deleted task:%n|    %s%n", removedTask);
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

}
