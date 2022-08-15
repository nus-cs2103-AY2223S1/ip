import java.util.Scanner;
import java.util.ArrayList;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class Gibson {
    private static ArrayList<Task> list = new ArrayList<Task>();
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
                int count = 1;
                System.out.println(line);
                System.out.println("Here are the task(s) in your list: ");
                for (Task t : list) {
                    System.out.println(count + "." + t.toString());
                    count++;
                }
                System.out.println(line);
            // MARK
            } else if (Pattern.matches("mark [0-9]+", input)) {
                int number = getTrailingInt(input) - 1;
                try {
                list.get(number).mark();
                System.out.println(line);
                System.out.println("Nice! I've marked this task as done:");
                System.out.println(list.get(number).toString());
                System.out.println(line);
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("There is not task numbered as " + number + ".");
                }
            }
            // UNMARK
            else if (Pattern.matches("unmark [0-9]+", input)) {
                int number = getTrailingInt(input) - 1;
                try {
                    list.get(number).unmark();
                    System.out.println(line);
                    System.out.println("OK, I've marked this task as not done yet:");
                    System.out.println(list.get(number).toString());
                    System.out.println(line);
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println(line);
                    System.out.println("There is not task numbered as " + number + ".");
                    System.out.println(line);
                }
            // TODOS
            } else if (Pattern.matches("(todo .+)|(todo( )?)", input)) {
                String taskString = substringAfterToken(input, "todo");
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
                String taskString = substringAfterToken(input, "deadline");
                String[] stringArray = substringBeforeAfterToken(taskString, "/by");
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
                String taskString = substringAfterToken(input, "event");
                String[] stringArray = substringBeforeAfterToken(taskString, "/at");
                try {
                    Event event = new Event(stringArray[0], stringArray[1]);
                    addTask(event);
                } catch (IllegalArgumentException e) {
                    System.out.println(line);
                    System.out.println(e.getMessage());
                    System.out.println(line);
                }
            } else if (Pattern.matches("delete [0-9]+", input)) {
                int number = getTrailingInt(input) - 1;
                try {
                    Task t = list.get(number);
                    list.remove(number);
                    System.out.println(line);
                    System.out.println("Noted. I've removed this task:");
                    System.out.println(t.toString());
                    System.out.println("Now you have " + list.size() + " task(s) in the list.");
                    System.out.println(line);
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("There is not task numbered as " + number + ".");
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
        list.add(task);
        System.out.println(line);
        System.out.println("Got it. I've added this task:\n" + task);
        System.out.println("Now you have " + list.size() + " task(s) in the list.");
        System.out.println(line);
    }

    // Returns trailing integers from a String
    // Throws error if no trailing integers
    private static int getTrailingInt(String string) {
        Pattern pattern = Pattern.compile("[^0-9]+([0-9]+)$");
        Matcher matcher = pattern.matcher(string);
        if (matcher.find()) {
            return Integer.parseInt(matcher.group(1));
        } else {
            throw new IllegalArgumentException("String is invalid");
        }
    }

    // Returns substring after first instance of token string
    // Throws error if token is not found in String
    private static String substringAfterToken(String string, String token) {
        Pattern pattern = Pattern.compile(token);
        Matcher matcher = pattern.matcher(string);
        if (matcher.find()) {
            try {
                return string.substring(matcher.end() + 1);
            } catch (StringIndexOutOfBoundsException e) {
                return "";
            }
        } else {
            throw new IllegalArgumentException("Unable to find token in string");
        }
    }

    // Returns substring before and after first instance of token string
    // Before stored in [0]. After stored in [1].
    // Throws error if token is not found in String
    private static String[] substringBeforeAfterToken(String string, String token) {
        String[] stringArray = new String[2];
        Pattern pattern = Pattern.compile(token);
        Matcher matcher = pattern.matcher(string);
        if (matcher.find()) {
            try {
                stringArray[0] = string.substring(0, matcher.start() - 1);
            } catch (StringIndexOutOfBoundsException e) {
                stringArray[0] = "";
            }
            try {
                stringArray[1] = string.substring(matcher.end() + 1);
            } catch (StringIndexOutOfBoundsException e) {
                stringArray[1] = "";
            }
            return stringArray;
        } else {
            throw new IllegalArgumentException("Unable to find token in string");
        }
    }
}
