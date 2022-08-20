import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

/**
 * CaCa is a personal assistant chatbot that helps users manage and track things.
 * <p>
 * Functions with respective commands are listed below as Function (description): command. e.g...
 * <ul><li>Greet user (triggered as soon as the chatbot is run)</li>
 * <li>Exit program (end chatbot): bye</li>
 * <li>Add tasks:</li>
 * <ul>
 *     <li>ToDos (tasks without any date/time): todo taskDescription</li>
 *     e.g.todo borrow book
 *     <li>Deadlines (tasks to be done before date/time): deadline taskDescription /by dateTime</li>
 *     e.g. deadline return book /by Sunday
 *     <li>Events (tasks that start and end at a specific time): event taskDescription /at dateTime</li>
 *     e.g. event project meeting /at Mon 2-4pm
 * </ul>
 * <li>List task (displays a list of all tasks stored): list</li>
 * <li>Mark task (marks task as done with a "X"): mark taskIndex. e.g. mark 2</li>
 * <li>Unmark task (marks task as not done and removes "X"): unmark taskIndex. e.g. unmark 2</li>
 * <li>Delete task (deletes task from list): delete taskIndex. e.g. delete 3</li>
 * </ul>
 * </p>
 * @author Carrie Zheng Jiarui
 * @version CS2103T AY22/23 Semester 1, iP
 */
public class CaCa {

    /**
     * A horizontal line to be displayed as separator for each activity with CaCa.
     */
    private static final String LINE = "____________________________________________________________\n";

    /**
     * A class-level array to store all user inputs.
     */
    private static final List<Task> tasks = new ArrayList<>();


    /**
     * Checks if task index is valid.
     */
    public static void isValid(int taskIndex) throws InvalidTaskIndex {
        if (taskIndex <= 0 || taskIndex > tasks.size()) {
            String MESSAGE = String.format("OOPS!!! You have entered an invalid task index. " +
                    "It should be between 1 and %d.", tasks.size());
            throw new InvalidTaskIndex(MESSAGE);
        }
    }

    /**
     * Greets user with CaCa logo and introduction.
     */
    public static void greet() {
        // ASCII text banner below created and adapted from
        // https://manytools.org/hacker-tools/ascii-banner/
        // with the following settings:
        // Banner text: CaCa, Font: Big, Horizontal spacing: Normal, Vertical spacing: Normal.
        String LOGO = "   _____       _____      \n"
                + "  / ____|     / ____|     \n"
                + " | |     __ _| |     __ _ \n"
                + " | |    / _` | |    / _` |\n"
                + " | |___| (_| | |___| (_| |\n"
                + "  \\_____\\__,_|\\_____\\__,_|\n\n";

        String GREETING = "Hello! I'm CaCa.\n"
                + "What can I do for you?\n";

        System.out.println(LINE + LOGO + GREETING + LINE);
    }

    /**
     * Says bye to user.
     */
    public static void bye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Adds a ToDo task to user list.
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
     * Adds a Deadline task to user list.
     * @param taskInfo Task information with task description and task date/time.
     * @throws MissingDetailException If task description or task date/time is missing.
     */
    public static void addDeadline(String taskInfo) throws MissingDetailException {
        String[] detailedCommand = taskInfo.split(" /by ", 2);
        if (detailedCommand.length == 1) {
            String message = "OOPS!!! Details missing! "
                    + "A deadline must have both description and date/time.";
            throw new MissingDetailException(message);
        } else {
            if (detailedCommand[0].isBlank() || detailedCommand[1].isBlank()) {
                String MESSAGE = "OOPS!!! I do not accept blank details. "
                        + "A deadline must have both description and date/time.";
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
     * Adds an Event task to user list.
     * @param taskInfo Task information with task description and task start & end time.
     * @throws MissingDetailException If task description or task start & end time is missing.
     */
    public static void addEvent(String taskInfo) throws MissingDetailException {
        String[] detailedCommand = taskInfo.split(" /at ", 2);
        if (detailedCommand.length == 1) {
            String message = "OOPS!!! Details missing! "
                    + "An event must have both description and specific start & end time.";
            throw new MissingDetailException(message);
        } else {
            if (detailedCommand[0].isBlank() || detailedCommand[1].isBlank()) {
                String message = "OOPS!!! I do not accept blank details. "
                        + "An event must have both description and specific start & end time.";
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
     * @param index Task index entered by user.
     * @throws InvalidTaskIndex If task index is invalid, i.e. out of range.
     */
    public static void markTask(String index) throws InvalidTaskIndex {
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
     * @param index Task index entered by user.
     * @throws InvalidTaskIndex If task index is invalid, i.e. out of range.
     */
    public static void unmarkTask(String index) throws InvalidTaskIndex {
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
     * @param index Task index entered by user.
     * @throws InvalidTaskIndex If task index is invalid, i.e. out of range.
     */
    public static void deleteTask(String index) throws InvalidTaskIndex {
        int taskIndex = Integer.parseInt(index);
        isValid(taskIndex);
        // taskIndex entered by user is 1 larger than its array index.
        Task taskToDelete = tasks.get(taskIndex - 1);
        tasks.remove(taskToDelete);
        System.out.println("Noted. I've removed this task:\n" + taskToDelete);
        System.out.printf("Now you have %d tasks in the list.\n", tasks.size());
    }

    /**
     * The main chatbot program greets user, reads and stores user input,
     * allows user to update task status as done or undone, displays all tasks
     * with status when user inputs list and exits when user inputs bye.
     * @param args Command line arguments
     */
    public static void main(String[] args) {

        greet();

        // Solution below on getting user input is
        // adapted from https://www.w3schools.com/java/java_user_input.asp
        Scanner sc = new Scanner(System.in); // Creates a Scanner Object.

        while (true) {
            // Reads user input.
            String input = sc.nextLine();

            // Detect user command, where 1st element is the type of action to be done (command type),
            // 2nd element is the task description, with or without date/time.
            String[] command = input.split(" ", 2);
            String commandType = command[0];

            // Prints a line after a user input, to start CaCa response.
            System.out.print(LINE);

            try {
                if (commandType.isBlank()) {
                    throw new EmptyInputException("OOPS!!! You have entered an empty input.");

                } else if (commandType.equals("bye")) {
                    bye();
                    break;

                } else if (commandType.equals("todo")
                        || commandType.equals("deadline")
                        || commandType.equals("event")) {

                    // Checks whether description is empty or contains only white spaces.
                    if (command.length == 1 || command[1].isBlank()) {
                        String message = String.format("OOPS!!! The description of %s cannot be empty.",
                                commandType);
                        throw new EmptyInputException(message);

                    } else if (commandType.equals("todo")) {
                        addToDo(command[1]);

                    } else if (commandType.equals("deadline")) {
                        addDeadline(command[1]);

                    } else { // Event
                        addEvent(command[1]);
                    }

                } else if (commandType.equals("list")) {
                    listTasks();

                } else if (commandType.equals("mark")) {
                    markTask(command[1]);

                } else if (commandType.equals("unmark")) {
                    unmarkTask(command[1]);

                } else if (commandType.equals("delete")) {
                    deleteTask(command[1]);

                } else {
                    // Invalid input.
                    System.out.println("OOPS!!! I'm sorry, but I don't know what that means :-(");

                }

            } catch (CaCaException e) {
                System.out.println(e.getMessage());
                System.out.println("Please try again!");

            } finally {
                // Prints a line to end CaCa response.
                System.out.println(LINE);
            }
        }
    }
}
