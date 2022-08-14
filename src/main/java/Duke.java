import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static final ArrayList<Task> history = new ArrayList<>();

    public static void main(String[] args) {
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?\n");

        Duke.history.add(new Todo("dummy task"));
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
            } else if (command.startsWith("todo")) {
                Task newTask = new Todo(command.substring(5));
                Duke.history.add(newTask);
                System.out.println("Got it. I've added this task:");
                System.out.println("  " + newTask);
                System.out.println("Now you have " + (Duke.history.size() - 1) + " tasks in the list.");
            } else if (command.startsWith("deadline")) {
                command = command.substring(9);
                String[] partsOfString = command.split(" /by ", 2);
                Task newTask = new Deadline(partsOfString[0], partsOfString[1]);
                Duke.history.add(newTask);
                System.out.println("Got it. I've added this task:");
                System.out.println("  " + newTask);
                System.out.println("Now you have " + (Duke.history.size() - 1) + " tasks in the list.");
            } else if (command.startsWith("event")) {
                command = command.substring(6);
                String[] partsOfString = command.split(" /at ", 2);
                Task newTask = new Event(partsOfString[0], partsOfString[1]);
                Duke.history.add(newTask);
                System.out.println("Got it. I've added this task:");
                System.out.println("  " + newTask);
                System.out.println("Now you have " + (Duke.history.size() - 1) + " tasks in the list.");
            } else {
                System.out.println("Unknown command");
            }
            System.out.println();
            command = myScanner.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
