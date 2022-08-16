import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Duke {
    public static List<Task> tasks = new ArrayList<>();

    public static void main(String[] args) {
        print("Hello! I'm Duke\nWhat can I do for you?");
        Scanner sc = new Scanner(System.in);
        scanner: while (sc.hasNext()) {
            String input = sc.next();
            switch (input) {
                case "list":
                    list();
                    break;
                
                case "todo":
                case "deadline":
                case "event":
                    add(input, sc.nextLine());
                    break;

                case "blah":
                    blah();
                    break;
                
                case "mark":
                    mark(sc.nextInt() - 1);
                    break;

                case "unmark":
                    unmark(sc.nextInt() - 1);
                    break;
                
                case "bye":
                    bye();
                    sc.close();
                    break scanner;
                
                default:
                    sc.nextLine();
                    print("Sorry, I'm not sure what you meant.");
                    break;
            }
        }
    }

    public static void print(String s) {
        String seperator = "    ____________________________________________________________\n";
        System.out.println(seperator + Stream.of(s.split("\n")).map(x -> "     " + x + "\n").reduce("", (x,y) -> x + y) + seperator);
    }

    public static void list() {
        print(IntStream.range(0, tasks.size()).mapToObj(x -> String.format("%d.%s\n", x + 1, tasks.get(x))).reduce("Here are the tasks in your list:\n", (x,y) -> x + y));
    }

    public static void blah() {
        print("blah");
    }

    public static void bye() {
        print("Bye. Hope to see you again soon!");
    }

    public static void add(String taskType, String taskDetails) {
        Task task;
        if (taskType.equals("deadline")) {
            String[] details = taskDetails.split(" /by ");
            task = new Deadline(details[0], details[1]);
        } else if (taskType.equals("event")) {
            String[] details = taskDetails.split(" /at ");
            task = new Event(details[0], details[1]);
        } else {
            task = new Task(taskDetails);
        }
        tasks.add(task);
        print(String.format("Got it. I've added this task:\n  %s\nNow you have %d tasks in the list.", tasks.get(tasks.size() - 1), tasks.size()));
    }

    public static void mark(int index) {
        tasks.get(index).markAsDone();
        print("Nice! I've marked this task as done:\n  " + tasks.get(index));
    }

    public static void unmark(int index) {
        tasks.get(index).markAsNotDone();
        print("OK, I've marked this task as not done yet:\n  " + tasks.get(index));
    }
}
