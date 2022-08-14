import java.util.Objects;
import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duke\n" +
                "What can I do for you?");
        Scanner sc = new Scanner(System.in);

        // Create arraylist to store given tasks
        ArrayList<Task> tasks = new ArrayList<Task>();

        // Prompt user input
        System.out.println("Enter command:");

        // Read string input from user
        String command = sc.nextLine();

        while (!Objects.equals(command, "bye")) {

            if (command.contains("deadline")) {
                String description = command.substring(9, command.indexOf('/') - 1);
                String by = command.substring(command.indexOf('/') + 4);
                Task t = new Deadline(description, by);
                tasks.add(t);
                String size = Integer.toString(tasks.size());
                System.out.println("Got it. I've added this task:\n" + "  " + t);
                System.out.println("Now you have " + size + " tasks in the list.");
                System.out.println("Enter command:");
                command = sc.nextLine();
                continue;
            }

            if (command.contains("todo")) {
                String description = command.substring(5);
                Task t = new Todo(description);
                tasks.add(t);
                String size = Integer.toString(tasks.size());
                System.out.println("Got it. I've added this task:\n" + "  " + t);
                System.out.println("Now you have " + size + " tasks in the list.");
                System.out.println("Enter command:");
                command = sc.nextLine();
                continue;
            }

            if (command.contains("event")) {
                String description = command.substring(6, command.indexOf('/') - 1);
                String at = command.substring(command.indexOf('/') + 4);
                Task t = new Event(description, at);
                tasks.add(t);
                String size = Integer.toString(tasks.size());
                System.out.println("Got it. I've added this task:\n" + "  " + t);
                System.out.println("Now you have " + size + " tasks in the list.");
                System.out.println("Enter command:");
                command = sc.nextLine();
                continue;
            }

            if (command.contains("mark")) {
                // Isolate int from string input
                String numString = command.replace("mark", "")
                        .replace(" ", "");
                int num = Integer.parseInt(numString);
                tasks.get(num - 1).markAsDone();
                System.out.println("Enter command:");
                command = sc.nextLine();
                continue;
            }

            if (Objects.equals(command, "list")) {
                int count = 1;
                for (Task t : tasks) {
                    String s = Integer.toString(count);
                    System.out.println(s + ". " + t);
                    count++;
                }
                System.out.println("Enter command:");
                command = sc.nextLine();
            }

        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
