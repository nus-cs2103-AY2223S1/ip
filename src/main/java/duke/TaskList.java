package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * This class stores the Tasks and handles operations involving its modification.
 *
 * @author Andrew Lo Zhi Sheng
 * @version CS2103T AY22/23 Semester 1
 */
public class TaskList {
    private ArrayList<Task> tasks;
    private Ui ui;
    private Storage storage;

    /**
     * Constructor for a TaskList instance.
     *
     * @param tasks The ArrayList storing the tasks
     * @param ui The Ui handling the user inputs
     * @param storage The Storage instance handling the file saving and loading
     */
    public TaskList(ArrayList<Task> tasks, Ui ui, Storage storage) {
        this.tasks = tasks;
        this.ui = ui;
        this.storage = storage;
    }

    /**
     * Add a to-do to the task list.
     *
     * @param input the description of the to-do
     *
     * @throws DukeException if the desc is empty.
     */
    public void addTodo(String input) throws DukeException {
        // Check if input is empty
        if (input.length() == 0) {
            throw new DukeException("The description of a todo cannot be empty.");
        }

        // Instantiate task object
        Task newTodo = new Todo(input);

        // Add to list
        this.tasks.add(newTodo);

        // Print message
        this.printAddedTask(newTodo);

        // Update data file
        this.storage.saveTasks(this.tasks);
    }

    /**
     * Add a duke.Deadline to the items list.
     *
     * @param input A String to be added to the list.
     *
     * @throws DukeException if either the desc or the by param is empty
     */
    public void addDeadline(String input) throws DukeException {
        // Get description and date of deadline
        int slashIdx = input.indexOf("/by");

        // If there is no /by, throw an error
        if (slashIdx == -1 || slashIdx > input.length() - 5) {
            throw new DukeException("Please set a due date!");
        }

        String desc = input.substring(0, slashIdx);
        String by = input.substring((slashIdx + 4));

        if (desc.length() == 0) {
            throw new DukeException("The description of a deadline cannot be empty.");
        }

        // Instantiate task object
        Task newDeadline = new Deadline(desc, by);

        // Add to list
        this.tasks.add(newDeadline);

        // Print message
        this.printAddedTask(newDeadline);

        // Update data file
        this.storage.saveTasks(this.tasks);
    }

    /**
     * Add a duke Event to the items list.
     *
     * @param input A String to be added to the list.
     *
     * @throws DukeException if description or date is empty
     */
    public void addEvent(String input) throws DukeException {
        // Get description and date of event
        int slashIdx = input.indexOf("/at");

        // If there is no /at, throw an error
        if (slashIdx == -1 || slashIdx > input.length() - 5) {
            throw new DukeException("Please set a date!");
        }

        String desc = input.substring(0, slashIdx);
        String at = input.substring((slashIdx + 4));

        if (desc.length() == 0) {
            throw new DukeException("The description of an event cannot be empty.");
        }

        // Instantiate task object
        Task newDeadline = new Event(desc, at);

        // Add to list
        this.tasks.add(newDeadline);

        // Print message
        this.printAddedTask(newDeadline);

        // Update data file
        this.storage.saveTasks(this.tasks);
    }

    /**
     * Prints the feedback when a new task has been added.
     *
     * @param newTask The recently added task
     */
    public void printAddedTask(Task newTask) {
        String output = "Got it. I've added this task: \n"
                        + "  \n"
                        + newTask
                        + String.format("\nNow you have %d tasks in the list.",
                                        this.tasks.size());

        this.ui.handleOutput(output);
    }

    /**
     * Prints all items in the items list.
     */
    public void printItems() {
        StringBuilder output = new StringBuilder("Here are the tasks in your list:\n");

        // Print every task in the list
        for (int i = 0; i < this.tasks.size(); i++) {
            String listItem = String.format("%s. %s\n", i + 1, this.tasks.get(i));
            output.append(listItem);
        }

        this.ui.handleOutput(output.toString());
    }

    /**
     * Prints all tasks on a specified date.
     *
     * @param input a String representing the desired date.
     */
    public void printAllOnDate(String input) {
        LocalDate date = LocalDate.parse(input);

        StringBuilder output = new StringBuilder("Here are the tasks on "
                + date.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                + "\n");

        for (Task task : this.tasks) {
            // Check that task is not a To-do which has no deadline
            if (task instanceof Todo) {
                continue;
            }

            // If the deadline is equal to the date, print it
            if (task.getDeadline().equals(date)) {
                output.append(task).append("\n");
            }
        }

        this.ui.handleOutput(output.toString());
    }

    /**
     * Marks a task at a certain index as done.
     *
     * @param index the index of the task to be marked as done
     *
     * @throws DukeException when an invalid index is provided
     */
    public void mark(int index) throws DukeException {
        // Check if the index is within the bounds of the list
        if (index <= 0 || index > this.tasks.size()) {
            throw new DukeException("Invalid index");
        }

        // Get the task to be marked
        Task selectedTask = this.tasks.get(index - 1);

        // Mark it as done
        selectedTask.mark();

        // Print message
        this.ui.handleOutput("Nice! I've marked this task as done:\n"
                             + selectedTask);

        // Update data file
        this.storage.saveTasks(this.tasks);
    }

    /**
     * Marks a task at a certain index as done.
     *
     * @param index the index of the task to be marked as done
     *
     * @throws DukeException when an invalid index is provided
     */
    public void unmark(int index) throws DukeException {
        // Check if the index is within the bounds of the list
        if (index <= 0 || index > this.tasks.size()) {
            throw new DukeException("Invalid index");
        }

        // Get the task to be marked
        Task selectedTask = this.tasks.get(index - 1);

        // Mark it as not done
        selectedTask.unmark();

        // Print message
        this.ui.handleOutput("OK, I've marked this task as not done yet:\n"
                             + selectedTask);

        // Update data file
        this.storage.saveTasks(this.tasks);
    }

    /**
     * Remove a task from the task list.
     *
     * @param index The index of the task to be removed.
     *
     * @throws DukeException if the index is invalid.
     */
    public void delete(int index) throws DukeException {
        // Check if the index is within the bounds of the list
        if (index <= 0 || index > this.tasks.size()) {
            throw new DukeException("Invalid index");
        }

        // Remove task from list and get removed task
        Task removedTask = this.tasks.remove(index - 1);

        // Print message
        String output = "Noted. I've removed this task:\n"
                        + removedTask
                        + "\n"
                        + String.format("Now you have %d tasks in the list.",
                                        this.tasks.size());
        this.ui.handleOutput(output);

        // Update data file
        this.storage.saveTasks(this.tasks);
    }

    /**
     * Prints all tasks with description containing key.
     *
     * @param key a String that is contained within the desired tasks
     */
    public void find(String key) {
        StringBuilder output = new StringBuilder("Here are the matching tasks in your list:\n");

        for (Task task : this.tasks) {
            if (task.toString().contains(key)) {
                output.append(task);
            }
        }

        this.ui.handleOutput(output.toString());
    }
}
