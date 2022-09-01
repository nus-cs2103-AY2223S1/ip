package caca;

import java.util.ArrayList;
import java.util.List;

import caca.exceptions.EmptyInputException;
import caca.exceptions.InvalidDateException;
import caca.exceptions.InvalidIndexException;
import caca.exceptions.MissingDetailException;
import caca.tasks.Deadline;
import caca.tasks.Event;
import caca.tasks.Task;
import caca.tasks.Todo;

/**
 * This class contains CaCa's task list.
 * <p>
 * The list of operations that CaCa can perform with the tasks are listed below as
 * Function (description): command. e.g...
 * <ul>
 * <li>Add tasks:</li>
 * <ul>
 *     <li>ToDos (tasks without any date & time): todo taskDescription</li>
 *     e.g.todo borrow book
 *     <li>Deadlines (deadlines by a date & time): deadline taskDescription /by dd/MM/yyyy HHmm</li>
 *     e.g. deadline return book /by 01/09/2022 1200
 *     <li>Events (events at a date & time): event taskDescription /at dd/MM/yyyy HHmm</li>
 *     e.g. event project meeting /at 01/09/2022 1600
 * </ul>
 * <li>List task (displays a list of all tasks stored): list</li>
 * <li>Mark task (marks task as done with a "X"): mark taskIndex. e.g. mark 2</li>
 * <li>Unmark task (marks task as not done and removes "X"): unmark taskIndex. e.g. unmark 2</li>
 * <li>Delete task (deletes task from list): delete taskIndex. e.g. delete 3</li>
 * <li>Find task (finds all matching tasks from list): find task. e.g. find book</li>
 * </ul>
 * </p>
 *
 * @author Carrie Zheng Jiarui
 * @version CS2103T AY22/23 Semester 1, iP
 */
public class TaskList {

    /**
     * An array to store the list of tasks input by user.
     */
    private static List<Task> tasks = new ArrayList<>();

    /**
     * Constructor for creating a TaskList.
     *
     * @param tasks The list of tasks.
     */
    public TaskList(ArrayList<Task> tasks) {

        if (tasks == null) {
            TaskList.tasks = new ArrayList<>();
        } else {
            TaskList.tasks = tasks;
        }
    }

    /**
     * Gets the list of tasks.
     *
     * @return The list of tasks.
     */
    public List<Task> getTasks() {
        return tasks;
    }

    /**
     * Adds a task to the list.
     *
     * @param task Task to be added to the list.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Checks if task index is valid.
     *
     * @param taskIndex Task index entered by user.
     * @throws InvalidIndexException If task index is invalid, i.e. out of range.
     */
    public static void isValid(int taskIndex) throws InvalidIndexException {
        if (taskIndex <= 0 || taskIndex > tasks.size()) {
            String message = String.format("OOPS!!! You have entered an invalid task index. "
                    + "It should be between 1 and %d.", tasks.size());
            throw new InvalidIndexException(message);
        }
    }

    /**
     * Checks if task description is empty or contains only white spaces.
     *
     * @param command User input with 1st element as command type, 2nd element as task description.
     * @throws EmptyInputException If task description is empty or left blank.
     */
    public static void hasDescription(String[] command) throws EmptyInputException {
        String commandType = command[0];
        if (command.length == 1 || command[1].isBlank()) {
            String message = String.format("OOPS!!! The description of %s cannot be empty.",
                    commandType);
            throw new EmptyInputException(message);
        }
    }

    /**
     * Adds a ToDo task to user list.
     *
     * @param command User command as input.
     * @return CaCa's response after adding a new ToDo.
     * @throws EmptyInputException If task description is empty or left blank.
     */
    public static String addToDo(String[] command) throws EmptyInputException {
        // Checks for valid description, i.e. not empty or blank.
        hasDescription(command);

        String taskDescription = command[1];
        Task taskToAdd = new Todo(taskDescription);
        tasks.add(taskToAdd);

        String response = String.format("Got it. I've added this task:\n"
                + "%s\n"
                + "Now you have %d tasks in the list.\n",
                taskToAdd, tasks.size());

        return response;
    }

    /**
     * Adds a Deadline to user list.
     *
     * @param command User command as input.
     * @return CaCa's response after adding a new Deadline.
     * @throws MissingDetailException If task description or task date & time is missing.
     * @throws InvalidDateException If date entered by user is not in the specified format.
     * @throws EmptyInputException If task description is empty or left blank.
     */
    public static String addDeadline(String[] command) throws
            MissingDetailException, InvalidDateException, EmptyInputException {
        // Checks for valid description, i.e. not empty or blank, before adding deadline.
        hasDescription(command);

        String taskInfo = command[1];
        String[] detailedCommand = taskInfo.split(" /by ", 2);
        if (detailedCommand.length == 1) {
            String message = "OOPS!!! Details missing! "
                    + "A deadline must have both description and date & time.";
            throw new MissingDetailException(message);

        } else {
            if (detailedCommand[0].isBlank() || detailedCommand[1].isBlank()) {
                String message = "OOPS!!! I do not accept blank details. "
                        + "A deadline must have both description and date & time.";
                throw new MissingDetailException(message);

            } else {
                String description = detailedCommand[0];
                String by = detailedCommand[1];
                Task taskToAdd = new Deadline(description, by);
                tasks.add(taskToAdd);

                String response = String.format("Got it. I've added this task:\n"
                        + "%s\n"
                        + "Now you have %d tasks in the list.\n",
                        taskToAdd, tasks.size());
                return response;
            }
        }
    }

