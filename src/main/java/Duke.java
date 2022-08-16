import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static final ArrayList<Task> history = new ArrayList<>();

    private static void addTask(Task t) {
        Duke.history.add(t);
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + t);
        System.out.println("Now you have " + (Duke.history.size() - 1) + " tasks in the list.");
    }

    public static void main(String[] args) {
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?\n");

        Duke.history.add(new Todo("dummy task"));
        Scanner myScanner = new Scanner(System.in);
        String command = myScanner.nextLine();
        while (!command.equals("bye")) {
            String[] partsOfCommand = command.split(" ", 2);
            switch (partsOfCommand[0]) {
            case "list": {
                System.out.println("Here are the tasks in your list:");
                for (int i = 1; i < Duke.history.size(); i++) {
                    System.out.println(i + "." + Duke.history.get(i));
                }
                break;
            }
            case "mark": {
                int taskIndex = Character.getNumericValue(partsOfCommand[1].charAt(0));
                Duke.history.get(taskIndex).markAsDone();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println("  " + Duke.history.get(taskIndex));
                break;
            }
            case "unmark": {
                int taskIndex = Character.getNumericValue(partsOfCommand[1].charAt(0));
                Duke.history.get(taskIndex).markAsNotDone();
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println("  " + Duke.history.get(taskIndex));
                break;
            }
            case "todo": {
                Duke.addTask(new Todo(partsOfCommand[1]));
                break;
            }
            case "deadline": {
                String[] partsOfString = partsOfCommand[1].split(" /by ", 2);
                Duke.addTask(new Deadline(partsOfString[0], partsOfString[1]));
                break;
            }
            case "event": {
                String[] partsOfString = partsOfCommand[1].split(" /at ", 2);
                Duke.addTask(new Event(partsOfString[0], partsOfString[1]));
                break;
            }
            default: {
                System.out.println("Unknown command");
            }
            }
            System.out.println();
            command = myScanner.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
