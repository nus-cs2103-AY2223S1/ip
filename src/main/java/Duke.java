import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    public static final String LONG_LINE = "    ____________________________________________________________\n";

    public static final String GREETING_MESSAGE = LONG_LINE +
            "     Hello! I'm Duke\n" +
            "     What can I do for you?\n" +
            LONG_LINE;
    public static final String GOODBYE_MESSAGE = LONG_LINE +
            "     Bye. Hope to see you again soon!\n" +
            LONG_LINE;

    private final List<Task> taskList = new ArrayList<>();

    public static void main(String[] args) {
        // Initialize Duke chatbot
        Duke chatBot = new Duke();

        // Greeting message is always printed
        System.out.println(GREETING_MESSAGE);

        Scanner in = new Scanner(System.in);
        String command = in.nextLine();

        // Continuously executes user's command until "bye" is entered
        while (!command.equals("bye")) {
            chatBot.executeCommand(command);
            command = in.nextLine();
        }
        in.close();

        // Goodbye message is always printed
        System.out.println(GOODBYE_MESSAGE);
    }

    public void executeCommand(String command) {
        // Used to generate the response message after executing commands
        StringBuilder sb = new StringBuilder();

        // Limit of 2 is used to avoid splitting the second argument in command
        String[] inputStrings = command.split(" ", 2);
        try {
            switch (inputStrings[0]) {
                case "list": {
                    // Return all tasks
                    sb.append(LONG_LINE).append("     Here are the tasks in your list:\n");
                    for (int i = 0; i < taskList.size(); i++) {
                        sb.append("     ").append(i + 1).append(".").append(taskList.get(i)).append("\n");
                    }
                    sb.append(LONG_LINE);
                    break;
                }
                case "mark": {
                    // Tasks are displayed as 1-indexed, but they are stored as 0-indexed
                    // Hence, we need to account for this offset here
                    int taskIndex = Integer.parseInt(inputStrings[1]) - 1;
                    Task task = this.taskList.get(taskIndex);
                    task.markTask();

                    sb.append(LONG_LINE)
                            .append("     Nice! I've marked this task as done:\n")
                            .append("       ").append(task).append("\n")
                            .append(LONG_LINE);
                    break;
                }
                case "unmark": {
                    // Tasks are displayed as 1-indexed, but they are stored as 0-indexed
                    // Hence, we need to account for this offset here
                    int taskIndex = Integer.parseInt(inputStrings[1]) - 1;
                    Task task = this.taskList.get(taskIndex);
                    task.unmarkTask();

                    sb.append(LONG_LINE)
                            .append("     OK, I've marked this task as not done yet:\n")
                            .append("       ").append(task).append("\n")
                            .append(LONG_LINE);
                    break;
                }
                case "todo": {
                    if (inputStrings.length == 1) {
                        throw new DukeException("     ☹ OOPS!!! The description of a todo cannot be empty.\n");
                    }
                    Todo todo = new Todo(inputStrings[1]);
                    this.taskList.add(todo);
                    sb.append(LONG_LINE)
                            .append("     Got it. I've added this task:\n")
                            .append("       ").append(todo).append("\n")
                            .append("     Now you have ").append(this.taskList.size()).append(" task(s) in the list.\n")
                            .append(LONG_LINE);
                    break;
                }
                case "deadline": {
                    if (inputStrings.length == 1) {
                        throw new DukeException("     ☹ OOPS!!! The description of a deadline cannot be empty.\n");
                    }
                    String[] deadlineStrings = inputStrings[1].split(" /by ", 2);
                    if (deadlineStrings.length == 1) {
                        throw new DukeException("     ☹ OOPS!!! The date/time of a deadline cannot be empty.\n");
                    }
                    Deadline deadline = new Deadline(deadlineStrings[0], deadlineStrings[1]);
                    this.taskList.add(deadline);
                    sb.append(LONG_LINE)
                            .append("     Got it. I've added this task:\n")
                            .append("       ").append(deadline).append("\n")
                            .append("     Now you have ").append(this.taskList.size()).append(" task(s) in the list.\n")
                            .append(LONG_LINE);
                    break;
                }
                case "event": {
                    if (inputStrings.length == 1) {
                        throw new DukeException("     ☹ OOPS!!! The description of an event cannot be empty.\n");
                    }
                    String[] eventStrings = inputStrings[1].split(" /at ", 2);
                    if (eventStrings.length == 1) {
                        throw new DukeException("     ☹ OOPS!!! The date/time of an event cannot be empty.\n");
                    }
                    Event event = new Event(eventStrings[0], eventStrings[1]);
                    this.taskList.add(event);
                    sb.append(LONG_LINE)
                            .append("     Got it. I've added this task:\n")
                            .append("       ").append(event).append("\n")
                            .append("     Now you have ").append(this.taskList.size()).append(" task(s) in the list.\n")
                            .append(LONG_LINE);
                    break;
                }
                default: {
                    throw new DukeException("     ☹ OOPS!!! I'm sorry, but I don't know what that means :-(\n");
                }
            }
        } catch (NumberFormatException | IndexOutOfBoundsException exception) {
            // Catches exceptions thrown when parsing integers in mark/unmark commands or
            // when accessing invalid taskList index
            sb.append(LONG_LINE).append("     ☹ OOPS!!! The index specified is invalid.\n").append(LONG_LINE);
        } catch (DukeException exception) {
            // All DukeExceptions will be printed
            sb.append(LONG_LINE).append(exception).append(LONG_LINE);
        }

        // Print out response after executing command
        System.out.println(sb);
    }
}
