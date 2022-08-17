import java.util.Scanner;
import java.util.function.Supplier;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Emily Ong Hui Qi
 */
public class Duke {
    // Name of the chatbot
    private static final String NAME = "Duke";

    // The greeting message used by the chatbot when the program starts
    private static final String GREETING_MESSAGE = String.format("Hello! I'm %s\nWhat can I do for you?", Duke.NAME);
    // The goodbye message used by the chatbot when the program terminates
    private static final String GOODBYE_MESSAGE = "Bye. Hope to see you again soon!";
    private static final String ADD_TASK_MESSAGE = "Got it. I've added this task:";
    private static final String MARK_TASK_AS_DONE_MESSAGE = "Nice! I've marked this task as done:";
    private static final String MARK_TASK_AS_UNDONE_MESSAGE = "OK, I've marked this task as not done yet:";
    private static final String TASK_LIST_STATUS_MESSAGE = "Now you have %s task(s) in the list.";

    private static final String UNKNOWN_COMMAND_ERROR = "Hmm...I do not understand your command!";
    private static final String MISSING_TASK_INDEX_ERROR = "Oops! You are missing a task number!\n" +
            "Use the 'list' command to view the tasks and their number.";
    private static final String NAN_TASK_INDEX_ERROR = "Oops! The task number you provided is not a number!";
    private static final String TASK_INDEX_IS_INVALID_ERROR = "Oops! The task number you provided is not valid!\n" +
            "Use the 'list' command to view the tasks and their number.";
    private static final String INVALID_TODO_TASK_ERROR = "Oops! Use the 'todo' command together with a task " +
            "description!\nFor example: 'todo borrow book'";
    private static final String INVALID_DEADLINE_TASK_ERROR = "Oops! Use the 'deadline' command together with the " +
            "task description and deadline\nFor example: 'deadline return book /by Sunday'";
    private static final String INVALID_EVENT_TASK_ERROR = "Oops! Use the 'event' command together with the " +
            "task description and date time\nFor example: 'event project meeting /at Mon 2-4pm'";

    private static final String LIST_COMMAND = "list";
    private static final String MARK_COMMAND = "mark";
    private static final String UNMARK_COMMAND = "unmark";
    private static final String TODO_COMMAND = "todo";
    private static final String DEADLINE_COMMAND = "deadline";
    private static final String EVENT_COMMAND = "event";
    private static final String BYE_COMMAND = "bye";

    // Regex patterns for matching input commands

    // Matches a non-empty description, for example: <description>
    private static final Pattern MATCH_TODO_TASK = Pattern.compile("(.+)");
    // Matches a non-empty description and a non-empty deadline, separated by a '/by' command
    // For example: (<description> /by <deadline>)
    private static final Pattern MATCH_DEADLINE_TASK = Pattern.compile("(.+?)\\s/by\\s(.+)");
    // Matches a non-empty description and a non-empty datetime, separated by a '/at' command
    // For example: <description> /at <datetime>
    private static final Pattern MATCH_EVENT_TASK = Pattern.compile("(.+?)\\s/at\\s(.+)");

    private static Task getTask(TaskManager taskManager, int taskNumber) {
        Task task;
        try {
            task = taskManager.get(taskNumber);
            return task;
        } catch (IndexOutOfBoundsException e) {
            return null;
        }
    }

