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
     * Checks if task description is missing or contains only white spaces.
     *
     * @param taskType The type of task, i.e. ToDo, Deadline or Event.
     * @param taskInfo The task information, i.e. description with date & time if applicable.
     * @throws EmptyInputException If task description is missing or left blank.
     */
    public static void hasDescription(String taskType, String taskInfo) throws EmptyInputException {
        if (taskInfo == null || taskInfo.isBlank()) {
            String message = String.format("OOPS!!! (*_*)\nThe description of %s cannot be empty.",
                    taskType);
            throw new EmptyInputException(message);
        }
    }

    /**
     * Creates a Todo, a Deadline or an Event.
     *
     * @param taskType The type of task, i.e. ToDo, Deadline or Event.
     * @param taskInfo The task information, i.e. description with date & time if applicable.
     * @return A task that has been created.
     * @throws EmptyInputException If task description is missing or left blank.
     * @throws MissingDetailException If task description or task date & time is missing.
     * @throws InvalidDateException If date entered by user is not in the specified format.
     */
    public static Task createTask(String taskType, String taskInfo) throws
            EmptyInputException, MissingDetailException, InvalidDateException {

        assert taskType != null;

        hasDescription(taskType, taskInfo);

        String[] detailedCommand = new String[0];
        Task taskToCreate;

        switch (taskType) {
        case "todo":
            taskToCreate = new Todo(taskInfo);
            return taskToCreate;
        case "deadline":
            detailedCommand = taskInfo.split(" /by ", 2);
            break;
        case "event":
            detailedCommand = taskInfo.split(" /at ", 2);
            break;
        default:
            // The method will not be called if the task type does not match any of the above cases.
            // Hence, will never reach default case. This is added for checkstyle to work.
            taskToCreate = null;
        }
        boolean isDetailMissing = detailedCommand.length < 2;
        if (isDetailMissing) {
            String errorMessage = String.format("OOPS!!! (*_*)\nDetails missing as "
                    + "%s must have both description and date & time.", taskType);
            throw new MissingDetailException(errorMessage);
        }

        boolean isDetailBlank = detailedCommand[0].isBlank() || detailedCommand[1].isBlank();
        if (isDetailBlank) {
            String errorMessage = String.format("OOPS!!! (*_*)\nI do not accept blank details as "
                    + "%s must have both description and date & time specified clearly.", taskType);
            throw new MissingDetailException(errorMessage);
        }

        String taskDescription = detailedCommand[0];
        String dateTime = detailedCommand[1];
        taskToCreate = taskType.equals("deadline")
                ? new Deadline(taskDescription, dateTime)
                : new Event(taskDescription, dateTime);

        return taskToCreate;
    }

    /**
     * Adds a task to the list; recognises any duplicate tasks and warn the user.
     *
     * @param task Task to be added to the list.
     * @return CaCa's response after successfully adding a task to the list; or a warning for duplicates.
     */
    public static String addTask(Task task) {
        assert task != null;

        // Adapted from https://stackabuse.com/java-check-if-array-contains-value-or-element/
        boolean hasDuplicate = tasks.stream()
                .anyMatch(taskToCheck -> taskToCheck.toString().equals(task.toString()));
        if (hasDuplicate) {
            String duplicateWarning = String.format("OOPS!!! (*_*)\nDuplicate task:\n"
                    + "%s\n"
                    + "This is not added again.", task);
            return duplicateWarning;
        }

        tasks.add(task);

        String response = String.format("Got it. I've added this task (^_^)\n"
                        + "%s\n"
                        + "Now you have %d tasks in the list.\n",
                task, tasks.size());

        return response;
    }

    /**
     * Displays a list of all the tasks stored in CaCa.
     *
     * @return Entire list of tasks.
     */
    public static String listTasks() {
        String response = "";

        if (tasks.isEmpty()) {
            return "There is no task in your list (>_<)";

        } else {
            for (int i = 0; i < tasks.size(); i++) {
                Task task = tasks.get(i);
                response = String.format("%s%d.%s\n", response, i + 1, task);
            }

            String responseHeader = "Your task list (^_^)";
            return String.format("%s\n%s", responseHeader, response);
        }
    }

    /**
     * Checks if task index input by user is a number.
     *
     * @param taskIndexInput Task index input by user.
     * @return True if user has entered a number as the index; false otherwise.
     */
    public static boolean isNumber(String taskIndexInput) {
        try {
            Integer.parseInt(taskIndexInput);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    /**
     * Checks if task index is valid.
     *
     * @param taskIndexInput Task index input by user.
     * @throws InvalidIndexException If task index is missing, not a number or out of range.
     */
    public static void isValid(String taskIndexInput) throws InvalidIndexException {

        if (tasks.isEmpty()) {
            String errorMessage = "OOPS!!! (*_*)\nThere is no task in your list!\n"
                    + "Add one first.";
            throw new InvalidIndexException(errorMessage);
        }

        if (taskIndexInput == null) {
            String errorMessage = "OOPS!!! (*_*)\nTask index cannot be empty. "
                    + "It must be specified and must be a number.";
            throw new InvalidIndexException(errorMessage);
        }

        if (!isNumber(taskIndexInput)) {
            String errorMessage = "OOPS!!! (*_*)\nYou have entered an invalid task index. "
                    + "It must be a number.";
            throw new InvalidIndexException(errorMessage);
        }

        int taskIndexNumber = Integer.parseInt(taskIndexInput);
        if (taskIndexNumber <= 0 || taskIndexNumber > tasks.size()) {
            String errorMessage = String.format("OOPS!!! (*_*)\nYou have entered an invalid task index. "
                    + "It should be between 1 and %d.", tasks.size());
            throw new InvalidIndexException(errorMessage);
        }
    }

    /**
     * Carries out operations that involve task index, i.e. mark, unmark and delete task operations.
     *
     * @param operationType The type of task, i.e. mark, unmark or delete.
     * @param taskIndexInput Task index input by user.
     * @return CaCa's response after marking a task as done, not done or deleting a task.
     * @throws InvalidIndexException If task index is missing, not a number or out of range.
     */
    public static String indexOperation(String operationType, String taskIndexInput) throws InvalidIndexException {
        isValid(taskIndexInput);

        int taskIndex = Integer.parseInt(taskIndexInput);
        int arrayIndex = taskIndex - 1;
        Task taskToModify = tasks.get(arrayIndex);

        String response = "";

        switch (operationType) {

        case "mark":
            if (taskToModify.getStatusIcon().equals("X")) {
                response = String.format("OOPS!!! (*_*)\nYou have already completed this task!\n"
                        + "But GOOD JOB (> O <)\n"
                        + "You can enter \"delete %s\" to remove it.", taskIndex);
                return response;
            }

            taskToModify.markAsDone();
            response = String.format("Nice! (> O <)\n"
                    + "I've marked this task as done:\n%s", taskToModify);
            break;

        case "unmark":
            if (taskToModify.getStatusIcon().equals(" ")) {
                response = "OOPS!!! (*_*)\n"
                        + "You have not completed this task yet, so it is already unmarked!\n"
                        + "You may want to start working on it now.\n"
                        + "ALL THE BEST (^ O ^)";
                return response;
            }

            taskToModify.markAsUndone();
            response = String.format("OK (O_O)\n"
                    + "I've marked this task as not done yet:\n%s", taskToModify);
            break;

        case "delete":
            tasks.remove(taskToModify);
            response = String.format("Noted (^_^)\n"
                    + "I've removed this task:\n"
                    + "%s\n"
                    + "Now you have %d tasks in the list.\n",
                    taskToModify, tasks.size());
            break;

        default:
            response = String.format("OOPS!!! (*_*)\nI'm sorry, I can't %s that task. "
                    + "Please try again.", operationType);
        }

        return response;

    }

    /**
     * Finds all matching tasks from the list with the given keyword.
     *
     * @param keyword Keyword entered by user.
     * @return CaCa's response after finding the corresponding task.
     */
    public static String findTask(String taskType, String keyword) throws EmptyInputException {
        assert taskType != null;

        hasDescription(taskType, keyword);

        List<Task> matchingTasks = new ArrayList<>();

        int taskCount = 1;

        String response = "Matching tasks in your list (^_^)\n";

        for (Task task : tasks) {
            if (task.getDescription().toLowerCase().contains(keyword.toLowerCase())) {
                response = String.format("%s%d.%s\n", response, taskCount, task);
                taskCount += 1;

                matchingTasks.add(task);
            }
        }

        if (matchingTasks.size() < 1) {
            return "OOPS!!! (*_*)\nThere is no matching task in your list.";
        }

        return response;

    }

}
