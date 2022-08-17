import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

/**
 * CaCa is a personal assistant chatbot that helps users manage and track your things.
 */
public class CaCa {

    /**
     * A class-level array to store all user inputs.
     */
    private static List<Task> tasks = new ArrayList<>();

    /**
     * The program greets user, reads and stores user input,
     * allows user to update task status as done or undone, displays all tasks
     * with status when user inputs list and exits when user inputs bye.
     * @param args Command line arguments
     */
    public static void main(String[] args) {
        String line = "____________________________________________________________\n";

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

        // Solution below on getting user input is
        // adapted from https://www.w3schools.com/java/java_user_input.asp
        Scanner sc = new Scanner(System.in); // Creates a Scanner Object.

        while (true) {
            // Reads user input.
            String input = sc.nextLine();
            // Detect user command to mark task as done or undone.
            String[] markTask = input.split(" ");

            System.out.print(line);

            if (markTask[0].equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!\n" + line);
                break;

            } else if (markTask[0].equals("list")) {
                System.out.println("Here are the tasks in your list:");

                for (int i = 0; i < tasks.size(); i++) {
                    Task task = tasks.get(i);
                    String statusIcon = task.getStatusIcon();
                    String description = task.getDescription();
                    System.out.printf("%d.[%s] %s%n", i + 1, statusIcon, description);

                    if (i == tasks.size() - 1) {
                        System.out.print(line);
                    }
                }

            } else if (markTask[0].equals("mark")) {
                // taskIndex entered by user is 1 larger than its array index.
                int taskIndex = Integer.parseInt(markTask[1]);
                Task taskToMark = tasks.get(taskIndex - 1);
                taskToMark.setIsDone(true);

                String statusIcon = taskToMark.getStatusIcon();
                String description = taskToMark.getDescription();

                System.out.println("Nice! I've marked this task as done:");
                System.out.printf("[%s] %s%n %s", statusIcon, description, line);

            } else if (markTask[0].equals("unmark")) {
                int taskIndex = Integer.parseInt(markTask[1]);
                Task taskToMark = tasks.get(taskIndex - 1);
                taskToMark.setIsDone(false);

                String statusIcon = taskToMark.getStatusIcon();
                String description = taskToMark.getDescription();

                System.out.println("OK, I've marked this task as not done yet:");
                System.out.printf("[%s] %s%n %s", statusIcon, description, line);

            } else {
                Task task = new Task(input);
                tasks.add(task);
                System.out.println("added: " + input + "\n" + line);
            }
        }
    }
}
