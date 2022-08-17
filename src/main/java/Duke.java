import java.io.PrintStream;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Duke {

    private static final Scanner echo = new Scanner(System.in);
    private static Pattern checkString = Pattern.compile("-?\\d+");

    private static ArrayList<Task> storage = new ArrayList<Task>();

    public static boolean isInteger(String strNum) {
        if (strNum == null) {
            return false;
        }
        return checkString.matcher(strNum).matches();
    }

    public static void addTask(Task task) {
        storage.add(task);
        System.out.println("-----------------------------------------------");
        System.out.println("added: " + task.getDescription());
        System.out.println("-----------------------------------------------");
    }

    public static void markString(Task task) {
        task.mark();
        System.out.println("-----------------------------------------------");
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(task);
        System.out.println("-----------------------------------------------");
    }

    public static void unMarkString(Task task) {
        task.unMark();
        System.out.println("-----------------------------------------------");
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(task);
        System.out.println("-----------------------------------------------");
    }

    public static void reply() {
        String response = echo.nextLine();
        String[] parts = response.split(" ", 2);
        String part1 = parts[0];
        String part2;
        switch (part1) {
            case "bye":
                System.out.println("-----------------------------------------------");
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println("-----------------------------------------------");
                storage.clear();
                echo.close();
                return;
            case "list":
                System.out.println("-----------------------------------------------");
                for (int i = 0; i < storage.size(); i++) {
                    System.out.println((i + 1) + "." + storage.get(i).toString());
                }
                System.out.println("-----------------------------------------------");
                reply();
                break;
            case "mark":
                part2 = parts[1];
                if (isInteger(part2)) {
                    int number = Integer.parseInt(part2);
                    markString(storage.get(number - 1));
                    reply();
                } else {
                    Task task = new Task(response);
                    addTask(task);
                    reply();
                }
                break;
            case "unmark":
                part2 = parts[1];
                if (isInteger(part2)) {
                    int number = Integer.parseInt(part2);
                    unMarkString(storage.get(number - 1));
                    reply();
                } else {
                    Task task = new Task(response);
                    addTask(task);
                    reply();
                }
                break;
            case "deadline":
                part2 = parts[1];
                String[] deadlineParts =  part2.split("/by ", 2);
                Deadline deadline = new Deadline(deadlineParts[0], deadlineParts[1]);
                storage.add(deadline);
                System.out.println("-----------------------------------------------");
                System.out.println("Got it. I've added this task:");
                System.out.println(deadline);
                System.out.println("Now you have " + storage.size() +" tasks in the list.");
                System.out.println("-----------------------------------------------");
                reply();
                break;
            case "todo":
                part2 = parts[1];
                Todo todo = new Todo(part2);
                storage.add(todo);
                System.out.println("-----------------------------------------------");
                System.out.println("Got it. I've added this task:");
                System.out.println(todo);
                System.out.println("Now you have " + storage.size() +" tasks in the list.");
                System.out.println("-----------------------------------------------");
                reply();
                break;
            case "event":
                part2 = parts[1];
                String[] eventParts = part2.split("/at ", 2);
                Event event = new Event(eventParts[0], eventParts[1]);
                storage.add(event);
                System.out.println("-----------------------------------------------");
                System.out.println("Got it. I've added this task:");
                System.out.println(event);
                System.out.println("Now you have " + storage.size() +" tasks in the list.");
                System.out.println("-----------------------------------------------");
                reply();
                break;
            default:
                Task task = new Task(response);
                addTask(task);
                reply();
        }
    }


    public static void main(String[] args) {
        System.out.println("Hello! I'm Duke\nWhat can I do for you?\n" );
        reply();
    }
}
