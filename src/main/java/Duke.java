import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo + "I'm Duke! \n What can I do for you?");

        // Read user input
        Scanner scanner = new Scanner(System.in);

        // list of items
        ArrayList<Task> list = new ArrayList<>();

        while (true) {
            String input = scanner.nextLine();
            String command = input.split(" ")[0];
            if (input.equals("bye")) {
                printWithLineBreak("Goodbye! Hope to see you again!");
                break;
            } else if (input.equals("list")) {
                printWithLineBreak(printArray(list));
            } else if (command.equals("mark")) {
                int index = Integer.parseInt(input.split(" ")[1]) - 1;

                list.get(index).setDone();
                printWithLineBreak("Nice! I've marked this task as done:\n" + "[X] " + list.get(index));
            } else if (command.equals("unmark")) {
                int index = Integer.parseInt(input.split(" ")[1]) - 1;

                list.get(index).setUndone();
                printWithLineBreak("OK, I've marked this task as not done yet:\n" + "[ ] " + list.get(index));
            } else if (command.equals("todo")) {
                String description = input.split(" ", 2)[1];
                Todo todo = new Todo(description);
                list.add(todo);

                printWithLineBreak("Got it. I've added this task:\n" + todo + "\nNow you have " + list.size()
                        + " tasks in your list.");
            } else if (command.equals("deadline")) {
                String description = input.split(" ", 2)[1].split("/")[0];
                String date = input.split(" ", 2)[1].split("/")[1].split(" ")[1];
                Deadline deadline = new Deadline(description, date);
                list.add(deadline);

                printWithLineBreak("Got it. I've added this task:\n" + deadline + "\nNow you have " + list.size()
                        + " tasks in your list.");
            } else if (command.equals("event")) {
                String description = input.split(" ", 2)[1].split("/")[0];
                String date = input.split(" ", 2)[1].split("/")[1].split(" ", 2)[1];
                Event event = new Event(description, date);
                list.add(event);

                printWithLineBreak("Got it. I've added this task:\n" + event + "\nNow you have " + list.size()
                        + " tasks in your list.");
            } else {
                printWithLineBreak("Sorry, I don't understand you. Please try again.");
            }
        }

        // Close scanner
        scanner.close();
    }

    public static void printWithLineBreak(String text) {
        System.out.println("\n" + text + "\n");
    }

    public static String printArray(ArrayList<Task> list) {
        String result = "Here are the tasks in your list:\n";
        for (int i = 0; i < list.size(); i++) {
            result += "\t" + (i + 1) + ". " + list.get(i) + "\n";
        }
        return result;
    }
}
