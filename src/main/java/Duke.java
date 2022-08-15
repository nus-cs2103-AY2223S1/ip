import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    public static final String LONG_LINE = "    ____________________________________________________________";
    public static final String LONG_LINE_WITH_NEW_LINE = LONG_LINE + "\n";

    public static final String GREETING_MESSAGE = LONG_LINE_WITH_NEW_LINE +
            "     Hello! I'm Duke\n" +
            "     What can I do for you?\n" +
            LONG_LINE_WITH_NEW_LINE;
    public static final String GOODBYE_MESSAGE = LONG_LINE_WITH_NEW_LINE +
            "     Bye. Hope to see you again soon!\n" +
            LONG_LINE_WITH_NEW_LINE;

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
        StringBuilder sb = new StringBuilder();
        if (command.equals("list")) {
            // Return all tasks
            sb.append(LONG_LINE_WITH_NEW_LINE).append("     Here are the tasks in your list:\n");
            for (int i = 0; i < taskList.size(); i++) {
                sb.append("     ").append(i + 1).append(".").append(taskList.get(i)).append("\n");
            }
            sb.append(LONG_LINE);
        } else if (command.startsWith("mark")) {
            // Tasks are displayed as 1-indexed, but they are stored as 0-indexed
            // Hence, we need to account for this offset here
            int taskIndex = Integer.parseInt(command.replaceFirst("mark ", "")) - 1;
            Task task = this.taskList.get(taskIndex);
            task.markTask();

            sb.append(LONG_LINE_WITH_NEW_LINE)
                    .append("     Nice! I've marked this task as done:\n")
                    .append("       ").append(task).append("\n")
                    .append(LONG_LINE_WITH_NEW_LINE);
        } else if (command.startsWith("unmark")) {
            // Tasks are displayed as 1-indexed, but they are stored as 0-indexed
            // Hence, we need to account for this offset here
            int taskIndex = Integer.parseInt(command.replaceFirst("unmark ", "")) - 1;
            Task task = this.taskList.get(taskIndex);
            task.unmarkTask();

            sb.append(LONG_LINE_WITH_NEW_LINE)
                    .append("     OK, I've marked this task as not done yet:\n")
                    .append("       ").append(task).append("\n")
                    .append(LONG_LINE_WITH_NEW_LINE);
        } else {
            // Otherwise, add command to task list
            this.taskList.add(new Task(command));
            sb.append(LONG_LINE_WITH_NEW_LINE)
                    .append("     added: ")
                    .append(command).append("\n")
                    .append(LONG_LINE_WITH_NEW_LINE);
        }

        // Print out response after executing command
        System.out.println(sb);
    }
}
