import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

/**
 * An interactive chatbot which can keep track of a list of tasks.
 *
 * @author Lai Han Wen
 */
public class Duke {

    /**
     * Arraylist which stores the current list of tasks.
     */
    private static ArrayList<Task> tasks = new ArrayList<>();

    /**
     * Marks a particular task as done.
     *
     * @param input String input from the user.
     */
    public static void isMark(String input) {
        // Isolate int from string input
        String numString = input.replace("mark", "")
                .replace(" ", "");
        int num = Integer.parseInt(numString);
        tasks.get(num - 1).markAsDone();
        System.out.println("Enter command:");
    }

    /**
     * Prints the current list of tasks.
     */
    public static void isList() {
        int count = 1;
        for (Task t : tasks) {
            String s = Integer.toString(count);
            System.out.println(s + ". " + t);
            count++;
        }
        System.out.println("Enter command:");
    }

    /**
     * Adds a deadline task to the current list of tasks.
     *
     * @param input String input from the user.
     * @throws DukeException if deadline task is empty.
     */
    public static void isDeadline(String input) throws DukeException {
        if (input.replace(" ", "").length() == 8) {
            throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
        }
        String description = input.substring(9, input.indexOf('/') - 1);
        String by = input.substring(input.indexOf('/') + 4);
        Task t = new Deadline(description, by);
        tasks.add(t);
        reply(t);
    }

    /**
     * Adds a todo task to the current list of tasks.
     *
     * @param input String input from the user.
     * @throws DukeException if todo task is empty.
     */
    public static void isTodo(String input) throws DukeException {
        if (input.replace(" ", "").length() == 4) {
            throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
        }
        String description = input.substring(5);
        Task t = new Todo(description);
        tasks.add(t);
        reply(t);
    }

    /**
     * Adds an event task to the current list of tasks.
     *
     * @param input String input from the user.
     * @throws DukeException if event task is empty.
     */
    public static void isEvent(String input) throws DukeException {
        if (input.replace(" ", "").length() == 5) {
            throw new DukeException("☹ OOPS!!! The description of an event cannot be empty.");
        }
        String description = input.substring(6, input.indexOf('/') - 1);
        String at = input.substring(input.indexOf('/') + 4);
        Task t = new Event(description, at);
        tasks.add(t);
        reply(t);
    }

    /**
     * Removes a particular task from the current list of tasks.
     *
     * @param input String input from the user.
     */
    public static void isDelete(String input) {
        String numString = input.replace("delete", "")
                .replace(" ", "");
        int num = Integer.parseInt(numString);
        System.out.println("Noted. I've removed this task:\n" + "  " + tasks.get(num - 1));
        tasks.remove(num - 1);
        String size = Integer.toString(tasks.size());
        System.out.println("Now you have " + size + " tasks in the list.");
        System.out.println("Enter command:");
    }

    /**
     * Prints reply after adding a task to the current list of tasks.
     *
     * @param t The task added to the current list of tasks.
     */
    public static void reply(Task t) {
        String size = Integer.toString(tasks.size());
        System.out.println("Got it. I've added this task:\n" + "  " + t);
        System.out.println("Now you have " + size + " tasks in the list.");
        System.out.println("Enter command:");
    }

    /**
     * Reads String input from user and decides what to do next. Possible actions
     * include adding a task, marking a task as done and deleting a task from the
     * current list of tasks.
     */
    public static void decision() {
        Scanner sc = new Scanner(System.in);

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

            if (command.contains("delete")) {
                isDelete(command);
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

            // Incorrect input: unknown input
            System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(" +
                    "\nEnter command:");
            command = sc.nextLine();
        }

        System.out.println("Bye. Hope to see you again soon!");
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

        // Prompt user input
        System.out.println("Enter command:");

        decision();
    }
}
