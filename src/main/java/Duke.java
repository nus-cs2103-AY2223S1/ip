import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class Duke {
    private static final String name = "Duke";
    private static final ArrayList<Task> userCommands = new ArrayList<>();

    public static void main(String[] args) {
        greet();
        processUserCommand();
    }

    private static void greet() {
        System.out.printf("Hello! I'm %s\n", name);
        System.out.println("What can I do for you?");
    }

    private static void processUserCommand() {
        boolean isDone = false;
        Scanner myScanner = new Scanner(System.in);

        while (!isDone) {
            String userCommand = myScanner.nextLine();

            if (userCommand.equalsIgnoreCase("bye")) {
                sayGoodbye();
                isDone = true;
            } else if (userCommand.equalsIgnoreCase("list")) {
                listUserCommands();
            } else if (userCommand.toLowerCase().startsWith("mark")) {
                userCommands.get(Integer.parseInt(userCommand.split(" ")[1]) - 1).markAsDone();
            } else if (userCommand.toLowerCase().startsWith("unmark")) {
                userCommands.get(Integer.parseInt(userCommand.split(" ")[1]) - 1).markAsNotDone();
            } else {
                userCommands.add(new Task(userCommand));
                System.out.printf("added: %s\n", userCommand);
            }

        }
    }

    private static void sayGoodbye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    private static void listUserCommands() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < userCommands.size(); i++) {
            System.out.printf("%d. %s\n", i + 1, userCommands.get(i));
        }
    }

    public static void addTask() {

    }

}
