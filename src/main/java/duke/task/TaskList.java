package duke.task;

import java.util.ArrayList;

import duke.exception.DukeException;

/**
 * Represents all tasks in TaskList.
 *
 * @author Elgin
 */
public class TaskList {
    /** List of tasks. */
    private static ArrayList<Task> tasks;

    /**
     * Constructor for TaskList.
     *
     * @param tasks The tasks in TaskList.
     */
    public TaskList(ArrayList<Task> tasks) {
        TaskList.tasks = tasks;
    }

    /**
     * Constructor for TaskList.
     *
     */
    public TaskList() {
        TaskList.tasks = new ArrayList<>();
    }

    /**
     * Getter for all tasks.
     *
     * @return All tasks in TaskList.
     */
    public ArrayList<Task> getTasks() {
        return TaskList.tasks;
    }

    /**
     * Gets the total number of tasks.
     *
     * @return The total number of tasks currently.
     */
    public int getTaskLen() {
        return TaskList.tasks.size();
    }

    /**
     * Adds one To Do task and adds it to the array list.
     *
     * @param description The task description.
     * @return The new ToDo task created.
     */
    public ToDo addToDo(String description) {
        ToDo newToDo = new ToDo(description);
        TaskList.tasks.add(newToDo);

        return newToDo;
    }

    /**
     * Creates one Deadline and adds it to the array list.
     *
     * @param userInput The description of the task, and deadline (e.g. sleep /by 2022-12-12 1800).
     * @return The new Deadline task created.
     */
    public Deadline addDeadline(String userInput) {
        String[] details = userInput.split(" /by");

        if (details.length != 2) {
            throw new DukeException("Usage description /by date time");
        }

        String[] dateAndTime = details[1].trim().split(" ");
        boolean hasDateAndTime = dateAndTime.length == 2;

        if (!hasDateAndTime) {
            throw new DukeException("Usage description /by date time");
        }

        String taskDescription = details[0];
        String date = dateAndTime[0];

        String time = dateAndTime[1];
        // Adds a semicolon between hours and minutes, e.g. 1800 to 18:00
        String formattedTime = time.substring(0, 2) + ":" + time.substring(2);

        Deadline newDeadline = new Deadline(taskDescription, date, formattedTime);
        TaskList.tasks.add(newDeadline);

        return newDeadline;
    }

    /**
     * Creates one Event and adds it to the array list.
     *
     * @param userInput The description of the task, and event time.
     * @return The new Event created.
     */
    public Event addEvent(String userInput) {
        String[] details = userInput.split(" /at");

        if (details.length != 2) {
            throw new DukeException("Usage event description /at date time");
        }

        String[] dateAndTime = details[1].trim().split(" ");
        boolean hasDateAndTime = dateAndTime.length == 2;

        if (!hasDateAndTime) {
            throw new DukeException("Usage description /by date time");
        }

        String taskDescription = details[0];
        String date = dateAndTime[0];

        String time = dateAndTime[1];
        // Adds a semicolon between hours and minutes, e.g. 1800 to 18:00
        String formattedTime = time.substring(0, 2) + ":" + time.substring(2);

        Event newEvent = new Event(taskDescription, date, formattedTime);
        TaskList.tasks.add(newEvent);

        return newEvent;
    }

    /**
     * Deletes a task from the tasks array list.
     *
     * @param userInput The index of item to delete.
     * @return String representation of the task deleted.
     * @throws DukeException If index is empty or out of bounds from the array list.
     * @throws NumberFormatException If index cannot be casted into an integer.
     */
    public String deleteItem(String userInput) throws DukeException, NumberFormatException {
        String index = userInput.trim();

        int deleteIndex = Integer.parseInt(index);

        if (deleteIndex < 1 || deleteIndex > TaskList.tasks.size()) {
            throw new DukeException("Invalid index, choose a valid item index!");
        }

        // Removes the task, and returns the string representation of the task deleted.
        return TaskList.tasks.remove(deleteIndex - 1).toString();
    }

    /**
     * Marks a Task as done, or unmarks a task.
     *
     * @param userInput The index of task to be marked as done, preceded by an empty space.
     * @param isMarkDone If true, mark task as done, else, unmark task.
     * @return String representation of the task that was marked or unmarked.
     * @throws DukeException If index is not given, or index <= 1 or index >= tasks.size().
     * @throws NumberFormatException If index given by user cannot be casted into an integer.
     */
    public String markOrUnmark(String userInput, boolean isMarkDone) throws DukeException, NumberFormatException {
        int index = Integer.parseInt(userInput.trim());

        if (index < 1 || index > TaskList.tasks.size()) {
            throw new DukeException("Invalid index, please provide a valid input");
        }

        if (!isMarkDone) {
            TaskList.tasks.get(index - 1).unmark();
        } else {
            TaskList.tasks.get(index - 1).markAsDone();
        }

        return TaskList.tasks.get(index - 1).toString();
    }

    /**
     * Finds and returns String representation of
     * all tasks that matches the search descriptor.
     *
     * @param searchInput The search input the user submitted.
     * @return All search results in listed format, else returns String that states failure to find
     *         relevant search results.
     */
    public String findMatches(String searchInput) {
        StringBuilder sb = new StringBuilder();

        int foundCount = 0;
        for (Task task : TaskList.tasks) {
            String taskName = task.getTaskName();
            if (taskName.contains(searchInput)) {
                sb.append(++foundCount).append(".").append(task).append("\n");
            }
        }

        String searchResults = sb.toString();

        return searchResults.isEmpty()
                ? "Sorry, there are no search results. Try a different term"
                : searchResults;
    }

    /**
     * Returns String representation in a list format of all tasks stored.
     *
     * @return String representation of all tasks
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < TaskList.tasks.size(); i++) {
            int itemIndex = i + 1;

            sb.append(itemIndex).append(".").append(TaskList.tasks.get(i)).append("\n");
        }

        return sb.toString();
    }
}
