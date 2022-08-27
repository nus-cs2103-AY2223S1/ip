import java.sql.SQLOutput;
import java.time.LocalDate;
import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    private static Scanner input = new Scanner(System.in);  // Create a Scanner object
    private static ArrayList<Task> list = new ArrayList<>(); // list of user input
    private static int size = 0;

    /**
     * Parses an input sentence into different words/arguments separated by space.
     *
     * @param input Input of the user to be parsed.
     * @return String array of words/arguments.
     */
    public static String[] parse(String input) {
        return input.split(" ");
    }

    /**
     * Outputs the goodbye messages.
     */
    public static void bye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Returns true if input is within scope of the program.
     *
     * @param in Input of the user.
     * @return True if input is within scope of the program, false otherwise.
     */
    public static boolean withinScope(String in) {
        return (in.startsWith("list") || in.startsWith("mark") || in.startsWith("unmark") || in.startsWith("deadline")
                || in.startsWith("event") || in.startsWith("todo") || in.startsWith("delete"));
    }

    /**
     * Lists the inputs of the user.
     */
    public static void list() {
        int count = 1;
        System.out.println("Here are the tasks in your list:");
        for (Task tasks : list) {
            System.out.println(count + ". " + tasks.toString());
            count += 1;
        }
    }

    /**
     * Deletes a task.
     *
     * @param in Input of the user.
     */
    public static void delete(String in) {
        char n = in.charAt(7);
        int number = Character.getNumericValue(n) - 1;
        System.out.println("Noted. I've removed this task:");
        System.out.println("  " + list.get(number).toString());
        list.remove(number);
        size -= 1;
        System.out.println("Now you have " + size + " tasks in the list");
    }

    /**
     * Unmarks a task.
     *
     * @param in Input of the user.
     */
    public static void unmark(String in) {
        char n = in.charAt(7);
        int number = Character.getNumericValue(n) - 1;
        Task t = list.get(number);
        t.markAsUndone();
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(t.toString());
    }

    /**
     * Marks a task.
     *
     * @param in Input of the user.
     */
    public static void mark(String in) {
        char n = in.charAt(5);
        int number = Character.getNumericValue(n) - 1;
        Task t = list.get(number);
        t.markAsDone();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(t.toString());
    }

    /**
     * Adds a deadline event to the list.
     *
     * @param in Input of the user.
     * @throws DukeException An exception unique to Duke.
     */
    public static void deadline(String in) throws DukeException {
        String deadLine = in.replaceFirst("deadline", "");
        if (deadLine.trim().isEmpty()) {
            throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
        } else {
            String[] aStr = deadLine.split("/by ", 2);
            String desc = aStr[0];
            String by = aStr[1];
            Deadline d = new Deadline(desc, LocalDate.parse(by));
            list.add(d);
            size += 1;
            System.out.println("Got it. I've added this task:");
            System.out.println("  " + d.toString());
            System.out.println("Now you have " + size + " tasks in the list");
        }
    }

    /**
     * Adds an event task to the list.
     *
     * @param in Input of the user.
     * @throws DukeException An exception unique to Duke.
     */
    public static void event(String in) throws DukeException {
        String event = in.replaceFirst("event", "");
        if (event.trim().isEmpty()) {
            throw new DukeException("☹ OOPS!!! The description of an event cannot be empty.");
        } else {
            String[] aStr = event.split("/at ", 2);
            String desc = aStr[0];
            String by = aStr[1];
            Event e = new Event(desc, LocalDate.parse(by));
            list.add(e);
            size += 1;
            System.out.println("Got it. I've added this task:");
            System.out.println("  " + e.toString());
            System.out.println("Now you have " + size + " tasks in the list");
        }
    }

    /**
     * Adds a to-do task to the list.
     *
     * @param in Input of the user.
     * @throws DukeException An exception unique to Duke.
     */
    public static void todo(String in) throws DukeException {
        String todo = in.replaceFirst("todo", "");
        if (todo.trim().isEmpty()) {
            throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
        } else {
            Todo t = new Todo(todo);
            list.add(t);
            size += 1;
            System.out.println("Got it. I've added this task:");  // Output user input
            System.out.println("  " + t.toString());
            System.out.println("Now you have " + size + " tasks in the list");
        }
    }



    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("What may I do for you?");

        while (input.hasNextLine()) {
            String in = input.nextLine();  // Read user input
            try {
                if (in.equals("bye")) {
                    bye();
                    break;
                } else if (!withinScope(in)) {
                    throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-( ");
                } else if (in.equals("list")) {
                    list();
                } else if (in.startsWith("delete")) {
                    delete(in);
                } else if (in.startsWith("unmark")) {
                    unmark(in);
                } else if (in.startsWith("mark")) {
                    mark(in);
                } else if (in.startsWith("deadline")) {
                    deadline(in);
                } else if (in.startsWith("event")) {
                    event(in);
                } else {
                    todo(in);
                }
            } catch (DukeException e) {
                System.out.println(e.toString());
            }
        }
    }
}

