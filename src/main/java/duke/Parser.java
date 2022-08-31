package duke;

import java.util.Arrays;
import java.util.Scanner;

/**
 * The Parser class parses the input received when running DUke.
 */

public class Parser {
    private String input = "";
    private Scanner scan = new Scanner(System.in);
    private String[] validInputs = {"delete", "mark", "unmark", "todo", "deadline", "event", "find"};
    private TaskList list;

    /**
     * Constructor for Parser.
     *
     * @param list
     */
    public Parser(TaskList list) {
        this.list = list;
    }

    /**
     * @param input The String input from the user.
     * @return Whether "bye" has been inputted.
     */
    public boolean parse(String input) {
        if (input.equals("bye")) {
            return false;
        } else if (input.equals("list")) {
            list.printTaskList();
        } else {
            String[] split = input.split(" ", 2);
            try {
                if (split.length > 0 && Arrays.asList(validInputs).contains(split[0])) {
                    switch (split[0]) {
                    case "delete": // Checks for delete
                        int index = Integer.parseInt(split[1]) - 1;
                        this.list.deleteTask(index);
                        break;
                    case "mark": // Checks for mark
                        index = Integer.parseInt(split[1]) - 1;
                        this.list.markTask(index);
                        break;
                    case "unmark": // Checks for unmark
                        index = Integer.parseInt(split[1]) - 1;
                        this.list.unmarkTask(index);
                        break;
                    case "todo": // Checks for Todo
                        if (split.length < 2) {
                            throw new DukeException("todo");
                        } else {
                            String description = split[1];
                            this.list.addTodo(description);
                        }
                        break;
                    case "deadline": // Checks for Deadline
                        if (split.length < 2) {
                            throw new DukeException("deadline");
                        } else {
                            String[] temp = split[1].split(" /by ", 2);
                            String description = temp[0];
                            String by = temp[1];
                            this.list.addDeadline(description, by);
                        }
                        break;
                    case "event": // Checks for Event
                        if (split.length < 2) {
                            throw new DukeException("event");
                        } else {
                            String[] temp = split[1].split(" /at ", 2);
                            String description = temp[0];
                            String when = temp[1];
                            this.list.addEvent(description, when);
                        }
                        break;
                    case "find":
                        if (split.length < 2) {
                            throw new DukeException("find");
                        } else {
                            String keyword = split[1];
                            this.list.findTask(keyword).printTaskList();
                        }
                        break;
                    default: // Default case
                        System.out.println("Wrong input");
                    }
                } else {
                    throw new DukeException(); // Invalid input
                }
            } catch (DukeException e) {
                System.out.println(e.getDescription());
            }
        }
        return true;
    }
}
