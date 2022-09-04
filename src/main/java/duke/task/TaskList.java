package duke.task;

import java.util.ArrayList;

import duke.exceptions.InvalidTaskException;
import duke.ui.Ui;

/**
 * TaskList class that stores all tasks specified by user/file.
 */
public class TaskList {
    private final ArrayList<Task> tasks = new ArrayList<>();

    /**
     * Initialises ArrayList of Tasks from ArrayList of strings
     *
     * @param stringList List of strings of tasks.
     */
    public TaskList(ArrayList<String> stringList) {
        for (String line : stringList) {
            parseLine(line);
        }
    }

    public ArrayList<Task> get() {
        return tasks;
    }

    private void parseLine(String line) {
        String[] parts = line.split(" \\| ");
        Task task;
        switch (parts[0]) {
        case "T":
            task = new Todo(parts[2]);
            break;
        case "D":
            task = new Deadline(parts[2], parts[3]);
            break;
        case "E":
            task = new Event(parts[2], parts[3]);
            break;
        default:
            task = null;
            break;
        }

        switch (parts[1]) {
        case "0":
            task.markAsUndone();
            break;
        case "1":
            task.markAsDone();
            break;
        default:
            break;
        }
        tasks.add(task);
    }

    /**
     * Displays tasks separated by newlines.
     *
     * @param ui UI object to display tasks
     */
    public String listTasks(Ui ui) {
        return ui.showList(tasks);
    }

    /**
     * Marks specified task as done and displays result to user.
     *
     * @param taskNumber Index of task to be marked as done
     * @param ui         UI object to display result
     */
    public String markAsDone(int taskNumber, Ui ui) {
        try {
            tasks.get(taskNumber).markAsDone();
            return "Nice! I've marked this task as done:\n  " + tasks.get(taskNumber);
        } catch (Exception e) {
            throw new InvalidTaskException("Please give a valid task number");
        }
    }

    /**
     * Marks specified task as undone and displays result to user.
     *
     * @param taskNumber Index of task to be marked as undone
     * @param ui         UI object to display result
     */
    public String markAsUndone(int taskNumber, Ui ui) {
        try {
            tasks.get(taskNumber).markAsUndone();
            return "OK, I've marked this task as not done yet:\n  " + tasks.get(taskNumber);
        } catch (Exception e) {
            throw new InvalidTaskException("Please give a valid task number");
        }
    }

    private String printOnAdd(Task task, Ui ui) {
        return "Got it. I've added this task:\n  "
                + task
                + "\nNow you have "
                + tasks.size()
                + " tasks in the list.";
    }

    /**
     * Adds task to TaskList and displays success to user.
     *
     * @param task Task to be added to list
     * @param ui   UI object to display result
     */
    public String addTask(Task task, Ui ui) {
        tasks.add(task);
        return printOnAdd(task, ui);
    }

    private String printOnDelete(int taskNum, Ui ui) {
        return "Noted. I've deleted this task:\n  "
                + tasks.get(taskNum)
                + "\nNow you have "
                + (tasks.size() - 1)
                + " tasks in the list.";
    }

    /**
     * Deletes specified task and displays result to user.
     *
     * @param taskNumber Index of task to be deleted
     * @param ui         UI object to display result
     */
    public String deleteTask(int taskNumber, Ui ui) {
        if (taskNumber >= 0 && taskNumber < tasks.size()) {
            String toReturn = printOnDelete(taskNumber, ui);
            tasks.remove(taskNumber);
            return toReturn;
        } else {
            throw new InvalidTaskException("Please give a valid task number");
        }
    }

    /**
     * Returns tasks that match specified search query.
     *
     * @param toFind String specifying search query.
     * @return List of tasks matching search query.
     */
    public ArrayList<Task> find(String toFind) {
        ArrayList<Task> foundTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getDescription().contains(toFind)) {
                foundTasks.add(task);
            }
        }

        return foundTasks;
    }
}