    /**
     * Retrieve the task supplied by the caller and adds that task to the specified task manager.
     *
     * @param taskManager Task manager in charge of the list of tasks
     * @param taskSupplier Supplier function that returns a task
     */
    private static void addTask(TaskManager taskManager, Supplier<? extends Task> taskSupplier) {
        Task task = taskSupplier.get();
        taskManager.add(task);
        DukePrinter.print(
                String.format(
                        "%s\n\t%s\n%s",
                        Duke.ADD_TASK_MESSAGE,
                        task,
                        String.format(Duke.TASK_LIST_STATUS_MESSAGE, taskManager.count())
                )
        );
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TaskManager taskManager = new TaskManager();

        // Greet the user
        DukePrinter.print(Duke.GREETING_MESSAGE);

        while (scanner.hasNextLine()) {
            // Receive the command entered by the user
            String inputLine = scanner.nextLine();
            String[] input = inputLine.split(" ");
            String command = input[0];
            String arguments = inputLine.replaceFirst(command, "").strip();

            if (command.equals(BYE_COMMAND)) {
                break;
            }

            switch (command) {
                case LIST_COMMAND: {
                    DukePrinter.print(taskManager.toString());
                    break;
                }
                case MARK_COMMAND: {
                    // Retrieve the task index (1-indexed) to mark the task as done
                    if (arguments.length() == 0) {
                        DukePrinter.print(Duke.MISSING_TASK_INDEX_ERROR);
                        continue;
                    }
                    int taskNumber;
                    try {
                        taskNumber = Integer.parseInt(arguments);
                    } catch (NumberFormatException e) {
                        DukePrinter.print(Duke.NAN_TASK_INDEX_ERROR);
                        continue;
                    }
                    Task task = Duke.getTask(taskManager, taskNumber);
                    if (task != null) {
                        task.markAsDone();
                        DukePrinter.print(String.format("%s\n\t%s", Duke.MARK_TASK_AS_DONE_MESSAGE, task));
                    } else {
                        DukePrinter.print(Duke.TASK_INDEX_IS_INVALID_ERROR);
                    }
                    break;
                }
                case UNMARK_COMMAND: {
                    // Retrieve the task index (1-indexed) to mark the task as undone
                    if (arguments.length() == 0) {
                        DukePrinter.print(Duke.MISSING_TASK_INDEX_ERROR);
                        continue;
                    }
                    int taskNumber;
                    try {
                        taskNumber = Integer.parseInt(arguments);
                    } catch (NumberFormatException e) {
                        DukePrinter.print(Duke.NAN_TASK_INDEX_ERROR);
                        continue;
                    }
                    Task task = Duke.getTask(taskManager, taskNumber);
                    if (task != null) {
                        task.markAsUndone();
                        DukePrinter.print(String.format("%s\n\t%s", Duke.MARK_TASK_AS_UNDONE_MESSAGE, task));
                    } else {
                        DukePrinter.print(Duke.TASK_INDEX_IS_INVALID_ERROR);
                    }
                    break;
                }
                case TODO_COMMAND: {
                    Matcher matcher = Duke.MATCH_TODO_TASK.matcher(arguments);
                    if (!matcher.find()) {
                        DukePrinter.print(Duke.INVALID_TODO_TASK_ERROR);
                        continue;
                    }
                    Duke.addTask(taskManager, () -> new ToDo(arguments.strip()));
                    break;
                }
                case DEADLINE_COMMAND: {
                    Matcher matcher = Duke.MATCH_DEADLINE_TASK.matcher(arguments);
                    if (!matcher.find()) {
                        DukePrinter.print(Duke.INVALID_DEADLINE_TASK_ERROR);
                        continue;
                    }

                    Duke.addTask(taskManager, () -> new Deadline(matcher.group(1).strip(), matcher.group(2).strip()));
                    break;
                }
                case EVENT_COMMAND: {
                    Matcher matcher = Duke.MATCH_EVENT_TASK.matcher(arguments);
                    if (!matcher.find()) {
                        DukePrinter.print(Duke.INVALID_EVENT_TASK_ERROR);
                        continue;
                    }

                    Duke.addTask(taskManager, () -> new Deadline(matcher.group(1).strip(), matcher.group(2).strip()));
                    break;
                }
                default: {
                    DukePrinter.print(Duke.UNKNOWN_COMMAND_ERROR);
                    break;
                }
            }
        }

        scanner.close();

        // Bid the user goodbye
        DukePrinter.print(Duke.GOODBYE_MESSAGE);
    }
}
