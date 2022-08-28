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
            try {
                String input = scanner.nextLine();
                String[] inputArray = input.split(" ", 2);
                Commands command = Commands.parseCommand(inputArray[0])
                        .orElseThrow(() -> new DukeException("Sorry, I don't understand you. Please try again."));
                String instructions = inputArray.length > 1 ? inputArray[1] : "";
                if (command == Commands.bye) {
                    printWithLineBreak("Goodbye! Hope to see you again!");
                    break;
                } else if (command == Commands.list) {
                    printWithLineBreak(printArray(list));
                } else if (command == Commands.mark) {
                    // Throw exception if no index is provided
                    if (instructions.isEmpty()) {
                        throw new DukeException("Please specify an index.");
                    }
                    // Throw exception if instruction is not a number
                    if (!instructions.matches("[0-9]+")) {
                        throw new DukeException("Please specify a valid index.");
                    }
                    int index = Integer.parseInt(instructions) - 1;
                    if (list.size() <= index) {
                        throw new DukeException("Please specify a valid index.");
                    }
                    list.get(index).setDone();
                    printWithLineBreak("Nice! I've marked this task as done:\n" + "[X] " + list.get(index));
                } else if (command == Commands.unmark) {
                    // Throw exception if no index is provided
                    if (instructions.isEmpty()) {
                        throw new DukeException("Please specify an index.");
                    }
                    // Throw exception if instruction is not a number
                    if (!instructions.matches("[0-9]+")) {
                        throw new DukeException("Please specify a valid index.");
                    }
                    int index = Integer.parseInt(instructions) - 1;
                    if (list.size() <= index) {
                        throw new DukeException("Please specify a valid index.");
                    }
                    list.get(index).setUndone();
                    printWithLineBreak("OK, I've marked this task as not done yet:\n" + "[ ] " + list.get(index));
                } else if (command == Commands.todo) {
                    if (instructions == "") {
                        throw new DukeException("The description of a todo cannot be empty.");
                    }
                    Todo todo = new Todo(instructions);
                    list.add(todo);

                    printWithLineBreak("Got it. I've added this task:\n" + todo + "\nNow you have " + list.size()
                            + " tasks in your list.");
                } else if (command == Commands.deadline) {
                    if (instructions == "") {
                        throw new DukeException("The description of a deadline cannot be empty.");
                    }
                    // Parse input into description and deadline, based on the /by keyword
                    String description = instructions.split("/")[0];
                    String date = instructions.split("/by")[1].trim();
                    Deadline deadline = new Deadline(description, date);
                    list.add(deadline);

                    printWithLineBreak(
                            "Got it. I've added this task:\n" + deadline + "\nNow you have " + list.size()
                                    + " tasks in your list.");
                } else if (command == Commands.event) {
                    String description = input.split(" ", 2)[1].split("/")[0];
                    String date = input.split(" ", 2)[1].split("/")[1].split(" ", 2)[1];
                    Event event = new Event(description, date);
                    list.add(event);

                    printWithLineBreak("Got it. I've added this task:\n" + event + "\nNow you have " + list.size()
                            + " tasks in your list.");
                } else if (command == Commands.delete) {
                    // Throw exception if no index is provided
                    if (instructions.isEmpty()) {
                        throw new DukeException("Please specify an index.");
                    }
                    // Throw exception if instruction is not a number
                    if (!instructions.matches("[0-9]+]")) {
                        throw new DukeException("Please specify a valid index.");
                    }
                    int index = Integer.parseInt(instructions) - 1;
                    if (list.size() <= index) {
                        throw new DukeException("Please specify a valid index.");
                    }
                    Task deleted = list.get(index);
                    list.remove(index);
                    printWithLineBreak(
                            "Noted, I've removed this task:\n" + deleted + "\nNow you have " + list.size()
                                    + " tasks in your list.");
                }
            } catch (DukeException e) {
                printWithLineBreak(e.getMessage());
            } catch (Exception e) {
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
        if (list.size() == 0) {
            return "You have no tasks in your list.";
        }
        String result = "Here are the tasks in your list:\n";
        for (int i = 0; i < list.size(); i++) {
            result += "\t" + (i + 1) + ". " + list.get(i) + "\n";
        }
        return result;
    }
}
