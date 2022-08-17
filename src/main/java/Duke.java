import java.sql.SQLOutput;
import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) throws DukeException {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("What may I do for you?");

        ArrayList<Task> list = new ArrayList<>(); // list of user input
        int size = 0;
        while (true) {
            Scanner input = new Scanner(System.in);  // Create a Scanner object
            String in = input.nextLine();  // Read user input
            try {
                if (in.equals("bye")) {
                    System.out.println("Bye. Hope to see you again soon!"); // Output the goodbye messages
                    break;
                } else if (!(in.startsWith("list") || in.startsWith("mark") || in.startsWith("unmark") || in.startsWith("deadline")
                        || in.startsWith("event") || in.startsWith("todo") || in.startsWith("delete"))) {
                    throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-( ");
                } else if (in.equals("list")) {
                    int count = 1;
                    System.out.println("Here are the tasks in your list:");
                    for (Task tasks : list) {
                        System.out.println(count + ". " + tasks.toString());
                        count += 1;
                    }
                } else if (in.startsWith("delete")) {
                    char n = in.charAt(7);
                    int number = Character.getNumericValue(n) - 1;
                    System.out.println("Noted. I've removed this task:");
                    System.out.println("  " + list.get(number).toString());
                    list.remove(number);
                    size -= 1;
                    System.out.println("Now you have " + size + " tasks in the list");
                } else if (in.contains("unmark")) {
                    char n = in.charAt(7);
                    int number = Character.getNumericValue(n) - 1;
                    Task t = list.get(number);
                    t.markAsUndone();
                    System.out.println("OK, I've marked this task as not done yet:");
                    System.out.println(t.toString());
                } else if (in.contains("mark")) {
                    char n = in.charAt(5);
                    int number = Character.getNumericValue(n) - 1;
                    Task t = list.get(number);
                    t.markAsDone();
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println(t.toString());
                } else if (in.contains("deadline")) {
                    String deadLine = in.replaceFirst("deadline", "");
                    if (deadLine.trim().isEmpty()) {
                        throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
                    } else {
                        String[] aStr = deadLine.split("/by", 2);
                        String desc = aStr[0];
                        String by = aStr[1];
                        Deadline d = new Deadline(desc, by);
                        list.add(d);
                        size += 1;
                        System.out.println("Got it. I've added this task:");
                        System.out.println("  " + d.toString());
                        System.out.println("Now you have " + size + " tasks in the list");
                    }
                } else if (in.contains("event")) {
                    String event = in.replaceFirst("event", "");
                    if (event.trim().isEmpty()) {
                        throw new DukeException("☹ OOPS!!! The description of an event cannot be empty.");
                    } else {
                        String[] aStr = event.split("/at", 2);
                        String desc = aStr[0];
                        String by = aStr[1];
                        Event e = new Event(desc, by);
                        list.add(e);
                        size += 1;
                        System.out.println("Got it. I've added this task:");
                        System.out.println("  " + e.toString());
                        System.out.println("Now you have " + size + " tasks in the list");
                    }
                } else {
                    String todo = in.replaceFirst("todo", "");
                    if (todo.trim().isEmpty()) {
                        throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
                    } else {
                        Todo t = new Todo(todo);
                        list.add(t);
                        size += 1;
                        System.out.println("Got it. I've added this task: ");  // Output user input
                        System.out.println("  " + t.toString());
                        System.out.println("Now you have " + size + " tasks in the list");
                    }
                }
            } catch (DukeException e) {
                System.out.println(e.toString());
            }
        }
    }
}

