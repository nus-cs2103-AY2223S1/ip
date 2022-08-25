import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Collections;
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
                    String date = sc.nextLine().trim();
                    if (date.isEmpty()) {
                        list();
                    } else {
                        list(date);
                    }
                    break;
                
                case "todo":
                case "deadline":
                case "event":
                    add(input, sc.nextLine().trim());
                    break;
                
                case "sort":
                    sort();
                    break;
                
                case "format":
                    format(sc.nextLine().trim());
                    break;

                case "mark":
                    mark(sc.nextLine().trim());
                    break;

                case "unmark":
                    unmark(sc.nextLine().trim());
                    break;
                
                case "delete":
                    delete(sc.nextLine().trim());
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

    private void list(String date) throws DukeException {
        LocalDate convertedDate;
        try {
            convertedDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        } catch (DateTimeParseException e) {
            throw new DukeException("Wrong date format. Please input date in the format dd/MM/yyyy.");
        }
        String content = IntStream.range(0, tasks.size())
            .filter(x -> tasks.get(x) instanceof TimedTask && ((TimedTask) tasks.get(x)).time.toLocalDate().equals(convertedDate))
            .mapToObj(x -> String.format("%d.%s\n", x + 1, tasks.get(x)))
            .reduce("", (x,y) -> x + y);
        print(String.format("Here are the tasks in your list at %s:\n%s", date, content));
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
            task = new Todo(rawDetails);
        }
        tasks.add(task);
        print(String.format("Got it. I've added this task:\n  %s\nNow you have %d tasks in the list.", tasks.get(tasks.size() - 1), tasks.size()));
    }

    private void sort() {
        Collections.sort(tasks);
        list();
    }

    private void format(String format) throws DukeException {
        TimedTask.setFormat(format);
        list();
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

    private void delete(String input) throws DukeException {
        try {
            int index = Integer.parseInt(input);
            Task removedTask = tasks.remove(index - 1);
            print(String.format("Noted. I've removed this task:\n  %s\nNow you have %d tasks in the list.", removedTask, tasks.size()));
        } catch (NumberFormatException e) {
            throw new DukeException(String.format("\u2639 OOPS!!! Invalid index %s. Index must be an integer and must not be blank.", input));
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException(String.format("\u2639 OOPS!!! Invalid index %s. You only have %d tasks in your list.", input, tasks.size()));
        }
    }

    private void bye() {
        print("Bye. Hope to see you again soon!");
    }
}
