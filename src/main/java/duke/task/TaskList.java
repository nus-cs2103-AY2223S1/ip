package duke.task;

import duke.exceptions.InvalidTaskException;
import duke.ui.Ui;

import java.util.ArrayList;

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
        }
        tasks.add(task);
    }

    /**
     * Displays tasks separated by newlines.
     *
     * @param ui UI object to display tasks
     */
    public void listTasks(Ui ui) {
        ui.showList(tasks);
    }

    /**
     * Marks specified task as done and displays result to user.
     *
     * @param taskNumber Index of task to be marked as done
     * @param ui         UI object to display result
     */
    public void markAsDone(int taskNumber, Ui ui) {
        try {

            tasks.get(taskNumber).markAsDone();
            ui.showMessage("Nice! I've marked this task as done:\n  " + tasks.get(taskNumber));
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
    public void markAsUndone(int taskNumber, Ui ui) {
        try {
            tasks.get(taskNumber).markAsUndone();
            ui.showMessage("OK, I've marked this task as not done yet:\n  " + tasks.get(taskNumber));
        } catch (Exception e) {
            throw new InvalidTaskException("Please give a valid task number");
        }
    }

    private void printOnAdd(Task task, Ui ui) {
        ui.showMessage("Got it. I've added this task:\n  " + task + "\nNow you have " + tasks.size() + " tasks in the list.");
    }

    /**
     * Adds task to TaskList and displays success to user.
     *
     * @param task Task to be added to list
     * @param ui   UI object to display result
     */
    public void addTask(Task task, Ui ui) {
        tasks.add(task);
        printOnAdd(task, ui);
    }

    private void printOnDelete(int taskNum, Ui ui) {
        ui.showMessage("Noted. I've deleted this task:\n  " + tasks.get(taskNum) + "\nNow you have " + (tasks.size() - 1) + " tasks in the list.");
    }

    /**
     * Deletes specified task and displays result to user.
     *
     * @param taskNumber Index of task to be deleted
     * @param ui         UI object to display result
     */
    public void deleteTask(int taskNumber, Ui ui) {
        if (taskNumber >= 0 && taskNumber < tasks.size()) {
            printOnDelete(taskNumber, ui);
            tasks.remove(taskNumber);
        } else {
            throw new InvalidTaskException("Please give a valid task number");
        }
    }
}
