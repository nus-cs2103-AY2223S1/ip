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

        // Goodbye message is always printed
        System.out.println(GOODBYE_MESSAGE);
    }

    public void executeCommand(String command) {
        // Used to generate the response message after executing commands
        StringBuilder sb = new StringBuilder();

        // Limit of 2 is used to avoid
        String[] inputStrings = command.split(" ", 2);
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
                Todo todo = new Todo(inputStrings[1]);
                this.taskList.add(todo);
                sb.append(LONG_LINE)
                        .append("     Got it. I've added this task:\n")
                        .append("       ").append(todo).append("\n")
                        .append("     Now you have ").append(this.taskList.size()).append(" tasks in the list.\n")
                        .append(LONG_LINE);
                break;
            }
            case "deadline": {
                String[] deadlineStrings = inputStrings[1].split(" /by ", 2);
                Deadline deadline = new Deadline(deadlineStrings[0], deadlineStrings[1]);
                this.taskList.add(deadline);
                sb.append(LONG_LINE)
                        .append("     Got it. I've added this task:\n")
                        .append("       ").append(deadline).append("\n")
                        .append("     Now you have ").append(this.taskList.size()).append(" tasks in the list.\n")
                        .append(LONG_LINE);
                break;
            }
            case "event": {
                String[] eventStrings = inputStrings[1].split(" /at ", 2);
                Event event = new Event(eventStrings[0], eventStrings[1]);
                this.taskList.add(event);
                sb.append(LONG_LINE)
                        .append("     Got it. I've added this task:\n")
                        .append("       ").append(event).append("\n")
                        .append("     Now you have ").append(this.taskList.size()).append(" tasks in the list.\n")
                        .append(LONG_LINE);
                break;
            }
            default: {
                // Otherwise, add command to task list
                this.taskList.add(new Task(command));
                sb.append(LONG_LINE)
                        .append("     added: ")
                        .append(command).append("\n")
                        .append(LONG_LINE);
                break;
            }
        }

        // Print out response after executing command
        System.out.println(sb);
    }
}
