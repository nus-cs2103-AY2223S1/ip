import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Jude is a task tracker which appears like a command line interface.
 * When > shows up, you can type a command.
 *
 * Here are the list of commands:
 * list - lists all tasks
 * mark - mark the task with a specified index (from list command) as done
 *   e.g. mark 2 marks second task as done
 * unmark - mark the task with a specified index (from list command) as undone,
 *   e.g. unmark 2 marks second task as undone
 * bye - exits the program
 *
 * If the command does not have these prefixes, a new task with a description corresponding to the
 * name of the command is added.
 */
public class Jude {
    private static List<Task> tasks = new ArrayList<>();

    /**
     * Runs the task tracker.
     *
     * @param args not used for now
     */
    public static void main(String[] args) {
        System.out.println("Hello! I'm Jude.");
        System.out.println("What can I do for you?");

        // Solution below adapted from
        // https://stackoverflow.com/questions/5287538/how-to-get-the-user-input-in-java
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println();
            System.out.print("> ");
            String str = sc.nextLine();
            String[] tokens = str.split(" ");
            if (tokens[0].equals("mark")) {
                int index = Integer.parseInt(tokens[1])-1;
                Task task = tasks.get(index);

                //Solution below adapted from
                //https://nus-cs2103-ay2223s1.github.io/website/schedule/week2/project.html
                task.markAsDone();

                System.out.println("The following task has been marked as done");
                System.out.printf("[%s] %s\n", task.getStatusIcon(), task.getDescription());
            } else if (tokens[0].equals("unmark")) {
                int index = Integer.parseInt(tokens[1])-1;
                Task task = tasks.get(index);
                task.markAsUndone();
                System.out.println("The following task has been marked as undone");
                System.out.printf("[%s] %s\n", task.getStatusIcon(), task.getDescription());
            } else if (str.equals("list")) {
                for (int i = 0; i < tasks.size(); i++) {
                    Task task = tasks.get(i);
                    String description = task.getDescription();
                    String statusIcon = task.getStatusIcon();
                    System.out.printf("%d.[%s] %s\n", i+1, statusIcon, description);
                }
            } else if (str.equals("bye")) {
                System.out.println("Goodbye! Have a nice day!");
                break;
            } else {
                System.out.println("Sorry, I don't understand what this means!");
            }
        }
    }
}
