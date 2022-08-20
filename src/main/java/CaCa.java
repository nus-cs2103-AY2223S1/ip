import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

/**
 * CaCa is a personal assistant chatbot that helps users manage and track your things.
 * Functions with respective commands are listed below as
 * Function (description): command. e.g...
 * - Greet user (triggered as soon as the chatbot is run)
 * - Exit program (end chatbot): bye
 * - Add tasks:
 *     - ToDos (tasks without any date/time): todo taskDescription.
 *     e.g.todo borrow book
 *     - Deadlines (tasks to be done before date/time): deadline taskDescription /by dateTime.
 *     e.g. deadline return book /by Sunday
 *     - Events (tasks that start and end at a specific time): event taskDescription /at dateTime
 *     e.g. event project meeting /at Mon 2-4pm
 * - List task (displays a list of all tasks stored): list
 * - Mark task (marks task as done with a "X"): mark taskIndex. e.g. mark 2
 * - Unmark task (marks task as not done and removes "X"): unmark taskIndex. e.g. unmark 2
 * - Delete task (deletes task from list): delete taskIndex. e.g. delete 3
 */
public class CaCa {

    /**
     * A horizontal line to be displayed as separator for each activity with CaCa.
     */
    private static String line = "____________________________________________________________\n";

    /**
     * A class-level array to store all user inputs.
     */
    private static List<Task> tasks = new ArrayList<>();

    /**
     * Checks if task index is valid.
     */
    public static void isValid(int taskIndex) throws InvalidTaskIndex {
        if (taskIndex <= 0 || taskIndex > tasks.size()) {
            String message = String.format("OOPS!!! You have entered an invalid task index. " +
                    "It should be between 1 and %d.", tasks.size());
            throw new InvalidTaskIndex(message);
        }
    }

    /**
     * Prints message to greet the user.
     */
    public static void greeting() {
        // ASCII text banner below created and adapted from
        // https://manytools.org/hacker-tools/ascii-banner/
        // with the following settings:
        // Banner text: CaCa, Font: Big, Horizontal spacing: Normal, Vertical spacing: Normal.
        String logo = "   _____       _____      \n"
                + "  / ____|     / ____|     \n"
                + " | |     __ _| |     __ _ \n"
                + " | |    / _` | |    / _` |\n"
                + " | |___| (_| | |___| (_| |\n"
                + "  \\_____\\__,_|\\_____\\__,_|\n\n";

        String greeting = "Hello! I'm CaCa.\n"
                + "What can I do for you?\n";

        System.out.println(line + logo + greeting + line);
    }

    /**
     * Prints message to say bye to the user.
     */
    public static void bye() {
        System.out.println("Bye. Hope to see you again soon!\n" + line);
    }

    /**
     * Displays a list of all the tasks stored in CaCa.
     */
    public static void listTasks() {
        if (tasks.isEmpty()) {
            // No task in the tasks list.
            System.out.println("There is no task in your list!\n" + line);
        } else {
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                Task task = tasks.get(i);
                System.out.printf("%d.%s%n", i + 1, task);

                if (i == tasks.size() - 1) {
                    System.out.println(line);
                }
            }
        }
    }

    /**
     * Marks a task as done as instructed by user.
     * @param command User command with type of task and task description.
     * @throws InvalidTaskIndex Indicates that the task index is invalid, i.e. out of range.
     */
    public static void markTask(String[] command) throws InvalidTaskIndex {
        // taskIndex entered by user is 1 larger than its array index.
        int taskIndex = Integer.parseInt(command[1]);
        isValid(taskIndex);
        Task taskToMark = tasks.get(taskIndex - 1);
        taskToMark.markAsDone();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(taskToMark + "\n" + line);
    }

    public static void unmarkTask(String[] command) throws InvalidTaskIndex {
        // taskIndex entered by user is 1 larger than its array index.
        int taskIndex = Integer.parseInt(command[1]);
        isValid(taskIndex);
        Task taskToUnmark = tasks.get(taskIndex - 1);
        taskToUnmark.markAsUndone();
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(taskToUnmark + "\n" + line);
    }

    /**
     * The main chatbot program greets user, reads and stores user input,
     * allows user to update task status as done or undone, displays all tasks
     * with status when user inputs list and exits when user inputs bye.
     * @param args Command line arguments
     */
    public static void main(String[] args) {

        greeting();

        // Solution below on getting user input is
        // adapted from https://www.w3schools.com/java/java_user_input.asp
        Scanner sc = new Scanner(System.in); // Creates a Scanner Object.

        while (true) {
            // Reads user input.
            String input = sc.nextLine();

            // Detect user command, where 1st element is the type of task to be done,
            // 2nd element is the task description with or without date/time.
            String[] command = input.split(" ", 2);

            System.out.print(line);

            try {
                if (command[0].isBlank()) {
                    throw new EmptyInputException("OOPS!!! You have entered an empty input.");

                } else if (command[0].equals("bye")) {
                    bye();
                    break;

                } else if (command[0].equals("list")) {
                    listTasks();

                } else if (command[0].equals("mark")) {
                    markTask(command);

                } else if (command[0].equals("unmark")) {
                    unmarkTask(command);

                } else if (command[0].equals("delete")) {
                    int taskIndex = Integer.parseInt(command[1]);
                    isValid(taskIndex);
                    Task taskToMark = tasks.get(taskIndex - 1);
                    tasks.remove(taskToMark);
                    System.out.println("Noted. I've removed this task:\n" + taskToMark);
                    System.out.printf("Now you have %d tasks in the list.\n", tasks.size());
                    System.out.println(line);

                } else if (command[0].equals("todo") || command[0].equals("deadline") || command[0].equals("event")) {
                    Task taskToAdd = null;

                    // Checks whether description is empty or contains only white spaces.
                    if (command.length == 1 || command[1].isBlank()) {
                        String message = String.format("OOPS!!! The description of %s cannot be empty.", command[0]);
                        throw new EmptyInputException(message);

                    } else if (command[0].equals("todo")) {
                        String description = command[1];
                        taskToAdd = new Todo(description);

                    } else if (command[0].equals("deadline")) {
                        String[] detailedCommand = command[1].split(" /by ", 2);
                        if (detailedCommand.length == 1) {
                            String message = "OOPS!!! Details missing! "
                                    + "A deadline must have both description and date/time.";
                            throw new MissingDetailException(message);
                        } else {
                            if (detailedCommand[0].isBlank() || detailedCommand[1].isBlank()) {
                                String message = "OOPS!!! I do not accept blank details. "
                                        + "A deadline must have both description and date/time.";
                                throw new MissingDetailException(message);
                            } else {
                                String description = detailedCommand[0];
                                String by = detailedCommand[1];
                                taskToAdd = new Deadline(description, by);
                            }
                        }

                    } else { // event
                        String[] detailedCommand = command[1].split(" /at ", 2);
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
                                taskToAdd = new Event(description, at);
                            }
                        }
                    }

                    tasks.add(taskToAdd);
                    System.out.println("Got it. I've added this task:");
                    System.out.println(taskToAdd);
                    System.out.printf("Now you have %d tasks in the list.\n", tasks.size());
                    System.out.println(line);

                } else {
                    // Invalid input.
                    System.out.println("OOPS!!! I'm sorry, but I don't know what that means :-(\n" + line);

                }
            } catch (CaCaException e) {
                System.out.println(e.getMessage());
                System.out.println("Please try again!");
                System.out.println(line);
            }
        }
    }
}
