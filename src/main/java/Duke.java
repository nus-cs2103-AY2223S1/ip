import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Duke {
    private Scanner sc = new Scanner(System.in);
    private List<Task> tasks = new ArrayList<>();

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.run();
    }

    private void run() {
        print("Hello! I'm Duke\nWhat can I do for you?");
        while (sc.hasNext()) {
            String input = sc.next();
            try {
                switch (input) {
                    case "list":
                        list();
                        break;
                    
                    case "todo":
                    case "deadline":
                    case "event":
                        add(input, sc.nextLine());
                        break;

                    case "mark":
                        mark(sc.nextLine());
                        break;

                    case "unmark":
                        unmark(sc.nextLine());
                        break;
                    
                    case "bye":
                        bye();
                        sc.close();
                        return;
                    
                    default:
                        sc.nextLine();
                        throw new DukeException("\u2639 OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
            } catch (DukeException e) {
                print(e.getMessage());
            }
        }
    }

    private void print(String s) {
        String seperator = "    ____________________________________________________________\n";
        System.out.println(seperator + Stream.of(s.split("\n")).map(x -> "     " + x + "\n").reduce("", (x,y) -> x + y) + seperator);
    }

    private void list() {
        print(IntStream.range(0, tasks.size()).mapToObj(x -> String.format("%d.%s\n", x + 1, tasks.get(x))).reduce("Here are the tasks in your list:\n", (x,y) -> x + y));
    }

    private void bye() {
        print("Bye. Hope to see you again soon!");
    }

    private String[] getTaskDetails(String rawDetails, String delimiter) throws DukeException {
        String[] details = rawDetails.split(delimiter);
        if (details.length != 2 || details[0].isEmpty() || details[1].isEmpty()) {
            throw new DukeException("\u2639 OOPS!!! Please make sure task description and dates are inputted properly!");
        }
        return details;
    }

    private void add(String taskType, String rawDetails) throws DukeException {
        Task task;
        if (rawDetails.isEmpty()) throw new DukeException(String.format("\u2639 OOPS!!! The description of a %s cannot be empty.", taskType));
        if (taskType.equals("deadline")) {
            String[] details = getTaskDetails(rawDetails, " /by ");
            task = new Deadline(details[0], details[1]);
        } else if (taskType.equals("event")) {
            String[] details = getTaskDetails(rawDetails, " /at ");
            task = new Event(details[0], details[1]);
        } else {
            task = new Task(rawDetails);
        }
        tasks.add(task);
        print(String.format("Got it. I've added this task:\n  %s\nNow you have %d tasks in the list.", tasks.get(tasks.size() - 1), tasks.size()));
    }

    private void mark(String input) throws DukeException {
        try {
            int index = Integer.parseInt(input);
            tasks.get(index - 1).markAsDone();
            print("Nice! I've marked this task as done:\n  " + tasks.get(index - 1));
        } catch (NumberFormatException e) {
            throw new DukeException(String.format("\u2639 OOPS!!! Invalid index %s. Index must be an integer and must not be blank.", input));
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException(String.format("\u2639 OOPS!!! Invalid index %s. You only have %d tasks in your list.", input, tasks.size()));
        }
    }

    private void unmark(String input) throws DukeException {
        try {
            int index = Integer.parseInt(input);
            tasks.get(index - 1).markAsDone();
            print("OK, I've marked this task as not done yet:\n  " + tasks.get(index - 1));
        } catch (NumberFormatException e) {
            throw new DukeException(String.format("\u2639 OOPS!!! Invalid index %s. Index must be an integer and must not be blank.", input));
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException(String.format("\u2639 OOPS!!! Invalid index %s. You only have %d tasks in your list.", input, tasks.size()));
        }
    }
}
