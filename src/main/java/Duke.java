import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static final ArrayList<Task> history = new ArrayList<>();

    public static void main(String[] args) {
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?\n");

        Duke.history.add(new Task("dummy task"));
        Scanner myScanner = new Scanner(System.in);
        String command = myScanner.nextLine();
        while (!command.equals("bye")) {
            if (command.equals("list")) {
                System.out.println("Here are the tasks in your list:");
                for (int i = 1; i < Duke.history.size(); i++) {
                    System.out.println(i + "." + Duke.history.get(i));
                }
            } else if (command.startsWith("mark")) {
                int taskIndex = Character.getNumericValue(command.charAt(5));
                Duke.history.get(taskIndex).markAsDone();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println("  " + Duke.history.get(taskIndex));
            } else if (command.startsWith("unmark")) {
                int taskIndex = Character.getNumericValue(command.charAt(7));
                Duke.history.get(taskIndex).markAsNotDone();
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println("  " + Duke.history.get(taskIndex));
            } else {
                Duke.history.add(new Task(command));
                System.out.println("added: " + command);
            }
            System.out.println();
            command = myScanner.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