    /**
     * Adds an Event to user list.
     *
     * @param command User command as input.
     * @return CaCa's response after adding a new Event.
     * @throws MissingDetailException If task description or task date & time is missing.
     * @throws InvalidDateException If date entered by user is not in the specified format.
     * @throws EmptyInputException If task description is empty or left blank.
     */
    public static String addEvent(String[] command) throws
            MissingDetailException, InvalidDateException, EmptyInputException {
        // Checks for valid description, i.e. not empty or blank, before adding deadline.
        hasDescription(command);

        String taskInfo = command[1];
        String[] detailedCommand = taskInfo.split(" /at ", 2);
        if (detailedCommand.length == 1) {
            String message = "OOPS!!! Details missing! "
                    + "An event must have both description and date & time.";
            throw new MissingDetailException(message);

        } else {
            if (detailedCommand[0].isBlank() || detailedCommand[1].isBlank()) {
                String message = "OOPS!!! I do not accept blank details. "
                        + "An event must have both description and date & time.";
                throw new MissingDetailException(message);

            } else {
                String description = detailedCommand[0];
                String at = detailedCommand[1];
                Task taskToAdd = new Event(description, at);
                tasks.add(taskToAdd);

                String response = String.format("Got it. I've added this task:\n"
                        + "%s\n"
                        + "Now you have %d tasks in the list.\n",
                        taskToAdd, tasks.size());
                return response;
            }
        }
    }

    /**
     * Displays a list of all the tasks stored in CaCa.
     *
     * @return Entire list of tasks.
     */
    public static String listTasks() {
        String response = "";

        if (tasks.isEmpty()) {
            return "There is no task in your list!";

        } else {
            for (int i = 0; i < tasks.size(); i++) {
                Task task = tasks.get(i);
                response = String.format("%s%d.%s\n", response, i + 1, task);
            }

            String responseHeader = "Here are the tasks in your list:";
            return String.format("%s\n%s", responseHeader, response);
        }
    }

    /**
     * Marks a task as done in task list.
     *
     * @param index Task index entered by user.
     * @return CaCa's response after marking a task as done.
     * @throws InvalidIndexException If task index is invalid, i.e. out of range.
     */
    public static String markTask(String index) throws InvalidIndexException {
        int taskIndex = Integer.parseInt(index);
        isValid(taskIndex);

        // taskIndex entered by user is 1 larger than its array index.
        Task taskToMark = tasks.get(taskIndex - 1);
        taskToMark.markAsDone();

        String response = String.format("Nice! I've marked this task as done:\n%s", taskToMark);
        return response;
    }

    /**
     * Marks a task as not done in task list.
     *
     * @param index Task index entered by user.
     * @return CaCa's response after marking a task as not done (unmark).
     * @throws InvalidIndexException If task index is invalid, i.e. out of range.
     */
    public static String unmarkTask(String index) throws InvalidIndexException {
        int taskIndex = Integer.parseInt(index);
        isValid(taskIndex);

        // taskIndex entered by user is 1 larger than its array index.
        Task taskToUnmark = tasks.get(taskIndex - 1);
        taskToUnmark.markAsUndone();

        String response = String.format("OK, I've marked this task as not done yet:\n%s", taskToUnmark);
        return response;
    }

    /**
     * Deletes a task from task list.
     *
     * @param index Task index entered by user.
     * @return CaCa's response after deleting a task.
     * @throws InvalidIndexException If task index is invalid, i.e. out of range.
     */
    public static String deleteTask(String index) throws InvalidIndexException {
        int taskIndex = Integer.parseInt(index);
        isValid(taskIndex);

        // taskIndex input is 1 larger than array index.
        Task taskToDelete = tasks.get(taskIndex - 1);
        tasks.remove(taskToDelete);

        String response = String.format("Noted. I've removed this task:\n"
                + "%s\n"
                + "Now you have %d tasks in the list.\n",
                taskToDelete, tasks.size());
        return response;
    }

    /**
     * Finds all matching tasks from the list with the given keyword.
     *
     * @param keyword Keyword entered by user.
     * @return CaCa's response after finding the corresponding task.
     */
    public static String findTask(String keyword) {
        List<Task> matchingTasks = new ArrayList<>();

        int taskCount = 1;

        String response = "Here are the matching tasks in your list:\n";

        for (Task task : tasks) {
            if (task.getDescription().toLowerCase().contains(keyword.toLowerCase())) {
                response = String.format("%s%d.%s\n", response, taskCount, task);
                taskCount += 1;

                matchingTasks.add(task);
            }
        }

        if (matchingTasks.size() < 1) {
            return "OOPS!!! There is no matching task in your list.";
        }

        return response;

    }

}
