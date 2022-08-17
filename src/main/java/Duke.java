import java.io.PrintStream;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Duke {
    private static boolean done;
    private static final Scanner echo = new Scanner(System.in);
    private static Pattern checkString = Pattern.compile("-?\\d+");

    private static ArrayList<Task> storage = new ArrayList<Task>();

    private static boolean isInteger(String strNum) {
        if (strNum == null) {
            return false;
        }
        return checkString.matcher(strNum).matches();
    }

    private static void addTask(Task task) {
        storage.add(task);
        System.out.println("-----------------------------------------------");
        System.out.println("added: " + task.getDescription());
        System.out.println("-----------------------------------------------");
    }

    private static void markString(Task task) {
        task.mark();
        System.out.println("-----------------------------------------------");
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(task);
        System.out.println("-----------------------------------------------");
    }

    private static void unMarkString(Task task) {
        task.unMark();
        System.out.println("-----------------------------------------------");
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(task);
        System.out.println("-----------------------------------------------");
    }

    private static void addDetailedTask(Task task) {
        System.out.println("-----------------------------------------------");
        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        System.out.println("Now you have " + storage.size() +" tasks in the list.");
        System.out.println("-----------------------------------------------");
    }

    private static void reply(String response) throws DukeException {
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
                done = true;
                break;
            case "list":
                if (storage.size() == 0) {
                    throw new DukeException("there's nothing!");
                }
                System.out.println("-----------------------------------------------");
                for (int i = 0; i < storage.size(); i++) {
                    System.out.println((i + 1) + "." + storage.get(i).toString());
                }
                System.out.println("-----------------------------------------------");
                break;
            case "mark":
                if(parts.length <= 1) {
                    throw new DukeException("Please tell me what to mark!");
                }
                part2 = parts[1];
                if (isInteger(part2)) {
                    int number = Integer.parseInt(part2);
                    if(storage.size() < number) {
                        throw new DukeException("There's no such task to mark!");
                    }
                    markString(storage.get(number - 1));
                } else {
                    throw new DukeException("I don't know which to mark!");
                }
                break;
            case "unmark":
                if(parts.length <= 1) {
                    throw new DukeException("Please tell me what to unmark!");
                }
                part2 = parts[1];
                if (isInteger(part2)) {
                    int number = Integer.parseInt(part2);
                    if(storage.size() < number) {
                        throw new DukeException("There's no such task to unmark!");
                    }
                    unMarkString(storage.get(number - 1));
                } else {
                    throw new DukeException("I don't know which to unmark!");
                }
                break;
            case "deadline":
                if(parts.length <= 1) {
                    throw new DukeException("There's no deadline task!");
                }
                part2 = parts[1];
                String[] deadlineParts =  part2.split("/by ", 2);
                if (deadlineParts.length <= 1) {
                    throw new DukeException("You didn't specify the deadline! Please use /by.");
                }
                Deadline deadline = new Deadline(deadlineParts[0], deadlineParts[1]);
                storage.add(deadline);
                addDetailedTask(deadline);
                break;
            case "todo":
                if(parts.length <= 1) {
                    throw new DukeException("There's no todo task!");
                }
                part2 = parts[1];
                Todo todo = new Todo(part2);
                storage.add(todo);
                addDetailedTask(todo);
                break;
            case "event":
                if(parts.length <= 1) {
                    throw new DukeException("There's no event task!");
                }
                part2 = parts[1];
                String[] eventParts = part2.split("/at ", 2);
                if (eventParts.length <= 1) {
                    throw new DukeException("You didn't specify the event time! Please use /at.");
                }
                Event event = new Event(eventParts[0], eventParts[1]);
                storage.add(event);
                addDetailedTask(event);
                break;
            default:
                throw new DukeException("I'm sorry, but I don't know what that means.");
        }
    }

    public static void main(String[] args) {
        System.out.println("Hello! I'm Duke\nWhat can I do for you?\n" );
        String response;
        done = false;
        while (!done) {
            try {
                response = echo.nextLine();
                reply(response);
            } catch (DukeException e) {
                System.out.println("-----------------------------------------------");
                System.out.println(e);
                System.out.println("-----------------------------------------------");
            }
        }
    }
}
