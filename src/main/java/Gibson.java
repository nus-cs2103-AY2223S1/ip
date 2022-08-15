import java.util.Scanner;
import java.util.ArrayList;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class Gibson {
    public static void main(String[] args) {
        final String line = "____________________________________________________________";
        final String logo = " _____ ______ ____   _____  ____  _   _\n" +
        "/ _____|_   _|  _ \\ / ____|/ __ \\| \\ | |\n" +
        "| |  __  | | | |_) | (___ | |  | |  \\| |\n" +
        "| | |_ | | | |  _ < \\___ \\| |  | | . ` |\n" +
        "| |__| |_| |_| |_) |____) | |__| | |\\  |\n" +
        "\\______|_____|____/|_____/ \\____/|_| \\_|\n";
        ArrayList<Task> list = new ArrayList<Task>();

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
                System.out.println("Here are the tasks in your list: ");
                for (Task t : list) {
                    System.out.println(count + "." + t.toString());
                    count++;
                }
                System.out.println(line);
            // MARK
            } else if (Pattern.matches("mark [0-9]+", input)) {
                int number = getTrailingInt(input) - 1;
                list.get(number).mark();
                System.out.println(line);
                System.out.println("Nice! I've marked this task as done:");
                System.out.println(list.get(number).toString());
                System.out.println(line);
            }
            // UNMARK
            else if (Pattern.matches("unmark [0-9]+", input)) {
                int number = getTrailingInt(input) - 1;
                list.get(number).unmark();
                System.out.println(line);
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println(list.get(number).toString());
                System.out.println(line);
            // TODOS
            } else if (Pattern.matches("todo .+", input)){
                String taskString = input.substring(5);
                Task task = new Task(taskString);
                list.add(task);
                System.out.println(line);
                System.out.println("Got it. I've added this task:\n" + task);
                System.out.println("Now you have " + list.size() + " task(s) in the list.");
                System.out.println(line);
            // NOT RECOGNIZED
            } else {
                System.out.println(line);
                System.out.println("Command not recognized.");
                System.out.println(line);
            }
        }
    }

    // Gets trailing integers from a String
    // Throws error if no trailing integers
    private static int getTrailingInt(String s) {
        Pattern pattern = Pattern.compile("[^0-9]+([0-9]+)$");
        Matcher matcher = pattern.matcher(s);
        if (matcher.find()) {
            return Integer.parseInt(matcher.group(1));
        } else {
            throw new IllegalArgumentException("String is invalid");
        }
    }
}
