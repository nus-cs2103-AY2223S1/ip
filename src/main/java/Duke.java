import java.util.Objects;
import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

    // Create static arraylist to store given tasks
    private static ArrayList<Task> tasks = new ArrayList<>();

    public static void isMark(String input) {
        // Isolate int from string input
        String numString = input.replace("mark", "")
                .replace(" ", "");
        int num = Integer.parseInt(numString);
        tasks.get(num - 1).markAsDone();
        System.out.println("Enter command:");
    }

    public static void isList() {
        int count = 1;
        for (Task t : tasks) {
            String s = Integer.toString(count);
            System.out.println(s + ". " + t);
            count++;
        }
        System.out.println("Enter command:");
    }

    public static void isDeadline(String input) throws DukeException {
        // Error due to incorrect input: empty deadline
        if (input.replace(" ", "").length() == 8) {
            throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
        }
        String description = input.substring(9, input.indexOf('/') - 1);
        String by = input.substring(input.indexOf('/') + 4);
        Task t = new Deadline(description, by);
        tasks.add(t);
        reply(t);
    }

    public static void isTodo(String input) throws DukeException {
        // Error due to incorrect input: empty todo
        if (input.replace(" ", "").length() == 4) {
            throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
        }
        String description = input.substring(5);
        Task t = new Todo(description);
        tasks.add(t);
        reply(t);
    }

    public static void isEvent(String input) throws DukeException {
        // Error due to incorrect input: empty event
        if (input.replace(" ", "").length() == 5) {
            throw new DukeException("☹ OOPS!!! The description of an event cannot be empty.");
        }
        String description = input.substring(6, input.indexOf('/') - 1);
        String at = input.substring(input.indexOf('/') + 4);
        Task t = new Event(description, at);
        tasks.add(t);
        reply(t);
    }

    public static void reply(Task t) {
        String size = Integer.toString(tasks.size());
        System.out.println("Got it. I've added this task:\n" + "  " + t);
        System.out.println("Now you have " + size + " tasks in the list.");
        System.out.println("Enter command:");
    }

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

        // Prompt user input
        System.out.println("Enter command:");

        // Read string input from user
        String command = sc.nextLine();

        while (!Objects.equals(command, "bye")) {

            if (command.contains("deadline")) {
                try {
                    isDeadline(command);
                } catch (DukeException e) {
                    System.out.println(e);
                }
                command = sc.nextLine();
                continue;
            }

            if (command.contains("todo")) {
                try {
                    isTodo(command);
                } catch (DukeException e) {
                    System.out.println(e);
                }
                command = sc.nextLine();
                continue;
            }

            if (command.contains("event")) {
                try {
                    isEvent(command);
                } catch (DukeException e) {
                    System.out.println(e);
                }
                command = sc.nextLine();
                continue;
            }

            if (command.contains("mark")) {
                isMark(command);
                command = sc.nextLine();
                continue;
            }

            if (Objects.equals(command, "list")) {
                isList();
                command = sc.nextLine();
                continue;
            }

            System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(" +
                    "\nEnter command:");
            command = sc.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
