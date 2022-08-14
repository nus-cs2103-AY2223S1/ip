import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Jude is a task tracker which appears like a command line interface.
 * When > shows up, you can type a command.
 *
 * There are three types of tasks, namely the todo, deadline and event.
 * Todo tasks are tasks without an associated date/time.
 * Deadline tasks have a specific deadline by which the task must be completed.
 * Event tasks have a start time and an end time.
 *
 * Here are the list of commands:
 * todo (description) - adds a todo task with some description
 * deadline (description) /by (deadline) - adds a deadline task with the specified description and
 *   deadline
 * event (description) /at (daterange) - adds an event task with start time and end time as part of
 *   daterange parameter
 * list - lists all tasks
 * mark - mark the task with a specified index (from list command) as done
 *   e.g. mark 2 marks second task as done
 * unmark - mark the task with a specified index (from list command) as undone,
 *   e.g. unmark 2 marks second task as undone
 * bye - exits the program
 *
 * If the command does not have these prefixes, an error will be thrown saying that the bot does
 * not understand.
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
            String[] tokens = str.split(" ", 2);
            if (tokens[0].equals("todo") || tokens[0].equals("deadline") || tokens[0].equals("event")) {
                Task taskAdded = null;
                if (tokens[0].equals("todo")) {
                    String description = tokens[1];
                    taskAdded = new Todo(description, false);
                } else if (tokens[0].equals("deadline")) {
                    String remText = tokens[1];
                    String[] remTextTokens = remText.split(" /by ", 2);
                    String description = remTextTokens[0];
                    String deadline = remTextTokens[1];
                    taskAdded = new Deadline(description, false, deadline);
                } else if (tokens[0].equals("event")) {
                    String remText = tokens[1];
                    String[] remTextTokens = remText.split(" /at ", 2);
                    String description = remTextTokens[0];
                    String when = remTextTokens[1];
                    taskAdded = new Event(description, false, when);
                }

                tasks.add(taskAdded);
                System.out.printf("The following %s task has been added:\n  ", tokens[0]);
                System.out.println(taskAdded);
                System.out.printf("The task list now contains %d task(s).\n", tasks.size());
            } else if (tokens[0].equals("mark")) {
                int index = Integer.parseInt(tokens[1])-1;
                Task task = tasks.get(index);

                // Solution below adapted from
                // https://nus-cs2103-ay2223s1.github.io/website/schedule/week2/project.html
                task.markAsDone();

                System.out.println("The following task has been marked as done");
                System.out.println(task);
            } else if (tokens[0].equals("unmark")) {
                int index = Integer.parseInt(tokens[1])-1;
                Task task = tasks.get(index);
                task.markAsUndone();
                System.out.println("The following task has been marked as undone");
                System.out.println(task);
            } else if (str.equals("list")) {
                for (int i = 0; i < tasks.size(); i++) {
                    Task task = tasks.get(i);
                    System.out.printf("%d.%s\n", i+1, task);
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
