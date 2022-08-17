import java.util.ArrayList;
import java.util.Scanner;

/**
 * MakiBot
 */
public class Duke {
    protected ArrayList<Task> taskList = new ArrayList<Task>();

    public static void main(String[] args) {
        System.out.println("Hello from\n" + "MAKIBOT");
        new Duke().start();
    }

    protected void printNewTaskMessage(Task t) {
        System.out.println(String.format("Got it. I've added this task:\n" +
                        "\t%s\n" +
                        "Now you have %d tasks in the list.",
                t, taskList.size())
        );
    }

    /**
     * Start a conversation with MakiBot
     */
    public void start() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Hello! I'm Makibot\n" +
                "What can I do for you?");

        // Event loop
        while (true) {
            String input = sc.nextLine();
            // Handle special commands
            if (input.equals("bye")) {
                // Exit command
                System.out.println("Bye. Hope to see you again soon!");
                return;
            } else if (input.equals("list")) {
                // List all tasks
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < taskList.size(); i++) {
                    System.out.println(i + 1 + ". " + taskList.get(i));
                }
            } else if (input.startsWith("mark ")) {
                // Mark task as done
                try {
                    int taskNum = Integer.parseInt(input.substring(5)) - 1;
                    Task t = taskList.get(taskNum);
                    t.markAsDone();
                    System.out.println("Nice! I've marked this tasks as done:\n" + t);
                } catch (NumberFormatException e) {
                    System.out.println(e);
                }
            } else if (input.startsWith("unmark ")) {
                // Mark task as undone
                try {
                    int taskNum = Integer.parseInt(input.substring(7)) - 1;
                    Task t = taskList.get(taskNum);
                    t.markAsUndone();
                    System.out.println("OK, I've marked this task as not done yet:\n" + t);
                } catch (NumberFormatException e) {
                    System.out.println(e);
                }
            } else if (input.startsWith("todo ")) {
                // Create new Todo
                Todo td = new Todo(input.substring(5));
                taskList.add(td);
                printNewTaskMessage(td);
            } else if (input.startsWith("deadline ")) {
                // Create new Deadline
                String[] newDeadline = input.substring(9).split(" /by ", 2);
                Deadline dl = new Deadline(newDeadline[0], newDeadline[1]);
                taskList.add(dl);
                printNewTaskMessage(dl);
            } else if (input.startsWith("event ")) {
                // Create new Event
                String[] newEvent = input.substring(6).split(" /at ", 2);
                Event ev = new Event(newEvent[0], newEvent[1]);
                taskList.add(ev);
                printNewTaskMessage(ev);
            } else {
                // Create new Task
                Task newTask = new Task(input);
                taskList.add(newTask);
                System.out.println("added: " + input);
            }
        }
    }
}
