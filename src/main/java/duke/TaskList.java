package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * Represents the list of tasks that have been created in the Duke application.
 */
public class TaskList {

    private ArrayList<Task> taskList;

    /**
     * Initialises an empty task list.
     */
    public TaskList() {
        taskList = new ArrayList<Task>();
    }

    /**
     * Initialises a task list from an existing list of tasks.
     * @param taskStringList List with each element containing details of a task.
     */
    public TaskList(ArrayList<String> taskStringList) {
        taskList = new ArrayList<Task>();
        for (int i = 0; i < taskStringList.size(); i++) {
            String taskString = taskStringList.get(i);
            Task task = parseTaskString(taskString);
            taskList.add(task);
        }
    }

    /**
     * Adds a task to this task list.
     * @param task Task to be added.
     */
    public void addTask(Task task) {
        taskList.add(task);
    }

    /**
     * Deletes a task from this task list.
     * @param index Task number of task to be deleted.
     * @return Deleted task.
     * @throws DukeException If task number <= 0 or exceeds number of tasks.
     */
    public Task deleteTask(int i) throws DukeException {
        if (i < 1 || i > taskList.size()) {
            throw new DukeException("Invalid task number.");
        }
        Task removedTask = taskList.remove(i - 1);
        return removedTask;
    }

    /**
     * Gets the number of tasks in this task list.
     * @return Number of tasks in this task list.
     */
    public int getNumTasks() {
        return taskList.size();
    }

    /**
     * Gets the task at the provided number in this task list.
     * @param i task number of task to be retrieved.
     * @return Task with that task number i.
     * @throws DukeException If task number <= 0 or exceeds number of tasks.
     */
    public Task getTask(int i) throws DukeException {
        if (i < 1 || i > taskList.size()) {
            throw new DukeException("Invalid task number.");
        }
        return taskList.get(i - 1);
    }

    /**
     * Gets String representation of this task list.
     * @return string representation of this task list.
     */
    @Override
    public String toString() {
        String tasks = "";
        for (int i = 0; i < taskList.size(); i++) {
            tasks += "\n" + (i + 1) + ". " + taskList.get(i).toString();
        }
        return tasks;
    }

    /**
     * Creates a task object from its string description.
     * @param taskString Task description in string.
     * @return Task that is described by taskString.
     */
    private Task parseTaskString(String taskString) {
        String withoutNumber = taskString.substring(taskString.indexOf(".") + 2);
        String typeOfTask = withoutNumber.substring(1, 2);
        String marked = withoutNumber.substring(4, 5);
        String description = withoutNumber.substring(7);
        if (typeOfTask.equals("T")) {
            Task task = new Todo(description);
            if (marked.equals("X")) {
                task.markAsDone();
            }
            return task;
        } else if (typeOfTask.equals("D")) {
            String[] descriptionAndDate = description.split(" \\(by: ");
            String descriptionOnly = descriptionAndDate[0];
            String dateOnly = descriptionAndDate[1].substring(0, descriptionAndDate[1].length() - 1);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
            LocalDateTime dateTime = LocalDateTime.parse(dateOnly, formatter);

            Task task = new Deadline(descriptionOnly, dateTime);
            if (marked.equals("X")) {
                task.markAsDone();
            }
            return task;
        } else {
            String[] descriptionAndDate = description.split(" \\(at: ");
            String descriptionOnly = descriptionAndDate[0];
            String dateOnly = descriptionAndDate[1].substring(0, descriptionAndDate[1].length() - 1);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
            LocalDateTime dateTime = LocalDateTime.parse(dateOnly, formatter);

            Task task = new Event(descriptionOnly, dateTime);
            if (marked.equals("X")) {
                task.markAsDone();
            }
            return task;
        }
    }

}
