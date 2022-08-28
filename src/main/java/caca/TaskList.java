package caca;

import caca.exceptions.EmptyInputException;
import caca.exceptions.InvalidDateException;
import caca.exceptions.InvalidIndexException;
import caca.exceptions.MissingDetailException;
import caca.tasks.Deadline;
import caca.tasks.Event;
import caca.tasks.Task;
import caca.tasks.Todo;

import java.util.ArrayList;
import java.util.List;

/**
 * This class contains CaCa's task list.
 * <p>
 * The list of operations that CaCa can perform with the tasks are listed below as
 * Function (description): command. e.g...
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
            this.tasks = new ArrayList<>();
        } else {
            this.tasks = tasks;
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
            String MESSAGE = String.format("OOPS!!! You have entered an invalid task index. " +
                    "It should be between 1 and %d.", tasks.size());
            throw new InvalidIndexException(MESSAGE);
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
            String MESSAGE = String.format("OOPS!!! The description of %s cannot be empty.",
                    commandType);
            throw new EmptyInputException(MESSAGE);
        }
    }

    /**
     * Adds a ToDo task to user list.
     *
     * @param taskDescription Task description entered by user.
     */
    public static void addToDo(String taskDescription) {
        Task taskToAdd = new Todo(taskDescription);
        tasks.add(taskToAdd);

        System.out.println("Got it. I've added this task:");
        System.out.println(taskToAdd);
        System.out.printf("Now you have %d tasks in the list.\n", tasks.size());
    }

    /**
     * Adds a Deadline to user list.
     *
     * @param taskInfo Task information with task description and task date & time.
     * @throws MissingDetailException If task description or task date & time is missing.
     * @throws InvalidDateException If date entered by user is not in the specified format.
     */
    public static void addDeadline(String taskInfo) throws MissingDetailException, InvalidDateException {
        String[] detailedCommand = taskInfo.split(" /by ", 2);
        if (detailedCommand.length == 1) {
            String message = "OOPS!!! Details missing! "
                    + "A deadline must have both description and date & time.";
            throw new MissingDetailException(message);

        } else {
            if (detailedCommand[0].isBlank() || detailedCommand[1].isBlank()) {
                String MESSAGE = "OOPS!!! I do not accept blank details. "
                        + "A deadline must have both description and date & time.";
                throw new MissingDetailException(MESSAGE);

            } else {
                String description = detailedCommand[0];
                String by = detailedCommand[1];
                Task taskToAdd = new Deadline(description, by);
                tasks.add(taskToAdd);

                System.out.println("Got it. I've added this task:");
                System.out.println(taskToAdd);
                System.out.printf("Now you have %d tasks in the list.\n", tasks.size());
            }
        }
    }

    /**
     * Adds an Event to user list.
     *
     * @param taskInfo Task information with task description and task date & time.
     * @throws MissingDetailException If task description or task date & time is missing.
     * @throws InvalidDateException If date entered by user is not in the specified format.
     */
    public static void addEvent(String taskInfo) throws MissingDetailException, InvalidDateException {
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

                System.out.println("Got it. I've added this task:");
                System.out.println(taskToAdd);
                System.out.printf("Now you have %d tasks in the list.\n", tasks.size());
            }
        }
    }

    /**
     * Displays a list of all the tasks stored in CaCa.
     */
    public static void listTasks() {
        if (tasks.isEmpty()) {
            // No task in the tasks list.
            System.out.println("There is no task in your list!");
        } else {
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                Task task = tasks.get(i);
                System.out.printf("%d.%s%n", i + 1, task);
            }
        }
    }

    /**
     * Marks a task as done in task list.
     *
     * @param index Task index entered by user.
     * @throws InvalidIndexException If task index is invalid, i.e. out of range.
     */
    public static void markTask(String index) throws InvalidIndexException {
        int taskIndex = Integer.parseInt(index);
        isValid(taskIndex);

        // taskIndex entered by user is 1 larger than its array index.
        Task taskToMark = tasks.get(taskIndex - 1);
        taskToMark.markAsDone();

        System.out.println("Nice! I've marked this task as done:");
        System.out.println(taskToMark);
    }

    /**
     * Marks a task as not done in task list.
     *
     * @param index Task index entered by user.
     * @throws InvalidIndexException If task index is invalid, i.e. out of range.
     */
    public static void unmarkTask(String index) throws InvalidIndexException {
        int taskIndex = Integer.parseInt(index);
        isValid(taskIndex);

        // taskIndex entered by user is 1 larger than its array index.
        Task taskToUnmark = tasks.get(taskIndex - 1);
        taskToUnmark.markAsUndone();

        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(taskToUnmark);
    }

    /**
     * Deletes a task from task list.
     *
     * @param index Task index entered by user.
     * @throws InvalidIndexException If task index is invalid, i.e. out of range.
     */
    public static void deleteTask(String index) throws InvalidIndexException {
        int taskIndex = Integer.parseInt(index);
        isValid(taskIndex);

        // taskIndex input is 1 larger than array index.
        Task taskToDelete = tasks.get(taskIndex - 1);
        tasks.remove(taskToDelete);

        System.out.println("Noted. I've removed this task:\n" + taskToDelete);
        System.out.printf("Now you have %d tasks in the list.\n", tasks.size());
    }

    /**
     * Finds all matching tasks from the list with the given keyword.
     *
     * @param keyword Keyword entered by user.
     */
    public static void findTask(String keyword) {
        List<Task> matchingTasks = new ArrayList<>();

        System.out.println("Here are the matching tasks in your list:");

        int taskCount = 1;

        for (Task task : tasks) {
            if (task.getDescription().toLowerCase().contains(keyword.toLowerCase())) {
                System.out.printf("%d.%s%n", taskCount, task);
                taskCount += 1;

                // Add all matching tasks into the array.
                matchingTasks.add(task);
            }
        }

        if (matchingTasks.size() < 1) {
            System.out.println("OOPS!!! There is no matching task in your list.");
        }

    }
}
