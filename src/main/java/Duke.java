import java.util.Scanner;

public class Duke {
    /** List of items stored by Duke. */
    private static final Task[] tasks = new Task[100];

    private static void greetUser() {
        String message = Duke.formatText("Hello! I'm Duke\n" + "What can I do for you?");
        System.out.println(message);
    }

    private static void startService() {
        Scanner sc = new Scanner(System.in);

        while (true) {
            String userInput = sc.nextLine();

            // End service.
            if (userInput.equals("bye")) {
                System.out.println(Duke.formatText("Bye. Hope to see you again soon!"));
                break;
            }

            // List items in list.
            if (userInput.equals("list")) {
                StringBuilder string = new StringBuilder("");
                for (int i = 0; i < Task.totalTasks; i++) {
                    int itemIndex = i + 1;

                    string.append(itemIndex).append(".").append(Duke.tasks[i]).append("\n");
                }

                System.out.println(Duke.formatText("Here are the tasks in your list\n" + string.toString()));
                continue;
            }

            // Mark item as done or undone.
            if (userInput.contains("mark")) {
                String action = userInput.split(" ")[0];
                int index = Integer.parseInt(userInput.split(" ")[1]);

                if (action.equals("mark")) {
                    Duke.tasks[index - 1].markAsDone();
                    System.out.println(Duke.formatText("Nice! I've marked this task as done:\n" + Duke.tasks[index - 1]));
                    continue;
                }

                if (action.equals("unmark")) {
                    Duke.tasks[index - 1].unmark();
                    System.out.println(Duke.formatText("OK, I've marked this task as not done yet:\n" + Duke.tasks[index - 1]));

                    continue;
                }
            }

            // Add item to list.
            if (userInput.contains("todo")) {
                Duke.tasks[Task.totalTasks++] = new ToDo(userInput.substring(5));
            } else if (userInput.contains("deadline")) {
                String details = userInput.substring(9);
                String taskName = details.split(" /by")[0];
                String date = details.split(" /by")[1];
                Duke.tasks[Task.totalTasks++] = new Deadline(taskName, date);
            } else if (userInput.contains("event")) {
                String details = userInput.substring(6);
                String taskName = details.split(" /at")[0];
                String date = details.split(" /at")[1];
                Duke.tasks[Task.totalTasks++] = new Event(taskName, date);
            }

            System.out.println(Duke.formatText("Got it. I've added this task:\n" + "  " +
                    Duke.tasks[Task.totalTasks - 1] + "\n" +
                    "Now you have " + Task.totalTasks + " tasks in the list."));
        }
    }

    /**
     * Styles a given text with indentation and wraps the text around horizontal lines.
     *
     * @param text String that needs to be styled.
     * @return Formatted String that has proper indentation and wrapped around horizontal lines.
     */
    private static String formatText(String text) {
        final String HORIZONTAL_LINE = "\t--------------------------------------------------\n";

        String[] lines = text.split("\\r?\\n");
        StringBuilder formattedText = new StringBuilder(HORIZONTAL_LINE);
        for (String line : lines) {
            formattedText.append("\t").append(line).append("\n");
        }

        return formattedText + HORIZONTAL_LINE;
    }

    public static void main(String[] args) {
        Duke.greetUser();
        Duke.startService();
    }
}
