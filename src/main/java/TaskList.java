import java.time.format.DateTimeParseException;
import java.util.ArrayList;

/**
 * Represents all tasks.
 *
 * @author Elgin
 */
public class TaskList {
    /** List of tasks. */
    private final ArrayList<Task> tasks;

    /**
     * Constructor for TaskList.
     *
     * @param tasks The tasks in TaskList.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Constructor for TaskList.
     *
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Getter for tasks.
     *
     * @return An array list of tasks.
     */
    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

    /**
     * Adds one To Do task and adds it to the array list.
     *
     * @param description The task description.
     */
    public void addToDo(String description) {
        this.tasks.add(new ToDo(description));

        System.out.println(Duke.formatText("Got it. I've added this task:\n" + "  "
                + this.tasks.get(this.tasks.size() - 1) + "\n"
                + "Now you have " + this.tasks.size() + " tasks in the list."));
    }

    /**
     * Creates one Deadline and adds it to the array list.
     *
     * @param userInput The description of the task, and deadline.
     * @throws DukeException If userInput is not in the form "description /by deadline".
     * @throws DateTimeParseException If deadline date given by user cannot be casted to a date (require "yyyy-mm-dd")
     */
    public void addDeadline(String userInput) throws DukeException, DateTimeParseException {
        String[] detailsFragments = userInput.split(" /by");

        if (detailsFragments.length != 2) {
            throw new DukeException("Usage description /by deadline");
        }

        this.tasks.add(new Deadline(detailsFragments[0], detailsFragments[1].trim()));

        System.out.println(Duke.formatText("Got it. I've added this task:\n" + "  "
                + this.tasks.get(this.tasks.size() - 1) + "\n"
                + "Now you have " + this.tasks.size() + " tasks in the list."));
    }

    /**
     * Creates one Event and adds it to the array list.
     *
     * @param userInput The description of the task, and event time.
     * @throws DukeException If userInput is not in the form "description /at time".
     */
    public void addEvent(String userInput) throws DukeException, DateTimeParseException {
        String[] detailsFragments = userInput.split(" /at");

        if (detailsFragments.length != 2) {
            throw new DukeException("Usage description /at time");
        }

        this.tasks.add(new Event(detailsFragments[0], detailsFragments[1].trim()));

        System.out.println(Duke.formatText("Got it. I've added this task:\n" + "  "
                + this.tasks.get(this.tasks.size() - 1) + "\n"
                + "Now you have " + this.tasks.size() + " tasks in the list."));
    }

    /**
     * Deletes a task from the tasks array list.
     *
     * @param userInput The index of item to delete, preceded by an empty space.
     * @throws DukeException If index is empty or out of bounds from the array list.
     * @throws NumberFormatException If index cannot be casted into an integer.
     */
    public void deleteItem(String userInput) throws DukeException, NumberFormatException {
        String index = userInput.trim();
        if (index.length() == 0) {
            throw new DukeException("Index cannot be empty!");
        }

        int deleteIndex = Integer.parseInt(index);

        if (deleteIndex < 1 || deleteIndex > this.tasks.size()) {
            throw new DukeException("Invalid index, choose a valid item index!");
        }

        System.out.println(Duke.formatText("Noted. I've removed this task:\n  "
                + this.tasks.get(deleteIndex - 1) + "\n"
                + "Now you have " + (this.tasks.size() - 1) + " tasks in the list"));

        this.tasks.remove(deleteIndex - 1);
    }

    /**
     * Marks a Task as done, or unmarks a task.
     *
     * @param userInput The index of task to be marked as done, preceded by an empty space.
     * @param isMarkDone If true, mark task as done, else, unmark task.
     * @throws DukeException If index is not given, or index <= 1 or index >= tasks.size().
     * @throws NumberFormatException If index given by user cannot be casted into an integer.
     */
    public void markOrUnmark(String userInput, boolean isMarkDone) throws DukeException, NumberFormatException {
        if (userInput.trim().length() == 0) {
            throw new DukeException("Index of mark cannot be empty!");
        }

        int index = Integer.parseInt(userInput.trim());

        if (index < 1 || index > this.tasks.size()) {
            throw new DukeException("Invalid index, please provide a valid input");
        }

        if (!isMarkDone) {
            this.tasks.get(index - 1).unmark();
            System.out.println(Duke.formatText("OK, I've marked this task as not done yet:\n"
                    + this.tasks.get(index - 1)));
        } else {
            this.tasks.get(index - 1).markAsDone();
            System.out.println(Duke.formatText("Nice! I've marked this task as done:\n" + this.tasks.get(index - 1)));
        }
    }

    /**
     * Prints all items in a list format that is stored.
     *
     */
    public void listItems() {
        StringBuilder string = new StringBuilder();
        for (int i = 0; i < this.tasks.size(); i++) {
            int itemIndex = i + 1;

            string.append(itemIndex).append(".").append(this.tasks.get(i)).append("\n");
        }

        System.out.println(Duke.formatText("Here are the tasks in your list\n" + string));
    }
}
