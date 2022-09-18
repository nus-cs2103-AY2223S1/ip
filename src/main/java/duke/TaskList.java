package duke;

import duke.exception.DukeException;
import duke.exception.DukeRuntimeException;
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

    private ArrayList<Task> tasks;

    /**
     * Initialises an empty task list.
     */
    public TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Initialises a task list from an existing list of tasks.
     * @param taskStringList List with each element containing details of a task.
     */
    public TaskList(ArrayList<String> taskStringList) {
        tasks = new ArrayList<>();
        for (String taskString : taskStringList) {
            Task task = parseTaskString(taskString);
            tasks.add(task);
        }
    }

    /**
     * Adds a task to this task list.
     * @param task Task to be added.
     */
    public void addTask(Task task) {
        assert task != null : "Task to be added to tasks is null.";
        tasks.add(task);
    }

    /**
     * Deletes a task from this task list.
     * @param i Task number of task to be deleted.
     * @return Deleted task.
     * @throws DukeException If task number <= 0 or exceeds number of tasks.
     */
    public Task deleteTask(int i) throws DukeException {
        boolean isTaskNumberWithinLimit = i < 1 || i > tasks.size();
        if (isTaskNumberWithinLimit) {
            throw new DukeException("Invalid task number.");
        }
        return tasks.remove(i - 1);
    }

    /**
     * Gets the number of tasks in this task list.
     * @return Number of tasks in this task list.
     */
    public int getNumTasks() {
        return tasks.size();
    }

    /**
     * Gets the task at the provided number in this task list.
     * @param i task number of task to be retrieved.
     * @return Task with that task number i.
     * @throws DukeException If task number <= 0 or exceeds number of tasks.
     */
    public Task getTask(int i) throws DukeException {
        boolean isTaskNumberWithinLimit = i < 1 || i > tasks.size();
        if (isTaskNumberWithinLimit) {
            throw new DukeException("Invalid task number.");
        }
        return tasks.get(i - 1);
    }

    /**
     * Returns TaskList containing all found tasks.
     * @param keyword Keyword to be searched for.
     * @return TaskList containing all tasks that matched keyword.
     */
    public TaskList find(String keyword) {
        TaskList foundTasks = new TaskList();
        for (Task task : tasks) {
            String taskDescription = task.getDescription();
            if (taskDescription.contains(keyword)) {
                foundTasks.addTask(task);
            }
        }
        return foundTasks;
    }

    /**
     * Gets String representation of this task list.
     * @return string representation of this task list.
     */
    @Override
    public String toString() {
        String tasksString = "";
        for (int i = 0; i < tasks.size(); i++) {
            tasksString += "\n" + (i + 1) + ". " + tasks.get(i).toString();
        }
        return tasksString;
    }

    /**
     * Creates a task object from its string description.
     * @param taskString Task description in string.
     * @return Task that is described by taskString.
     */
    private Task parseTaskString(String taskString) {
        String withoutNumber = taskString.substring(taskString.indexOf(".") + 2);
        String typeOfTask = withoutNumber.substring(1, 2);
        boolean isTodo = typeOfTask.equals("T");
        boolean isEvent = typeOfTask.equals("E");
        boolean isDeadline = typeOfTask.equals("D");
        assert (isTodo || isDeadline || isEvent) : "Invalid stored task type.";
        String marked = withoutNumber.substring(4, 5);
        String description = withoutNumber.substring(7);
        if (isTodo) {
            Task task = new Todo(description);
            if (marked.equals("X")) {
                task.markAsDone();
            }
            return task;
        } else if (isDeadline) {
            assert description.contains(" (by: ") : "Stored deadline does not contain by date.";
            return parseDescriptionDate("by", CommandWords.DEADLINE, description, marked);
        } else if (isEvent) {
            assert description.contains(" (at: ") : "Stored event does not contain at date.";
            return parseDescriptionDate("at", CommandWords.EVENT, description, marked);
        } else {
            throw new DukeRuntimeException("TaskList::parseTaskString Stored task is not a todo, deadline or event");
        }
    }

    private Task parseDescriptionDate(String separator, CommandWords command, String description, String marked) {
        String[] descriptionAndDate = description.split(" \\("+ separator + ": ");
        String descriptionOnly = descriptionAndDate[0];
        String dateOnly = descriptionAndDate[1].substring(0, descriptionAndDate[1].length() - 1);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
        LocalDateTime dateTime = LocalDateTime.parse(dateOnly, formatter);
        Task task;

        switch (command) {
        case EVENT:
            task = new Event(descriptionOnly, dateTime);
            break;
        case DEADLINE:
            task = new Deadline(descriptionOnly, dateTime);
            break;
        default:
            throw new DukeRuntimeException("TaskList::parseDescriptionDate Invalid input");
        }

        boolean isMarked = marked.equals("X");
        if (isMarked) {
            task.markAsDone();
        }
        return task;
    }

}
