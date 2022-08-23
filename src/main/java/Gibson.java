import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class Gibson {
    private static TaskList taskList = new TaskList();
    private static final String line = "____________________________________________________________";
    private static final String logo = " _____ ______ ____   _____  ____  _   _\n" +
            "/ _____|_   _|  _ \\ / ____|/ __ \\| \\ | |\n" +
            "| |  __  | | | |_) | (___ | |  | |  \\| |\n" +
            "| | |_ | | | |  _ < \\___ \\| |  | | . ` |\n" +
            "| |__| |_| |_| |_) |____) | |__| | |\\  |\n" +
            "\\______|_____|____/|_____/ \\____/|_| \\_|\n";

    public static void main(String[] args) {
        // Prints introduction
        System.out.println(line);
        System.out.println(logo);
        System.out.println("Hello! I'm Gibson");
        System.out.println("What can I do for you?");
        System.out.println(line);

        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String input = scanner.nextLine();
            // BYE
            if (input.equals("bye")) {
                System.out.println(line);
                System.out.println("Bye. Hope to see you soon!");
                System.out.println(line);
                break;
            // LIST
            } else if (input.equals("list")) {
                System.out.println(line);
                System.out.println("Here are the task(s) in your list: ");
                System.out.println(taskList.toString());
                System.out.println(line);
            // MARK
            } else if (Pattern.matches("mark [0-9]+", input)) {
                int number = RegexUtility.getTrailingInt(input);
                int index = number - 1;
                try {
                    Task t = taskList.mark(index);
                    System.out.println(line);
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println(t.toString());
                    System.out.println(line);
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("There is not task numbered as " + number + ".");
                }
            }
            // UNMARK
            else if (Pattern.matches("unmark [0-9]+", input)) {
                int number = RegexUtility.getTrailingInt(input);
                int index = number - 1;
                try {
                    Task t = taskList.unmark(index);
                    System.out.println(line);
                    System.out.println("OK, I've marked this task as not done yet:");
                    System.out.println(t.toString());
                    System.out.println(line);
                } catch (IndexOutOfBoundsException e) {
                    System.out.println(line);
                    System.out.println("There is not task numbered as " + number + ".");
                    System.out.println(line);
                }
            // TODOS
            } else if (Pattern.matches("(todo .+)|(todo( )?)", input)) {
                String taskString = RegexUtility.substringAfterToken(input, "todo");
                try {
                    Task task = new Task(taskString);
                    addTask(task);
                } catch (IllegalArgumentException e) {
                    System.out.println(line);
                    System.out.println(e.getMessage());
                    System.out.println(line);
                }
            // DEADLINES
            } else if (Pattern.matches("(deadline .+ /by .+)|(deadline .+ /by( )?)", input)) {
                String taskString = RegexUtility.substringAfterToken(input, "deadline");
                String[] stringArray = RegexUtility.substringBeforeAfterToken(taskString, "/by");
                try {
                    Deadline deadline = new Deadline(stringArray[0], stringArray[1]);
                    addTask(deadline);
                } catch (IllegalArgumentException e) {
                    System.out.println(line);
                    System.out.println(e.getMessage());
                    System.out.println(line);
                }
            // EVENTS
            } else if (Pattern.matches("(event .+ /at .+)|(event .+ /at( )?)", input)) {
                String taskString = RegexUtility.substringAfterToken(input, "event");
                String[] stringArray = RegexUtility.substringBeforeAfterToken(taskString, "/at");
                try {
                    Event event = new Event(stringArray[0], stringArray[1]);
                    addTask(event);
                } catch (IllegalArgumentException e) {
                    System.out.println(line);
                    System.out.println(e.getMessage());
                    System.out.println(line);
                }
            // REMOVE
            } else if (Pattern.matches("delete [0-9]+", input)) {
                int number = RegexUtility.getTrailingInt(input);
                int index = number - 1;
                try {
                    Task t = taskList.remove(index);
                    System.out.println(line);
                    System.out.println("Noted. I've removed this task:");
                    System.out.println(t.toString());
                    System.out.println("Now you have " + taskList.size() + " task(s) in the list.");
                    System.out.println(line);
                } catch (IndexOutOfBoundsException e) {
                    System.out.println(line);
                    System.out.println("There is not task numbered as " + number + ".");
                    System.out.println(line);
                }

            // NOT RECOGNIZED
            } else {
                System.out.println(line);
                System.out.println("I'm sorry. I do not know what that means.");
                System.out.println(line);
            }
        }
    }

    // Add task to list and print an acknowledgement
    private static void addTask(Task task) {
        taskList.add(task);
        System.out.println(line);
        System.out.println("Got it. I've added this task:\n" + task);
        System.out.println("Now you have " + taskList.size() + " task(s) in the list.");
        System.out.println(line);
    }

}
